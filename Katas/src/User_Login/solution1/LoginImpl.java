package User_Login.solution1;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

class LoginImpl implements Login {
    private final UserLogin userLogin;

    LoginImpl(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public String login(String loginName, String password) throws Exception {
        if (!userLogin.isUserRegistered(loginName))
            throw new UnregisteredUserException();
        if (!userLogin.isConfirmedUser(loginName))
            throw new UnconfirmedUserException();
        if (userLogin.isInvalidPassword(loginName, password))
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
}