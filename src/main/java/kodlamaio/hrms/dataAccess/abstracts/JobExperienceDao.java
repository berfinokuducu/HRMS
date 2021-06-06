package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.concretes.JobExperience;
import kodlamaio.hrms.entities.dtos.JobExperienceDetailDto;

public interface JobExperienceDao extends JpaRepository<JobExperience,Integer>{
	List<JobExperience> getAllByCurriculumVitae_idOrderByDateOfEndDesc(int id);
	
	@Query("Select new kodlamaio.hrms.entities.dtos.JobExperienceDetailDto(j.workplaceName,j.position,j.dateOfStart,j.dateOfEnd) From JobExperience j Join j.curriculumVitae cs Where cs.id=:id Order By j.dateOfEnd DESC")
	List<JobExperienceDetailDto> getJobExperienceDetailByCurriculumVitae_IdOrderByDateOfEndDesc(int id);

}
