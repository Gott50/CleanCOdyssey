#[Function Kata „Mail Followup“](http://ccd-school.de/en/coding-dojo/function-katas/mail-followup/)


Implement a function which maps special mail followup email addresses to date and time values. You may also take a look at the [„Mail Followup“](http://ccd-school.de/en/coding-dojo/architecture-katas/mail-followup/) architecture kata.

The function to be implemented should have the following signature:

    DateTime FollowupPointInTime(DateTime now, string emailaddress);


Examples for email addresses:
- 7days@followup.cc
In 7 days from now on.
- 12hours@followup.cc
In 12 hours from now on.
- aug15-9am@followup.cc
At next august the 15. at 9 A.M.
- 1week3days5hours@followup.cc
In one week, three days and five hours from now on.

Feel free to add more functionality.



Here’s an example: if the function is called with the following values

    var t = FollowupPointInTime(new DateTime(2013, 2, 4, 10, 30, 0),
                            "2weeks1day1hour@followup.cc");

then t has a value of

    DateTime(2013, 2, 19, 11, 30, 0).


by [Clean Code Developer School](http://ccd-school.de/)