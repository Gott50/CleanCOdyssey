package Bowling.solution1;

import java.util.ArrayList;
import java.util.Arrays;

class Frame {
    final ArrayList<Integer> pinsRolled;
    int score;

    public Frame(int score, Integer... pinsRolled) {
        this.pinsRolled = new ArrayList<>(Arrays.asList(pinsRolled));
        this.score = score;
    }

    public Frame() {
        this(0);
    }

    @Override
    public String toString() {
        return "(" + pinsRolled + "," + score + ")";
    }

    boolean isSpare() {
        return pinsRolled.size() == 2 && score == 10;
    }

    boolean isStrike() {
        return pinsRolled.size() == 1 && pinsRolled.get(0) == 10;
    }

    void addPinsRolled(int pins) {
        pinsRolled.add(pins);
        score += pins;
    }

    boolean wasNotRolled(Integer index) {
        return pinsRolled.size() < index + 1;
    }
}
