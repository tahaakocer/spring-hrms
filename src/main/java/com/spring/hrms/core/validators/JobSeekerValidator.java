package com.spring.hrms.core.validators;

import java.util.Optional;

import com.spring.hrms.dataAccess.abstracts.JobSeekersDao;
import com.spring.hrms.entities.concretes.JobSeeker;

public class JobSeekerValidator {
	
		private JobSeekersDao dao;
		public JobSeekerValidator(JobSeekersDao dao) {
			this.dao = dao;
		}
		
	  public boolean isJobSeekerExists(String tcNo) {
	        if(dao.getByTcNo(tcNo) == null) {
	        	return false;
	        }
	        else
	        {
	        	return true;
	        }
	    }
	
}
