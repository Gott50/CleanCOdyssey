package User_Login.solution1;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.HashMap;

abstract class UserLogin {
    final UserManager userManager = new UserManager();
    final PasswordManager passwordManager = new PasswordManager(userManager);
    private final HashMap<String, User> tokenMap = new HashMap<>();
    private LocalDateTime localDateTime = generateLocalDateTime();
    final Login login = new LoginImpl(userManager, tokenMap, this, localDateTime, passwordManager);
    final Administration administration = new AdministrationImpl(tokenMap, userManager, passwordManager, login, localDateTime);
    private final Registration registration = new RegistrationImpl(userManager, passwordManager, this, localDateTime);

    @NotNull
    protected LocalDateTime generateLocalDateTime() {
        return LocalDateTime.now();
    }

    void setDateTime(LocalDateTime dateTime) {
        localDateTime = dateTime;
    }


    protected abstract void sendNewPasswordEmail(String email, String password);

    protected abstract void sendPasswordResetEmail(String resetRequestNumber);

    protected abstract void sendRegistrationEmail(int registrationNumber);


    void updateRegistrations() {
        final int daysTillRegistrationExpires = 1;
        userManager.getUsers().removeIf(user -> !user.confirmed &&
                localDateTime.isAfter(
                        user.registrationDate.plusDays(daysTillRegistrationExpires)));
    }

    Registration getRegistration() {
        updateRegistrations();
        return registration;
    }
}
