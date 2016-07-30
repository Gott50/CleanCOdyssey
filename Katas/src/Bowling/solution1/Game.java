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

            if (isSpare(frames[9])) return false;
            if (rollIndex >= 20) return true;

        }
        return false;
    }

    private boolean isSpare(Frame frame) {
        return frame.score == 10;
        //return frame.pinsRolled[1] != null && frame.pinsRolled[0] + frame.pinsRolled[1] == 10;
    }

    private boolean isStrike(Frame frame) {
        return frame.pinsRolled[0] != null && frame.pinsRolled[0] == 10;
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
            if (0 == (rollIndex % 2))
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
        int rollOfFrameIndex = getRollOfFrameIndex(pins, frames[index], index);
        switch (rollOfFrameIndex) {
            case 0:
            case 1:
                if (index > 0 && (isStrike(frames[index - 1]) || isSpare(frames[index - 1])))
                    frames[index - 1].score += pins;
                break;
            case 2:

            default:
                frames[index].pinsRolled[rollOfFrameIndex] = pins;
        }
        frames[index].score += pins;
    }

    private int getRollOfFrameIndex(int pins, Frame frame, int index) {
        if (isFirstRollInFrame(frame)) {
            return 0;
        } else if (wasRolled(frame.pinsRolled[1])) {
            return 1;

        } else
            return 2;
    }

    private boolean wasRolled(Integer i) {
        return i == null;
    }

    private boolean isFirstRollInFrame(Frame frame) {
        return frame.score == 0;
    }

    private class Frame {
        Integer[] pinsRolled = new Integer[3];
        int score = 0;
    }
}
