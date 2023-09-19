package com.klu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klu.entity.Participant;
import com.klu.model.ParticipantManager;

@RestController
@RequestMapping("/samyak")
public class SamyakController {
	@Autowired
	ParticipantManager pm;
	@GetMapping("/events")
	public String events() {
		return pm.getEvents().toString();
	}
	@PostMapping("/register")
	public String register(@RequestBody Participant p) {
		return pm.registration(p);
	}
	
	@GetMapping("/participantslist/{eid}")
	 public String participantslist(@PathVariable("eid") Long eid)
	 {
	return pm.participantsList(eid).toString();
	}
	
		@GetMapping("/read/{eid}")
	   public String read(@PathVariable("eid") Long eid)
	   {
	     return pm.readDetails(eid);
	   }
		
	@PutMapping("/update/{id}")
	public String update(@PathVariable("id") Long id,@RequestBody Participant p) {
		return pm.updateData(id, p);
	}
	@DeleteMapping("/cancel/{id}")
	public String cancel(@PathVariable("id") Long id) {
		return pm.cancelRegistration(id);
	}
}