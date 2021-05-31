package kodlamaio.hrms.core.utilities.adapters;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.mernis.FakeMernisService;

@Service
public class MernisServiceAdapter implements UserCheckService{

	@Override
	public boolean checkIfRealPerson(String nationalityId, String firstName, String lastName, int dateOfBirth) {
		FakeMernisService mernisService=new FakeMernisService();
		return mernisService.TCKimlikNoDogrula(nationalityId, firstName, lastName, dateOfBirth);
	}

}
