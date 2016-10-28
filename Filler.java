/**
 * This class will attempt to fill a Sudoku puzzle with a solution.
 * 
 * Utilizing a recursive call to the fillBoard() method, it will try every
 * possible solution in each square. If at any point,it expends all possible 
 * values of any individual square, it will return false all the way down.
 * 
 * If it reaches the end of the board, having filled all previous spaces, it
 * will replace the field puzzle with the solved puzzle, which can then be
 * printed by calling the printAllRows() method.
 * 
 * @author Michael Crinite
 * @version 09.24.2016
 */
public class Filler {
    int[][] puzzle;             // The inputted puzzle
    int width;                  // The amount of columns in the puzzle
    int height;                 // The amount of rows in the puzzle    
    int cSize;                  // The amount of columns in one block
    int rSize;                  // The amount of rows in one block
    
    Checker checker = Checker.getInstance();
    
    /**
     * Driver method.
     * Utilizes test methods to test a default puzzle for solutions.
     * 
     * @param args Default main method arguments 
     */
    public static void main(String args[]){
        
        final long startTime = System.currentTimeMillis();//Take start time
        
        Filler filler = new Filler(Filler.testPuzzle(), 2, 3);
        filler.fillBoard(Filler.testPuzzle(), 0, 0);
        filler.printAllRows();
        
        final long endTime = System.currentTimeMillis();  //Take end time
        System.out.println("Execution time: " + (endTime - startTime) + " ms");
    }
    
    /**
     * Constructor for type SudokuFiller
     * Creates a puzzle board from a passed-in argument of type 2D array
     * 
     * @param sudoku Two-dimensional array read in from a file
     */
    public Filler(int[][] sudoku, int h, int w){
        puzzle = sudoku;
        width = sudoku[0].length; //This assumes equal length for every row
        height = sudoku.length;        
        rSize = h;
        cSize = w;
        
    }
    
    /**
     * This method attempts to fill the Sudoku board.
     * 
     * It will attempt to fill each square with each possible value, checking
     * along the way that the value is valid in that position. If at any point
     * it expends every possible value for a single space, it will return false.
     * 
     * If it reaches the end of the possible, having filled in every space, it
     * will return true.
     * 
     * @param board The board to be solved.
     * @param row The row at which to begin solving (always enter 0)
     * @param col The column at which to begin solving (always enter 0)
     * @return True if the puzzle is solved, false if unsolvable
     */
    public boolean fillBoard(int[][] board, int row, int col){
        int index = 1;              //Next value to try when an empty is found
        boolean solved = false;
        
        for(int i = row; i < width; i++){       //For each row
            for(int j = col; j < height; j++){  //For each column
                if(board[i][j]==0){             //0 represents empty
                    while(!solved){             //While the puzzle is unsolved
                        board[i][j] = index;    //Try to fill with index
                        if(checker.checkRow(board[i]) 
                                && checker.checkColumn(columnToArray(board, j))
                                && checker.checkBox(board, i, j, 
                                        index, rSize, cSize)
                                ){
                                solved = fillBoard(board, i, 0); //Next space
                        }
                        if(!solved){
                            board[i][j] = 0;
                        
                            if(index < width){
                                index++;
                            }else{
                                return false;
                            }  
                        }
                    }
                }
            }
        }
        puzzle = copyPuzzle(board);
        return true;
    }
    
    /**
     * Converts a single column in an array to a new array
     * 
     * @param col Column to create an array from
     * @return The new array, composed of the column contents from top to bottom
     */
    private int[] columnToArray(int[][] array, int col){
        int[] column = new int[height];
        for(int row = 0; row < height; row++){
            column[row] = array[row][col];
        }
        
        return column;
    }
    
    /**
     * Copies a puzzle
     * 
     * @param from The puzzle from which to copy
     * @return  The copied puzzle
     */
    private int[][] copyPuzzle(int[][] from){
        int[][] copy = new int[height][width];
        
        for(int i = 0; i < from.length; i++){
            for(int j = 0; j < from[i].length; j++){
                copy[i][j] = from[i][j];
            }
        }
        return copy;
    }
    
    /**
     * Prints a single row to console
     * 
     * @param row The index of the row which is to be printed.
     */
    private void printRow(int row){
        for(int i : puzzle[row]){
            System.out.print(i + " ");
        }
    }
    
    /**
     * Prints the entire puzzle to the console by row using printRow
     */
    public void printAllRows(){
        for(int i = 0; i < height; i++){
            printRow(i);
            System.out.println();
        }
    }
    
    /**
     * Creates a new puzzle with blanks
     * @return A solvable 6x6 puzzle 
     */
    private static int[][] testPuzzle(){
        int[][] multi = new int[][]{
            { 1, 0, 3, 4, 0, 0 },
            { 4, 0, 6, 0, 0, 3 },
            { 2, 0, 1, 0, 6, 0 },
            { 5, 0, 4, 2, 0, 0 },
            { 3, 0, 2, 0, 4, 0 },
            { 6, 0, 5, 0, 0, 2 }};
        return multi;
    }
    
    /**
     * Creates a smaller fake puzzle
     * @return A solvable 4v4 puzzle
     */
    private static int[][] testPuzzle2(){
        int[][] multi = new int[][]{
            {1, 2, 0, 0},
            {0, 4, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 3, 2}};
        return multi;
    }
    
    /**
     * Creates the smallest puzzle
     * @return A solvable 2x2 puzzle
     */
     private static int[][] testPuzzle3(){
        int[][] multi = new int[][]{
            {1, 0},
            {2, 0}};
        return multi;
    }
     
    /**
     * Creates a new puzzle with blanks
     * @return A solvable 6x6 puzzle 
     */
    private static int[][] testPuzzle4(){
        int[][] multi = new int[][]{
            { 0, 0, 0, 1, 0, 5, 0, 6, 8 },
            { 0, 0, 0, 0, 0, 0, 7, 0, 1 },
            { 9, 0, 1, 0, 0, 0, 0, 3, 0 },
            { 0, 0, 7, 0, 2, 6, 0, 0, 0 },
            { 5, 0, 0, 0, 0, 0, 0, 0, 3 },
            { 0, 0, 0, 8, 7, 0, 4, 0, 0 },
            { 0, 3, 0, 0, 0, 0, 8, 0, 5 },
            { 1, 0, 5, 0, 0, 0, 0, 0, 0 },
            { 7, 9, 0, 4, 0, 1, 0, 0, 0 }};
        return multi;
    }
}