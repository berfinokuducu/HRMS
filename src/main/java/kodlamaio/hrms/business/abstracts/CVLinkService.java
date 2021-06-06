package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.CVLink;
import kodlamaio.hrms.entities.dtos.CVLinkDetailDto;

public interface CVLinkService {
	Result add(CVLink link);
	DataResult<List<CVLink>> getAll();
	DataResult<List<CVLinkDetailDto>> getAllByCvId(int id);

}
