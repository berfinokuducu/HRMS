package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.SchoolInfo;

public interface SchoolInfoService {
	Result add(SchoolInfo schoolInfo);
	DataResult<List<SchoolInfo>> getAll();
	DataResult<List<SchoolInfo>> getByCurriculumVitae_IdOrderByGraduationDateDesc(int id);
}
