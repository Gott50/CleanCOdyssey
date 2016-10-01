package User_Login.solution1;

public interface Administration {
    User currentUser(String token);

    void rename(String userId, String email, String nickname);

    void changePassword(String userId, String password);

    void delete(String userId, String password);

}
