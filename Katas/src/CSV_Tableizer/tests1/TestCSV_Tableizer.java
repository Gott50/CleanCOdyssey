package CSV_Tableizer.tests1;

import CSV_Tableizer.solution1.CSV_Tableizer;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("unused")
public class TestCSV_Tableizer {
    @Test
    public void testTableWithoutSpaces() {
        assertEquals(Collections.singletonList("Name|Strasse|Ort|Alter|"),
                CSV_Tableizer.toTable(Collections.singletonList("Name;Strasse;Ort;Alter")));
    }

    @Test
    public void testTableWithSpaces() {
        Iterable<String> list = Arrays.asList(
                "Name;Strasse;Ort;Alter",
                "Peter Pan;Am Hang 5;12345 Einsam;42");
        Iterable<String> expected = Collections.singletonList(
                "Name     |Strasse  |Ort         |Alter|");

        assertEquals(expected.iterator().next(), CSV_Tableizer.toTable(list).iterator().next());
    }

    @Test
    public void testTableLine() {
        Iterable<String> list = Arrays.asList(
                "Name;Strasse;Ort;Alter",
                "Peter Pan;Am Hang 5;12345 Einsam;42");
        Iterable<String> expected = Arrays.asList(
                "Name     |Strasse  |Ort         |Alter|",
                "---------+---------+------------+-----+",
                "Peter Pan|Am Hang 5|12345 Einsam|42   |");

        assertEquals(expected, CSV_Tableizer.toTable(list));
    }

    @Test
    public void testTable4Rows() {
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

        assertEquals(expected, CSV_Tableizer.toTable(list));
    }

}
