#[Function Kata „CSV tabellieren“](http://ccd-school.de/coding-dojo/function-katas/csv-tabellieren/)

Schreibe eine Funktion, die CSV Zeilen tabelliert.

    IEnumerable<string>  Tabelliere(IEnumerable<string> CSV_zeilen);

Als Eingabe erhält die Funktion eine Aufzählung von Strings. Jeder dieser Strings ist eine CSV-Zeile, die beispielsweise aus einer Datei stammen könnte.

Beispiel für eine mögliche Eingabe:

    Name;Strasse;Ort;Alter
    Peter Pan;Am Hang 5;12345 Einsam;42
    Maria Schmitz;Kölner Straße 45;50123 Köln;43
    Paul Meier;Münchener Weg 1;87654 München;65

In den Eingabedaten trennt ein Semikolon innerhalb der Zeilen die einzelnen Werte voneinander. Kompliziertere CSV Mechanismen (beispielsweise ein Semikolon in den Daten), müssen nicht berücksichtigt werden. Die Eingabedaten sind immer korrekt aufgebaut, es ist keine Fehlerbehandlung erforderlich.

Als Ausgabe liefert die Funktion eine formatierte Tabelle der Eingangsdaten. Dabei wird die erste Zeile der Eingangsdaten als Überschrift verwendet. Die Überschrift wird von den Daten durch eine Trennzeile getrennt. Die Spaltenbreite richtet sich nach dem breitesten Wert in den Daten. Dabei wird auch die Überschrift berücksichtigt.

Ausgabe für das obige Beispiel:

    Name         |Strasse         |Ort          |Alter|
    -------------+----------------+-------------+-----+
    Peter Pan    |Am Hang 5       |12345 Einsam |42   |
    Maria Schmitz|Kölner Straße 45|50123 Köln   |43   |
    Paul Meier   |Münchener Weg 1 |87654 München|65   |



by [Clean Code Developer School](http://ccd-school.de/)