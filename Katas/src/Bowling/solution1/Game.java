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
        if (frames[9] != null) {
            if (rollIndex > 20) return true;
            if (isSpare(frames[9])) return false;
            if (rollIndex >= 20) return true;

        }
        return false;
    }

    private boolean isSpare(Frame frame) {
        return frame.pinsRolled[0] != 10 && frame.pinsRolled[0] + frame.pinsRolled[1] == 10;
    }

    void addRoll(int pins) throws Exception {
        if (isOver()) throw new Exception();

        int index;
        if (rollIndex < 20) {
            index = rollIndex / 2;
            if (rollIndex % 2 == 0)
                frames[index] = new Frame();
        } else index = 9;

        roll(pins, frames, index);
        rollIndex++;
    }

    private void roll(int pins, Frame[] frames, int index) {
        if (index != 0 && frames[index - 1].score == 10) frames[index - 1].score += pins;

        final Frame frame = frames[index];
        putPinsRolledInFrame(pins, frame);
    }

    private void putPinsRolledInFrame(int pins, Frame frame) {
        if (frame.score == 0)
            frame.pinsRolled[0] = pins;
        else if (frame.pinsRolled[1] == 0)
            frame.pinsRolled[1] = pins;
        else frame.pinsRolled[2] = pins;
        frame.score += pins;
    }

    private class Frame {
        final int[] pinsRolled = new int[3];
        int score = 0;


    }
}
