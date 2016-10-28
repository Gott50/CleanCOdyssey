package User_Login.solution1;

class AdministrationImpl implements Administration {
    private final UserLogin userLogin;

    AdministrationImpl(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public User currentUser(String token) {
        if (!userLogin.getLogin().isLoginValid(token)) return null;
        return userLogin.getTokenMap().get(token);
    }

    @Override
    public void rename(String userId, String email, String nickname) throws Exception {
        if (email.isEmpty() && nickname.isEmpty())
            throw new Exception("The new Name must not be Empty");

        User user = userLogin.getUser(userId);
        if (!email.isEmpty()) user.email = email;
        if (!nickname.isEmpty()) user.nickname = nickname;

    }

    @Override
    public void changePassword(String userId, String password) throws Exception {
        if (password.isEmpty())
            throw new Exception("The new Password must not be Empty");

        User user = userLogin.getUser(userId);
        user.lastUpdatedDate = userLogin.generateLocalDateTime();
        userLogin.savePassword(user, password);
    }

    @Override
    public void delete(String userId, String password) throws Exception {
        User user = userLogin.getUser(userId);
        if (userLogin.login.isInvalidPassword(user.email, password, userLogin))
            throw new Exception("If you want to delete your Account you need to tip in the correct Password");
        else
            userLogin.getUsers().remove(user);
    }
}