package com.pv.eventsbeltreviewer.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.pv.eventsbeltreviewer.models.User;
import com.pv.eventsbeltreviewer.repositories.UserRepository;

@Component
public class UserValidator implements Validator {
	
	@Autowired
	private UserRepository userRepo;

//	@Override
//	public boolean supports(Class<?> clazz) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public void validate(Object target, Errors errors) {
//		// TODO Auto-generated method stub
//		
//	}
	
	
    // 1 -- will use User model to be authenticate
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
    
    // 2 - will check password to make sure the same
    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        
        if (!user.getPasswordConfirmation().equals(user.getPassword())) {
            // 3
            errors.rejectValue("passwordConfirmation", "Match");
        }
        // check DB by email with parameter user email from front end and if found return error
        if (this.userRepo.findByEmail(user.getEmail()) != null) {
            // 3
            errors.rejectValue("email", "Exists", "Email already registered");
            // attach error to, key (exists) and value
        }         
    
        // truthy variation
//        if (this.userRepo.existsByEmail(user.getEmail())) {
//        	errors.rejectValue("email", "Exists", "we got your email already");
//        }
    
        // other variations could be on name
    
    }	


}

