package antoniocham;

import java.security.MessageDigest;
import java.security.SecureRandom;

public class SecurityUtil {
     // Generate a secure random salt
    public static String generateSalt() {
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[16]; // 128-bit salt
        sr.nextBytes(salt);
        return bytesToHex(salt);
    }

    // Hash password with salt using SHA-256
    public static String hashPassword(String password, String salt) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(hexToBytes(salt)); // include salt
        byte[] hash = md.digest(password.getBytes("UTF-8"));
        return bytesToHex(hash);
    }

    // Convert bytes → hex string
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    // Convert hex string → bytes
    private static byte[] hexToBytes(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4) + Character.digit(hex.charAt(i+1), 16));
        }
        return data;
    }
}
