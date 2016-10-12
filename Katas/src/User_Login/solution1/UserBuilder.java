package User_Login.solution1;

class UserBuilder {

    private final TestUser user;

    UserBuilder() {
        this.user = new TestUser();
        user.email = "some.one@one.com";
        user.nickname = "someone";
        user.password = "password";
        user.confirmed = false;
    }

    TestUser build() {
        return user;
    }

    UserBuilder withEmail(String email) {
        user.email = email;
        return this;
    }

    UserBuilder withPassword(String password) {
        user.password = password;
        return this;
    }

    UserBuilder withConform(boolean confirmed) {
        user.confirmed = confirmed;
        return this;
    }

    UserBuilder withNickname(String nickname) {
        user.nickname = nickname;
        return this;
    }

    class TestUser extends User {
        String password;
    }
}
