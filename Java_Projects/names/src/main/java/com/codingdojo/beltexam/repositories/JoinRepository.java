package com.codingdojo.beltexam.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.beltexam.models.Join;

@Repository
public interface JoinRepository extends CrudRepository<Join, Long>{

	Object count = null;
	
	List<Join> findAll();

}
