package com.klu.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.klu.entity.Event;
import com.klu.entity.Participant;
import com.klu.repository.EventRepository;
import com.klu.repository.ParticipantRepository;

@Service
public class ParticipantManager {
	@Autowired
	ParticipantRepository pr;
	@Autowired
	EventRepository er;
	public List<String> getEvents(){
		List<String> list=new ArrayList<String>();
		for(Event e:er.findAll()) {
			list.add(convertToJson(e));
		}
		return list;
	}
	public String convertToJson(Object obj) {
		GsonBuilder gb=new GsonBuilder();
		Gson g=gb.create();
		return g.toJson(obj);
	}
	public String registration(Participant p) {
		if(pr.validateByMobileNo(p.getContactno())>0) {
			return "Already register with same number";
		}
		pr.save(p);
		return "Registration Done Successfully!! No of registered participants: "+pr.noOfParticipants();
	}
	
	
	public List<String> participantsList(Long eid)
	   {
	     List<String> list = new ArrayList<String>();
	     for(Participant S : pr.fetchAllById(eid))
	     {
	       list.add(convertToJson(S));
	     }
	     return list;
	   }
	
	public String readDetails(Long eid)
	   {
	     Participant tmp = pr.findById(eid).get();
	     return convertToJson(tmp);
	   }
	
	public String updateData(Long id,Participant p) {
		Participant temp=pr.findById(id).get();
		temp.setName(p.getName());
		temp.setCollege(p.getCollege());
		temp.setAdress(p.getAdress());
		temp.setEmail(p.getEmail());
		temp.setContactno(p.getContactno());
		pr.save(temp);
		return "updation done successfully";
	}
	
	public String cancelRegistration(Long id) {
		pr.deleteById(id);
		return "Registration has cancelled";
	}
}