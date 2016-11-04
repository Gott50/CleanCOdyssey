package User_Login.solution1;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class UserLoginTest {

    private final ArrayList<Integer> registrationEmails = new ArrayList<>();
    private final ArrayList<String> passwordResetEmails = new ArrayList<>();
    private final HashMap<String, String> newPasswordEmails = new HashMap<>();
    private final String TOKEN_WITHOUT_EXPIRATION_DATE = "1586225867!";
    private final String GENERATED_PASSWORD = "1273852453";
    private LocalDateTime dateTime;

    private UserLogin test;

    @Before
    public void setUp() throws Exception {
        dateTime = LocalDateTime.of(2016, 10, 9, 9, 17, 10, 0);

        test = new UserLogin() {
            @Override
            protected @NotNull LocalDateTime generateLocalDateTime() {
                return dateTime;
            }

            @Override
            protected void sendNewPasswordEmail(String email, String password) {
                newPasswordEmails.put(email, password);
            }

            @Override
            protected void sendPasswordResetEmail(String resetRequestNumber) {
                passwordResetEmails.add(resetRequestNumber);
            }

            @Override
            protected void sendRegistrationEmail(int registrationNumber) {

                registrationEmails.add(registrationNumber);
            }

        };
    }

    @Test(expected = UnregisteredUserException.class)
    public void login_GivenNewUser_ReturnsException() throws Exception {
        test.login.login("NewUser", null);
    }

    @Test(expected = Exception.class)
    public void register_GivenNoEmailAddress() throws Exception {
        register(a(user().withEmail("")), "something");
    }

    private void register(UserBuilder.TestUser user, String password) throws Exception {


        test.getRegistration().register(user.email, password, user.nickname);
    }

    @Test
    public void register_GivenAllInformation_SavesRegistration() throws Exception {
        UserBuilder.TestUser user = a(user().withPassword("password4321"));
        register(user);
        assertUser(a(user().withPassword("password4321").withConform(false)), test.userManager.getUsers().get(0));
    }

    @Test(expected = Exception.class)
    public void register_GivenEmailIsTaken_ThrowException() throws Exception {
        register(a(user(0).withEmail("e@mail.de")));
        register(a(user(1).withEmail("e@mail.de")));
    }

    @Test(expected = Exception.class)
    public void register_GivenNicknameIsTaken_ThrowException() throws Exception {
        register(a(user(0).withNickname("one")));
        register(a(user(1).withNickname("one")));
    }

    @Test
    public void register_GeneratesIndividualId() throws Exception {
        register(a(user(0)));
        register(a(user(1)));

        Assert.assertNotEquals(test.userManager.getUsers().get(0).id, test.userManager.getUsers().get(1).id);
        Assert.assertEquals("0", test.userManager.getUsers().get(0).id);
        Assert.assertEquals("1", test.userManager.getUsers().get(1).id);
    }

    private void register(UserBuilder.TestUser user) throws Exception {
        register(user, user.password);
    }

    private void assertUser(UserBuilder.TestUser expected, User user) {
        Assert.assertEquals(expected.email, user.email);
        Assert.assertArrayEquals(Encryption.encryptPassword(expected.password), test.passwordManager.getPassword(user.id));
        Assert.assertEquals(expected.nickname, user.nickname);
        Assert.assertEquals(expected.confirmed, user.confirmed);
        Assert.assertEquals(expected.registrationDate, user.registrationDate);
    }

    @Test
    public void register_GivenNoPassword_GeneratesOne() throws Exception {
        UserBuilder.TestUser user = a(user());
        register(user, "");
        assertUser(a(user().withPassword(GENERATED_PASSWORD)), test.userManager.getUsers().get(0));
    }

    @Test
    public void giveRegister_ThenApplicantReceivesEmailWithRegistrationNumber() throws Exception {
        register(a(user()), "");
        Assert.assertEquals(new Integer(0), registrationEmails.get(0));
    }

    @Test
    public void giveConformRegistration_UserConfirmedIsTrue() throws Exception {
        UserBuilder.TestUser user = makeConfirmedUser(a(user()));
        assertUser(a(user().withConform(true)), test.userManager.getUsers().get(0));
        assertTrue(user.confirmed);
    }

    @Test
    public void loginWithEmail_givenConfirmedUser_ReturnsToken() throws Exception {
        UserBuilder.TestUser user = a(user());
        makeConfirmedUser(user);
        assertLogin(TOKEN_WITHOUT_EXPIRATION_DATE + dateTime.plusDays(1), user);
    }

    @Test
    public void loginWithEmail_givenMultipleConfirmedUser_ReturnsToken() throws Exception {
        UserBuilder.TestUser user = a(user());
        makeConfirmedUser(user);

        makeConfirmedUser(a(user(1)));
        makeConfirmedUser(a(user(2)));
        makeConfirmedUser(a(user(3)));

        assertLogin(TOKEN_WITHOUT_EXPIRATION_DATE + dateTime.plusDays(1), user);
    }

    @Test(expected = Exception.class)
    public void loginWithWrongPassword_givenConfirmedUser_ReturnsException() throws Exception {
        UserBuilder.TestUser user = a(user());
        makeConfirmedUser(user);
        assertLogin(null, a(user().withPassword("WrongPassword")));
    }

    private void assertLogin(String expected, UserBuilder.TestUser user) throws Exception {
        Assert.assertEquals(expected, test.login.login(user.email, user.password));
        Assert.assertEquals(expected, test.login.login(user.nickname, user.password));
    }

    @Test(expected = UnconfirmedUserException.class)
    public void login_GivenUnconfirmedUser_ThrowsException() throws Exception {
        UserBuilder.TestUser user = a(user());
        register(user);
        test.login.login(user.email, user.password);

    }

    @Test
    public void requestPasswordReset_ThenSendEmailWithLink() throws Exception {
        UserBuilder.TestUser user = a(user());
        makeConfirmedUser(user);

        test.login.requestPasswordReset(user.email);
        Assert.assertEquals(user.email, passwordResetEmails.get(0));
    }

    @Test
    public void requestPasswordReset_GivenClickedLink_SendsNewPasswordEmail() throws Exception {
        UserBuilder.TestUser user = a(user());
        makeConfirmedUser(user);

        test.login.requestPasswordReset(user.email);
        String resetRequestNumber = passwordResetEmails.get(0);

        test.login.resetPassword(resetRequestNumber);

        Assert.assertEquals(GENERATED_PASSWORD, newPasswordEmails.get(user.email));
    }

    private UserBuilder.TestUser makeConfirmedUser(UserBuilder.TestUser user) throws Exception {
        register(user);
        int index = user.registrationNumber;
        test.getRegistration().confirm(index + "");
        return user.cloneAttributes(test.userManager.getUsers().get(index));
    }

    private UserBuilder user() {
        return new UserBuilder(dateTime, 0);
    }

    private UserBuilder user(int number) {
        return new UserBuilder(dateTime, number);
    }

    private UserBuilder.TestUser a(UserBuilder builder) {
        return builder.build();
    }

    @Test
    public void currentUser_GivenToken_ReturnsUser() throws Exception {
        assertUser(a(user().withConform(true)), test.administration.currentUser(makeLoginToken()));
    }

    @Test
    public void currentUser_GivenExpiredToken_ReturnNull() throws Exception {
        String token = makeLoginToken();
        dateTime = dateTime.plusDays(2);
        test.setDateTime(dateTime);

        Assert.assertEquals(null, test.administration.currentUser(token));
    }

    @Test(expected = Exception.class)
    public void rename_GivenEmptyStrings_ReturnsException() throws Exception {
        test.administration.rename("UserID", "", "");
    }

    @Test
    public void rename_GivenNewEmail_ChangesEmailAddress() throws Exception {
        User user = test.administration.currentUser(makeLoginToken());
        test.administration.rename(user.id, "newMail@ress.de", "");
        assertUser(a(user().withConform(true).withEmail("newMail@ress.de")), user);
    }

    @Test
    public void rename_GivenNewNickname_ChangesNickname() throws Exception {
        User user = test.administration.currentUser(makeLoginToken());
        test.administration.rename(user.id, "", "newNick'sName");
        assertUser(a(user().withConform(true).withNickname("newNick'sName")), user);
    }

    private String makeLoginToken() throws Exception {
        UserBuilder.TestUser user = makeConfirmedUser(a(user()));
        return test.login.login(user.email, user.password);
    }

    @Test(expected = Exception.class)
    public void changePassword_GivenEmptyString_ReturnsException() throws Exception {
        test.administration.changePassword("UserID", "");
    }

    @Test
    public void changePassword_GivenNewPassword_ChangesPassword() throws Exception {
        User user = test.administration.currentUser(makeLoginToken());
        test.administration.changePassword(user.id, "NewPassword123");

        assertUser(a(user().withConform(true).withPassword("NewPassword123")), user);
    }

    @Test(expected = Exception.class)
    public void delete_GivenEmptyString_ThrowsException() throws Exception {
        User user = test.administration.currentUser(makeLoginToken());
        test.administration.delete(user.id, "");
    }

    @Test(expected = Exception.class)
    public void delete_GivenFalsePassword_ThrowsException() throws Exception {
        User user = test.administration.currentUser(makeLoginToken());
        test.administration.delete(user.id, "FalsePassword");
    }

    @Test
    public void delete_GivenCorrectPassword_deletesUser() throws Exception {
        User user = test.administration.currentUser(makeLoginToken());
        test.administration.delete(user.id, a(user()).password);

        Assert.assertEquals(0, test.userManager.getUsers().size());
    }

    @Test
    public void register_GivenAllData_PasswordsAreStoredInSaltedHashes() throws Exception {
        UserBuilder.TestUser user = makeConfirmedUser(a(user().withPassword("PW1234")));
        byte[] expected = {125, 95, 100, 103, -84, 107, 38, 69, -21, -29, 23, -6, -128, 78, -84, 94, -111, -50, -46, 116, 56, -82, 118, -13, -31, 126, 84, 16, -54, -8, -52, -107};

        Assert.assertArrayEquals(expected, test.passwordManager.getPassword(user.id));
    }

    @Test
    public void register_GivenAllData_UserId() throws Exception {
        UserBuilder.TestUser user = makeConfirmedUser(a(user()));

        Assert.assertEquals("0", test.userManager.getUsers().get(0).id);
        Assert.assertEquals("0", user.id);
    }

    @Test
    public void register_GivenSuccessful_LastLoginDateIsNull() throws Exception {
        UserBuilder.TestUser user = a(user());
        makeConfirmedUser(user);

        assertLastLoginDate(null);
    }

    @Test
    public void login_GivenSuccessful_updateLastLoginDate() throws Exception {
        UserBuilder.TestUser user = a(user());
        makeConfirmedUser(user);

        test.login.login(user.email, user.password);

        assertLastLoginDate(this.dateTime);
    }

    private void assertLastLoginDate(LocalDateTime expected) {
        Assert.assertEquals(expected, test.userManager.getUsers().get(0).lastLoginDate);
    }

    @Test

    public void register_GivenSuccessful_lastUpdatedDateIsNull() throws Exception {
        User user = makeConfirmedUser(a(user()));

        assertLastUpdatedDate(user.registrationDate);
    }

    private void assertLastUpdatedDate(LocalDateTime expected) {
        Assert.assertEquals(expected, test.userManager.getUsers().get(0).lastUpdatedDate);
    }

    @Test
    public void changePassword_GivenSuccessful_updateLastUpdatedDate() throws Exception {
        UserBuilder.TestUser user = a(user());
        makeConfirmedUser(user);
        dateTime = dateTime.plusDays(7);
        test.setDateTime(dateTime);

        test.administration.changePassword(user.email, "1newPassword!");

        assertLastUpdatedDate(dateTime);
    }

    @Test
    public void updateRegistrations_GivenRegistrationOlderThan1Day_DeleteRegistration() throws Exception {
        UserBuilder.TestUser user = a(user());
        register(user);

        dateTime = dateTime.plusDays(7);
        test.setDateTime(dateTime);

        test.updateRegistrations();

        Assert.assertEquals(0, test.userManager.getUsers().size());
    }
    @Test
    public void updateRegistrations_GivenRegistrationIsExpired_DoNothing() throws Exception {
        UserBuilder.TestUser user = a(user());
        register(user);

        test.updateRegistrations();

        Assert.assertEquals(1, test.userManager.getUsers().size());
    }
    @Test
    public void autoUpdateRegistrations_GivenRegister_DeleteExpiredRegistration() throws Exception {
        register(a(user(0)));

        dateTime = dateTime.plusDays(7);
        test.setDateTime(dateTime);

        register(a(user(1)));

        Assert.assertEquals(1, test.userManager.getUsers().size());

    }

}