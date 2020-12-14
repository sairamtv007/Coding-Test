package com.example.demo.controller;
import java.util.List;

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
import com.example.demo.jpa.DependentsRepository;
import com.example.demo.jpa.EnrolleesRepository;
import com.example.demo.model.Dependents;


@RestController
@RequestMapping
public class DependentsController {
	
	@Autowired
	private DependentsRepository dependentRepository;
	@Autowired
	private EnrolleesRepository enrolleesRepository;
	
		/**
	 * To find Dependents by enrollees id
	 * @return
	 */
	@GetMapping(path="/enrollees/{enrolleesId}/dependents")
	public List<Dependents> getEnrolleesById(@PathVariable (value="enrolleesId")Long enrolleesId) {
		if(!(enrolleesRepository.existsById(enrolleesId))) {
			throw new HealthCareException("Enrollees Not Found");
		}
		
		return dependentRepository.findByEnrolleesId(enrolleesId);
	}
	/**
	 * To create a new Dependents
	 * @param enrollees
	 * @return
	 */
	@PostMapping(path="/enrollees/{enrolleesId}/dependents")
	public Dependents createDependents(@PathVariable(value="enrolleesId")Long enrollesId ,
			@Valid @RequestBody Dependents dependents) {
		return enrolleesRepository.findById(enrollesId)
				.map(enrollee-> {
					dependents.setEnrollees(enrollee);
				return dependentRepository.save(dependents);
				}).orElseThrow(() ->new HealthCareException("Enrolles not found with id: "+enrollesId));
		
	}
    /**
     * udpate Dependents by enrolleesId 
     * @param dependentsId
     * @param dependents
     * @return
     */
	@PutMapping(path="/enrollees/{enrolleesId}/dependents/{dependentsId}")
	public Dependents updateDependents(@PathVariable(value="enrolleesId") Long enrolleesId,
			@PathVariable(value="dependentsId") Long dependentsId, 
			@Valid @RequestBody Dependents dependents) {
		
		if(!(enrolleesRepository.existsById(enrolleesId))) {
			throw new HealthCareException("Enrollees Not Found with id :"+enrolleesId);
		}
		         return dependentRepository.findById(dependentsId)
		        		 .map(updatedDependents->{
		        			 updatedDependents.setBirthDate(dependents.getBirthDate());
		        			 updatedDependents.setName(dependents.getName());
		        			 return dependentRepository.save(updatedDependents);
		        			 
		        		 }).orElseThrow(()-> new HealthCareException("Dependents not Found"));
			
	}		
	
	 @DeleteMapping("/enrollees/{enrolleesId}/dependents/{dependentId}")
	    public String deleteDependent(@PathVariable Long enrolleesId,
	    							   @PathVariable Long dependentId) {
	    	
		 if(!(enrolleesRepository.existsById(enrolleesId))) {
				throw new HealthCareException("Enrollees Not Found with id :"+enrolleesId);
			}
	        return dependentRepository.findById(dependentId)
	                .map(dependents -> {
	                	dependentRepository.delete(dependents);
	                    return "Deleted Successfully!";
	                }).orElseThrow(() -> new HealthCareException("Dependent details not found!"));
	    }

}
