package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobAdvertisementDao;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;

@Service
public class JobAdvertisementManager implements JobAdvertisementService {
	private JobAdvertisementDao jobAdvertisementDao;
	
	@Autowired
	public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao) {
		super();
		this.jobAdvertisementDao = jobAdvertisementDao;
	}

	@Override
	public Result add(JobAdvertisement jobAdvertisement) {
		if(!checkIfNullInfo(jobAdvertisement)) {
			return new ErrorResult("Bazı boş alanlar var.");
		}
		this.jobAdvertisementDao.save(jobAdvertisement);
		return new SuccessResult("iş İlanı Eklendi");
	}
	
	private boolean checkIfNullInfo(JobAdvertisement jobAdvertisement) {
		if(jobAdvertisement.getJobPosition()!=null && jobAdvertisement.getDescription()!=null && jobAdvertisement.getCity()!=null&& jobAdvertisement.getOpenPositionCount()!=0) {
			return true;
		}
		return false;
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAll() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findAll());
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllActiveJobAdvertisement() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getByIsActive());
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllByOrderByCreatedDateDesc() {
		// TODO Auto-generated method stub
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getAllByOrderByCreatedDateDesc());
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllActiveJobAdvertisementsForEmployer(int id) {
		// TODO Auto-generated method stub
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getAllActiveJobAdvertisementsForEmployer(id));
	}

	@Override
	public Result deactivateJobAdvertisement(int id) {
		
		JobAdvertisement jobAdvertisement=this.jobAdvertisementDao.getById(id);
		if(!jobAdvertisement.isActive()) {
			return new ErrorResult("Pasif bir ilanı pasif yapamazsınız.");
		}
		jobAdvertisement.setActive(false);
		jobAdvertisementDao.save(jobAdvertisement);
		
		return new SuccessResult("İş ilanı pasif hale getirildi");
	}

}
