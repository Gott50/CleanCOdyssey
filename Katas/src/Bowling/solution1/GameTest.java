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
    public void TotalScore_after1Roll_given0() throws Exception {
        rollTimes(1, 0);
        assertEquals(0, bowlingGame.totalScore());
    }

    @Test
    public void TotalScore_after20Roll_given0() throws Exception {
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
    public void TotalScore_after20Roll_given1() throws Exception {
        rollTimes(20, 1);
        assertEquals(20, bowlingGame.totalScore());
    }

    @Test
    public void TotalScore_rollSpareAnd5() throws Exception {
        rollTimes(3, 5);
        assertEquals(20, bowlingGame.totalScore());
    }

    @Test
    public void TotalScore_after18Rolls_given0_And3Rolls_given5() throws Exception {
        rollTimes(18, 0);
        rollTimes(3, 5);
        assertEquals(15, bowlingGame.totalScore());
    }

    @Test(expected = Exception.class)
    public void TotalScore_rollAfterGameOver() throws Exception {
        rollTimes(21, 0);
    }

    @Test(expected = Exception.class)
    public void TotalScore_roll22Spares() throws Exception {
        rollTimes(22, 5);
    }


}