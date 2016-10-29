package User_Login.solution1;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

class LoginImpl implements Login {
    private final UserLogin userLogin;
    private final ArrayList<User> users;

    private final HashMap<String, String> resetRequests = new HashMap<>();

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
        if (userLogin.isInvalidPassword(loginName, password, userLogin))
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
        resetRequests.put(key, email);

        userLogin.sendPasswordResetEmail(key);
    }

    @Override
    public void resetPassword(String resetRequestNumber) {
        String email = resetRequests.remove(resetRequestNumber);
        User user = userLogin.getUser(email);
        String password = userLogin.generatePassword(user);
        userLogin.savePassword(user, password);

        userLogin.sendNewPasswordEmail(user.email, password);
    }

    boolean isUserRegistered(String loginName) {
        for (User user : users)
            if ((Objects.equals(user.email, loginName) || Objects.equals(user.nickname, loginName)))
                return true;
        return false;
    }

    private boolean isConfirmedUser(String loginName, UserLogin userLogin) {
        return userLogin.getUser(loginName).confirmed;
    }

}