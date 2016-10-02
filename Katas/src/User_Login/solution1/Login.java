package User_Login.solution1;

interface Login {
    String login(String loginName, String password) throws Exception;

    boolean isLoginValid(String token);

    void requestPasswordReset(String email);

    void resetPassword(String resetRequestNumber);
}
