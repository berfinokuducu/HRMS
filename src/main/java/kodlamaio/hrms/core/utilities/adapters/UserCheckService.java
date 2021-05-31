package kodlamaio.hrms.core.utilities.adapters;

public interface UserCheckService {
	boolean checkIfRealPerson(String nationalityId,String firstName,String lastName,int dateOfBirth);
}
