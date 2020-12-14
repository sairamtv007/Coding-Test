# Coding-Test

# The challenge

Using Spring Boot or Go, and your database of choice (PostgreSQL, MySQL, MongoDB -- any you'd like), develop a microservice for tracking the status of enrollees in a health care program.
- Enrollees must have an id, name, and activation status (true or false), and a birth date
- Enrollees may have a phone number (although they do not have to supply this)
- Enrollees may have zero or more dependents
- Each of an enrollee's dependents must have an id, name, and birth date

The application we will be building will need to be able to do these things:
- Add a new enrollee
- Modify an existing enrollee
- Remove an enrollee entirely
- Add dependents to an enrollee
- Remove dependents from an enrollee
- Modify existing dependents

Implementation Details :
=====================
In this Challenge i created following source code files .I have not created Service component intentionally to reduce size of source code  

1. Crated Spring boot project  with WEB, JPA , POSTGRESSQL,Validation, and Test Dependencies .
2.Define Database configurations for posrgres sql
3.Create an Entity Class created named as Enrollees and Depedents and associated data table names enrollees and dependent 
4. Create Spring  JPA Data Repository layer to support CREATE,UPDATE,READ, DELETE operations for HTTPS GET,POST,PUT,DELETE end points
5.Create Rest Controllers and map API requests.
ex: EnrolleesControler.java and
    DependentsController.java
6.Create Unit Testing for API requests and run the unit testing.using Junit frame work and mockito
7. Build and run the Project.: using Maven build 

Exposed REST API for GET/POST/PUT/DELETE Enrollees and Dependents
==================================================================

For Enrollees: Please refer EnrolleesControler.java 
----------------------------------------------
url :http://localhost:8080
Base path : /healthCare
@GetMapping(path="/enrollees") -> to Get all enrollees
@GetMapping(path="/enrolles/{id}")-> to get enrolless by id
@PostMapping("/enrollees") -> to add/create new enrollee
@PutMapping(path="/enrollees/{id}")-> to update enrollee by id 
@DeleteMapping(path="/enrollees/{id}")-> to delete enrollee by id

For Dependents : Please refer DependentsController.java
--------------------------------------------------------
@GetMapping(path="/enrollees/{enrolleesId}/dependents") -> to Get all the dependents based in enrolle id 
@PostMapping(path="/enrollees/{enrolleesId}/dependents") - to created a new dependent
@PutMapping(path="/enrollees/{enrolleesId}/dependents/{dependentsId}") - to update the dependents based on enrolle id
@DeleteMapping("/enrollees/{enrolleesId}/dependents/{dependentId}")- to Delete the Dependent based on enrollee id and dependent id

TODO LIST:
=============
-Swagger configuration 
-Test case for all the methods
- Lombok is not working due to plugin issue i need to fix and refactor the code .

