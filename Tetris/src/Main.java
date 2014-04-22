import java.util.ArrayList;
import java.util.Scanner;

/*
 * Random Falling
 * Coded by Rafi Long
 * Reviewed by Phil Long
 * 
 * This program is meant to calculate the average amount of squares dropped in a Tetris game if each gamepiece, or Tetrimo, was a single square.
 * However, this program allows you to change the gameboard width.
 * 
 * View the graphed data below:
 * https://docs.google.com/presentation/d/197B9tDgbns_cOALpqiPZdx58X_jv4NQK8UDJqL6JUAQ/edit?usp=sharing
 * 
 * Read about the classes at the top of each file.
 * As always, documentation of the code is in the code.
 * Thank you for checking out my code! I hope you have fun!
 */

/*
 * Terms:
 * Grid - A grid of `tiles` (see below) of height 22 and variable width.
 * Tile - A space in the `grid` (see above) where `squares` (see below) can potentially be.
 * Square - A space on the `grid` (see above) that is occupied. In other words, an active `tile`.
 * Run - What would be called a tetris `game`. A cycle, started by the spawning of one block and ending with one column of `squares` (see above) reaching the top.
 */

/**
 * Performs calculations and interprets user input
 *
 * @author Rafi Long
 * @see Tile
 * @see Grid
 *
 * @version 1
 * @since 0.1
 */
public class Main {
    /**
     * Stores the amount of total runs
     */
	private static int totalRuns = 5;

    /**
     * Stores whether the current run is finished
     */
	public static boolean finishedRun = false;

    /**
     * Stores the amount of dropped squares for the current run
     */
	private static int droppedSquares = 0;

    /**
     * Stores the total amount of dropped squares
     */
    //TODO remove array list and replace with an int
	private static ArrayList<Integer> droppedSquaresList = new ArrayList<Integer>();

    /**
     * The grid that all calculations are run on
     */
    private static Grid grid = new Grid();

    /**
     * Initializes the grid and runs methods to run the simulation
     *
     * @param args unused parameter
     */
	public static void main(String args[]) {
		grid.initialize();
		System.out.println("Run counts are:");
		
		for (int i = 0; i < totalRuns; i++) {
			while (!finishedRun) {
				grid.spawnNew();
                //TODO check whether this is necessary
				grid.dropAll();
				droppedSquares++;
				grid.checkClearLine();
			}
			System.out.println(droppedSquares);
			droppedSquaresList.add(droppedSquares);
			finishedRun = false;
			droppedSquares = 0;
			grid.clearGrid();
		}
		System.out.println("Average dropped squares are " + findAverage());
	}

    /**
     * Asks a question and gets user input by using a scanner
     *
     * @param question Prints the question before opening a scanner
     * @return The scanner
     */
	public static int input(String question) {
		System.out.println(question);
		Scanner sc = new Scanner(System.in);
		String text = sc.nextLine();
		sc.close();
		try {
			return Integer.parseInt(text);
		} catch (NumberFormatException e) {
			System.err.println("Enter a valid integer");
			return input(question);
		}
	}

    /**
     * Finds the average blocks dropped
     *
     * @return The average number of blocks dropped
     */
	private static double findAverage() {
		double total = 0;
        for (Integer aDroppedSquaresList : droppedSquaresList) {
            total += aDroppedSquaresList;
        }
		return (total/droppedSquaresList.size());
	}
}