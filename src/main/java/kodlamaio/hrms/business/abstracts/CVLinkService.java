package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.CVLink;

public interface CVLinkService {
	Result add(CVLink link);
	DataResult<List<CVLink>> getAll();

}
