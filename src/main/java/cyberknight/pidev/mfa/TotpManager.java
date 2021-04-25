package cyberknight.pidev.mfa;

public interface TotpManager {
	  String generateSecret();
	  String getUriForImage(String secret);
	  boolean verifyCode(String code, String secret);
	}
