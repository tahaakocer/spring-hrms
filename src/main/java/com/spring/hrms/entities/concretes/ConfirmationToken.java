package com.spring.hrms.entities.concretes;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "email_confirmation")
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmationToken {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "token_id")
	private Long tokenId;

	@Column(name = "confirmation_token")
	private String confirmationToken;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@OneToOne(targetEntity = JobSeeker.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "jobseeker_id")
	private JobSeeker jobSeeker;
	
	public ConfirmationToken(JobSeeker jobSeeker) {
        this.jobSeeker = jobSeeker;
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }
}
