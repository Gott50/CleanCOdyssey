package User_Login.solution1;

import java.util.ArrayList;

class UserLogin implements Login, Registration {
    private final ArrayList<String[]> registrations = new ArrayList<>();

    @Override
    public String login(String loginName, String password) throws Exception {
        throw new Exception("New users need to register first");
    }

    @Override
    public boolean isLoginValid(String token) {
        return false;
    }

    @Override
    public void requestPasswordReset(String email) {

    }

    @Override
    public void resetPassword(String resetRequestNumber) {

    }

    @Override
    public void register(String email, String password, String nickname) throws Exception {
        if (email.isEmpty())
            throw new Exception("At least you need to give an EmailAddress");

        if (password.isEmpty()) password = generatePassword();

        registrations.add(new String[]{email, password, nickname});
    }

    private String generatePassword() {
        return "Password123";
    }

    @Override
    public void confirm(String registrationNumber) {

    }

    ArrayList<String[]> getRegistrations() {
        return registrations;

    }
}
