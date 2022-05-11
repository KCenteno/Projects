package com.codingdojo.beltexam.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.beltexam.models.Joy;

@Repository
public interface JoyRepository extends CrudRepository<Joy, Long>{
	
	Optional<Joy> findByName(String name);
	
	List<Joy> findAll();
}
