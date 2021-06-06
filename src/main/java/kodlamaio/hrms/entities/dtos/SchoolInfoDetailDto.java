package kodlamaio.hrms.entities.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolInfoDetailDto {
	private String name;
	private String department;
	private LocalDate startDate;
	private LocalDate graduationDate;
}
