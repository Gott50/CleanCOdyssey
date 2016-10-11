package MathProblems;

import org.junit.Ignore;
import org.junit.Test;

import static org.testng.AssertJUnit.assertEquals;

public class CodeCrackingTest {
    @Test
    public void encrypt_given111_Returns123() throws Exception {
        assertEquals(123, CodeCracking.encrypt(111));
    }

    @Test
    public void encrypt_given1111111_Returns1234567() throws Exception {
        assertEquals(1234567, CodeCracking.encrypt(1111111));
    }

    @Test
    public void shrinkNumber_given111_Returns11() throws Exception {
        assertEquals(12, CodeCracking.subNumber(123));
    }

    @Test
    public void getOriginal_given1234567_Returns1111111() throws Exception {
        assertEquals(1111111, CodeCracking.getOriginal(1234567));
    }

    @Test
    public void getOriginal_given8504798_Returns7654321() throws Exception {
        assertEquals(7654321, CodeCracking.getOriginal(8504798));
    }

    @Test
    @Ignore
    public void getOriginal_given603567764060350L_ReturnSolution() throws Exception {
        assertEquals(0, CodeCracking.getOriginal(603567764060350L));
    }

    @Test
    public void encrypt2_given1111111_Returns2234567() throws Exception {
        assertEquals(2234567, CodeCracking.encrypt2(1111111));
    }

    @Test
    public void getOriginal2_given2234567_Returns111111() throws Exception {
        assertEquals(1111111, CodeCracking.getOriginal2(2234567));
    }

    @Test
    public void getOriginal2_given6133371_ReturnsSolution2() throws Exception {
        assertEquals(1020035, CodeCracking.getOriginal2(6133371));
    }


}