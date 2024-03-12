package com.spring.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.hrms.business.abstracts.JobSeekerService;
import com.spring.hrms.core.utilities.results.Result;
import com.spring.hrms.entities.concretes.JobSeeker;

@RestController
@RequestMapping("/api/jobseekers")
public class JobSeekerController {

	private JobSeekerService service;

	@Autowired
	public JobSeekerController(JobSeekerService service) {
		super();
		this.service = service;
	}

	@PostMapping("/add")
	public Result add(@RequestBody JobSeeker jobSekeer) {

		return this.service.add(jobSekeer);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestBody int id) {
		return this.service.delete(id);
	}

	@GetMapping("/getAll")
	public Result getAll() {
		return this.service.getAll();
	}

}
