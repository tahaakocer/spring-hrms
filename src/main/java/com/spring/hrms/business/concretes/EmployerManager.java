package com.spring.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.hrms.business.abstracts.EmployerService;
import com.spring.hrms.dataAccess.abstracts.EmployerDao;

@Service
public class EmployerManager implements EmployerService {
	
	private EmployerDao employerDao;
	
	@Autowired
	public EmployerManager(EmployerDao employerDao) {
		super();
		this.employerDao = employerDao;
	}

	
}
