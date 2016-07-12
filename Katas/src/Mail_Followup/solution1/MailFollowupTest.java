package Mail_Followup.solution1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class MailFollowupTest {
    private static Date now = new Date(2016, 1, 2, 3, 4, 5);
    private final String emailAddress;
    private final Date expected;

    public MailFollowupTest(Date now, String emailAddress, Date expected) {
        MailFollowupTest.now = now;
        this.emailAddress = emailAddress;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {now, "", now.clone()},
                {now, "7days@followup.cc", new Date(2016, 1, 2 + 7, 3, 4, 5)},
                {now, "12hours@followup.cc", new Date(2016, 1, 2, 3 + 12, 4, 5)},
                {now, "1week3days5hours@followup.cc", new Date(2016, 1, 2 + 7 + 3, 3 + 5, 4, 5)},
                {now, "aug15-9am@followup.cc", new Date(2016, 8, 15, 9, 0, 0)},
                {new Date(2016, 8, 16, 9, 0, 0), "aug15-9am@followup.cc", new Date(2017, 8, 15, 9, 0, 0)},
                {now, "aug15-9pm@followup.cc", new Date(2016, 8, 15, 9 + 12, 0, 0)},
                {new Date(2013, 2, 4, 10, 30, 0), "2weeks1day1hour@followup.cc", new Date(2013, 2, 19, 11, 30, 0)},
        });
    }

    @Test
    public void pointInTime() {
        assertEquals(expected, MailFollowup.pointInTime(now, emailAddress));
    }
}