/*
 * Random Falling
 * Coded by Rafi Long
 * 
 * See Main.java more for documentation
 */

/*
 * Class: Square
 * Purpose: to provide a Square object and manipulation of that object
 */

public class Square {
    /* Instance Variables */
	public boolean active;
	public int x;
	public int y;
	public Square below;

	public Square(int x, int y) { 
		this.active = false;
		this.x = x;
		this.y = y;
	}

	public static void dropAll() {
		for (int x = 0; x < Main.gameWidth; x++) {
			for (int y = 0; y < Main.gameHeight; y++) {
				if (Main.grid[x][y].active) {
					Main.grid[x][y].drop();
				}
			}
		}
	}

	public void drop() {
		if (this.y > 0 && !this.below.active) {
			this.active = false;
			this.below.active = true;
			this.below.drop();
		}
	}

	public static void spawnNew() {
        int ranX = (int) (Math.random() * Main.gameWidth);
        if (!Main.grid[ranX][Main.gameHeight -1].active) {
        	Main.grid[ranX][Main.gameHeight -1].active = true;
        	Main.grid[ranX][Main.gameHeight -1].drop();
        } else {
        	Main.finishedRun = true;
        }
	}

	public static void clearGrid() {
		for (int x = 0; x < Main.gameWidth; x++) {
			for (int y = 0; y < Main.gameHeight; y++) {
				Main.grid[x][y].active = false;
			}
		}
	}
}