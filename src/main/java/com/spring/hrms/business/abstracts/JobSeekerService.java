package com.spring.hrms.business.abstracts;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.spring.hrms.core.utilities.results.DataResult;
import com.spring.hrms.core.utilities.results.Result;
import com.spring.hrms.entities.concretes.JobSeeker;

public interface JobSeekerService {
	
	Result add(JobSeeker jobSeeker);
	Result delete(int id);
	DataResult<List<JobSeeker>> getAll();
	//email verification
    ResponseEntity<?> confirmEmail(String confirmationToken);
}
