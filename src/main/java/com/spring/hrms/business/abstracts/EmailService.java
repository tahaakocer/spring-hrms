package com.spring.hrms.business.abstracts;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
	void sendEmail(SimpleMailMessage email);
}
