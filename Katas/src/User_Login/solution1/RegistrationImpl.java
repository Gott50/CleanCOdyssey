package User_Login.solution1;

import org.jetbrains.annotations.NotNull;

class RegistrationImpl implements Registration {
    private final UserLogin userLogin;

    RegistrationImpl(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public void register(String email, String password, String nickname) throws Exception {
        if (email.isEmpty())
            throw new RegistrationException("At least you need to give an EmailAddress");
        else if (userLogin.login.isUserRegistered(email))
            throw new RegistrationException("EmailAddress is already taken");
        else if (userLogin.login.isUserRegistered(nickname))
            throw new RegistrationException("Nickname is already taken");


        @NotNull User newUser = userLogin.buildUser(email, password, nickname);
        userLogin.getUsers().add(newUser);

        userLogin.updateRegistrations();
        userLogin.sendRegistrationEmail(userLogin.getRegistrationNumber(newUser));

    }

    @Override
    public void confirm(String registrationNumber) {
        int index = Integer.parseInt(registrationNumber);
        userLogin.getUsers().get(index).confirmed = true;
    }
}