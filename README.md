# Brute Force/Backtracking Sudoku Solver

This is a Sudoku solver that uses a brute force/backtracking approach. It is written entirely in Java.

This Sudoku solver reads a file from the command line. If it is properly formatted (see below), it stores the puzzle as a 2D Array and attempts to solve.

The solver checks each cell sequentially, from top left to bottom right, and if empty, it attempts to fill the cell. It does this by filling the space in a temporary puzzle and then making a recursive call to the solving method, passing that puzzle as if the space was solved. If that does not lead to a solution, it simply backtracks to that call and tries to fill the space with something else until it has exhausted all possibilities.

The solver works for any Sudoku puzzle size W x H (where W and H are the dimensions of a single box/partition of the puzzle) up to a puzzle with 400 spaces. It can easily be modified to support only the typical 3 x 3 (81 cell) puzzle.

------------------------------------
- The input class was written by Jacob Caggese
- The Filler class was written by me, Michael Crinite
- The Checker class was written by Alexander Luongo, and edited by Michael Crinite for version 2
- The SudokuDriver class was written by me, Michael Crinite


10/16/2016

##Proper formatting:
- The first line should contain the AMOUNT OF COLUMNS IN A SINGLE BOX/PARTITION OF THE PUZZLE. This is the same as the amount of rows of boxes/partitions in the puzzle. The first partition contains the `0 3 0` from the first line and the `2 0 0` from the second.
- The second line should contain the AMOUNT OF ROWS IN A SINGLE BOX/PARTITION OF THE PUZZLE.
- The rest of the puzzle should be laid out so that each number represents one space in the puzzle. A `0` denotes a blank space, which it is the solver's objective to fill.

```
3
2
0 3 0 0 0 1
2 0 0 0 0 0
0 2 0 0 1 0
0 0 0 0 0 6
0 0 4 6 5 0
5 0 2 0 4 0
```
