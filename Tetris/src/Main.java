import java.util.ArrayList;
import java.util.Scanner;


public class Main {
	public static int gameHeight = 22;
	public static int gameWidth = input();
	public static Square[][] grid = new Square[gameWidth][gameHeight];

	private static int currentRuns = 0;
	public static boolean finishedRun = false;
	private static int droppedSquares = 0;
	private static ArrayList<Integer> droppedSquaresList = new ArrayList();

	public static void main(String args[]) {
		initialize();
		System.out.println("Run counts are:");
		
		while (currentRuns < 100) {
			while (!finishedRun) {
				Square.spawnNew();
				Square.dropAll();
				droppedSquares++;
				checkClearLine();
//				printGrid();
//				System.out.println();
			}
			System.out.println(droppedSquares);
			droppedSquaresList.add(droppedSquares);
			currentRuns++;
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

	private static int input() {
		System.out.println("What is the width of the gameboard?");
		Scanner sc = new Scanner(System.in);
		String text = sc.nextLine();
		sc.close();
		try {
			return Integer.parseInt(text);
		} catch (NumberFormatException e) {
			System.err.println("Enter a valid integer");
			return input();
		}
	}
	
	private static void printGrid() {
		for (int y = gameHeight -1; y >= 0; y--) {
			for (int x = 0; x < Main.gameWidth; x++) {
//				System.out.println(grid[x][y].current);
				if (grid[x][y].current) System.out.print("1 ");
				if (!grid[x][y].current) System.out.print("0 "); 
			}
			System.out.println();
		}
	}
	
	private static void checkClearLine() {
		boolean allAlive = true;
		for (int x = 0; x < gameWidth; x++) {
			if (!grid[x][0].current) {
				allAlive = false;
			}
		}
		if (allAlive) { 
			clearLine(0);
		}
	}
	
	private static void clearLine(int y) {
		for (int x = 0; x < gameWidth; x++) {
			grid[x][y].current = false;
		}
		Square.dropAll();
	}
	
	private static double findAverage() {
		double total = 0;
		for (int i = 0; i < droppedSquaresList.size(); i++) {
			total += droppedSquaresList.get(i);
		}
		return (total/droppedSquaresList.size());
	}
}