package com.spring.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.hrms.entities.concretes.ConfirmationToken;

@Repository
public interface ConfirmationTokenDao extends JpaRepository<ConfirmationToken, Long>{
	
    ConfirmationToken findByConfirmationToken(String confirmationToken);

}
