package User_Login.solution1;

import org.jetbrains.annotations.NotNull;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

abstract class UserLogin {
    private final ArrayList<User> users = new ArrayList<>();
    final RegistrationImpl registration = new RegistrationImpl(users, this);
    private final HashMap<String, User> tokenMap = new HashMap<>();
    final AdministrationImpl administration = new AdministrationImpl(users, tokenMap, this);
    final LoginImpl login = new LoginImpl(users, tokenMap, this);

    private final HashMap<String, byte[]> idPasswordMap = new HashMap<>();


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


    @NotNull
    protected LocalDateTime generateLocalDateTime() {
        return LocalDateTime.now();
    }

    protected abstract void sendNewPasswordEmail(String email, String password);

    protected abstract void sendPasswordResetEmail(String resetRequestNumber);

    protected abstract void sendRegistrationEmail(int registrationNumber);

    User getUser(String loginName) {
        int userIndex = getUserIndex(loginName);
        return users.get(userIndex);
    }

    private int getUserIndex(String loginName) {
        for (int i = 0; i < users.size(); i++) {
            if (Objects.equals(users.get(i).id, loginName) || Objects.equals(users.get(i).email, loginName) || Objects.equals(users.get(i).nickname, loginName))
                return i;
        }
        return -1;
    }

    void savePassword(User user, String password) {
        idPasswordMap.put(user.id, encryptPassword(password));
    }

    String generatePassword(User user) {
        String userdata = user.id + (user.email + user.nickname).hashCode() + user.registrationDate;
        return Arrays.toString(UserLogin.encryptPassword(userdata)).hashCode() + "";
    }

    ArrayList<User> getUsers() {
        return users;

    }

    byte[] getPassword(String id) {
        return idPasswordMap.get(id);
    }


    boolean isInvalidPassword(String loginName, String password, UserLogin userLogin) {
        byte[] pw = userLogin.getPassword(userLogin.getUser(loginName).id);
        return (!Arrays.equals(pw, encryptPassword(password)));
    }

    boolean isUserRegistered(String loginName) {
        for (User user : users)
            if ((Objects.equals(user.email, loginName) || Objects.equals(user.nickname, loginName)))
                return true;
        return false;
    }
}
