package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.concretes.CVLink;
import kodlamaio.hrms.entities.dtos.CVLinkDetailDto;

public interface CVLinkDao extends JpaRepository<CVLink,Integer>{
	@Query("Select new kodlamaio.hrms.entities.dtos.CVLinkDetailDto(l.name,l.url) From CVLink l Join l.curriculumVitae cs Where cs.id=:id")
	List<CVLinkDetailDto> getByCurriculumVitae_Id(int id);
}
