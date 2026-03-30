package io.interview;

public class Board {
    private static final int ROWS = 6;
    private static final int COLS = 7;
    private static final int[][] dirs = new int[][]{
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}
    };

    private DiscColor[][] grid;

    public int getRows(){
        return ROWS;
    }

    public int getCols() {
        return COLS;
    }

    /**
     * Decide whether a disc can be placed in a given column or not.
     * Only the first row of the given column can decide this.
     * @param column int
     * @param color DiscColor
     * @return true: Yes, false: No
     */
    public boolean canPlace(int column) {
        if(grid[0][column] != null){
            return false;
        }
        return grid[0][column] == null;
    }

    /**
     * Place the given disc in a given color
     * @param column int
     * @param color DiscColor
     * @return row number, default -1
     */
    public int placeDisc(int column, DiscColor color){
        if(!canPlace(column)){
            return -1;
        }
        for(int row=ROWS - 1; row >= 0; row--) {
            if(grid[row][column] == null){
                grid[row][column] = color;
                return row;
            }
        }

        return -1;
    }

    /**
     * Check whether this board is full or not
     * @return full: true, not full: false
     */
    public boolean isFull() {
        for(int column=0; column < COLS; column++) {
            if(grid[0][column] == null){
                return true;
            }
        }

        return false;
    }

    public DiscColor getCell(int row, int column) {
        if(!inBounds(row, column)){
            return null;
        }

        return grid[row][column];
    }

    /**
     * Clears the cell. Can be used if the player wants to undo the last move
     * @param row
     * @param column
     */
    public void clearCell(int row, int column){
        grid[row][column] = null;
    }

    public boolean checkWin(int row, int column, DiscColor color){
        // If the current row, column is out of bounds or another player has already marked this cell
        if(!inBounds(row, column) || grid[row][column] != color){
            return false;
        }

        int count = 0;
        for(int[] dir : dirs) {
            // Pick one direction and start checking in this direction till we see the same color as of this player
            count += countInDirection(row, column, dir[0], dir[1], color);
            count += countInDirection(row, column, -dir[0], -dir[1], color);
            if(count >= 4){
                return true;
            }
        }

        return false;
    }

    private int countInDirection(int row, int column, int dr, int dc, DiscColor color) {
        int count = 0;
        int r = row + dr;
        int c = column + dc;

        while(inBounds(r, c) && grid[row][column] == color){
            count++;
            r += dr;
            c += dc;
        }
        return count;
    }

    private boolean inBounds(int row, int column) {
        return row >= 0 && row < ROWS && column >= 0 && column < COLS;
    }
}
