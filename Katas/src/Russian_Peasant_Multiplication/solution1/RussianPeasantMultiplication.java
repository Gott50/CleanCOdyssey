package Russian_Peasant_Multiplication.solution1;

class RussianPeasantMultiplication {
    static int mul(int x, int y) {
        int out = 0;
        while (x >= 1) {
            if (x % 2 != 0) out += y;
            x *= 0.5;
            y *= 2;
        }
        return out;
    }
}
