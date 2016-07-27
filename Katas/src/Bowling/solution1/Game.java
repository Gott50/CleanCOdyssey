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
        return !isStrike(frame) && frame.pinsRolled[0] + frame.pinsRolled[1] == 10;
    }

    private boolean isStrike(Frame frame) {
        return frame.pinsRolled[0] == 10;
    }

    void addRoll(int pins) throws Exception {
        if (isOver()) throw new Exception();

        roll(pins, frames, getIndex());
        rollIndex++;
    }

    private int getIndex() {
        int index;
        if (rollIndex < 20) {
            index = rollIndex / 2;
            if (isSecondRoll(rollIndex % 2))
                frames[index] = new Frame();
        } else index = 9;
        return index;
    }

    private int getFrameIndex() {
        for (int i = 0; i < frames.length; i++) {
            if (frames[i] != null) {
                if ((frames[i].pinsRolled[1] == -1 && !isStrike(frames[i])) || (i == 9 && frames[i].score == 10))
                    return i;
            } else {
                frames[i] = new Frame();
                return i;
            }
        }
        return 0;
    }

    private void roll(int pins, Frame[] frames, int index) {
        if (isFirstRollInFrame(frames[index])) {
            if (index != 0 && isSpare(this.frames[index - 1])) this.frames[index - 1].score += pins;
            frames[index].pinsRolled[0] = pins;
        } else if (isSecondRoll(frames[index].pinsRolled[1])) {
            frames[index].pinsRolled[1] = pins;
            if (index != 0 && isStrike(this.frames[index - 1])) this.frames[index - 1].score += pins;
        } else frames[index].pinsRolled[2] = pins;
        frames[index].score += pins;
    }

    private boolean isSecondRoll(int i) {
        return i == 0;
    }

    private boolean isFirstRollInFrame(Frame frame) {
        return isSecondRoll(frame.score);
    }

    private class Frame {
        final int[] pinsRolled = new int[3];
        int score = 0;
    }
}
