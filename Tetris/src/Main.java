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

/*
 * Class: Main
 * Purpose: to perform calculations and provide user input and output
 */

public class Main {
	/* Grid variables */
	public static int gameHeight = 22;
	public static int gameWidth = input("What is the width of the gameboard?");
	public static Square[][] grid = new Square[gameWidth][gameHeight];

	/* Calculation variables */
	private static int totalRuns = 5;
	public static boolean finishedRun = false;
	private static int droppedSquares = 0;
	private static ArrayList<Integer> droppedSquaresList = new ArrayList<Integer>();

	public static void main(String args[]) {
		initialize();
		System.out.println("Run counts are:");
		
		for (int i = 0; i < totalRuns; i++) {
			while (!finishedRun) {
				Square.spawnNew();
				Square.dropAll();
				droppedSquares++;
				checkClearLine();
			}
			System.out.println(droppedSquares);
			droppedSquaresList.add(droppedSquares);
			finishedRun = false;
			droppedSquares = 0;
			Square.clearGrid();
		}
		System.out.println("Average dropped squares are " + findAverage());
	}

	private static void initialize() {
		for (int x = 0; x < gameWidth; x++) {
			for (int y = 0; y < Main.gameHeight; y++) {
				grid[x][y] = new Square(x, y);
				if (y > 0) {
					grid[x][y].below = grid[x][y-1];
				} else {
					grid[x][y].below = null;
				}
			}
		}
	}

	private static int input(String question) {
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

	private static void checkClearLine() {
		boolean allAlive = true;
		for (int x = 0; x < gameWidth; x++) {
			if (!grid[x][0].active) {
				allAlive = false;
			}
		}
		if (allAlive) {
			clearLine(0);
		}
	}

	private static void clearLine(int y) {
		for (int x = 0; x < gameWidth; x++) {
			grid[x][y].active = false;
		}
		Square.dropAll();
	}

	private static double findAverage() {
		double total = 0;
        for (Integer aDroppedSquaresList : droppedSquaresList) {
            total += aDroppedSquaresList;
        }
		return (total/droppedSquaresList.size());
	}
	
	
	@SuppressWarnings("unused")
	private static void printGrid() {
		for (int y = gameHeight -1; y >= 0; y--) {
			for (int x = 0; x < Main.gameWidth; x++) {
				if (grid[x][y].active) System.out.print("1 ");
				if (!grid[x][y].active) System.out.print("0 ");
			}
			System.out.println();
		}
		System.out.println();
	}
}