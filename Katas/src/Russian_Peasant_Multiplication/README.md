#[Function Kata „Russian Peasant Multiplication“](http://ccd-school.de/en/coding-dojo/function-katas/russian-peasant-multiplication/)

Write a function that multiplies two whole numbers by using the Russian Peasant Multiplication algorithm [1].

The signature of the function should be as follows:

    int Mul(int x, int y);

The algorithm of the Russian Peasant Multiplication works as follows: you divide the left number by two as long as you reach one. Decimal places get truncated. The right number is written next to the left one and is doubled each time. At the end you have a table with two columns (see example below). Each number from the right column is crossed out where the corresponding number on the left is even. In the example the number 672 on the right is crossed out because the corresponding number on the left is 2, which is even. The last step is to add all the numbers from the right column that is not crossed out.

Example:

    47 * 42
    -------
    47   42
    23   84
    11  168
     5  336
     2  (672)
     1 1344
    =======
       1974

##Resources

[1] https://en.wikipedia.org/wiki/Ancient_Egyptian_multiplication#Russian_peasant_multiplication


by [Clean Code Developer School](http://ccd-school.de/)