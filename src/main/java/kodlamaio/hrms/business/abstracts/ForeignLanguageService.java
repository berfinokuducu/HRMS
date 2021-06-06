package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.ForeignLanguage;
import kodlamaio.hrms.entities.dtos.ForeignLanguageDetailDto;

public interface ForeignLanguageService {
	Result add(ForeignLanguage foreignLanguage);
	DataResult<List<ForeignLanguage>> getAll();
	DataResult<List<ForeignLanguageDetailDto>> getAllByCvId(int id);
	

}
