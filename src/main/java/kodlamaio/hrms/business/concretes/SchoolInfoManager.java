package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.SchoolInfoService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.SchoolInfoDao;
import kodlamaio.hrms.entities.concretes.SchoolInfo;
@Service
public class SchoolInfoManager implements SchoolInfoService{
	private SchoolInfoDao schoolInfoDao;
	
	@Autowired
	public SchoolInfoManager(SchoolInfoDao schoolInfoDao) {
		super();
		this.schoolInfoDao = schoolInfoDao;
		
	}

	@Override
	public Result add(SchoolInfo schoolInfo) {
		
		this.schoolInfoDao.save(schoolInfo);
		return new SuccessResult("Okul Bilgisi Başarıyla eklendi");
	}

	@Override
	public DataResult<List<SchoolInfo>> getAll() {
		
		return new SuccessDataResult<List<SchoolInfo>>(this.schoolInfoDao.findAll(),"Okul bilgileri listelendi.");
	}

	@Override
	public DataResult<List<SchoolInfo>> getByCurriculumVitae_IdOrderByGraduationDateDesc(int id) {
		return new SuccessDataResult<List<SchoolInfo>>(this.schoolInfoDao.getAllByCurriculumVitae_idOrderByGraduationDateDesc(id),"Listelendi");
	}

}
