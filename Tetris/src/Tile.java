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

/**
 * Provides Tile object
 * Provides manipulation of the Tile object
 *
 * @author Rafi Long
 * @see Main
 * @see Grid
 *
 * @version 1
 * @since 0.1
 */
public class Tile {
    /**
     * Whether the tile is 'active' or has a square in it
     */
	public boolean active;

    /**
     * Stores the y value of the tile
     */
	public int y;

    /**
     * Stores the tile below the current one
     */
	public Tile below;

    /**
     * Sets active to false
     * Sets y to the argument
     *
     * @param y Stores the y value
     */
	public Tile(int y) {
		this.active = false;
        this.y = y;
	}

    /**
     * Drops the current square if there is not a square beneath
     * Sets the active of the current tile to false
     * Sets the active of the below tile to true
     * Calls drop for the below tile
     */
	public void drop() {
		if (this.y > 0 && !this.below.active) {
			this.active = false;
			this.below.active = true;
			this.below.drop();
		}
	}
}