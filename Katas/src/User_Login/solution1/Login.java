package User_Login.solution1;

interface Login {
    String login(String loginName, String password) throws Exception;

    boolean isLoginvalid(String token);

    void requestPasswordReset(String email);

    void resetPassword(String resetRequestNumber);
}
