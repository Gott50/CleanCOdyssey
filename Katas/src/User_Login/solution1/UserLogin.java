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
    private final ArrayList<User> users = new ArrayList<>();
    private final HashMap<String, String> resetRequests = new HashMap<>();
    private final HashMap<String, byte[]> idPasswordMap = new HashMap<>();
    private final HashMap<String, User> tokenMap = new HashMap<>();

    @Override
    public String login(String loginName, String password) throws Exception {
        //TODO throw different Exceptions
        if (!isUserRegistered(loginName))
            throw new Exception("New users need to register first");
        if (!isConfirmedUser(loginName))
            throw new UserConformationException("User is not yet confirmed");
        if (!isPasswordValid(loginName, password))
            throw new PasswordInvalidException("Password is not Valid!");

        @NotNull String token = generateToken(loginName);
        tokenMap.put(token, getUser(loginName));

        return token;
    }

    private boolean isConfirmedUser(String loginName) {
        return getUser(loginName).confirmed;
    }

    private boolean isPasswordValid(String loginName, String password) {
        byte[] pw = getPassword(getUser(loginName).id);
        return (Arrays.equals(pw, encryptPassword(password)));
    }

    @NotNull
    private String generateToken(String loginName) {
        User user = getUser(loginName);
        LocalDateTime expirationDate = generateLocalDateTime().plusDays(1);

        return
                user.id.hashCode() + "" +
                        user.email.hashCode() + "" +
                        user.nickname.hashCode() + "" +
                        "!" + expirationDate;
    }

    @NotNull
    LocalDateTime generateLocalDateTime() {
        return LocalDateTime.now();
    }

    private boolean isUserRegistered(String loginName) {
        for (User user : users)
            if ((Objects.equals(user.email, loginName) || Objects.equals(user.nickname, loginName)))//&& user.confirmed)
                return true;

        return false;
    }

    @Override
    public boolean isLoginValid(String token) {
        LocalDateTime expirationDate = extractDateTime(token);
        @NotNull LocalDateTime now = generateLocalDateTime();
        if (now.isAfter(expirationDate)) {
            tokenMap.remove(token);
            return false;
        }
        return tokenMap.containsKey(token);
    }

    private LocalDateTime extractDateTime(String token) {
        return LocalDateTime.parse(token.split("!")[1]);
    }

    @Override
    public void requestPasswordReset(String email) {
        String key = generateResetRequestNumber(email);
        resetRequests.put(key, email);

        sendPasswordResetEmail(key);
    }

    private String generateResetRequestNumber(String email) {
        return email;
    }


    protected abstract void sendNewPasswordEmail(String email, String password);

    protected abstract void sendPasswordResetEmail(String resetRequestNumber);

    @Override
    public void resetPassword(String resetRequestNumber) {
        String email = resetRequests.remove(resetRequestNumber);
        User user = getUser(email);
        String password = generatePassword(user.email, user.nickname);
        savePassword(user, password);

        sendNewPasswordEmail(user.email, password);
    }

    private User getUser(String loginName) {
        int userIndex = getUserIndex(loginName);
        return users.get(userIndex);
    }

    private void savePassword(User user, String password) {
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
            throw new Exception("At least you need to give an EmailAddress");
        else if (isUserRegistered(email))
            throw new Exception("EmailAddress is already taken");
        else if (isUserRegistered(nickname))
            throw new Exception("Nickname is already taken");

        if (password.isEmpty()) password = generatePassword(email, nickname);

        @NotNull User newUser = buildUser(email, password, nickname);
        users.add(newUser);

        sendRegistrationEmail(getRegistrationNumber(newUser));
    }

    abstract void sendRegistrationEmail(int registrationNumber);

    private int getRegistrationNumber(@NotNull User newUser) {
        return users.indexOf(newUser);
    }

    @NotNull
    private User buildUser(String email, String password, String nickname) {
        User user = new User();
        user.id = email; //TODO should be an individual String
        user.email = email;
        user.nickname = nickname;
        savePassword(user, password);
        user.confirmed = false;
        user.registrationDate = generateLocalDateTime();
        return user;
    }

    private String generatePassword(String email, String nickname) {
        return email + nickname;
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
            if (Objects.equals(users.get(i).email, loginName) || Objects.equals(users.get(i).nickname, loginName))
                return i;
        }
        return -1;
    }

    @Override
    public User currentUser(String token) {
        if (!isLoginValid(token)) return null;
        return tokenMap.get(token);
    }

    @Override
    public void rename(String userId, String email, String nickname) throws Exception {
        if (email.isEmpty() && nickname.isEmpty())
            throw new Exception("The new Name must not be Empty");

        User user = getUser(userId);//TODO
        if (!email.isEmpty()) user.email = email;
        if (!nickname.isEmpty()) user.nickname = nickname;

    }

    @Override
    public void changePassword(String userId, String password) throws Exception {
        if (password.isEmpty())
            throw new Exception("The new Password must not be Empty");
        else savePassword(getUser(userId), password);
    }

    @Override
    public void delete(String userId, String password) throws Exception {
        User user = getUser(userId);
        if (!isPasswordValid(user.email, password))
            throw new Exception("If you want to delete your Account you need to tip in the correct Password");
        else
            users.remove(user);
    }

    byte[] getPassword(String id) {
        return idPasswordMap.get(id);
    }
}
