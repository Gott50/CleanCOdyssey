package FizzBuzz.solution1;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzz {

    public static List<String> compute(int lastNumber) {
        List<String> out = new ArrayList<>();
        for (int i = 1; i <= lastNumber; i++)
            if (isSpecial(i, 3) && isSpecial(i, 5)) out.add("FizzBuzz");
            else if (isSpecial(i, 3)) out.add("Fizz");
            else if (isSpecial(i, 5)) out.add("Buzz");
            else out.add(i + "");

        return out;
    }

    private static boolean isSpecial(int number, int trigger) {
        return number % trigger == 0 ||
                String.valueOf(number).contains(String.valueOf(trigger));
    }
}
