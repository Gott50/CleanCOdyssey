package User_Login.solution1;

interface Registration {
    void register(String email, String password, String nickname) throws Exception;

    void confirm(String registrationNumber);
}
