package User_Login.solution1;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

class RegistrationImpl implements Registration {
    private final UserLogin userLogin;
    private final ArrayList<User> users;
    private final int daysTillRegistrationExpires = 1;
    private int userCount = 0;

    RegistrationImpl(ArrayList<User> users, UserLogin userLogin) {
        this.userLogin = userLogin;
        this.users = users;
    }

    @Override
    public void register(String email, String password, String nickname) throws Exception {
        if (email.isEmpty())
            throw new RegistrationException("At least you need to give an EmailAddress");
        else if (userLogin.isUserRegistered(email))
            throw new RegistrationException("EmailAddress is already taken");
        else if (userLogin.isUserRegistered(nickname))
            throw new RegistrationException("Nickname is already taken");


        @NotNull User newUser = buildUser(email, password, nickname, userLogin);
        users.add(newUser);

        userLogin.registration.updateRegistrations(userLogin);
        userLogin.sendRegistrationEmail(getRegistrationNumber(newUser));

    }

    @Override
    public void confirm(String registrationNumber) {
        int index = Integer.parseInt(registrationNumber);
        users.get(index).confirmed = true;
    }

    @NotNull
    private User buildUser(String email, String password, String nickname, UserLogin userLogin) {
        User user = new User();
        user.email = email;
        user.nickname = nickname;
        user.confirmed = false;
        user.registrationDate = userLogin.generateLocalDateTime();
        user.lastUpdatedDate = user.registrationDate;
        user.id = String.valueOf(userCount++);

        if (password.isEmpty()) password = userLogin.generatePassword(user);
        userLogin.savePassword(user, password);

        return user;
    }

    private int getRegistrationNumber(@NotNull User newUser) {
        return users.indexOf(newUser);
    }

    void updateRegistrations(UserLogin userLogin) {
        users.removeIf(user -> !user.confirmed &&
                userLogin.generateLocalDateTime().isAfter(
                        user.registrationDate.plusDays(daysTillRegistrationExpires)));
    }
}