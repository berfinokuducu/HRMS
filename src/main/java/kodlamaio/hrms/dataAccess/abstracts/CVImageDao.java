package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.CVImage;

public interface CVImageDao extends JpaRepository<CVImage,Integer>{
	CVImage getByCurriculumVitae_Id(int id);
}
