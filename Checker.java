/**
 * 1.0: Contains methods that check that Sudoku rules are met when numbers are added into puzzle
 *      Arrays are put into HashSets to check for duplicates in each row,column,and box.
 *
 * 1.1: a. Changed class to a singleton class. This minimizes memory usage because every call to the
 *      Checker class will now be a call to the same exact Checker object. Not only that, but because
 *      the checkADT (described below) is also static, each call to Checker will change the same
 *      "collection" in order to check the row/column/box
 *
 *      b. Changed datatype from HashSet to Array-Based Abstract Datatype for checking in O(1) rather
 *      than risking an O(N^2)
 *
 *      [ 0 , 1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 , ... ]
 *
 *      The ADT will initialize to false. When a value is found in the area of the puzzle being checked
 *      then ADT[value] will become true. This way, if the value is found in the same area of the puzzle
 *      a second time, the ADT will already be true, indicating that the value had previously been found
 *      in the part of the puzzle being checked.
 *
 * @author Alex Luongo, Michael Crinite
 * @version 1.1 10/28/16
 */
public class Checker {
    private static Checker INSTANCE = null;
    private static boolean[] checkADT = new boolean[20];    // Array ADT for checking puzzle
                                                            // Initializes to false
                                                            // Default size 20 because that accommodates
                                                            //      a very large puzzle size

    /**
     * Constructor for Checker
     */
    private Checker(){
    }

    /**
     * Creates an object of this class ONLY if one does not already exist
     *
     * Ensures the absolute minimum amount of memory will be used by Checker because
     * any call to Checker will be a call to the same exact object in memory
     *
     * @return The singleton instance of the Checker class
     */
    public static Checker getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Checker();
        }
        return INSTANCE;
    }

    /**
     * Checks the input row or column to see if Sudoku rules are met
     * @param (arr) row or column to be checked
     * @return does row or column meet rules(true/false)
     */
    public boolean checkRowOrCol(int[] arr){
        restore();                              // Reset the checker array to false
        int curr = 0;
        for(int i = 0; i < arr.length;i++){     // For each value in the array to check
            curr = arr[i];
            if(checkADT[curr]){                 // If the checker has already found the value
                return false;                   // Return false
            }else if(curr > 0){                 // else, if the space is not blank
                checkADT[curr] = true;          // Mark the value in the space as having been filled.
            }
        }
        return true;                            // Return true only if the entire array passes.
    }


    /**
     * Checks the input box to make sure Sudoku rules are met
     *
     * @param board, width and height of a box
     * @return does box meet rules(true/false)
     */
    public boolean checkBox(int[][] board, int col, int row, int rSize, int cSize){
        restore();  // Reset the array to false
        int curr = 0;

        int rBox = row/rSize;
        int cBox = col/cSize;
        for (int r=rSize*rBox; r < rSize*rBox+rSize; r++){
            for (int c=cSize*cBox; c < cSize*cBox+cSize; c++){
                curr = board[r][c];
                if(checkADT[curr]){
                    return false;               // Once again, the checker has found a value it already marked
                }else if(curr > 0){
                    checkADT[curr] = true;      // Mark the found value as true.
                }
            }
        }
        return true;
    }

    /**
     * Resets the array to false
     */
    public void restore(){
        checkADT = new boolean[20];
    }

    /**
     * Checks whether the values in the unsolved puzzle are valid to begin with.
     *
     * @param board The board to be checked
     * @return <code>True</code> only if the puzzle is a valid puzzle
     */
    public boolean isValid(int[][] board){
        int size;
        if(board.length > 0){
            size = board.length;
        }else{
            return false;
        }

        for(int i = 0; i < size; i++) {       //For each row
            for(int j = 0; j < size; j++) {
                if(board[i][j] > size) {
                    return false;
                }
            }
        }
        return true;
    }
}
