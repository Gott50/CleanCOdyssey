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
    public void gameOver_after1Roll() {
        rollTimes(1, 0);
        assertEquals(false, bowlingGame.isOver());
    }

    @Test
    public void TotalScore_after1Roll_given0() {
        rollTimes(1, 0);
        assertEquals(0, bowlingGame.totalScore());
    }

    @Test
    public void TotalScore_after20Roll_given0() {
        rollTimes(20, 0);
        assertEquals(0, bowlingGame.totalScore());
    }

    @Test
    public void gameOver_after20Rolls_given0() {
        rollTimes(20, 0);
        assertEquals(true, bowlingGame.isOver());
    }

    private void rollTimes(int times, int pins) {
        for (int i = 0; i < times; i++) {
            bowlingGame.addRoll(pins);
        }
    }

    @Test
    public void TotalScore_after20Roll_given1() {
        rollTimes(20, 1);
        assertEquals(20, bowlingGame.totalScore());
    }


}