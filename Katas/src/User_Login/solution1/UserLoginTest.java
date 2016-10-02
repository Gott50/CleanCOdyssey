package User_Login.solution1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserLoginTest {

    private UserLogin test;

    @Before
    public void setUp() throws Exception {
        test = new UserLogin();
    }

    @Test(expected = Exception.class)
    public void login_GivenNewUser_ReturnsException() throws Exception {
        test.login("NewUser", null);
    }

    @Test(expected = Exception.class)
    public void register_GivenNoEmailAddress() throws Exception {
        register(a(user().withEmail("")), "something");
    }

    private void register(User user, String password) throws Exception {
        test.register(user.Email, password, user.Nickname);
    }

    @Test
    public void register_GivenAllInformation_SavesRegistration() throws Exception {
        User user = a(user());
        register(user, "password");
        String expected = "password";
        assertPassword(user, expected);
    }

    private void assertPassword(User user, String expected) {
        Assert.assertArrayEquals(new String[]{user.Email, expected, user.Nickname}, test.getRegistrations().get(0));
    }

    @Test
    public void register_GivenNoPassword_GeneratesOne() throws Exception {
        register(a(user()), "");
        String expected = "Password123";
        assertPassword(a(user()), expected);
    }

    private UserBuilder user() {
        return new UserBuilder();
    }

    private User a(UserBuilder builder) {
        return builder.build();
    }

    private class UserBuilder {

        final User user;

        UserBuilder() {
            this.user = new User();
            user.Email = "some.one@one.com";
            user.Nickname = "someone";
        }

        private User build() {
            return user;
        }

        private UserBuilder withEmail(String email) {
            user.Email = email;
            return this;
        }

    }
}