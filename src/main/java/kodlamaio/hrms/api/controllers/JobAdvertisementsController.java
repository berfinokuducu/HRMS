package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;

@RestController
@RequestMapping("/api/jobAdvertisements")
public class JobAdvertisementsController {
	private JobAdvertisementService jobAdvertisementService;
	
	@Autowired
	public JobAdvertisementsController(JobAdvertisementService jobAdvertisementService) {
		super();
		this.jobAdvertisementService = jobAdvertisementService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody JobAdvertisement jobadvertisement) 
	{
		return this.jobAdvertisementService.add(jobadvertisement);
	}
	
	@GetMapping("/getAllActiveJobAdvertisement")
	public DataResult<List<JobAdvertisement>> getAllActiveJobAdvertisement(){
		return this.jobAdvertisementService.getAllActiveJobAdvertisement();
	}
	
	@GetMapping("/getAllByOrderByCreatedDateDesc")
	public DataResult<List<JobAdvertisement>> getAllByOrderByCreatedDateDesc(){
		return this.jobAdvertisementService.getAllByOrderByCreatedDateDesc();
	}
	
	@GetMapping("/getAllActiveJobAdvertisementsForEmployer")
	public DataResult<List<JobAdvertisement>> getAllActiveJobAdvertisementsForEmployer(@RequestParam int id){
		return this.jobAdvertisementService.getAllActiveJobAdvertisementsForEmployer(id);
	}
	
	@PostMapping("/deactivateJobAdvertisement")
	public Result deactivateJobAdvertisement(@RequestBody int id) 
	{
		return this.jobAdvertisementService.deactivateJobAdvertisement(id);
	}
	
	
	

}
