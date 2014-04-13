
public class Square {
	public boolean current; //a variable for holding whether there is a square in the square (if there is no square, the square variable will still be in the grid, but `current` will be set to false)
	public int x; //a variable for holding the x
	public int y; //a variable for holding the y
	public Square below; //holds the square the square

	//a constructor, with the arguments of the x and y
	public Square(int x, int y) { 
		this.current = false; //sets current to be false (there is no square there yet)
		this.x = x; //sets x to the x argument
		this.y = y; //sets y to the y argument
	}

	//drops all of the squares by calling drop for every square
	public static void dropAll() {
		for (int x = 0; x < Main.gameWidth; x++) { //loops for gameWidth
			for (int y = 0; y < Main.gameHeight; y++) { //loops for gameHeight
				if (Main.grid[x][y].current) { //if there is a square in the `square`
					Main.grid[x][y].drop(); //calls drop for the square
				}
			}
		}
	}

	//tries to drop the square
	public void drop() {
		if (this.y > 0 && !this.below.current) { //if the square below doesn't have a square
			this.current = false; //sets the square to be false
			this.below.current = true; //sets the below square to be true
			this.below.drop(); //tries to drop the below square
		}
	}

	//spawns a new square randomly on the top
	public static void spawnNew() {
        int ranX = (int) (Math.random() * Main.gameWidth); //sets the ranX variable to equal a random number between 0 and the gameWidth
        if (!Main.grid[ranX][Main.gameHeight -1].current) { //if there isn't a square in the square
        	Main.grid[ranX][Main.gameHeight -1].current = true; //sets the square to be true
        	Main.grid[ranX][Main.gameHeight -1].drop(); //tries to drop the square
        } else { //if there is a square already when it tries to spawn a square
        	Main.finishedRun = true; //sets the finishedRun to be true
        }
	}

	//clears the grid
	public static void clearGrid() {
		for (int x = 0; x < Main.gameWidth; x++) { //for the gameWidth
			for (int y = 0; y < Main.gameHeight; y++) { //for the gameHeight
				Main.grid[x][y].current = false; //sets all squares.current to false
			}
		}
	}
}