package com.pv.eventsbeltreviewer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pv.eventsbeltreviewer.models.Event;
import com.pv.eventsbeltreviewer.models.User;
import com.pv.eventsbeltreviewer.repositories.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private UserService userService;
	
	
// find all events in same state as User
	public List<Event> eventsInState(String state) {
		return this.eventRepository.findByState(state);
	}
	
	
// find all events NOT in same state as User
	public List<Event> eventsNotInState(String state) {
		return this.eventRepository.findByStateNot(state);
	}
	
	
// create an event
	public Event createEvent(Event c) {
		return this.eventRepository.save(c);
	}

// show one event
	public Event showEvent(Long id) {
		return this.eventRepository.findById(id).orElse(null);
	}
	
// update event
	public Event updateEvent(Event event) {
		return this.eventRepository.save(event);
	}
	
	
// delete event
	public void deleteEvent(Long id) {
		this.eventRepository.deleteById(id);
	}


// Join an Event
	
	public void joinEvent(Long eventId, Long guestId) {
		Event event = this.eventRepository.findById(eventId).orElse(null);
		User guest = this.userService.findUserById(guestId);
		event.getGuests().add(guest);
		this.eventRepository.save(event);
	}
	
	
// Cancel Join on an Event
	public void unJoinEvent(Long eventId, Long guestId) {
		Event event = this.eventRepository.findById(eventId).orElse(null);
		User guest = this.userService.findUserById(guestId);
		event.getGuests().remove(guest);
		this.eventRepository.save(event);
	}
	
	
	
	
// Join an event/cancel a join
//	public void manageAttendees(Event event, User user, boolean isAttending) {
//		if(isAttending) {
//			event.getGuests().add(user);
//		} else {
//			event.getGuests().remove(user);
//		}
//		this.eventRepository.save(event);
//	}
	
	
	
	
	
}
