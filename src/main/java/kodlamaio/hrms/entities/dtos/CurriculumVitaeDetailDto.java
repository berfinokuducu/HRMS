package kodlamaio.hrms.entities.dtos;

import java.util.List;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurriculumVitaeDetailDto {
	private JobSeeker jobSeeker;
	private String coverLetter;
	private List<AbilityDetailDto> abilites;
	private List<CVLinkDetailDto> links;
	private List<ForeignLanguageDetailDto> languages;
	private List<JobExperienceDetailDto> jobExperiences;
	private List<SchoolInfoDetailDto> schools;
	private CVImageDetailDto image;
}
