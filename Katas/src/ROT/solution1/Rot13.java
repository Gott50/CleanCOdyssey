package ROT.solution1;

class Rot13 {
    private static final char LAST_DIGIT = '9';
    private static final char A = 'a';
    private static final char Z = 'z';
    private static final int SUM_OF_LETTERS_AND_DIGITS = 36;
    private static final int CHARS_BETWEEN_DIGITS_AND_LETTERS = A - (LAST_DIGIT + 1);

    static String encrypt(String message) {
        return encrypt(message, 13);
    }

    static String encrypt(String message, int shift) {
        if (message == null || message.isEmpty())
            return "";

        String out = "";
        for (char letter : replaceUmlaute(message).toCharArray()) {
            out += shiftLetter(letter, shift);
        }
        return out;
    }

    private static String replaceUmlaute(String message) {
        return message.toLowerCase().replaceAll("ö", "oe").replaceAll("ä", "ae").replaceAll("ü", "ue").replaceAll("ß", "ss");
    }

    private static String shiftLetter(char letter, int shift) {
        if (!Character.isAlphabetic(letter) && !Character.isDigit(letter)) return String.valueOf(letter);

        char c = (char) (letter + shift);
        while (c > Z) c -= SUM_OF_LETTERS_AND_DIGITS + CHARS_BETWEEN_DIGITS_AND_LETTERS;
        if (c > LAST_DIGIT & c < A) c += CHARS_BETWEEN_DIGITS_AND_LETTERS;

        return (c + "").toUpperCase();
    }
}
