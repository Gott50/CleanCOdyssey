package MathProblems;

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
    public void given1234567_Returns1111111() throws Exception {
        assertEquals(1111111, CodeCracking.getOriginal(1234567));
    }

    @Test
    public void given8504798_Returns7654321() throws Exception {
        assertEquals(7654321, CodeCracking.getOriginal(8504798));
    }

    @Test
    public void given8504798_ReturnSolution() throws Exception {
        assertEquals(0, CodeCracking.getOriginal(603567764060350L));
    }


}