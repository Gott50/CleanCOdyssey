package Bowling.solution1;

import java.util.Arrays;

public class Frame {
    Integer[] pinsRolled;
    int score;

    public Frame(int score, Integer... pinsRolled) {
        this.pinsRolled = pinsRolled;
        this.score = score;
    }

    public Frame(int length) {
        this(0, new Integer[length]);
    }

    @Override
    public String toString() {
        return "(" + Arrays.asList(pinsRolled) + "," + score + ")";
    }
}
