package kodlamaio.hrms.business.concretes;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.ActivationCodeService;
import kodlamaio.hrms.business.abstracts.AuthService;
import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.business.abstracts.UserService;
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
	private UserService userService;
	
	
	@Autowired
	public AuthManager(JobSeekerService jobSeekerService, EmployerService employerService,
			ActivationCodeService activationCodeService, VerificationService verificationService,UserCheckService userCheckService,UserService userService) {
		super();
		this.jobSeekerService = jobSeekerService;
		this.employerService = employerService;
		this.activationCodeService = activationCodeService;
		this.verificationService = verificationService;
		this.userCheckService=userCheckService;
		this.userService=userService;
	}

	


	@Override
	public Result registerJobSeeker(JobSeeker jobSeeker, String confirmPassword) {
		if(!checkIfNullConfirmPassword(confirmPassword)) {
			return new ErrorResult("Parola Tekrarı alanı zorunludur.");
		}
		if(!checkIfEqualPasswordAndConfirmPassword(jobSeeker.getPassword(),confirmPassword))
		{
			return new ErrorResult("Girdiğiniz parolalar eşleşmiyor.");
		}
		if(!checkIfEmailExists(jobSeeker.getEmail()))
		{
			return new ErrorResult("Girdiğiniz eposta sistemde mevcut.");
		}
		if(!checkIfNationalIdExistsForJobSeeker(jobSeeker.getNationalId())) {
			return new ErrorResult("Girdiğiniz TCNo sistemde mevcut.");
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
		if(!checkIfNullConfirmPassword(confirmPassword))
		{
			return new ErrorResult("Parola Tekrarı alanı zorunludur.");
		}
		if(!checkIfEqualPasswordAndConfirmPassword(employer.getPassword(),confirmPassword))
		{
			return new ErrorResult("Girdiğiniz parolalar eşleşmiyor.");
		}
		if(!checkIfEmailExists(employer.getEmail()))
		{
			return new ErrorResult("Girdiğiniz eposta sistemde mevcut.");
		}
		employerService.add(employer);
		String code=verificationService.sendActivationCode();
		addCode(employer.getId(),code);
		return new SuccessResult("İş Veren Başarıyla Kaydedildi.");
	}
	
	private boolean checkIfNullConfirmPassword(String confirmPassword) {
		if(confirmPassword!=null )
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
	private boolean checkIfEmailExists(String email)
	{
		if(userService.getUserByEmail(email).getData()!=null) {
			return false;
		}
		return true;
	}
	
	private boolean checkIfNationalIdExistsForJobSeeker(String tc)
	{
		if(jobSeekerService.getJobSeekerByNationalId(tc).getData()!=null) {
			return false;
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
