package Christmas_Tree.solution1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChristmasTreeTest {
    @Test
    public void drawStamp() {
        assertEquals("I", ChristmasTree.draw(0));
    }

    @Test
    public void drawGiven_1() {
        String expected =
                "X" + "\n" +
                        "I";
        assertEquals(expected, ChristmasTree.draw(1));
    }

    @Test
    public void drawGiven_2() {
        String expected =
                " X" + "\n" +
                        "XXX" + "\n" +
                        " I";
        assertEquals(expected, ChristmasTree.draw(2));
    }

    @Test
    public void drawGiven_5() {
        String expected =
                "    X" + "\n" +
                        "   XXX" + "\n" +
                        "  XXXXX" + "\n" +
                        " XXXXXXX" + "\n" +
                        "XXXXXXXXX" + "\n" +
                        "    I";
        assertEquals(expected, ChristmasTree.draw(5));
    }

    @Test
    public void drawWithTopGiven_0() {
        String expected =
                "*" + "\n" +
                        "I";
        assertEquals(expected, ChristmasTree.drawWithTop(0));
    }

    @Test
    public void drawWithTopGiven_2() {
        String expected =
                " *" + "\n" +
                        " X" + "\n" +
                        "XXX" + "\n" +
                        " I";
        assertEquals(expected, ChristmasTree.drawWithTop(2));
    }

}