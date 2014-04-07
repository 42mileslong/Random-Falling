
public class Square {
	public boolean current;
	public int x;
	public int y;
	public Square below;
	
	public Square(int x, int y) {
		this.current = false;
		this.x = x;
		this.y = y;
	}
	
	public static void dropAll() {
		for (int x = 0; x < Main.gameWidth; x++) {
			for (int y = 0; y < Main.gameHeight; y++) {
				Main.grid[x][y].drop();
			}
		}
	}
	
	private void drop() {
		boolean noneBelow = false;
		while (!noneBelow) {
			if (this.y > 0 && !this.below.current) {
				this.current = false;
				this.below.current = true;
				this.resetBelow();
			} else {
				noneBelow = true;
			}
		}
	}
		
	private void resetBelow() {
		if (this.y > 0) {
			this.below = Main.grid[x][y-1];
		} else {
			this.below = null;
		}
	}
	
	public static void spawnNew() {
        int ranX = (int) (Math.random() * Main.gameWidth);
        if (!Main.grid[ranX][Main.gameHeight -1].current) {
        	Main.grid[ranX][Main.gameHeight -1].current = true;
        	Main.grid[ranX][Main.gameHeight -1].drop();
        } else {
        	Main.finishedRun = true;
        }
	}
	
	public static void clearGrid() {
		for (int x = 0; x < Main.gameWidth; x++) {
			for (int y = 0; y < Main.gameHeight; y++) {
				Main.grid[x][y].current = false;
			}
		}
	}
}
