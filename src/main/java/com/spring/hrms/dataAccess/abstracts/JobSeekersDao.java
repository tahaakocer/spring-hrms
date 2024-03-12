package com.spring.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.spring.hrms.entities.concretes.JobSeeker;

@Repository 
public interface JobSeekersDao extends JpaRepository<JobSeeker,Integer>{
	
	JobSeeker getByTcNo(String tcno);
	
	// mail verification
	JobSeeker findByEmailIgnoreCase(String emailId);
    Boolean existsByEmail(String email);
}
