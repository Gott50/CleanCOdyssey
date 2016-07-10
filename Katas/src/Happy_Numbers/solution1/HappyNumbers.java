package Happy_Numbers.solution1;

import java.util.ArrayList;
import java.util.List;

class HappyNumbers {

    private ArrayList<Integer> list;

    HappyNumbers() {
        init();
    }

    private void init() {
        this.list = new ArrayList<>();
    }

    boolean isHappy(int number) {
        return number == 1 || !isCycle(number) && isHappy(calculateNextNumber(number));
    }

    private int calculateNextNumber(int number) {
        int out = 0;
        for (String s : String.valueOf(number).split("")) out += Math.pow(Double.parseDouble(s), 2);
        return out;
    }

    private boolean isCycle(int number) {
        if (list.contains(number)) return true;
        list.add(number);
        return false;
    }

    List<Integer> calculateNumbers(int start, int end) {
        List<Integer> out = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            if (isHappy(i))
                out.add(i);
            init();
        }
        return out;
    }
}
