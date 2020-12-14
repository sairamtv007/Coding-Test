/**
 * 
 */
package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
/*import lombok.AllArgsConstructor;
import lombok.Data;
*/
import com.sun.istack.NotNull;





/**
 * @author Venkatasairam Thumu
 * 
 * This is entity class for Enrollees data table 
 *
 */
//@Data
//@AllArgsConstructor
@Entity
@Table(name="enrollees")
public class Enrollees implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="name",nullable=false)
    @NotNull
	private String name ;
	
	@Column(name="activation_status",nullable = false)
	@NotNull
	private boolean activationStatus;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM-dd-yyyy")
	@Column( name="birthdate",nullable =false)
	@NotNull
	private Date  birthdate;
	
	
	@Column(name="phone_number",nullable = true)
	private String  phoneNumber;

	 @OneToMany(mappedBy="enrollees", cascade=CascadeType.ALL,fetch=FetchType.LAZY) 
	 private Set<Dependents> dependents;

	public Enrollees() {
	}

	public Enrollees(Long id, String name, boolean activationStatus, Date birthdate, String phoneNumber, Set<Dependents> dependents) {
		this.id = id;
		this.name = name;
		this.activationStatus = activationStatus;
		this.birthdate = birthdate;
		this.phoneNumber = phoneNumber;
		this.dependents = dependents;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActivationStatus() {
		return activationStatus;
	}

	public void setActivationStatus(boolean activationStatus) {
		this.activationStatus = activationStatus;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Set<Dependents> getDependents() {
		return dependents;
	}

	public void setDependents(Set<Dependents> dependents) {
		this.dependents = dependents;
	}
}
