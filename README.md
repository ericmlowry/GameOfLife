# GameOfLife

Run program:
1. Open terminal in the src folder.
2. Type 'javac GameOfLife.java'
3. Type 'java GameOfLife'

Run unit tests:
Open project in Eclipse and 
Run "test\GameOfLifeTest.java" as JUnit test.
or
Run "src\TestExecutor.java".
Not sure how to successfully do this in terminal - junit.jar is in test.
Not sure how to successfully simulate input and check code output for unit testing initializeGrid.
Experimenting with JUnit took 4x as long as writing the program itself.

Game of Life:
1. The program uses user input to initialize the board asking for any number of rows and columns.
2. The user can create the board row by row. The program will ask for the row again if the column number is wrong or the user input a char other than 'o' or '.'.
3. The program will then ask the user if they want to create another board, progress to the next state, or be done.
4. If the user chooses to create another board, 1 and 2 are repeated. Then 3.
5. If the user chooses to progress to the next state, the board is updated according to the rules of the Game of Life and displayed to the user.
6. The new state of the board is now the new board. 3 is repeated.
7. If the user is done, the program terminates.

Additional features:
"any number of rows and columns" demonstrating robustness 
"new state of the board is now the new board" allowing the user to continue the game

Design:
newStateCell sets booleans before the count to use conditionals to short circuit checks. This efficiently handles borders and corners and determines the next state for a single cell.
setNewStateGrid uses newStateCell as a helper function and uses it's own code for making gridAsString to not have to use arrayToString and save a loop.
setNewStateGrid probably shouldn't update the state and rather just return a 2d boolean array (for robustness) because it is doing too much. However in this case, allowing this made the program simpler.
gridAsString exists so the string is saved rather than having to translate from the 2d boolean array everytime a function wants to show the user.
initializeGrid was designed to guarantee a board from the user by the end of the function. stringAsOfNow is used to make it easier for the user to see the board they have developed. 
arrayToString was used in initializeGrid because there wasn't a guarantee that the user dictated row was valid during the loop.
stringToArray doesn't and shouldn't exist because gridAsString should be derivative of grid.
