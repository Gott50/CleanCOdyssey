package User_Login.solution1;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

abstract class UserLogin {
    final PasswordManager passwordManager = new PasswordManager();
    private final ArrayList<User> users = new ArrayList<>();
    final UserManager userManager = new UserManager(users);
    final RegistrationImpl registration = new RegistrationImpl(userManager, users, this);
    private final HashMap<String, User> tokenMap = new HashMap<>();
    final Administration administration = new AdministrationImpl(users, tokenMap, this);
    final Login login = new LoginImpl(users, tokenMap, this);


    @NotNull
    protected LocalDateTime generateLocalDateTime() {
        return LocalDateTime.now();
    }

    protected abstract void sendNewPasswordEmail(String email, String password);

    protected abstract void sendPasswordResetEmail(String resetRequestNumber);

    protected abstract void sendRegistrationEmail(int registrationNumber);


}
