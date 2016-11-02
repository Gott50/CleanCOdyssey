package User_Login.solution1;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

abstract class UserLogin {
    final PasswordManager passwordManager = new PasswordManager();
    private final ArrayList<User> users = new ArrayList<>();
    final UserManager userManager = new UserManager(users);
    private final HashMap<String, User> tokenMap = new HashMap<>();
    final Administration administration = new AdministrationImpl(userManager, tokenMap, this, passwordManager);
    final Login login = new LoginImpl(userManager, tokenMap, this);
    private final Registration registration = new RegistrationImpl(userManager, passwordManager, this);

    @NotNull
    protected LocalDateTime generateLocalDateTime() {
        return LocalDateTime.now();
    }

    protected abstract void sendNewPasswordEmail(String email, String password);

    protected abstract void sendPasswordResetEmail(String resetRequestNumber);

    protected abstract void sendRegistrationEmail(int registrationNumber);


    void updateRegistrations() {
        final int daysTillRegistrationExpires = 1;
        userManager.getUsers().removeIf(user -> !user.confirmed &&
                generateLocalDateTime().isAfter(
                        user.registrationDate.plusDays(daysTillRegistrationExpires)));
    }

    Registration getRegistration() {
        updateRegistrations();
        return registration;
    }
}
