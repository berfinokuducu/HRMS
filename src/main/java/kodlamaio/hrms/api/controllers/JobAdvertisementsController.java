package kodlamaio.hrms.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;

@RestController
@RequestMapping("/api/jobAdvertisements")
@CrossOrigin
public class JobAdvertisementsController {
	private JobAdvertisementService jobAdvertisementService;
	
	@Autowired
	public JobAdvertisementsController(JobAdvertisementService jobAdvertisementService) {
		super();
		this.jobAdvertisementService = jobAdvertisementService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody JobAdvertisement jobadvertisement) 
	{
		return ResponseEntity.ok( this.jobAdvertisementService.add(jobadvertisement));
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
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions){
		Map<String,String> validationErrors= new HashMap<String, String>();
		for(FieldError fieldError: exceptions.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		ErrorDataResult<Object> errors=new ErrorDataResult<Object>(validationErrors,"Doğrulama Hataları");
		return errors;
	}
	
	

}
