package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.AbilityService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.AbilityDao;
import kodlamaio.hrms.entities.concretes.Ability;
import kodlamaio.hrms.entities.dtos.AbilityDetailDto;

@Service
public class AbilityManager implements AbilityService{
	private AbilityDao abilityDao;
	
	@Autowired
	public AbilityManager(AbilityDao abilityDao) {
		super();
		this.abilityDao = abilityDao;
		
	}

	@Override
	public Result add(Ability ability) {
		this.abilityDao.save(ability);
		return new SuccessResult("Başarıyla eklendi");
	}

	@Override
	public DataResult<List<Ability>> getAll() {
		
		return new SuccessDataResult<List<Ability>>(this.abilityDao.findAll(),"Yetenekler listelendi");
	}

	@Override
	public DataResult<List<AbilityDetailDto>> getAllByCvId(int id) {
		// TODO Auto-generated method stub
		return new SuccessDataResult<List<AbilityDetailDto>>(this.abilityDao.findByCurriculumVitae_Id(id));
	}

}
