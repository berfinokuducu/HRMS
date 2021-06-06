package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.concretes.Ability;
import kodlamaio.hrms.entities.dtos.AbilityDetailDto;

public interface AbilityDao extends JpaRepository<Ability,Integer> {
	
	@Query("Select new kodlamaio.hrms.entities.dtos.AbilityDetailDto(a.name) From Ability a Join a.curriculumVitae cs Where cs.id=:id")
	List<AbilityDetailDto> findByCurriculumVitae_Id(int id);
}
