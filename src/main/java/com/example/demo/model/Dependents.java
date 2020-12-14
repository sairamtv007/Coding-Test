package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

/*import lombok.AllArgsConstructor;
import lombok.Data;
*/
@Entity
@Table(name="dependents")
//@Data
//@AllArgsConstructor
public class Dependents {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long dependentId;
	@Column(name="name",nullable = false)
    
	private String name;
	
	@Column(name="birth_date",nullable = false)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM-dd-yyyy")
	@NotNull
	private Date birthDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="enrollees_id",nullable = false)
	@JsonIgnore
	 private Enrollees enrollees;
	
	
	public Dependents() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Dependents(long dependentId, String name, Date birthDate) {
		super();
		this.dependentId = dependentId;
		this.name = name;
		this.birthDate = birthDate;
		
	}


	public long getDependentId() {
		return dependentId;
	}


	public void setDependentId(long dependentId) {
		this.dependentId = dependentId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Date getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


	public Enrollees getEnrollees() {
		return enrollees;
	}


	public void setEnrollees(Enrollees enrollees) {
		this.enrollees = enrollees;
	} 
	
	
}
