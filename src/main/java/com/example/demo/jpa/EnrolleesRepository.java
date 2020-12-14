package com.example.demo.jpa;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Enrollees;

public interface  EnrolleesRepository extends JpaRepository<Enrollees,Long>{

}
