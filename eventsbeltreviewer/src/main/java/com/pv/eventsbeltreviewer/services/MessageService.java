package com.pv.eventsbeltreviewer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pv.eventsbeltreviewer.models.Event;
import com.pv.eventsbeltreviewer.models.Message;
import com.pv.eventsbeltreviewer.models.User;
import com.pv.eventsbeltreviewer.repositories.MessageRepository;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private EventService eventservice;
	
	@Autowired
	private UserService userService;
	
// Add new Message(comment)
	public Message saveComment(Long eventId, Long userId, String comment) {
		Event event = this.eventservice.showEvent(eventId);
		User commenter = this.userService.findUserById(userId);
		Message newMessage = new Message(comment, event, commenter); 
		return this.messageRepository.save(newMessage);
}
	
	
}
