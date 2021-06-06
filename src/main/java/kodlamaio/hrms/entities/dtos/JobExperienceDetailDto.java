package kodlamaio.hrms.entities.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobExperienceDetailDto {
	private String workplaceName;
	private String position;
	private LocalDate dateOfStart;
	private LocalDate dateOfEnd;
}
