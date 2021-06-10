package kodlamaio.hrms.api.controllers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.business.abstracts.AbilityService;
import kodlamaio.hrms.business.abstracts.CVImageService;
import kodlamaio.hrms.business.abstracts.CVLinkService;
import kodlamaio.hrms.business.abstracts.CurriculumVitaeService;
import kodlamaio.hrms.business.abstracts.ForeignLanguageService;
import kodlamaio.hrms.business.abstracts.JobExperienceService;
import kodlamaio.hrms.business.abstracts.SchoolInfoService;
import kodlamaio.hrms.core.utilities.helpers.CloudinaryService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Ability;
import kodlamaio.hrms.entities.concretes.CVImage;
import kodlamaio.hrms.entities.concretes.CVLink;
import kodlamaio.hrms.entities.concretes.CurriculumVitae;
import kodlamaio.hrms.entities.concretes.ForeignLanguage;
import kodlamaio.hrms.entities.concretes.JobExperience;
import kodlamaio.hrms.entities.concretes.SchoolInfo;
import kodlamaio.hrms.entities.dtos.CurriculumVitaeDetailDto;

@RestController
@RequestMapping("/api/curriculumvitaes")
@CrossOrigin
public class CurriculumVitaesController {
	private CurriculumVitaeService curriculumVitaeService;
	private AbilityService abilityService;
	private CVLinkService linkService;
	private ForeignLanguageService languageService;
	private JobExperienceService jobExperienceService;
	private SchoolInfoService schoolInfoservice;
	private CVImageService imageService; 
	private CloudinaryService cloudinaryService;
	@Autowired
	public CurriculumVitaesController(CurriculumVitaeService curriculumVitaeService, AbilityService abilityService,
			CVLinkService linkService, ForeignLanguageService languageService,
			JobExperienceService jobExperienceService, SchoolInfoService schoolInfoservice,CVImageService imageService,
			CloudinaryService cloudinaryService) {
		super();
		this.curriculumVitaeService = curriculumVitaeService;
		this.abilityService = abilityService;
		this.linkService = linkService;
		this.languageService = languageService;
		this.jobExperienceService = jobExperienceService;
		this.schoolInfoservice = schoolInfoservice;
		this.imageService=imageService;
		this.cloudinaryService=cloudinaryService;
	}
	
	@PostMapping("/add")
	public Result create(@RequestBody CurriculumVitae curriculumVitae){
		return this.curriculumVitaeService.add(curriculumVitae);
	}
	
	@PostMapping("/addAbility")
	public Result addAbility(@RequestBody Ability ability){
		return this.abilityService.add(ability);
	}
	
	@PostMapping("/addLink")
	public Result addLink(@RequestBody CVLink link){
		return this.linkService.add(link);
	}
	
	@PostMapping("/addForeignLanguage")
	public Result addForeignLanguage(@RequestBody ForeignLanguage foreignLanguage){
		return this.languageService.add(foreignLanguage);
	}
	
	@PostMapping("/addJobExperience")
	public Result addJobExperience(@RequestBody JobExperience jobExperience){
		return this.jobExperienceService.add(jobExperience);
	}
	
	@PostMapping("/addSchoolInfo")
	public Result addSchoolInfo(@RequestBody SchoolInfo schoolInfo){
		return this.schoolInfoservice.add(schoolInfo);
	}
	
	@PostMapping("/addImage")
	public Result addImage(@RequestParam int cv_id ,@RequestParam MultipartFile multipartFile) throws IOException{
		BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
		if(bufferedImage == null) {
			
			return new ErrorResult("Fotoğraf yüklenemedi.");
		}
		Map result = cloudinaryService.upload(multipartFile);
		CVImage image=new CVImage();
		image.setUrl((String)result.get("url"));
		image.setCurriculumVitae(this.curriculumVitaeService.getById(cv_id).getData());
		return this.imageService.add(image,cv_id);
		
		
		
	}
	
	@GetMapping("/getCV")
	public DataResult<CurriculumVitaeDetailDto> getCvByJobSeekerId(@RequestParam int id) {
		return this.curriculumVitaeService.getByJobSeekerId(id);
	}
	
	

}
