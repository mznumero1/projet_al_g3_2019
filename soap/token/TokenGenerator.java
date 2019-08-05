package token;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class TokenGenerator {
	private static final String ALGO = "AES";
    private static final byte[] keyValue = new byte[] { 'M', 'Z', 'E', 'R', 'B', 'O', 'D', 'I', 'C', 'e', 's', 'p', '2', '0', '1', '9'};
    
    public static String encrypt(String data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encVal);
    }

    public static String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = Base64.getDecoder().decode(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        return new String(decValue);
    }
    private static Key generateKey() throws Exception {
        return new SecretKeySpec(keyValue, ALGO);
}
}
