package LOC.solution1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestLOC {
    @Test
    public void emptyString() {
        String code = "";
        assertEquals(0, LOC.countCode(code));
    }

    @Test
    public void onlyCode() {
        String code = "using System;\n" +
                "class Program\n" +
                "{\n" +
                "    static void Main()\n" +
                "    {\n" +
                "        Console.WriteLine(\"Hello, world!\");\n" +
                "    }\n" +
                "}";
        assertEquals(8, LOC.countCode(code));
    }

    @Test
    public void withWhitespace() {
        String code = "using System;\n" +
                "       \n";

        assertEquals(1, LOC.countCode(code));
    }

    @Test
    public void withCommentedLines() {
        String code = "using System;\n" +
                "//This is a Commented Line\n" +
                "/*This also*/\n";
        assertEquals(1, LOC.countCode(code));
    }

    @Test
    public void withCommentedLinesAndWhitespace() {
        String code = "using System;\n" +
                "    //This is a Commented Line   \n" +
                "  /*This also*/  \n" +
                "       \n";
        assertEquals(1, LOC.countCode(code));
    }

    @Test
    public void withInlineCommentsAfterCode() {
        String code = "using System; //This is a Comment\n" +
                "class Program /*This also*/ \n";
        assertEquals(2, LOC.countCode(code));
    }

    @Test
    public void withInlineCommentsBeforeCode() {
        String code = "/*This is a Comment*/ using System;\n";
        assertEquals(1, LOC.countCode(code));
    }

    @Test
    public void withInlineCommentsAroundCode() {
        String code = "/*This is*/ using System; /*A Comment*/\n";
        assertEquals(1, LOC.countCode(code));
    }

    @Test
    public void with3LineComment() {
        String code = "using System;\n" +
                "/*This \n" +
                "is A \n" +
                "Comment*/\n";
        assertEquals(1, LOC.countCode(code));
    }

    @Test
    public void countComments() {
        String code = "using System;\n" +
                "/*This \n" +
                "is A \n" +
                "Comment*/\n";
        assertEquals(3, LOC.countComments(code));
    }


}