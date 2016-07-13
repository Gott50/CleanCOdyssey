package ROT.solution1;

class Rot13 {
    private static final int SUM_OF_LETTERS_AND_DIGITS = 36;

    static String encrypt(String message) {
        return encrypt(message, 13);
    }

    private static String shiftLetter(char letter, int shift) {
        if (letter == 'ö') {
            return "OE";
        } else if (letter == 'ä') {
            return "AE";
        } else if (letter == 'ü') {
            return "UE";
        } else if (letter == 'ß') {
            return "SS";
        } else if (!Character.isAlphabetic(letter) && !Character.isDigit(letter)) {
            return letter + "";
        }

        char c = (char) (letter + shift);
        int CHARS_BETWEEN_DIGITS_AND_LETTERS = 'a' - ':';
        if (c > 'z') {
            c -= SUM_OF_LETTERS_AND_DIGITS + CHARS_BETWEEN_DIGITS_AND_LETTERS;
        }
        if (c > '9' & c < 'a') c += CHARS_BETWEEN_DIGITS_AND_LETTERS;

        return c + "";
    }

    static String encrypt(String message, int shift) {
        if (message == null || message.isEmpty())
            return "";
        String out = "";
        for (char letter : message.toLowerCase().toCharArray()) {
            out += shiftLetter(letter, shift);
        }
        return out.toUpperCase();
    }
}
