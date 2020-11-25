# simpleConsoleDrawing
A program to draw on console

# usage
```bash
Command 		Description
C w h           Should create a new canvas surface of width w and height h.
L x1 y1 x2 y2   Should create a new line from (x1,y1) to (x2,y2). Currently only
                horizontal or vertical lines are supported. Horizontal and vertical lines
                will be drawn using the 'x' character.
R x1 y1 x2 y2   Should create a new rectangle, whose upper left corner is (x1,y1) and
                lower right corner is (x2,y2). Horizontal and vertical lines will be drawn
                using the 'x' character.
Q               Should quit the program.
```

# commands

* From prebuilt JAR: java -jar console-drawing-1.0.jar
* From source:
  * Run program: mvn exec:java

# Important
The real one is [here](https://github.com/bobbui/console-drawing) already created by bobbui
