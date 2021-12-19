package com.pv.eventsbeltreviewer.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pv.eventsbeltreviewer.models.User;
import com.pv.eventsbeltreviewer.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
// register user and hash password	
	public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepository.save(user);		
	}


// find user by email - to help add user to session if authenticated
	public User findByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}
	
	
	
// authenticate user
    public boolean authenticateUser(String email, String password) {
        // first find the user by email
        User user = userRepository.findByEmail(email);
        // if we can't find it by email, return false
        if(user == null) {
            return false;
        } else {
            // if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }
	
    
// find user by id
    public User findUserById(Long id) {
    	Optional<User> u = this.userRepository.findById(id);
    	
    	if(u.isPresent()) {
    		return u.get();
    	}
    	else {
    		return null;
    	}
    }
    
    

	
}
