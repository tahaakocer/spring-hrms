package com.spring.hrms.entities.concretes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "employers")
@AllArgsConstructor
@NoArgsConstructor

public class Employer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "company_name")
	private String companyName;
	
	@Column(name = "website")
	private String website;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone")
	private String phone;	
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "email_verified")
	private boolean isVerified;
	
	@Column(name = "is_approved")
	private boolean isApproved;
}
