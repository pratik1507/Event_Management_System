function getEvents()
{
	var url="http://localhost:8080/samyak/events";
	callApi("GET",url,"",loadEvents);
}
function loadEvents(res)
{
	var data=JSON.parse(res);
	d1.innerText="";
	var option=document.createElement("option");
	option.value="";
	option.text="";
	d1.add(option);
	for(var x in data)
	{
		var option=document.createElement("option");
		option.value=data[x].eid;
		option.text=data[x].ename;
		d1.add(option);
	}
}
function register()
{
	var url="http://localhost:8080/samyak/register";
	var data=JSON.stringify({
		"name":t1.value,
		"college":t2.value,
		"adress":t3.value,
		"email":t4.value,
		"contactno":t5.value,
		"eid":d1.value
	});
	callApi("POST",url,data,getResponse);
}
function getResponse(res)
{
	alert(res);
}

function getList()
{
  var url = "http://localhost:8080/samyak/participantslist/" + d1.value;
  callApi("GET", url, "", generateTable);
}

function del()
{
  var url = "http://localhost:8080/samyak/cancel/" + T0.value;
  callApi("DELETE", url, "", getResponse);
}

function generateTable(res)
{
  var data = JSON.parse(res);
  var table = `<table border="1px">
          <tr>
            <th>ID</th>
            <th>NAME</th>
            <th>COLLEGE#</th>
            <th>ADDRESS</th>
            <th>EMAIL</th>
            <th>CONTACT NO</th>
          </tr>
        `;
  for(var x in data)
  {
    table += `<tr>
          <td> ` + data[x].id + ` </td>
          <td> ` + data[x].name + ` </td>
          <td> ` + data[x].college + ` </td>
          <td> ` + data[x].address + ` </td>
          <td> ` + data[x].email + ` </td>
          <td> ` + data[x].contactno + ` </td>
          </tr>
         `;
  }
  table += `</table>`;
  
  list.innerHTML = table;
}

function search(){
	 var url = "http://localhost:8080/samyak/read/" + T0.value;
  	 callApi("GET", url, "", loadData);
}

function loadData(res){
	var data = JSON.parse(res);
  T1.value = data.name;
  T2.value = data.college;
  T3.value = data.address;
  T4.value = data.email;
  T5.value = data.contactno;
}

function update()
{
  var url = "http://localhost:8080/samyak/update/" + T0.value;
  var data = JSON.stringify({
    name : T1.value,
    college : T2.value,
    address : T3.value,
    email : T4.value,
    contactno : T5.value
  });
  
  callApi("PUT", url, data, getResponse);
}

function callApi(METHOD,URL,DATA,SUCCESS)
{
	 var xhttp=new XMLHttpRequest();
	 xhttp.open(METHOD,URL,true);
	 xhttp.setRequestHeader('Content-Type','application/json');
	 xhttp.onreadystatechange=function()
	 {
	 	if(this.readyState == 4)
	  	{
		   	if(this.status == 200)
		    	SUCCESS(this.responseText);
		   	else
		    	alert("404: Service unavailable"); 
		}
		  
	 };
	 xhttp.send(DATA);
}