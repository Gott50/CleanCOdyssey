package User_Login.solution1;

class UserLogin implements Login, Registration {
    @Override
    public String login(String loginName, String password) throws Exception {
        throw new Exception("New users need to register first");
    }

    @Override
    public boolean isLoginvalid(String token) {
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
        throw new Exception("At least you need to give an EmailAddress");
    }

    @Override
    public void confirm(String registrationNumber) {

    }
}
