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
	public boolean active; //a variable for holding whether there is a square in the tile
	public int x; //a variable for holding the x
	public int y; //a variable for holding the y
	public Square below; //a pointer to the square in the tile beneath the current square

	//a constructor, with the arguments of the x and y
	public Square(int x, int y) { 
		this.active = false; //sets active to be false (there is no square there yet)
		this.x = x; //sets x to the x argument
		this.y = y; //sets y to the y argument
	}

	//drops all of the squares by calling drop for every square
	public static void dropAll() {
		for (int x = 0; x < Main.gameWidth; x++) { //loops for gameWidth
			for (int y = 0; y < Main.gameHeight; y++) { //loops for gameHeight
				if (Main.grid[x][y].active) { //if there is a square in the `square`
					Main.grid[x][y].drop(); //calls drop for the square
				}
			}
		}
	}

	//tries to drop the square
	public void drop() {
		if (this.y > 0 && !this.below.active) { //if the tile below doesn't have a square
			this.active = false; //sets the current square to be false
			this.below.active = true; //sets the tile beneath the current square to be active
			this.below.drop(); //tries to drop the below square
		}
	}

	//spawns a new square randomly on the top of the grid
	public static void spawnNew() {
        int ranX = (int) (Math.random() * Main.gameWidth); //sets the ranX variable to equal a random number between 0 and the gameWidth
        if (!Main.grid[ranX][Main.gameHeight -1].active) { //if there isn't a square in the chosen tile
        	Main.grid[ranX][Main.gameHeight -1].active = true; //sets the tile, and therefore the square, to be true
        	Main.grid[ranX][Main.gameHeight -1].drop(); //tries to drop the square
        } else { //if there is a square already in the tile when it tries to spawn a square
        	Main.finishedRun = true; //sets the finishedRun to be true
        }
	}

	//clears the grid (used after a run is finished
	public static void clearGrid() {
		for (int x = 0; x < Main.gameWidth; x++) { //for the gameWidth
			for (int y = 0; y < Main.gameHeight; y++) { //for the gameHeight
				Main.grid[x][y].active = false; //sets all squares.active to false
			}
		}
	}
}