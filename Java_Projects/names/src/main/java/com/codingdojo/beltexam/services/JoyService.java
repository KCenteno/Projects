package com.codingdojo.beltexam.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.beltexam.models.Join;
import com.codingdojo.beltexam.models.Joy;
import com.codingdojo.beltexam.repositories.JoinRepository;
import com.codingdojo.beltexam.repositories.JoyRepository;

@Service
public class JoyService {

	private final JoyRepository joyRepo;
	private final JoinRepository joinRepo;

	public JoyService(JoyRepository joyRepo, JoinRepository joinRepo) {
		super();
		this.joyRepo = joyRepo;
		this.joinRepo = joinRepo;
	}

	public Join makeJoin(Join join) {
		return joinRepo.save(join);
	}

	public List<Joy> allJoys() {
		return joyRepo.findAll();
	}

	public Joy findOne(Long id) {
		Optional<Joy> optionalJoy = joyRepo.findById(id);
		if (optionalJoy.isPresent()) {
			return optionalJoy.get();
		} else {
			return null;
		}
	}

	public Joy create(Joy joy, BindingResult result) {
		
		if(joyRepo.findByName(joy.getName()).isPresent()) {
    		result.rejectValue("name", "Unique", "You can not use this Name, pick a differnet Name!");
    	}
		
		if(result.hasErrors()) {
        	return null;
        }
		
		return joyRepo.save(joy);
	}

	public Joy update(Joy joy, BindingResult result) {
		return joyRepo.save(joy);
	}

	public void delete(Long id) {
		joyRepo.deleteById(id);
	}
	
	public void test() { 
		
		joinRepo.count();
		
	}
}
