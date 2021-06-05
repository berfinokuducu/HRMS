package kodlamaio.hrms.business.concretes;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kodlamaio.hrms.business.abstracts.CVImageService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CVImageDao;
import kodlamaio.hrms.entities.concretes.CVImage;

@Service
public class CVImageManager implements CVImageService{
	
	private CVImageDao cvImageDao;
	
	@Autowired
	public CVImageManager(CVImageDao cvImageDao) {
		super();
		this.cvImageDao = cvImageDao;
	}

	@Override
	public Result add(CVImage image,int id) {
		if(cvImageDao.getByCurriculumVitae_Id(id)!=null)
		{
			return new ErrorResult("Resminiz bulunuyor işleminize güncelleme olarak devam ediniz.");
		}
		this.cvImageDao.save(image);
		return new SuccessResult("Resim Başarıyla eklendi");
	}

	@Override
	public DataResult<List<CVImage>> getAll() {
		return new SuccessDataResult<List<CVImage>>(this.cvImageDao.findAll(),"Resimler listelendi");
	}

}
