import java.util.Scanner;


public class Main {
	public static int gameHeight = 22;
	public static int gameWidth = input();
	public static Square[][] grid = new Square[gameWidth][gameHeight];
	
	private static int currentRuns = 0;
	public static boolean finishedRun = false;
	private static int droppedSquares = 0;

	public static void main(String args[]) {
		initialize();
		
		while (currentRuns < 100) {
			while (!finishedRun) {
				Square.spawnNew();
				Square.dropAll();
				droppedSquares++;
			}
			currentRuns++;
			System.out.println("Run " + currentRuns + " count is " + droppedSquares);
			finishedRun = true;
			droppedSquares = 0;
			Square.clearGrid();
		}
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
}
