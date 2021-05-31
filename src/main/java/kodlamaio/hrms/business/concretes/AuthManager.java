package kodlamaio.hrms.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.ActivationCodeService;
import kodlamaio.hrms.business.abstracts.AuthService;
import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.core.utilities.adapters.UserCheckService;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.core.verification.VerificationService;
import kodlamaio.hrms.entities.concretes.ActivationCode;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.JobSeeker;

@Service
public class AuthManager implements AuthService{
	private JobSeekerService jobSeekerService;
	private EmployerService employerService;
	private ActivationCodeService activationCodeService;
	private VerificationService verificationService;
	private UserCheckService userCheckService; 
	
	
	@Autowired
	public AuthManager(JobSeekerService jobSeekerService, EmployerService employerService,
			ActivationCodeService activationCodeService, VerificationService verificationService,UserCheckService userCheckService) {
		super();
		this.jobSeekerService = jobSeekerService;
		this.employerService = employerService;
		this.activationCodeService = activationCodeService;
		this.verificationService = verificationService;
		this.userCheckService=userCheckService;
	}

	


	@Override
	public Result registerJobSeeker(JobSeeker jobSeeker, String confirmPassword) {
		if(!checkIfNullInfoForJobSeeker(jobSeeker, confirmPassword)) {
			return new ErrorResult("Tüm alanlar zorunludur.");
		}
		if(!checkIfEqualPasswordAndConfirmPassword(jobSeeker.getPassword(),confirmPassword))
		{
			return new ErrorResult("Girdiğiniz parolalar eşleşmiyor.");
		}
		if(!checkIfEmailOrNationalIdExistsForJobSeeker(jobSeeker.getEmail(),jobSeeker.getNationalId())) {
			return new ErrorResult("Girdiğiniz eposta veya TCNo sistemde mevcut.");
		}
		if(!checkIfRealPerson(jobSeeker))
		{
			return new ErrorResult("Tc doğrulama başarısız.");
		}
		jobSeekerService.add(jobSeeker);
		String code=verificationService.sendActivationCode();
		addCode(jobSeeker.getId(),code);
		
		return new SuccessResult("İş Arayan Başarıyla Kaydedildi.");
	}

	@Override
	public Result registerEmployer(Employer employer, String confirmPassword) {
		if(!checkIfNullInfoForEmployer(employer, confirmPassword))
		{
			return new ErrorResult("Tüm alanlar zorunludur.");
		}
		if(!checkIfEqualPasswordAndConfirmPassword(employer.getPassword(),confirmPassword))
		{
			return new ErrorResult("Girdiğiniz parolalar eşleşmiyor.");
		}
		if(!checkIfEmailExistsForEmployer(employer.getEmail()))
		{
			return new ErrorResult("Girdiğiniz eposta sistemde mevcut.");
		}
		employerService.add(employer);
		String code=verificationService.sendActivationCode();
		addCode(employer.getId(),code);
		return new SuccessResult("İş Veren Başarıyla Kaydedildi.");
	}
	
	private boolean checkIfNullInfoForEmployer(Employer employer,String confirmPassword) {
		if(employer.getEmail()!=null && employer.getCompanyName()!=null && confirmPassword!=null && employer.getPassword()!=null && employer.getWebAddress()!=null && employer.getPhoneNumber()!=null)
		{
			return true;
		}
		return false;
	}
	
	private boolean checkIfNullInfoForJobSeeker(JobSeeker jobSeeker,String confirmPassword) {
		if(jobSeeker.getEmail()!=null && jobSeeker.getFirstName()!=null && confirmPassword!=null && jobSeeker.getPassword()!=null && jobSeeker.getLastName()!=null && jobSeeker.getNationalId()!=null && jobSeeker.getBirthDate()!=null)
		{
			return true;
		}
		return false;
	}
	
	private boolean checkIfEqualPasswordAndConfirmPassword(String password,String confirmPassword)
	{
		if(password.equals(confirmPassword))
		{
			return true;
		}
		return false;
	}
	private boolean checkIfEmailExistsForEmployer(String email)
	{
		List<Employer> employers=new ArrayList<Employer>();
		employers=employerService.getAll().getData();
		for(Employer employer : employers)
		{
			if(employer.getEmail().equals(email)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean checkIfEmailOrNationalIdExistsForJobSeeker(String email,String tc)
	{
		List<JobSeeker> jobSeekers=new ArrayList<JobSeeker>();
		jobSeekers=jobSeekerService.getAll().getData();
		for(JobSeeker jobSeeker : jobSeekers)
		{
			if(jobSeeker.getEmail().equals(email) || jobSeeker.getNationalId().equals(tc)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean checkIfRealPerson(JobSeeker jobSeeker)
	{
		if(userCheckService.checkIfRealPerson(jobSeeker.getNationalId(),jobSeeker.getFirstName(), jobSeeker.getLastName(), jobSeeker.getBirthDate().getYear()))
		{
			return true;
		}
		return false;
	}
	
	private void addCode(int id,String code)
	{
		ActivationCode activationCode=new ActivationCode(id,code,false);
		activationCodeService.add(activationCode);
	}

}
