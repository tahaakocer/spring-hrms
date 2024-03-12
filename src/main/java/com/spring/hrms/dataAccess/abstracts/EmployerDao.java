package com.spring.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.hrms.entities.concretes.Employer;

public interface EmployerDao extends JpaRepository<Employer, Integer>{

}
