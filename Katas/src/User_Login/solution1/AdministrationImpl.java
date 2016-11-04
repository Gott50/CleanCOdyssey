package User_Login.solution1;

import java.time.LocalDateTime;
import java.util.HashMap;

class AdministrationImpl implements Administration {
    private final UserManager userManager;
    private final HashMap<String, User> tokenMap;
    private PasswordManager passwordManager;
    private Login login;
    private LocalDateTime localDateTime;

    AdministrationImpl(HashMap<String, User> tokenMap, UserManager userManager, PasswordManager passwordManager, Login login, LocalDateTime localDateTime) {
        this.tokenMap = tokenMap;
        this.login = login;
        this.userManager = userManager;
        this.passwordManager = passwordManager;
        this.localDateTime = localDateTime;
    }

    @Override
    public User currentUser(String token) {
        if (!login.isLoginValid(token)) return null;
        return tokenMap.get(token);
    }

    @Override
    public void rename(String userId, String email, String nickname) throws Exception {
        if (email.isEmpty() && nickname.isEmpty())
            throw new Exception("The new Name must not be Empty");

        User user = userManager.getUser(userId);
        if (!email.isEmpty()) user.email = email;
        if (!nickname.isEmpty()) user.nickname = nickname;

    }

    @Override
    public void changePassword(String userId, String password) throws Exception {
        if (password.isEmpty())
            throw new Exception("The new Password must not be Empty");

        User user = userManager.getUser(userId);
        user.lastUpdatedDate = localDateTime;
        passwordManager.savePassword(user, password);
    }

    @Override
    public void delete(String userId, String password) throws Exception {
        User user = userManager.getUser(userId);
        if (passwordManager.isInvalidPassword(user.email, password))
            throw new Exception("If you want to delete your Account you need to tip in the correct Password");
        else
            userManager.remove(user);
    }
}