import java.io.File;

/**
 * This is the driver class for the Input, Filler, and Checker
 * classes that solve a Sudoku Puzzle
 * 
 * @author Michael Crinite
 */
public class SudokuDriver {
    public static void main(String[] args){
        try{
            final long startTime = System.currentTimeMillis();//Take start time

            if(args.length > 0){
                File file = new File(args[0]);
                Input input = new Input(file);
                Filler filler = new Filler(input.getGrid(), 
                                            input.getH(), 
                                            input.getW());
                //Checker is created within the Filler object

                if(!filler.checker.isValid(filler.puzzle)
                        || !filler.fillBoard(filler.puzzle, 0, 0)){
                    System.out.println("This puzzle has no valid solution.");
                }else{
                    filler.printAllRows();
                }           
            }else{
                System.out.println("You must provide a filename.");
            }

            final long endTime = System.currentTimeMillis();  //Take end time
            System.out.println("Execution time: " + (endTime - startTime) + " ms");
        }catch(Exception e){
            System.out.println("SudokuSolver cannot read that file, or a valid path was not provided.");
        }
        
    }
}
