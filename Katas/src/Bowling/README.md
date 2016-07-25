#[Class Kata “Bowling”](http://ccd-school.de/en/coding-dojo/classes-katas/bowling/)

Implement a class that counts the pins in a Bowling game.

A Bowling game consists of rolls to clear 10 pins that stand at the end of the Bowling alley. With each roll one can clear from 0 to 10 pins. In each round the player has two rolls at max to knock down 10 pins.

Rolls are summed up to frames. Each frame gets points. A Bowling game consists of 10 frames.

The number of rolls per round are defined by the number of pins that are thrown down.

   - If one throws all 10 pins it is called a _strike_ and it is the only roll in the round.
   - Otherwise each frame consists of two rolls, each with 0 to 10 pins.
        Exception: if the 10th frame is a _strike_ or both rolls together are a _spare_ (see below) the player has a third roll.

The points of a frame are calculated by the following rules:

   - A frame where both rolls together have thrown down a maximum of 9 pins is counted as the sum of the pins.
   - A frame where both rolls together have thrown down all 10 pins is called a _spare_. It is counted as 10 plus the number of pins from the next roll.
   - A frame with a _strike_ is counted as 10 points plus the number of pins of the next two rolls.

Robert C. Martin shows the following diagram [1] as an example:

![example](http://ccd-school.de/wp-content/uploads/2016/07/1-6.png)

Each frame shows the first and second roll. The last frame shows the third roll too. Furthermore the points are cumulated in the middle of the box. If the box for the second roll is filled half it is a _spare_. If it is filled completely the first roll was a _strike_.

The interface of the class should be as follows:

    class Game {
        void AddRoll(int pins) {...}
        Frame[] Frames() {...}
        int TotalScore() {...}
        bool Over() {...}
    }

    class Frame {
        int[] PinsRolled;
        int Score; // Point of this frame only
    }

**AddRoll()** throws an exception if rolls should be registered after the game is over.

The following table shows the game that was shown in the diagram above:

    AddRoll() 	Frames() 	    TotalScore 	Over()
    1 	        ([1],1) 	            1           false
    4 	        ([1,4],5) 	        5
    4 	        ([1,4],5), ([4],4) 	9
    5 	        ([1,4],5), ([4,5],9) 	14
    6 	        …, ([6],6) 	        20
    4 	        …, ([6,4],10) 	        24
    5 	        …, ([6,4],15), ([5],5) 	34
    5 	        …, ([5,5],10) 	        39
    10      	…, ([5,5],20), ([10],10 59
    0 	        …, ([10],10), ([0],0) 	59
    1 	        …, ([10],11), ([0,1],1  61
    7 	        …, ([7],7)          	68
    3       	…, ([7,3],10) 	        71
    6       	…, ([7,3],16), ([6],6) 	83
    4       	…, ([6,4],10) 	        87
    10      	…, ([6,4],20), ([10],10)107
    2       	…, ([10],12), ([2],2) 	111
    8 	        …, ([10],20), ([2,8],10)127
    6 	        …, ([2,8,6],16) 	        133 	        true
##Resources

[1]Rober C. Martin, Bowling Game Kata, http://butunclebob.com/files/downloads/Bowling%20Game%20Kata.ppt
##Source

http://codingdojo.org/cgi-bin/wiki.pl?KataBowling

by [Clean Code Developer School](http://ccd-school.de/)