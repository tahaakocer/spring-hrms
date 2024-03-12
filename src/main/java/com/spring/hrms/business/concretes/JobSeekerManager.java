package com.spring.hrms.business.concretes;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.spring.hrms.business.abstracts.EmailService;
import com.spring.hrms.business.abstracts.JobSeekerService;
import com.spring.hrms.core.mernis.UBKKPSPublicSoap;
import com.spring.hrms.core.utilities.results.DataResult;
import com.spring.hrms.core.utilities.results.ErrorResult;
import com.spring.hrms.core.utilities.results.Result;
import com.spring.hrms.core.utilities.results.SuccessDataResult;
import com.spring.hrms.core.utilities.results.SuccessResult;
import com.spring.hrms.core.validators.JobSeekerValidator;
import com.spring.hrms.dataAccess.abstracts.ConfirmationTokenDao;
import com.spring.hrms.dataAccess.abstracts.JobSeekersDao;
import com.spring.hrms.entities.concretes.ConfirmationToken;
import com.spring.hrms.entities.concretes.JobSeeker;

@Service
public class JobSeekerManager implements JobSeekerService {

	private JobSeekersDao jobSeekerDao;
	
	@Autowired
    ConfirmationTokenDao confirmationTokenRepository;

    @Autowired
    EmailService emailService;
    
	@Autowired
	public JobSeekerManager(JobSeekersDao jobSeekerDao) {
		super();
		this.jobSeekerDao = jobSeekerDao;
	
	}

	@Override
	public Result add(JobSeeker jobSeeker) {
				
		JobSeekerValidator validate = new JobSeekerValidator(jobSeekerDao);
		if(validate.isJobSeekerExists(jobSeeker.getTcNo())) {
			return new ErrorResult("Bu TC no ile daha önce kayıt yapılmış!");
		}
		UBKKPSPublicSoap client = new UBKKPSPublicSoap();
		long tcno = Long.parseLong(jobSeeker.getTcNo());
		boolean isRealPerson = false;

		try {
			isRealPerson = client.TCKimlikNoDogrula(tcno, jobSeeker.getFirstName(), jobSeeker.getLast_name(),
					jobSeeker.getBirthYear());
	
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(isRealPerson) {
			this.jobSeekerDao.save(jobSeeker);
	        ConfirmationToken confirmationToken = new ConfirmationToken();
	        confirmationToken.setConfirmationToken(UUID.randomUUID().toString()); // Rastgele token oluşturuluyor
	        confirmationTokenRepository.save(confirmationToken);

	        SimpleMailMessage mailMessage = new SimpleMailMessage();
	        mailMessage.setTo(jobSeeker.getEmail());
	        mailMessage.setSubject("Complete Registration!");
	        mailMessage.setText("To confirm your account, please click here : "
	                +"http://localhost:8085/confirm-account?token="+confirmationToken.getConfirmationToken());
	        emailService.sendEmail(mailMessage);

	        System.out.println("Confirmation Token: " + confirmationToken.getConfirmationToken());

			return new SuccessResult("Verify email by the link sent on your email address");
		}
		else {
			return new ErrorResult("TC no geçerli değil!");
		}
	}

	@Override
	public DataResult<List<JobSeeker>> getAll() {

		return new SuccessDataResult<List<JobSeeker>>(this.jobSeekerDao.findAll(), "İş Arayan Listesi Getirildi.");
	}

	@Override
	public Result delete(int id) {
		jobSeekerDao.deleteById(id);
		return new SuccessResult("Silindi.");
	}

	@Override
    public ResponseEntity<?> confirmEmail(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
        
            return ResponseEntity.ok("Email verified successfully!");
        }
        return ResponseEntity.badRequest().body("Error: Couldn't verify email");
    }
}
