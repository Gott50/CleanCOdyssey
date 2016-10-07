package User_Login.solution1;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

abstract class UserLogin implements Login, Registration, Administration {
    private final ArrayList<User> users = new ArrayList<>();
    private final HashMap<String, String> resetRequests = new HashMap<>();

    @Override
    public String login(String loginName, String password) throws Exception {
        if (!isUserRegistered(loginName))
            throw new Exception("New users need to register first");
        return generateToken(loginName);
    }

    @NotNull
    private String generateToken(String loginName) {
        //TODO make opaque to clients
        //TODO tokens should have an expiration date
        return "Token";
    }

    private boolean isUserRegistered(String loginName) {
        for (User user : users) {
            if (Objects.equals(user.email, loginName) || Objects.equals(user.nickname, loginName) && user.confirmed)
                return true;
        }
        return false;
    }

    @Override
    public boolean isLoginValid(String token) {
        return false;
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
        String email = resetRequests.get(resetRequestNumber);
        int userIndex = getUserIndex(email);
        User user = users.get(userIndex);
        user.password = generatePassword();

        sendNewPasswordEmail(user.email, user.password);
    }

    @Override
    public void register(String email, String password, String nickname) throws Exception {
        if (email.isEmpty())
            throw new Exception("At least you need to give an EmailAddress");

        if (password.isEmpty()) password = generatePassword();

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
        user.email = email;
        user.nickname = nickname;
        user.password = password;
        user.confirmed = false;
        return user;
    }

    private String generatePassword() {
        //TODO make it stronger

        return "Password123";
    }

    @Override
    public void confirm(String registrationNumber) {
        int index = Integer.parseInt(registrationNumber);
        users.get(index).confirmed = true;

    }

    ArrayList<User> getUsers() {
        return users;

    }

    private int getUserIndex(String email) {
        for (int i = 0; i < users.size(); i++) {
            if (Objects.equals(users.get(i).email, email)) return i;
        }
        return -1;
    }

    @Override
    public User currentUser(String token) {
        return null;
    }

    @Override
    public void rename(String userId, String email, String nickname) {

    }

    @Override
    public void changePassword(String userId, String password) {

    }

    @Override
    public void delete(String userId, String password) {

    }
}
