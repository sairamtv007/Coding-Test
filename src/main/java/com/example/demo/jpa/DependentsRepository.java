package com.example.demo.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Dependents;

public interface DependentsRepository extends JpaRepository<Dependents, Long> {
List<Dependents>findByEnrolleesId(Long enrolleeId);

}
