package com.spring.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.hrms.entities.concretes.JobPosition;

public interface JobPositionsDao extends JpaRepository<JobPosition,Integer>{

}
