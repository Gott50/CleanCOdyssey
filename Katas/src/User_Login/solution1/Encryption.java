package User_Login.solution1;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


class Encryption {
    static byte[] encryptPassword(String password) {
        String salt = "42";
        MessageDigest sha256;
        try {
            sha256 = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new byte[0];
        }
        return sha256.digest((password + salt).getBytes());
    }
}
