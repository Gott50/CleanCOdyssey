package User_Login.solution1;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class LoginImpl implements Login {
    private final UserLogin userLogin;
    private final ArrayList<User> users;

    private final HashMap<String, String> resetRequests = new HashMap<>();
    private final HashMap<String, User> tokenMap;

    LoginImpl(ArrayList<User> users, HashMap<String, User> tokenMap, UserLogin userLogin) {
        this.tokenMap = tokenMap;
        this.userLogin = userLogin;
        this.users = users;
    }

    @Override
    public String login(String loginName, String password) throws Exception {
        if (!userLogin.userManager.isUserRegistered(loginName))
            throw new UnregisteredUserException();
        if (!isConfirmedUser(loginName, userLogin))
            throw new UnconfirmedUserException();
        if (userLogin.passwordManager.isInvalidPassword(loginName, password, userLogin))
            throw new PasswordInvalidException();

        User user = userLogin.userManager.getUser(loginName);
        user.lastLoginDate = userLogin.generateLocalDateTime();

        @NotNull String token = generateToken(loginName);
        tokenMap.put(token, user);

        return token;
    }

    @Override
    public boolean isLoginValid(String token) {
        LocalDateTime expirationDate = extractDateTime(token);
        @NotNull LocalDateTime now = userLogin.generateLocalDateTime();
        if (now.isAfter(expirationDate)) {
            tokenMap.remove(token);
            return false;
        }
        return tokenMap.containsKey(token);
    }

    @Override
    public void requestPasswordReset(String email) {
        String key = generateResetRequestNumber(email);
        resetRequests.put(key, email);

        userLogin.sendPasswordResetEmail(key);
    }

    @Override
    public void resetPassword(String resetRequestNumber) {
        String email = resetRequests.remove(resetRequestNumber);
        User user = userLogin.userManager.getUser(email);
        String password = userLogin.passwordManager.generatePassword(user);
        userLogin.passwordManager.savePassword(user, password);

        userLogin.sendNewPasswordEmail(user.email, password);
    }


    private boolean isConfirmedUser(String loginName, UserLogin userLogin) {
        return userLogin.userManager.getUser(loginName).confirmed;
    }

    @NotNull
    private String generateToken(String loginName) {
        User user = userLogin.userManager.getUser(loginName);
        LocalDateTime expirationDate = userLogin.generateLocalDateTime().plusDays(1);

        String userdata = user.id.hashCode() + "" +
                user.email.hashCode() + "" +
                user.nickname.hashCode() + "" +
                user.lastLoginDate.hashCode() + "" +
                user.lastUpdatedDate.hashCode() + "";
        return
                Arrays.toString(Encryption.encryptPassword(userdata)).hashCode() +
                        "!" + expirationDate;
    }

    private LocalDateTime extractDateTime(String token) {
        return LocalDateTime.parse(token.split("!")[1]);
    }

    private String generateResetRequestNumber(String email) {
        //TODO
        return email;
    }

}