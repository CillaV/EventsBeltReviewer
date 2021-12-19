package com.pv.eventsbeltreviewer.models;

import java.util.Date;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "First name must not be blank.")
	private String firstName;
	
	@NotBlank(message = "Last name must not be blank.")
	private String lastName;
	
	@Email(message = "Must be a valid email.")
	private String email;
	
	@NotBlank(message = "City must not be blank.")
	private String city;
	
	private String state;
	
	// Regex()
	@Size(min = 8, message = "Password must be at least 8 characters.")
	private String password;
	
	@Transient
	private String passwordConfirmation;
	
	@Column(updatable = false)
	private Date createdAt;
	private Date updatedAt;
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	
	
// relationships

	// many users have many events and vice versa
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "users_events", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "event_id")
    )
	    
    private List<Event> attendees;

    // user will have many of something
	@OneToMany(mappedBy="host", cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Event> plannedEvents;

	// user will have many of something
	@OneToMany(mappedBy="commenter", cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Message> comments;

	
	
	public User() {}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Event> getAttendees() {
		return attendees;
	}

	public void setAttendees(List<Event> attendees) {
		this.attendees = attendees;
	}

	public List<Event> getPlannedEvents() {
		return plannedEvents;
	}

	public void setPlannedEvents(List<Event> plannedEvents) {
		this.plannedEvents = plannedEvents;
	}

	public List<Message> getComments() {
		return comments;
	}

	public void setComments(List<Message> comments) {
		this.comments = comments;
	}
			
	
	
	
}
