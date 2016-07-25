package Bowling.solution1;

class Game {
    private final Frame[] frames = new Frame[10];
    private int rollIndex = 0;

    int totalScore() {
        int score = 0;
        for (Frame frame : frames) if (frame != null) score += frame.score;
        return score;
    }

    boolean isOver() {
        return frames[9] != null && rollIndex >= 19;
    }

    void addRoll(int pins) {
        int index = rollIndex / 2;
        if (rollIndex % 2 == 0)
            frames[index] = new Frame();
        roll(pins, frames[index]);
        rollIndex++;
    }

    private void roll(int pins, Frame frame) {
        if (frame.score == 0)
            frame.pinsRolled[0] = pins;
        else
            frame.pinsRolled[1] = pins;
        frame.score += pins;
    }

    private class Frame {
        final int[] pinsRolled = new int[2];
        int score = 0;


    }
}
