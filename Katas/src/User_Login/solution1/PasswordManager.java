package User_Login.solution1;

import java.util.Arrays;
import java.util.HashMap;

class PasswordManager {
    private final HashMap<String, byte[]> idPasswordMap = new HashMap<>();

    String generatePassword(User user) {
        String userdata = user.id + (user.email + user.nickname).hashCode() + user.registrationDate;
        return Arrays.toString(Encryption.encryptPassword(userdata)).hashCode() + "";
    }

    void savePassword(User user, String password) {
        idPasswordMap.put(user.id, Encryption.encryptPassword(password));
    }

    byte[] getPassword(String id) {
        return idPasswordMap.get(id);
    }

    boolean isInvalidPassword(String loginName, String password, UserLogin userLogin) {
        byte[] pw = getPassword(userLogin.userManager.getUser(loginName).id);
        return (!Arrays.equals(pw, Encryption.encryptPassword(password)));
    }
}
