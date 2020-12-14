package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.exception.HealthCareException;
import com.example.demo.jpa.EnrolleesRepository;
import com.example.demo.model.Enrollees;


@RestController
@RequestMapping("/healthCare")
public class EnrolleesControler {
	@Autowired
	private EnrolleesRepository enrolleesRepository;
	/**
	 * To return all Enrollees 
	 * @return
	 */
	@GetMapping(path="/enrollees")
	public List<Enrollees>getAllEnrollees(){
		return enrolleesRepository.findAll();
	}
	
	/**
	 * To find Enrollees by enrollees id
	 * @return
	 */
	@GetMapping(path="/enrolles/{id}")
	public Enrollees getEnrolleesById(@PathVariable  Long enrolleeId) {
		Optional<Enrollees> enrollees= enrolleesRepository.findById(enrolleeId);
		if(enrollees.isPresent()) {
			return enrollees.get();
		}else {
			throw new HealthCareException("Enrollees not found with id:"+enrolleeId);
		}
		
		
	}
	/**
	 * To create a new enrollee
	 * @param enrollees
	 * @return
	 */
	@PostMapping("/enrollees")
	public Enrollees createEnrollees(@Valid @RequestBody Enrollees enrollees) {
		return enrolleesRepository.save(enrollees);
	}
    /**
     * update enrollee object
     * @param enrolleeId
     * @param enrollees
     * @return
     */
	@PutMapping(path="/enrollees/{id}")
	public Enrollees updateEnrollee(@PathVariable(value="id") Long enrolleeId,
			@Valid @RequestBody Enrollees enrollees) {
		  Enrollees updateEnrollee =   enrolleesRepository.findById(enrolleeId).orElseThrow(()->
		  new HealthCareException("Enrollee Not found with id:"+enrolleeId));
		   updateEnrollee.setId(enrolleeId);
		   updateEnrollee.setName(enrollees.getName());
		   updateEnrollee.setActivationStatus(enrollees.isActivationStatus());
		   updateEnrollee.setBirthdate(enrollees.getBirthdate());
		   updateEnrollee.setPhoneNumber(enrollees.getPhoneNumber());
		    	return enrolleesRepository.save(updateEnrollee);
	}		
	/**
	 * Delete the Enrollee
	 * @param enrolleeId
	 * @return
	 */
	@DeleteMapping(path="/enrollees/{id}")
	public String enrolleeByid(@PathVariable(value="id") Long enrolleeId) {
		return enrolleesRepository.findById(enrolleeId)
				.map(deletedEnrollee->{
					enrolleesRepository.delete(deletedEnrollee);
					return "Enrollee Deleted Successfully";
				}).orElseThrow(()->new HealthCareException("Enrollee Not found with id:"+enrolleeId) );
	}
}
