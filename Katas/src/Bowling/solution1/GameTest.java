package Bowling.solution1;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameTest {
    private Game bowlingGame;

    @Before
    public void setUp() throws Exception {
        bowlingGame = new Game();
    }

    @Test
    public void gameOver_Start() {
        assertEquals(false, bowlingGame.isOver());
    }

    @Test
    public void gameOver_after1Roll() throws Exception {
        rollTimes(1, 0);
        assertEquals(false, bowlingGame.isOver());
    }

    @Test
    public void totalScore_after1Roll_given0() throws Exception {
        rollTimes(1, 0);
        assertEquals(0, bowlingGame.totalScore());
    }

    @Test
    public void totalScore_after20Roll_given0() throws Exception {
        rollTimes(20, 0);
        assertEquals(0, bowlingGame.totalScore());
    }

    @Test
    public void gameOver_after20Rolls_given0() throws Exception {
        rollTimes(20, 0);
        assertEquals(true, bowlingGame.isOver());
    }

    private void rollTimes(int times, int pins) throws Exception {
        for (int i = 0; i < times; i++) {
            bowlingGame.addRoll(pins);
        }
    }

    @Test
    public void totalScore_after20Roll_given1() throws Exception {
        rollTimes(20, 1);
        assertEquals(20, bowlingGame.totalScore());
    }

    @Test
    public void totalScore_rollSpareAnd5() throws Exception {
        rollTimes(3, 5);
        assertEquals(20, bowlingGame.totalScore());
    }

    @Test
    public void totalScore_after18Rolls_given0_And3Rolls_given5() throws Exception {
        rollTimes(18, 0);
        rollTimes(3, 5);
        assertEquals(15, bowlingGame.totalScore());
    }

    @Test(expected = Exception.class)
    public void totalScore_rollAfterGameOver() throws Exception {
        rollTimes(21, 0);
    }

    @Test(expected = Exception.class)
    public void totalScore_roll22Spares() throws Exception {
        rollTimes(22, 5);
    }

    @Test
    public void totalScore_rollStrikeAnd5And3() throws Exception {
        rollTimes(1, 10);
        rollTimes(1, 5);
        rollTimes(1, 3);
        assertEquals(18 + 8, bowlingGame.totalScore());
    }

    @Test
    public void integration() throws Exception {
        rollSequence(new int[]{1, 4, 4, 5, 6, 4, 5, 5, 10, 0, 1, 7, 3, 6, 4, 10, 2, 8, 6});
        Frame[] expectedFrames = new Frame[]{
                new Frame(5, 1, 4),
                new Frame(9, 4, 5),
                new Frame(15, 6, 4),
                new Frame(20, 5, 5),
                new Frame(11, 10),
                new Frame(1, 0, 1),
                new Frame(16, 7, 3),
                new Frame(20, 6, 4),
                new Frame(20, 10),
                new Frame(16, 2, 8, 6)};
        assertEquals(printFrames(expectedFrames), printFrames(bowlingGame.frames));
    }

    private void rollSequence(int[] rollSequence) throws Exception {
        for (int pins : rollSequence) {
            bowlingGame.addRoll(pins);
        }
    }

    private String printFrames(Frame[] frames) {
        String out = "";
        for (Frame frame : frames) {
            out += frame.toString();
        }
        return out;
    }

}