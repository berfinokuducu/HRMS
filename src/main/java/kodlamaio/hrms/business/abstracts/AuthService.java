package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.JobSeeker;

public interface AuthService {
	Result registerJobSeeker(JobSeeker jobSeeker,String confirmPassword);
	Result registerEmployer(Employer employer,String confirmPassword);
}
