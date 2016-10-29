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
    final Registration registration = new RegistrationImpl(this);
    private final ArrayList<User> users = new ArrayList<>();
    final Administration administration = new AdministrationImpl(users, this);
    final LoginImpl login = new LoginImpl(users, this);

    private final HashMap<String, byte[]> idPasswordMap = new HashMap<>();
    private final HashMap<String, User> tokenMap = new HashMap<>();
    private final int daysTillRegistrationExpires = 1;
    private int userCount = 0;

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

    @NotNull String generateToken(String loginName) {
        User user = getUser(loginName);
        LocalDateTime expirationDate = generateLocalDateTime().plusDays(1);

        String userdata = user.id.hashCode() + "" +
                user.email.hashCode() + "" +
                user.nickname.hashCode() + "" +
                user.lastLoginDate.hashCode() + "" +
                user.lastUpdatedDate.hashCode() + "";
        return
                Arrays.toString(encryptPassword(userdata)).hashCode() +
                        "!" + expirationDate;
    }

    @NotNull
    LocalDateTime generateLocalDateTime() {
        return LocalDateTime.now();
    }

    LocalDateTime extractDateTime(String token) {
        return LocalDateTime.parse(token.split("!")[1]);
    }

    String generateResetRequestNumber(String email) {
        return email;
    }

    protected abstract void sendNewPasswordEmail(String email, String password);

    protected abstract void sendPasswordResetEmail(String resetRequestNumber);

    User getUser(String loginName) {
        int userIndex = getUserIndex(loginName);
        return users.get(userIndex);
    }

    void savePassword(User user, String password) {
        idPasswordMap.put(user.id, encryptPassword(password));
    }

    abstract void sendRegistrationEmail(int registrationNumber);

    int getRegistrationNumber(@NotNull User newUser) {
        return users.indexOf(newUser);
    }

    @NotNull User buildUser(String email, String password, String nickname) {
        User user = new User();
        user.email = email;
        user.nickname = nickname;
        user.confirmed = false;
        user.registrationDate = generateLocalDateTime();
        user.lastUpdatedDate = user.registrationDate;
        user.id = String.valueOf(userCount++);

        if (password.isEmpty()) password = generatePassword(user);
        savePassword(user, password);

        return user;
    }

    String generatePassword(User user) {
        String userdata = user.id + (user.email + user.nickname).hashCode() + user.registrationDate;
        return Arrays.toString(UserLogin.encryptPassword(userdata)).hashCode() + "";
    }

    ArrayList<User> getUsers() {
        return users;

    }

    private int getUserIndex(String loginName) {
        for (int i = 0; i < users.size(); i++) {
            if (Objects.equals(users.get(i).id, loginName) || Objects.equals(users.get(i).email, loginName) || Objects.equals(users.get(i).nickname, loginName))
                return i;
        }
        return -1;
    }

    byte[] getPassword(String id) {
        return idPasswordMap.get(id);
    }

    void updateRegistrations() {
        users.removeIf(user -> !user.confirmed &&
                generateLocalDateTime().isAfter(
                        user.registrationDate.plusDays(daysTillRegistrationExpires)));
    }

    HashMap<String, User> getTokenMap() {
        return tokenMap;
    }

    Login getLogin() {
        return login;
    }


    boolean isInvalidPassword(String loginName, String password, UserLogin userLogin) {
        byte[] pw = userLogin.getPassword(userLogin.getUser(loginName).id);
        return (!Arrays.equals(pw, encryptPassword(password)));
    }
}
