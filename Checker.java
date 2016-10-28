import java.util.HashSet;

/**
 * 1.0: Contains methods that check that sudoku rules are met when numbers are added into puzzle
 *      Arrays are put into HashSets to check for duplicates in each row,column,and box.
 *
 * 1.1: Changed datatype from HashSet to Array-Based Abstract Datatype for checking in O(1) rather
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

    public static Checker getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Checker();
        }
        return INSTANCE;
    }

    /**
     * Checks the input column to see if Sudoku rules are met
     * @param (col)column to be checked
     * @return does column meet rules(true/false)
     */
    public boolean checkColumn(int[] col){
        HashSet<Integer> set = new HashSet<Integer>();
        for(int i = 0; i < col.length;i++){
            if(!set.add(col[i])  && col[i] > 0){//Duplicate?
                return false;
            }
        }
        return true;
    }

    /**
     * Checks the input row to see if Sudoku rules are met
     * @param (row)row to be checked
     * @return does row meet rules(true/false)
     */
    public boolean checkRow(int[]row){
        HashSet<Integer> set = new HashSet<Integer>();
        for(int i = 0; i < row.length;i++){
            if(!set.add(row[i]) && row[i] > 0){//Duplicate?
                return false;
            }
        }
        return true;
    }


    /**
     * Checks the input box to make sure Sudoku rules are met
     * @param board, width and height of a box
     * @return does box meet rules(true/false)
     */
    public boolean checkBox(int[][] board, int col, int row, int num, int rSize, int cSize){
        HashSet<Integer> set = new HashSet<Integer>();

        int rBox = row/rSize;
        int cBox = col/cSize;
        for (int r=rSize*rBox; r < rSize*rBox+rSize; r++){
            for (int c=cSize*cBox; c < cSize*cBox+cSize; c++){
                if(board[r][c] > 0 && !set.add(board[r][c])){
                    return false;
                } 
            }
        }
        return true;
    }			
}
