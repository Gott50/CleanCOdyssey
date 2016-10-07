package User_Login.solution1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

public class UserLoginTest {

    private final ArrayList<Integer> registrationEmails = new ArrayList<>();
    private final ArrayList<String> passwordResetEmails = new ArrayList<>();
    private final ArrayList<String> newPasswordEmails = new ArrayList<>();
    private final String TOKEN = "Token";
    private UserLogin test;

    @Before
    public void setUp() throws Exception {
        test = new UserLogin() {


            @Override
            protected void sendNewPasswordEmail(String email, String password) {
                newPasswordEmails.add(password);
            }

            @Override
            protected void sendPasswordResetEmail(String resetRequestNumber) {
                passwordResetEmails.add(resetRequestNumber);
            }

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
        assertUser(a(user().withPassword("password4321")), test.getUsers().get(0));
    }

    private void register(User user) throws Exception {
        register(user, user.password);
    }

    private void assertUser(User expected, User user) {
        Assert.assertEquals(expected.email, user.email);
        Assert.assertEquals(expected.password, user.password);
        Assert.assertEquals(expected.nickname, user.nickname);
        Assert.assertEquals(expected.confirmed, user.confirmed);
    }

    @Test
    public void register_GivenNoPassword_GeneratesOne() throws Exception {
        register(a(user()), "");
        assertUser(a(user().withPassword("Password123")), test.getUsers().get(0));
    }

    @Test
    public void giveRegister_ThenApplicantReceivesEmailWithRegistrationNumber() throws Exception {
        register(a(user()), "");
        Assert.assertEquals(new Integer(0), registrationEmails.get(0));
    }

    @Test
    public void giveConformRegistration_UserConfirmedIsTrue() throws Exception {
        makeConfirmedUser(a(user()));
        assertUser(a(user().withConform(true)), test.getUsers().get(0));
    }

    @Test
    public void loginWithEmail_givenConfirmedUser_ReturnsToken() throws Exception {
        User user = a(user());
        makeConfirmedUser(user);
        assertLogin(TOKEN, user);
    }

    private void assertLogin(String expected, User user) throws Exception {
        Assert.assertEquals(expected, test.login(user.email, user.password));
        Assert.assertEquals(expected, test.login(user.nickname, user.password));
    }

    @Test
    public void requestPasswordReset_ThenSendEmailWithLink() throws Exception {
        User user = a(user());
        makeConfirmedUser(user);
        test.requestPasswordReset(user.email);
        Assert.assertEquals(user.email, passwordResetEmails.get(0));
    }

    @Test
    public void requestPasswordReset_GivenClickedLink_SendsNewPasswordEmail() throws Exception {
        User user = a(user());
        makeConfirmedUser(user);
        test.requestPasswordReset(user.email);
        String resetRequestNumber = passwordResetEmails.get(0);
        test.resetPassword(resetRequestNumber);

        Assert.assertEquals("Password123", newPasswordEmails.get(0));
    }

    private User makeConfirmedUser(User user) throws Exception {
        register(user);
        test.confirm("0");
        return user;
    }

    private UserBuilder user() {
        return new UserBuilder();
    }

    private User a(UserBuilder builder) {
        return builder.build();
    }

    @Test
    @Ignore
    public void currentUser_GivenToken_ReturnsUser() throws Exception {
        User user = makeConfirmedUser(a(user()));
        String token = test.login(user.email, user.password);

        assertUser(user, test.currentUser(token));
    }
}