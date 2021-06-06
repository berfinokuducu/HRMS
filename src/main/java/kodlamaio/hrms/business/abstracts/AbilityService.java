package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Ability;
import kodlamaio.hrms.entities.dtos.AbilityDetailDto;

public interface AbilityService {
	Result add(Ability ability);
	DataResult<List<Ability>> getAll();
	DataResult<List<AbilityDetailDto>> getAllByCvId(int id);
	

}
