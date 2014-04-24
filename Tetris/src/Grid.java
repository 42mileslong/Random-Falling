/*
 * Random Falling
 * Coded by Rafi Long
 *
 * See Main.java more for documentation
 */

/**
 * Provides Grid object
 * Provides manipulation of the Grid object
 *
 * @author Rafi Long
 * @see Main
 * @see Tile
 *
 * @version 1
 * @since 0.1
 */
public class Grid {
    /**
     * Height of the grid
     * Default is 22
     */
    public static int gameHeight = 22;

    /**
     * Width of the grid
     * Default is 20
     * Gets user input
     */
    public static int gameWidth = Main.input("What is the width of the gameboard?");

    /**
     * A grid of squares that the simulation is performed on
     */
    private Tile[][] grid = new Tile[gameWidth][gameHeight];

    /**
     * Calls initialize when a Grid is created
     *
     * @see #initialize()
     */
    public Grid() {
        this.initialize();
    }

    /**
     * Fills `grid` with squares
     * Sets `below` to be the square below the current square
     *      On the bottom row `below` is set to null
     */
    public void initialize() {
        for (int x = 0; x < gameWidth; x++) {
            for (int y = 0; y < gameHeight; y++) {
                this.grid[x][y] = new Tile(y);
                if (y > 0) {
                    this.grid[x][y].below = this.grid[x][y-1];
                } else {
                    this.grid[x][y].below = null;
                }
            }
        }
    }

    /**
     * Checks whether the bottom line needs to be cleared
     * As only one block is spawned at a time, no other lines will need to be cleared
     */
    public void checkClearLine() {
        boolean allAlive = true;
        for (int x = 0; x < gameWidth; x++) {
            if (!this.grid[x][0].active) {
                allAlive = false;
            }
        }
        if (allAlive) {
            this.clearLine(0);
        }
    }

    /**
     * Clears the line
     * Sets all squares .active to be false
     * Calls the dropall method
     *
     * @see #dropAll()
     * @param y y of Line cleared
     */
    private void clearLine(int y) {
        for (int x = 0; x < gameWidth; x++) {
            this.grid[x][y].active = false;
        }
        this.dropAll();
    }

    /**
     * Calls Tile drop method for all active tiles
     *
     * @see Tile#drop()
     */
    public void dropAll() {
        for (int x = 0; x < gameWidth; x++) {
            for (int y = 0; y < gameHeight; y++) {
                if (this.grid[x][y].active) {
                    this.grid[x][y].drop();
                }
            }
        }
    }

    /**
     * Spawns a new square randomly at the top of the grid
     * Calls the Tile drop method for the tile
     *
     * @see Tile#drop()
     * @return Returns whether a tile was successfully dropped
     */
    public Boolean spawnNew() {
        int ranX = (int) (Math.random() * gameWidth);
        if (!grid[ranX][gameHeight -1].active) {
            this.grid[ranX][gameHeight -1].active = true;
            this.grid[ranX][gameHeight -1].drop();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets all tiles' .active to false
     */
    public void clearGrid() {
        for (int x = 0; x < gameWidth; x++) {
            for (int y = 0; y < gameHeight; y++) {
                this.grid[x][y].active = false;
            }
        }
    }

    /**
     * Prints the grid
     * Only for testing purposes
     */
    @SuppressWarnings("unused")
    private void printGrid() {
        for (int y = gameHeight -1; y >= 0; y--) {
            for (int x = 0; x < gameWidth; x++) {
                if (this.grid[x][y].active) System.out.print("1 ");
                if (!this.grid[x][y].active) System.out.print("0 ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
