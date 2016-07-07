#[Function Kata “CSV Table-izer”](http://ccd-school.de/en/coding-dojo/function-katas/csv-table-izer/)

Write a function to create a table from CSV data.

    Iterable<String> toTable(Iterable<String> CSV_lines)

The input to the function is a list of strings. Each string is formatted as a CSV record. Here’s an example for a possible input:

    Name;Street;City;Age
    Peter Pan;Am Hang 5;12345 Einsam;42
    Maria Schmitz;Kölner Straße 45;50123 Köln;43
    Paul Meier;Münchener Weg 1;87654 München;65

In the input a semicolon separates values within a line. More complicated features of CSV (e.g. delimiters within a value) need not be implemented. The input always is correctly formatted; no validation necessary.

The output should be the data formatted as an “ASCII table”. The first record is interpreted as a header line. A separator line should follow the header line. The column width follows the longest value in a column (which includes the header). Here’s an example output for the above input:

    Name         |Strasse         |Ort          |Alter|
    -------------+----------------+-------------+-----+
    Peter Pan    |Am Hang 5       |12345 Einsam |42   |
    Maria Schmitz|Kölner Straße 45|50123 Köln   |43   |
    Paul Meier   |Münchener Weg 1 |87654 München|65   |



by [Clean Code Developer School](http://ccd-school.de/)