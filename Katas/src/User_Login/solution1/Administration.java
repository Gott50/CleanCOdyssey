package User_Login.solution1;

interface Administration {
    User currentUser(String token);

    void rename(String userId, String email, String nickname) throws Exception;

    void changePassword(String userId, String password) throws Exception;

    void delete(String userId, String password);

}
