package kodlamaio.hrms.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="curriculum_vitae")
@AllArgsConstructor
@NoArgsConstructor
public class CurriculumVitae {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="cover_letter")
	private String coverLetter;
	
	@OneToOne()
	@JoinColumn(name="user_id")
	private JobSeeker jobSeeker;
	
	@JsonIgnore
	@OneToMany(mappedBy="curriculumVitae")
	private List<SchoolInfo> schoolInfos;
	
	@OneToMany(mappedBy="curriculumVitae")
	@JsonIgnore
	private List<JobExperience> jobExperiences;
	
	@OneToMany(mappedBy="curriculumVitae")
	@JsonIgnore
	private List<ForeignLanguage> languages;
	
	@OneToMany(mappedBy="curriculumVitae")
	@JsonIgnore
	private List<CVLink> cvLinks;

	@OneToMany(mappedBy="curriculumVitae")
	@JsonIgnore
	private List<Ability> abilities;
	
	@OneToOne(mappedBy="curriculumVitae")
	@JsonIgnore
	private CVImage image;
	
	
	
	

}
