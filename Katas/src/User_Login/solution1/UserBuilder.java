package User_Login.solution1;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

class UserBuilder {

    private final TestUser user;
    private final LocalDateTime date;

    UserBuilder(LocalDateTime date, int number) {
        this.date = date;
        this.user = new TestUser();

        String[] userData = getUserData(number);

        user.email = userData[0];
        user.nickname = userData[1];
        user.password = userData[2];
        user.confirmed = false;
        user.registrationDate = this.date;
        user.registrationNumber = number;
    }

    @NotNull
    private String[] getUserData(int number) {
        return new String[]{"some.one" + number + "@one.com",
                "someone" + number, "Password" + number};
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
        int registrationNumber;
        String password;

        TestUser cloneAttributes(User user) {
            this.confirmed = user.confirmed;
            this.id = user.id;
            return this;
        }
    }
}
