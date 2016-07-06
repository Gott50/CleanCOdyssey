package CSV_tabellieren.tests1;

import CSV_tabellieren.solution1.CSV_tabellieren;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("unused")
public class TestCSV_tabellieren {
    @Test
    public void testTabelliereWithoutSpaces() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Name;Strasse;Ort;Alter");
        ArrayList<String> expected = new ArrayList<>();
        expected.add("Name|Strasse|Ort|Alter|");

        assertEquals(expected, CSV_tabellieren.tabelliere(list));
    }

    @Test
    public void testTabelliereWithSpaces() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Name;Strasse;Ort;Alter");
        list.add("Peter Pan;Am Hang 5;12345 Einsam;42");
        ArrayList<String> expected = new ArrayList<>();
        expected.add("Name     |Strasse  |Ort         |Alter|");

        assertEquals(expected.get(0), CSV_tabellieren.tabelliere(list).get(0));
    }

    @Test
    public void testTabelliereLine() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Name;Strasse;Ort;Alter");
        list.add("Peter Pan;Am Hang 5;12345 Einsam;42");
        ArrayList<String> expected = new ArrayList<>();
        expected.add("Name     |Strasse  |Ort         |Alter|");
        expected.add("---------+---------+------------+-----+");

        assertEquals(expected.get(1), CSV_tabellieren.tabelliere(list).get(1));
    }

    @Test
    public void testTabelliere4Rows() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Name;Strasse;Ort;Alter");
        list.add("Peter Pan;Am Hang 5;12345 Einsam;42");
        list.add("Maria Schmitz;Kölner Straße 45;50123 Köln;43");
        list.add("Paul Meier;Münchener Weg 1;87654 München;65");
        ArrayList<String> expected = new ArrayList<>();
        expected.add("Name         |Strasse         |Ort          |Alter|");
        expected.add("-------------+----------------+-------------+-----+");
        expected.add("Peter Pan    |Am Hang 5       |12345 Einsam |42   |");
        expected.add("Maria Schmitz|Kölner Straße 45|50123 Köln   |43   |");
        expected.add("Paul Meier   |Münchener Weg 1 |87654 München|65   |");

        assertEquals(expected, CSV_tabellieren.tabelliere(list));
    }

}
