package MathProblems;

import org.junit.Test;
import org.testng.Assert;

import static org.testng.AssertJUnit.assertEquals;

public class MathProblemSolutionTest {
    @Test
    public void gererateNumbers_Returns142857() throws Exception {
        assertEquals(new Integer(142857), MathProblemSolution.generateNumbers().get(0));
    }

    @Test
    public void getResultinNumber_given111115_Returns511111() throws Exception {
        Assert.assertEquals(511111, MathProblemSolution.getResultingNumber(111115));
    }
}