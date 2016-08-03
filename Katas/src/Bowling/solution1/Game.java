package Bowling.solution1;

class Game {
    final Frame[] frames = new Frame[10];

    int totalScore() {
        int score = 0;
        for (Frame frame : frames) if (frame != null) score += frame.score;
        return score;
    }

    boolean isOver() {
        Frame lastFrame = frames[9];
        if (lastFrame != null) if (!lastFrame.wasNotRolled(1))
            return !(lastFrame.isSpare() && lastFrame.wasNotRolled(2));
        return false;
    }

    void addRoll(int pins) throws Exception {
        if (isOver()) throw new Exception();

        int index = getFrameIndex();
        frames[index].addPinsRolled(pins);

        if (frames[index].pinsRolled.size() < 3 &&
                index > 0 && (frames[index - 1].isStrike() || frames[index - 1].isSpare()))
            frames[index - 1].score += pins;
    }

    private int getFrameIndex() {
        for (int i = 0; i < frames.length; i++)
            if (frames[i] != null) {
                if ((frames[i].wasNotRolled(1) && !frames[i].isStrike()))
                    return i;
            } else {
                frames[i] = new Frame();
                return i;
            }
        return 9;
    }

}
