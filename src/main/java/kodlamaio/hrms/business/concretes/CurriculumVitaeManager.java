package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.AbilityService;
import kodlamaio.hrms.business.abstracts.CVImageService;
import kodlamaio.hrms.business.abstracts.CVLinkService;
import kodlamaio.hrms.business.abstracts.CurriculumVitaeService;
import kodlamaio.hrms.business.abstracts.ForeignLanguageService;
import kodlamaio.hrms.business.abstracts.JobExperienceService;
import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.business.abstracts.SchoolInfoService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CurriculumVitaeDao;
import kodlamaio.hrms.entities.concretes.CurriculumVitae;
import kodlamaio.hrms.entities.dtos.CurriculumVitaeDetailDto;

@Service
public class CurriculumVitaeManager implements CurriculumVitaeService{
	private CurriculumVitaeDao curriculumVitaeDao;
	private AbilityService abilityService;
	private JobSeekerService jobSeekerService;
	private CVImageService imageService;
	private CVLinkService linkService;
	private ForeignLanguageService languageService;
	private JobExperienceService jobExperienceService;
	private SchoolInfoService schoolInfoService;
	
	@Autowired
	public CurriculumVitaeManager(CurriculumVitaeDao curriculumVitaeDao,AbilityService abilityService,
			JobSeekerService jobSeekerService,CVImageService imageService,CVLinkService linkService,
			ForeignLanguageService languageService,JobExperienceService jobExperienceService,
			 SchoolInfoService schoolInfoService) {
		super();
		this.curriculumVitaeDao = curriculumVitaeDao;
		this.abilityService=abilityService;
		this.jobSeekerService=jobSeekerService;
		this.imageService=imageService;
		this.linkService=linkService;
		this.languageService=languageService;
		this.jobExperienceService=jobExperienceService;
		this.schoolInfoService=schoolInfoService;
	}

	@Override
	public Result add(CurriculumVitae curriculumVitae) {
		if(this.curriculumVitaeDao.getByJobSeeker_Id(curriculumVitae.getJobSeeker().getId())!=null)
		{
			return new ErrorResult("Bu kişi için cv mevcut.");
		}
		this.curriculumVitaeDao.save(curriculumVitae);
		return new SuccessResult("Başarıyla kaydedildi.");
	}

	@Override
	public DataResult<List<CurriculumVitae>> getAll() {
		return new SuccessDataResult<List<CurriculumVitae>>(this.curriculumVitaeDao.findAll(),"Özgeçmişler listelendi");
	}

	@Override
	public DataResult<CurriculumVitae> getById(int id) {
		return new SuccessDataResult<CurriculumVitae>(this.curriculumVitaeDao.getById(id));
	}

	@Override
	public DataResult<CurriculumVitaeDetailDto> getByJobSeekerId(int id) {
		CurriculumVitae cv=this.curriculumVitaeDao.getByJobSeeker_Id(id);
		if(cv ==null)
		{
			return new ErrorDataResult<CurriculumVitaeDetailDto>("Bu kişiye ait CV Bulunamadı");
		}
		CurriculumVitaeDetailDto cvDetail=new CurriculumVitaeDetailDto();
		cvDetail.setCoverLetter(cv.getCoverLetter());
		cvDetail.setJobSeeker(this.jobSeekerService.getById(id).getData());
		cvDetail.setAbilites(this.abilityService.getAllByCvId(cv.getId()).getData());
		cvDetail.setImage(this.imageService.getById(cv.getId()).getData());
		cvDetail.setLinks(this.linkService.getAllByCvId(cv.getId()).getData());
		cvDetail.setLanguages(this.languageService.getAllByCvId(cv.getId()).getData());
		cvDetail.setJobExperiences(this.jobExperienceService.getAllByCvId(cv.getId()).getData());
		cvDetail.setSchools(this.schoolInfoService.getAllByCvId(cv.getId()).getData());
		return new SuccessDataResult<CurriculumVitaeDetailDto>(cvDetail);
	}

}
