package kodlamaio.hrms.business.concretes;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobPositionService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobPositionDao;

import kodlamaio.hrms.entities.concretes.JobPosition;

@Service
public class JobPositionManager implements JobPositionService{
	private JobPositionDao jobPositionDao;
	
	@Autowired
	public JobPositionManager(JobPositionDao jobPositionDao) {
		super();
		this.jobPositionDao = jobPositionDao;
	}

	@Override
	public DataResult<List<JobPosition>> getAll() {
		// TODO Auto-generated method stub
		return new SuccessDataResult<List<JobPosition>>(jobPositionDao.findAll(),"İş pozisyonları listelendi");
	}

	@Override
	public Result add(JobPosition jobPosition) {
		if(!checkIfJobPositionExists(jobPosition.getPositionName())) {
			return new ErrorResult("Bu pozisyon sistemde bulunmaktadır.");
		}
		jobPositionDao.save(jobPosition);
		return new SuccessResult("İş pozisyonu eklendi.");
	}
	private boolean checkIfJobPositionExists(String positionName)
	{
		if(jobPositionDao.getByPositionName(positionName)!=null)
		{
			return false;
		}
		return true;
	}

}
