package com.spring.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.spring.hrms.business.abstracts.EmailService;

@Service("emailService")
public class EmailManager implements EmailService {
	
	private JavaMailSender javaMailSender;

    @Autowired
    public EmailManager(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


	@Override
	public void sendEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
	}

}
