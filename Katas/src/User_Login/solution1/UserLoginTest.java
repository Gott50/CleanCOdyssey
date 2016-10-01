package User_Login.solution1;

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
        test.register("", "something", "somewhere");
    }

    @Test
    public void register_GivenNoPassword_GeneratesOne() throws Exception {
        test.register("some.one@one.com", "", "somewhere");
        //Assert.assertEquals(test.getRegestrations());
    }

    //private class UserBuilder
}