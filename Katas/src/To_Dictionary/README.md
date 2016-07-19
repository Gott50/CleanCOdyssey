#[Function Kata „ToDictionary“](http://ccd-school.de/en/coding-dojo/function-katas/todictionary/)

Implement a function that maps a special formatted string into a dictionary. The function should have the following signature:

    Map<String, String> toDictionary(String input);

The following table shows some examples for input strings and the resulting dictionary.

    „a=1;b=2;c=3“ 	{{„a“, „1“}, {„b“, „2“},{„c“, „3“}}
    „a=1;a=2“ 	{{„a“, „2“}}
    „a=1;;b=2“ 	{{„a“, „1“}, {„b“, „2“}}
    „a=“ 	{{„a“, „“}}
    „=1“ 	Exception
    „“ 	{}
    „a==1“ 	{{„a“, „=1“}}


by [Clean Code Developer School](http://ccd-school.de/)