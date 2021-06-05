package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.SchoolInfoService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concretes.SchoolInfo;

@RestController
@RequestMapping("/api/schoolInfos")
public class SchoolInfosController {
	private SchoolInfoService schoolInfoService;
	
	@Autowired
	public SchoolInfosController(SchoolInfoService schoolInfoService) {
		super();
		this.schoolInfoService = schoolInfoService;
	}
	
	@GetMapping("/getByCurriculumVitae_IdOrderByGraduationDateDesc")
	public DataResult<List<SchoolInfo>> getByCurriculumVitae_IdOrderByGraduationDateDesc(int id){
		return this.schoolInfoService.getByCurriculumVitae_IdOrderByGraduationDateDesc(id);
	}

}
