package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.concretes.SchoolInfo;
import kodlamaio.hrms.entities.dtos.SchoolInfoDetailDto;

public interface SchoolInfoDao extends JpaRepository<SchoolInfo,Integer>{
	List<SchoolInfo> getAllByCurriculumVitae_idOrderByGraduationDateDesc(int id);
	
	@Query("Select new kodlamaio.hrms.entities.dtos.SchoolInfoDetailDto(s.name,s.department,s.startDate,s.graduationDate) From SchoolInfo s Join s.curriculumVitae cs Where cs.id=:id Order By s.graduationDate DESC")
	List<SchoolInfoDetailDto> getSchoolInfoDetailByCurriculumVitae_IdGraduationDateDesc(int id);
}
