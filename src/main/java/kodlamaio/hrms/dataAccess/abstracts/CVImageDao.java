package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.concretes.CVImage;
import kodlamaio.hrms.entities.dtos.CVImageDetailDto;

public interface CVImageDao extends JpaRepository<CVImage,Integer>{
	@Query("Select new kodlamaio.hrms.entities.dtos.CVImageDetailDto(i.url) From CVImage i Join i.curriculumVitae cs Where cs.id=:id")
	CVImageDetailDto getByCurriculumVitae_Id(int id);
}
