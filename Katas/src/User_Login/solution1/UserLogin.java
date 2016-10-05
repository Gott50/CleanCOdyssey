package User_Login.solution1;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

abstract class UserLogin implements Login, Registration {
    private final ArrayList<User> users = new ArrayList<>();

    @Override
    public String login(String loginName, String password) throws Exception {
        if (!isUserRegistered(loginName))
        throw new Exception("New users need to register first");
        return generateToken();
    }

    @NotNull
    private String generateToken() {
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
        sendPasswordResetEmail(email);
    }

    protected abstract void sendPasswordResetEmail(String email);

    @Override
    public void resetPassword(String resetRequestNumber) {

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
}
