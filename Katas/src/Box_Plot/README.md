#[Class Kata “Box Plot”](http://ccd-school.de/en/coding-dojo/classes-katas/box-plot/#_ftn1)

Develop a graphical control to show box plots [1].

A box plot visualizes a list of numbers. To quickly get an overview several values are displayed for each number:

    Minimum
    Lower quartile
    Median
    Upper quartile
    Maximum

The minimum value of a list of numbers is its smallest value; the maximum value is the largest number respectively.

The median is the number right in the middle of the sorted (!) list. If the list has an even number of elements the numbers “surrounding” the middle will be used to calculate an average. Example:

Original list: 2, 4, 1, 3

Sorted list: 1, 2, 3, 4 => 2 and 3 “surround” the middle of the list. (2+3)/2=2,5 then is the median.

To calculate the lower/upper quartile the list has to be sorted as well. Then the list is split into two halves. The lower quartile is the median of the lower half, the upper quartile is the median of the upper half.

The following figure shows how to calculate median as well as lower/upper quartile:

![median,quartiles](http://ccd-school.de/wp-content/uploads/2016/07/1-7.png)

The result then should be displayed as follows:

![displayed](http://ccd-school.de/wp-content/uploads/2016/07/2-3.png)


##Variations

The custom control should provide a property Orientation to choose whether the box is shown horizontally (as above) or vertically.
Resources

[1]Wikipedia, Box plot, https://en.wikipedia.org/wiki/Box_plot

by [Clean Code Developer School](http://ccd-school.de/)