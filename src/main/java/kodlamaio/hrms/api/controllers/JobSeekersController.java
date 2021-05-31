package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.AuthService;
import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobSeeker;


@RestController
@RequestMapping("/api/jobSeekers")
public class JobSeekersController {
	private JobSeekerService jobSeekerService;
	private AuthService authService;
	
	@Autowired
	public JobSeekersController(JobSeekerService jobSeekerService,AuthService authService) {
		super();
		this.jobSeekerService = jobSeekerService;
		this.authService=authService;
	}
	@GetMapping("/getall")
	public DataResult<List<JobSeeker>> getAll(){
		return this.jobSeekerService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody JobSeeker jobSeeker,String confirmPassword){
		return this.authService.registerJobSeeker(jobSeeker, confirmPassword);
	}

}
