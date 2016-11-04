package User_Login.solution1;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

class RegistrationImpl implements Registration {
    private final UserManager userManager;
    private final UserLogin userLogin;
    private int userCount = 0;
    private PasswordManager passwordManager;
    private LocalDateTime localDateTime;

    RegistrationImpl(UserManager userManager, PasswordManager passwordManager, UserLogin userLogin, LocalDateTime localDateTime) {
        this.userManager = userManager;
        this.passwordManager = passwordManager;
        this.userLogin = userLogin;
        this.localDateTime = localDateTime;
    }

    @Override
    public void register(String email, String password, String nickname) throws Exception {
        if (email.isEmpty())
            throw new RegistrationException("At least you need to give an EmailAddress");
        else if (userManager.isUserRegistered(email))
            throw new RegistrationException("EmailAddress is already taken");
        else if (userManager.isUserRegistered(nickname))
            throw new RegistrationException("Nickname is already taken");


        @NotNull User newUser = buildUser(email, password, nickname, userLogin);
        userManager.getUsers().add(newUser);

        userLogin.sendRegistrationEmail(getRegistrationNumber(newUser));

    }

    @Override
    public void confirm(String registrationNumber) {
        int index = Integer.parseInt(registrationNumber);
        userManager.getUsers().get(index).confirmed = true;
    }

    @NotNull
    private User buildUser(String email, String password, String nickname, UserLogin userLogin) {
        User user = new User();
        user.email = email;
        user.nickname = nickname;
        user.confirmed = false;
        user.registrationDate = localDateTime;
        user.lastUpdatedDate = user.registrationDate;
        user.id = String.valueOf(userCount++);

        if (password.isEmpty()) password = userLogin.passwordManager.generatePassword(user);
        passwordManager.savePassword(user, password);

        return user;
    }

    private int getRegistrationNumber(@NotNull User newUser) {
        return userManager.getUsers().indexOf(newUser);
    }

}