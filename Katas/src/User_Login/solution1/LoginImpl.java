package User_Login.solution1;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

class LoginImpl implements Login {
    private final UserLogin userLogin;
    private final ArrayList<User> users;

    LoginImpl(ArrayList<User> users, UserLogin userLogin) {
        this.userLogin = userLogin;
        this.users = users;
    }

    @Override
    public String login(String loginName, String password) throws Exception {
        if (!isUserRegistered(loginName))
            throw new UnregisteredUserException();
        if (!isConfirmedUser(loginName, userLogin))
            throw new UnconfirmedUserException();
        if (isInvalidPassword(loginName, password, userLogin))
            throw new PasswordInvalidException();

        User user = userLogin.getUser(loginName);
        user.lastLoginDate = userLogin.generateLocalDateTime();

        @NotNull String token = userLogin.generateToken(loginName);
        userLogin.getTokenMap().put(token, user);

        return token;
    }

    @Override
    public boolean isLoginValid(String token) {
        LocalDateTime expirationDate = userLogin.extractDateTime(token);
        @NotNull LocalDateTime now = userLogin.generateLocalDateTime();
        if (now.isAfter(expirationDate)) {
            userLogin.getTokenMap().remove(token);
            return false;
        }
        return userLogin.getTokenMap().containsKey(token);
    }

    @Override
    public void requestPasswordReset(String email) {
        String key = userLogin.generateResetRequestNumber(email);
        userLogin.getResetRequests().put(key, email);

        userLogin.sendPasswordResetEmail(key);
    }

    @Override
    public void resetPassword(String resetRequestNumber) {
        String email = userLogin.getResetRequests().remove(resetRequestNumber);
        User user = userLogin.getUser(email);
        String password = userLogin.generatePassword(user);
        userLogin.savePassword(user, password);

        userLogin.sendNewPasswordEmail(user.email, password);
    }

    public boolean isUserRegistered(String loginName) {
        for (User user : users)
            if ((Objects.equals(user.email, loginName) || Objects.equals(user.nickname, loginName)))
                return true;
        return false;
    }

    private boolean isConfirmedUser(String loginName, UserLogin userLogin) {
        return userLogin.getUser(loginName).confirmed;
    }

    public boolean isInvalidPassword(String loginName, String password, UserLogin userLogin) {
        byte[] pw = userLogin.getPassword(userLogin.getUser(loginName).id);
        return (!Arrays.equals(pw, userLogin.encryptPassword(password)));
    }
}