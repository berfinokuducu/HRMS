package kodlamaio.hrms.business.abstracts;

import java.util.List;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.CVImage;
import kodlamaio.hrms.entities.dtos.CVImageDetailDto;

public interface CVImageService {
	Result add(CVImage image,int id);
	DataResult<List<CVImage>> getAll();
	DataResult<CVImageDetailDto> getById(int id);
}
