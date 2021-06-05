package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CVLinkService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CVLinkDao;
import kodlamaio.hrms.entities.concretes.CVLink;

@Service
public class CVLinkManager implements CVLinkService{
	private CVLinkDao cvLinkDao;
	
	@Autowired
	public CVLinkManager(CVLinkDao cvLinkDao) {
		super();
		this.cvLinkDao = cvLinkDao;
	}

	@Override
	public Result add(CVLink link) {
		this.cvLinkDao.save(link);
		return new SuccessResult("Link Başarıyla eklendi");
	}

	@Override
	public DataResult<List<CVLink>> getAll() {
		// TODO Auto-generated method stub
		return new SuccessDataResult<List<CVLink>>(this.cvLinkDao.findAll(),"Linkler listelendi");
	}

}
