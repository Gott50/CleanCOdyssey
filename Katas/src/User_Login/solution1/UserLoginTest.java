package User_Login.solution1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class UserLoginTest {

    private final ArrayList<Integer> registrationEmails = new ArrayList<>();
    private UserLogin test;

    @Before
    public void setUp() throws Exception {
        test = new UserLogin() {
            @Override
            void sendRegistrationEmail(int registrationNumber) {
                registrationEmails.add(registrationNumber);
            }
        };
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
        test.register(user.email, password, user.nickname);
    }

    @Test
    public void register_GivenAllInformation_SavesRegistration() throws Exception {
        User user = a(user().withPassword("password4321"));
        register(user);
        assertUser(a(user().withPassword("password4321")));
    }

    private void register(User user) throws Exception {
        register(user, user.password);
    }

    private void assertUser(User user) {
        Assert.assertEquals(user.email, test.getUsers().get(0).email);
        Assert.assertEquals(user.password, test.getUsers().get(0).password);
        Assert.assertEquals(user.nickname, test.getUsers().get(0).nickname);
        Assert.assertEquals(user.confirmed, test.getUsers().get(0).confirmed);
    }

    @Test
    public void register_GivenNoPassword_GeneratesOne() throws Exception {
        register(a(user()), "");
        assertUser(a(user().withPassword("Password123")));
    }

    @Test
    public void giveRegister_ThenApplicantReceivesEmailWithRegistrationNumber() throws Exception {
        register(a(user()), "");
        Assert.assertEquals(new Integer(0), registrationEmails.get(0));
    }

    @Test
    public void giveConformRegistration_UserConfirmedIsTrue() throws Exception {
        register(a(user()));
        test.confirm("0");
        assertUser(a(user().withConform(true)));
    }

    private UserBuilder user() {
        return new UserBuilder();
    }

    private User a(UserBuilder builder) {
        return builder.build();
    }

}