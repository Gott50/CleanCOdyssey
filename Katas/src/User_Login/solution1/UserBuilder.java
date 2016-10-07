package User_Login.solution1;

class UserBuilder {

    private final User user;

    UserBuilder() {
        this.user = new User();
        user.email = "some.one@one.com";
        user.nickname = "someone";
        user.password = "password";
        user.confirmed = false;
    }

    User build() {
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

}
