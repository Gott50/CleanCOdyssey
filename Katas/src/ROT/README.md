#[Function Kata „ROT-13“](http://ccd-school.de/en/coding-dojo/function-katas/rot-13/)


Implement a function that encodes a text by [ROT-13].

The ROT-13 coding exchanges every character by the character 13 positions further back in the alphabet. If the value lies behind the end of the alphabet it is fetched from the beginning.


Example:

    Hello, World -> URYYB, JBEYQ

An „e“ gets to an „R“ and a „W“ gets to a „J“. Lower case letters are translated to upper case.

German “Umlaute” need to be replaced by the following character combinations before the coding: „Ö“ = „OE“, „Ä“ = „AE“, „Ü“ = „UE“, „ß“ = „SS“.

Characters that are no letters are not encoded.
##Variation #1

The offset (13 in this case) should be variable.
##Variation #2

Encode digits also. Digits and letters should form a unit: 0123456789ABCDEFGH…XYZ.

With offset 13 the „0“ gets to a „D“ and a „Z“ doesn’t get to an „M“ but to a „C“.
Resources

[ROT-13] https://en.wikipedia.org/wiki/ROT13



by [Clean Code Developer School](http://ccd-school.de/)