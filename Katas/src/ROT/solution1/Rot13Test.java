package ROT.solution1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Rot13Test {
    @Test
    public void emptyString() {
        assertEquals("", Rot13.encrypt(""));
    }

    @Test
    public void given_E() {
        assertEquals("R", Rot13.encrypt("E"));
    }

    @Test
    public void given_e() {
        assertEquals("R", Rot13.encrypt("e"));
    }

    @Test
    public void word() {
        assertEquals("URYY1", Rot13.encrypt("Hello"));
    }

    @Test
    public void sentence() {
        assertEquals("HELLO, WORLD", Rot13.encrypt("Hello, World", 0));
    }

    @Test
    public void sentence_36() {
        assertEquals("HELLO, WORLD", Rot13.encrypt("Hello, World", 36));
    }


    @Test
    public void umlaute() {
        assertEquals("OE", Rot13.encrypt("Ö", 0));
        assertEquals("AE", Rot13.encrypt("Ä", 0));
        assertEquals("UE", Rot13.encrypt("Ü", 0));
        assertEquals("SS", Rot13.encrypt("ß", 0));
    }

    @Test
    public void umlauteShift1() {
        assertEquals("PF", Rot13.encrypt("Ö", 1));
        assertEquals("BF", Rot13.encrypt("Ä", 1));
        assertEquals("VF", Rot13.encrypt("Ü", 1));
        assertEquals("TT", Rot13.encrypt("ß", 1));
    }

    @Test
    public void shift15() {
        assertEquals("T", Rot13.encrypt("E", 15));
    }

    @Test
    public void given7() {
        assertEquals("K", Rot13.encrypt("7"));
    }

    @Test
    public void given0() {
        assertEquals("D", Rot13.encrypt("0"));
    }

    @Test
    public void givenZ() {
        assertEquals("C", Rot13.encrypt("Z"));
    }


}