## Lecture 1

1. Intro to Karel
   1. World with straight streets (coords)
   2. Wall
   3. Beeper

## Lecture 2

1. Karel
   1. it knows only four commands/methods
      1. move: one step forward
      2. turnLeft: now east, then next west
      3. pickBeeper
      4. putBeeper
   2. speed control
2. First Karel Program
   1. Algorithm (could be in Java, Python, Chinese, English)
   2. valid karel program
   3. where to start: create the run command
   4. tab indentation
   5. extends karel
   6. semicolon - end of one statement
3. Run it. And we are now programmers!!!!
4. Run again? error, coz world changed
5. create a new command
6. FAQ: case sensitivity, location of a command
7. define turnAround (180 degrees)
8. extending super karel
9. for loop (repeat n times): move 10 steps
10. while loop (don't how many times)
11. if: not repeating, but only once:
    1.  nested if
12. steeple chase
    1.  create jump hurdle command, ascend, descend hurdlehang
    2.  programming is about writing programs that people understand 
    3.  descend hurdle uses move to wall
    4.  load another world: generality of the world

## Lecture 3

1. java files but only use karel commands
2. infinite loop
3. off by one bug
4. comment: for human being to understand
5. structured mind: from high level to the primitives
6. exmaple: double beepers
   1. algorithm: approach to something
   2. coding top-down, write comments
7. do your thing: really bad software engineering
8. good practices for decompose:
   1. solve 1 problem
   2. 1-15 loc
   3. good names
9. last example: cleanup karel

## Lecture 4

1. history of computing and computers
2. modern computers
   1. only understand 1s and 0s
   2. high level lang & compilation
3. compilation and interpretation: source code to object code
4. java: OOP
   1. concept of class: encapsulation of methods and data
   2. concept of hierarchy SuperKarel & Karel is like Humans & Primates
   3. concept of instance
5. Java Applet programs
   1. Java hierarchies
   2. HelloProgram (graphics)
   3. Add2Integers (console)
   4. summary: idea of inheriting behavors from base classes
6. collage graphics (why?)

## Lecture 5

1. variables, a box to store something
   1. being descriptive
   2. name: ID rules
   3. type
   4. value
2. declare a variable
   1. syntax: type name = value;
   2. where: inside method
   3. = is not mathematically euqals, but assignment: total=total+1
3. assignment statement
4. how to create an object using the new operator, e.g. GLabel
5. canvas coordinates
6. operations on GObject, GLable, GLine, GOval, GRect
7. an example of all the classes
8. arithmetic expression, especially remainder

## Lecture 6

1. readInt and readDouble methods
2. division of integers
3. evaluation order: left to right, precedence of operators
4. casting int to double
5. exmaple: averaging two ints
6. shorthand like x+=1, x++
7. constant like PI, nice software engineering practice
8. booleans: true, false, double equal ==, other comparators
9. short circuit evaluation
10. statement blocks: scope of variable
11. if, cascading if, flowchart
12. for loop (init, condition, step)
13. while loop(condition, body)

## Lecture 7

1. casting double to int must be explicit
2. while - indefinite loop
3. loop and a half
   1. read until a sentine
   2. break statement
4. for v.s. while: definite or indefinite
5. checkboard program: nested loop
6. concept of methods
   1. parameters: karel does not have, but java does
   2. information hiding
   3. metaphor of CD player: not throwing away CD players, but inserting different CDs
   4. syntax of invocation: receiver dot method_name (params)
   5. syntax of definition: visibility type name (args)
   6. return statement: the invocation gets replaced by the computed return value
7. examples
   1. defining inch2Feet
   2. defining max
   3. defining isOdd
   4. factorial: i is out of scope and can be reused
8. returning objects: filledCircle

## Lecture 8

1. Information hiding: metaphor of a toaster
2. return if void functions
3. bells of whistles of parameter passing in method invocation
   1. naming conflict?
   2. stack
4. go through an example of factorial
5. bad time of addFive
6. metaphor of chainsaw Monalisa
7. classes: user and coder
   1. instance variable
   2. comparison of instance variable and local variable
8. intro to random generators

## Lecture 9

1. Strings
   1. concatenation
   2. readline
2. how to write your own class
   1. class name and file name
   2. variables and methods in class
   3. extending is optional
3. example of writing a class
4. param or local variable shadowing instance variable
5. using the counter class
6. object in param passing: pass by reference, or the real object
7. class variable, i.e. static
8. javadoc
9. exmaple: Stanford student class

## Lecture 10

1. getters and setters
   1. getter: read-only
   2. both: still preferred for information hiding. e.g. computing on the fly
2. always have a toString method
3. inheritance: freshman is student
   1. `super` in constructor
   2. overriding `toString`
4. `acm.graphics`
   1. `GCanvas`: `add(...)` in `GraphicsProgram` just forwards the call to `GCanvas`
   2. methods of `GCanvas`
   3. methods of `GObject`
5. Interface
   1. difference between class and interface
   2. `GFillable`
6. example: bouncing ball
7. `GLabel` stuff
   1. baseline
   2. origin
   3. height
   4. ascent
   5. descent
   6. exampe: centering text
8. `GArc`

## Lecture 11

1. `GImage`
2. `GPolygon`
3. `GCompound`
   1. example: draw a face
   2. bouncing face
4. Event-driven programs
   1. listeners
   2. asycnchronous
5. Mouse events
   1. `ClickForFace` example: `init` v.s. `run`
   2. other events to listen for
   3. example: show mouse coords
6. Keyboard events
   1. example: dragging objects
7. games: space invaders & breakout

## Lecture 12

1. Enumeration
   1. example: enumerating stuent's years
2. Char: encoding of ASCII
3. example: `toLower` method
4. counting in a for loop condition
5. `Character` class and its static methods
6. `String`
   1. immutability: from `hello` to `jello` one has to create a new string
   2. `String` is an object while `char` is primitive
   3. operations:
      1. concat
      2. equality test
   4. example: palindrome test

## Lecture 13

1. String
   1. `countUpperCase`
   2. `replaceOccurrence`
2. Tokenizer
3. Encryption

## Lecture 14

1. Memory: units like bit, byte, kb, etc
2. Hexadecimal
3. Memory addresses:
   1. constant segment
   2. heap
   3. stack
4. sizes of different types of variables
5. memory allocation example of a program
   1. heap for dynamic (`new`) variables
   2. stack frame of method call

## Lecture 15

1. clarification of pointers
   1. `p1=p2`, assigning the same heap address
   2. `func(3)` is passing by value
   3. `func(p1)` is passing by ref, or passing by value of the pointer address
   4. primitives and their class equivalents
2. Files
   1. open
   2. read
   3. close
3. Exception
   1. throw and catch
4. example of reading a file with try/catch block
5. writing to a file

## Lecture 16

1. Array: a whole bunch of information
   1. ordered
   2. homogeneous
   3. syntax: type[] id = new type[size]
   4. iterating over an array
2. Unintialized array of objects contains null
3. `++` pre vs post increment
4. actual vs effective size of array
   1. example: reading miterm scores
5. passing array as an parameter, always pass by reference
6. `computeAverage`
7. buggy and happy array element swap
8. initialization at declaration
9. Hangman program part 3 uses `ArrayList`
10. `ArrayList` grows for user automatically

## Lecture 17

1. Multi-dimensional array
2. `ArrayList` is an template, parameterized by a type
3. `ArrayList` methods
4. example of reading, storing in `ArrayList` and printing lines
5. `ArrayList<int>` does not work, one has to use `ArrayList<Integer>`
6. example of appearing and falling numbers
7. example of grayscale image

## Lecture 18

1. Clarafication on multi-dim arrays
   1. example of read and print mid/final scores in multi-dim array
   2. same program using `ArrayList`
2. Pros and cons of array vs `ArrayList`
3. Debugging: zen/art and practical tools
   1. Design - Architect
   2. Coding - Engineer
   3. Testing - Vandal
   4. Debugging - Detective
4. 10x rule
5. three things causing bugs
   1. bad variable values
   2. faulty logic
   3. unwarranted assumptions
6. good practices of debugging
   1. bugs could be simple
   2. be systematic
   3. assumptions about the problem
   4. be critical
   5. don't panic
7. approaches
   1. `println` for variables, method entry/exit, etc.
   2. unit test
8. example of gambling Roulette

## Lecture 19

1. Interface: set of methods some classes share
   1. Syntax: implements
2. Map interface
   1. map: key-value storage
   2. examples: dict, phone book
3. `HashMap`
   1. usage: key type and value type
   2. value type must be class (wrapper for primitive types)
4. Could use `Map<String, String> m = new HashMap<String, String>()`, where `Map` is an interface
5. `ArrayList` is ordered, `HashMap` is not
6. The `Collection` and `Map` hierarchy
7. `Iterator` for `ArrayList`
8. `Iterator` for keys of `HashMap`
9. for each loop

## Lecture 20

1. GUI
   1. components of `Swing`
   2. example of clicking button and display texts
   3. example of advanced click for face

## Lecture 21

1. Interactors
   1. `getSource` of an action event
   2. example of using `getSource` instead of extracting command from event
   3. Text Field and `JTextField`
   4. example of attaching listeners and printing name entered
2. Layout
   1. default board layout
   2. grid layout - filled up with dynamic resizing
   3. table layout
3. example of fahrenheight to celcius conversion table layout program
4. Combining text and graphics

## Lecture 22

1. next assignment: NameSurfer demo
2. Component and Container
3. example of `MyCanvas` capable of realtime resizing
4. example of music data management
   1. `Album` class
   2. Using a `HashMap` to keep track of all albums
   3. top down design of `parseLine` to construct an `Album`

## Lecture 23

1. Searching and sorting
2. linear search
3. binary search
4. efficiency of searching algorithms
5. selection sort
6. other sort algorithms

## Lecture 24

1. Data structures
2. principles of managing data
   1. nouns -> classes
   2. verbs -> methods
   3. unique identifier
3. Example of FlyTunes

## Lecture 25

1. Demo of social network
2. six degrees of separation
3. concurrency
   1. already concurrency in breakout game
   2. concept of thread
   3. `Runnable` interface
   4. example of spawning multiple threads sliding colorful squares
4. Shared data
   1. example of racing squares

## Lecture 26

1. back to the standard Java world: the `main` method
2. JAR file
3. the manifest file and the class path
4. serving jar as applet in web pages
5. example of coding in plain java insteadof acm

## Lecture 27

1. Life after cs106a
   1. cs106b: programing abstractions, implementing hash, recursion
   2. cs103a/b/x: discrete math
   3. cs108: OOP, larger applications
   4. cs107: low level
   5. cs121 & cs221: AI
   6. cs140: OS
   7. cs161: data structures
   8. etc
2. majors
   1. CS
   2. EE: hardware
   3. Math + Computational Science: mathmatecal side
   4. Symbolic systems: liguistics + cs + philosophy + psychology
3. other stuff

## Lecture 28

1. Awards
2. Review
   1. On: chapter 2-13
      1. build a program
      2. object/class/interface
      3. method calls and pass-by-value/reference
      4. primitives vs objects
      5. String ops
      6. Graphics of ACM library
      7. event driven programs
      8. data structures: array, array list
      9. collection: hashmap, iterator
      10. files: reading at least
   2. Not on
      1. history: chap 1
      2. Karel
      3. stack/heap diagram
      4. bit ops on images
      5. polar coords
      6. layouts
      7. sorting
      8. threads (advanced topics)
      9. standard java: main method, JAR
3.  Sample exam programs