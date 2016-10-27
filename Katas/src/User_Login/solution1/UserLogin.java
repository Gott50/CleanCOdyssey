package User_Login.solution1;

import org.jetbrains.annotations.NotNull;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

abstract class UserLogin implements Login, Registration, Administration {
    private final Login loginImpl = new LoginImpl(this);
    
    private final ArrayList<User> users = new ArrayList<>();
    private final HashMap<String, String> resetRequests = new HashMap<>();
    private final HashMap<String, byte[]> idPasswordMap = new HashMap<>();
    private final HashMap<String, User> tokenMap = new HashMap<>();
    private final int daysTillRegistrationExpires = 1;
    private int userCount = 0;

    @Override
    public String login(String loginName, String password) throws Exception {


        return loginImpl.login(loginName, password);
    }

    boolean isConfirmedUser(String loginName) {
        return getUser(loginName).confirmed;
    }

    boolean isInvalidPassword(String loginName, String password) {
        byte[] pw = getPassword(getUser(loginName).id);
        return (!Arrays.equals(pw, encryptPassword(password)));
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

    boolean isUserRegistered(String loginName) {
        for (User user : users)
            if ((Objects.equals(user.email, loginName) || Objects.equals(user.nickname, loginName)))//&& user.confirmed)
                return true;

        return false;
    }

    @Override
    public boolean isLoginValid(String token) {
        return loginImpl.isLoginValid(token);
    }

    LocalDateTime extractDateTime(String token) {
        return LocalDateTime.parse(token.split("!")[1]);
    }

    @Override
    public void requestPasswordReset(String email) {

        loginImpl.requestPasswordReset(email);
    }

    String generateResetRequestNumber(String email) {
        return email;
    }


    protected abstract void sendNewPasswordEmail(String email, String password);

    protected abstract void sendPasswordResetEmail(String resetRequestNumber);

    @Override
    public void resetPassword(String resetRequestNumber) {

        loginImpl.resetPassword(resetRequestNumber);
    }

    User getUser(String loginName) {
        int userIndex = getUserIndex(loginName);
        return users.get(userIndex);
    }

    void savePassword(User user, String password) {

        idPasswordMap.put(user.id, encryptPassword(password));
    }

    byte[] encryptPassword(String password) {
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

    @Override
    public void register(String email, String password, String nickname) throws Exception {
        if (email.isEmpty())
            throw new RegistrationException("At least you need to give an EmailAddress");
        else if (isUserRegistered(email))
            throw new RegistrationException("EmailAddress is already taken");
        else if (isUserRegistered(nickname))
            throw new RegistrationException("Nickname is already taken");


        @NotNull User newUser = buildUser(email, password, nickname);
        users.add(newUser);

        updateRegistrations();
        sendRegistrationEmail(getRegistrationNumber(newUser));

    }

    abstract void sendRegistrationEmail(int registrationNumber);

    private int getRegistrationNumber(@NotNull User newUser) {
        return users.indexOf(newUser);
    }

    @NotNull
    private User buildUser(String email, String password, String nickname) {
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
        return Arrays.toString(encryptPassword(userdata)).hashCode() + "";
    }

    @Override
    public void confirm(String registrationNumber) {
        int index = Integer.parseInt(registrationNumber);
        users.get(index).confirmed = true;
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

    @Override
    public User currentUser(String token) {
        if (!loginImpl.isLoginValid(token)) return null;
        return tokenMap.get(token);
    }

    @Override
    public void rename(String userId, String email, String nickname) throws Exception {
        if (email.isEmpty() && nickname.isEmpty())
            throw new Exception("The new Name must not be Empty");

        User user = getUser(userId);
        if (!email.isEmpty()) user.email = email;
        if (!nickname.isEmpty()) user.nickname = nickname;

    }

    @Override
    public void changePassword(String userId, String password) throws Exception {
        if (password.isEmpty())
            throw new Exception("The new Password must not be Empty");

        User user = getUser(userId);
        user.lastUpdatedDate = generateLocalDateTime();
        savePassword(user, password);
    }

    @Override
    public void delete(String userId, String password) throws Exception {
        User user = getUser(userId);
        if (isInvalidPassword(user.email, password))
            throw new Exception("If you want to delete your Account you need to tip in the correct Password");
        else
            users.remove(user);
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

    HashMap<String, String> getResetRequests() {
        return resetRequests;
    }
}
