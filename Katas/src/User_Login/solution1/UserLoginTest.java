package User_Login.solution1;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class UserLoginTest {

    private final ArrayList<Integer> registrationEmails = new ArrayList<>();
    private final ArrayList<String> passwordResetEmails = new ArrayList<>();
    private final ArrayList<String> newPasswordEmails = new ArrayList<>();
    private final String TOKEN = "-1595444187-1595444187-2029640462!";
    private final String GENERATED_PASSWORD = "some.one@one.comsomeone";
    private LocalDateTime dateTime;

    private UserLogin test;

    @Before
    public void setUp() throws Exception {
        dateTime = LocalDateTime.of(2016, 10, 9, 9, 17, 10, 0);

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

            @Override
            @NotNull LocalDateTime generateLocalDateTime() {
                return dateTime;
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

    private void register(UserBuilder.TestUser user, String password) throws Exception {
        test.register(user.email, password, user.nickname);
    }

    @Test
    public void register_GivenAllInformation_SavesRegistration() throws Exception {
        UserBuilder.TestUser user = a(user().withPassword("password4321"));
        register(user);
        assertUser(a(user().withPassword("password4321")), test.getUsers().get(0));
    }

    @Test(expected = Exception.class)
    public void register_GivenEmailIsTaken_ThrowException() throws Exception {
        UserBuilder.TestUser user1 = a(user().withEmail("e@mail.de"));
        UserBuilder.TestUser user2 = a(user().withEmail("e@mail.de"));
        register(user1);
        register(user2);
    }

    private void register(UserBuilder.TestUser user) throws Exception {
        register(user, user.password);
    }

    private void assertUser(UserBuilder.TestUser expected, User user) {
        Assert.assertEquals(expected.email, user.email);
        Assert.assertEquals(expected.password, test.getPassword(user));
        Assert.assertEquals(expected.nickname, user.nickname);
        Assert.assertEquals(expected.confirmed, user.confirmed);
    }

    @Test
    public void register_GivenNoPassword_GeneratesOne() throws Exception {
        UserBuilder.TestUser user = a(user());
        register(user, "");
        assertUser(a(user().withPassword(GENERATED_PASSWORD)), test.getUsers().get(0));
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
        UserBuilder.TestUser user = a(user());
        makeConfirmedUser(user);
        assertLogin(TOKEN + dateTime.plusDays(1), user);
    }

    @Test(expected = Exception.class)
    public void loginWithWrongPassword_givenConfirmedUser_ReturnsException() throws Exception {
        UserBuilder.TestUser user = a(user());
        makeConfirmedUser(user);
        assertLogin(null, a(user().withPassword("WrongPassword")));
    }

    private void assertLogin(String expected, UserBuilder.TestUser user) throws Exception {
        Assert.assertEquals(expected, test.login(user.email, user.password));
        Assert.assertEquals(expected, test.login(user.nickname, user.password));
    }

    @Test
    public void requestPasswordReset_ThenSendEmailWithLink() throws Exception {
        UserBuilder.TestUser user = a(user());
        makeConfirmedUser(user);
        test.requestPasswordReset(user.email);
        Assert.assertEquals(user.email, passwordResetEmails.get(0));
    }

    @Test
    public void requestPasswordReset_GivenClickedLink_SendsNewPasswordEmail() throws Exception {
        UserBuilder.TestUser user = a(user());
        makeConfirmedUser(user);
        test.requestPasswordReset(user.email);
        String resetRequestNumber = passwordResetEmails.get(0);
        test.resetPassword(resetRequestNumber);

        Assert.assertEquals(GENERATED_PASSWORD, newPasswordEmails.get(0));
    }

    private UserBuilder.TestUser makeConfirmedUser(UserBuilder.TestUser user) throws Exception {
        register(user);
        test.confirm("0");
        return user;
    }

    private UserBuilder user() {
        return new UserBuilder();
    }

    private UserBuilder.TestUser a(UserBuilder builder) {
        return builder.build();
    }

    @Test
    public void currentUser_GivenToken_ReturnsUser() throws Exception {
        assertUser(a(user().withConform(true)), test.currentUser(makeLoginToken()));
    }

    @Test
    public void currentUser_GivenExpiredToken_ReturnNull() throws Exception {
        String token = makeLoginToken();
        dateTime = dateTime.plusDays(2);

        Assert.assertEquals(null, test.currentUser(token));
    }

    @Test(expected = Exception.class)
    public void rename_GivenEmptyStrings_ReturnsException() throws Exception {
        User user = test.currentUser(makeLoginToken());
        test.rename(user.id, "", "");
    }
    @Test
    public void rename_GivenNewEmail_ChangesEmailAddress() throws Exception {
        User user = test.currentUser(makeLoginToken());
        test.rename(user.id, "newMail@ress.de", "");
        assertUser(a(user().withConform(true).withEmail("newMail@ress.de")), user);
    }

    @Test
    public void rename_GivenNewNickname_ChangesNickname() throws Exception {
        User user = test.currentUser(makeLoginToken());
        test.rename(user.id, "", "newNick'sName");
        assertUser(a(user().withConform(true).withNickname("newNick'sName")), user);
    }

    private String makeLoginToken() throws Exception {
        UserBuilder.TestUser user = makeConfirmedUser(a(user()));
        return test.login(user.email, user.password);
    }

    @Test(expected = Exception.class)
    public void changePassword_GivenEmptyString_ReturnsException() throws Exception {
        User user = test.currentUser(makeLoginToken());
        test.changePassword(user.id, "");
    }

    @Test
    public void changePassword_GivenNewPassword_ChangesPassword() throws Exception {
        User user = test.currentUser(makeLoginToken());
        test.changePassword(user.id, "NewPassword123");

        assertUser(a(user().withConform(true).withPassword("NewPassword123")), user);
    }
}