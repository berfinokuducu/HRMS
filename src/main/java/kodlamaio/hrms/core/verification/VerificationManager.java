package kodlamaio.hrms.core.verification;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class VerificationManager implements VerificationService{

	@Override
	public String sendActivationCode() {
		String alphaNumeric  = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"+"abcdefghijklmnopqrstuvwxyz"+"0123456789";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		int length = 5;
		for(int i = 0; i < length; i++) {
			int index = random.nextInt(alphaNumeric.length());
			char randomChar = alphaNumeric.charAt(index);
			sb.append(randomChar);
		    }
		    String code = sb.toString();
		
		return code;
	}

	@Override
	public void hrmsVerification() {
		System.out.println("Sistem tarafından doğrulama başarılı");
		
	}

}
