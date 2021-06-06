package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.concretes.ForeignLanguage;
import kodlamaio.hrms.entities.dtos.ForeignLanguageDetailDto;

public interface ForeignLanguageDao extends JpaRepository<ForeignLanguage,Integer>{
	@Query("Select new kodlamaio.hrms.entities.dtos.ForeignLanguageDetailDto(l.name,l.level) From ForeignLanguage l Join l.curriculumVitae cs Where cs.id=:id")
	List<ForeignLanguageDetailDto> getByCurriculumVitae_Id(int id);
}
