
import java.util.Scanner;
/**
 * @author ericm
 *
 */
public class GameOfLife {
	//Variables
	private boolean[][] grid;
	private String gridAsString;
	
	//Constructors
	public GameOfLife() {
		this.setGrid(null);
		setGridAsString("");
	}
	public GameOfLife(boolean[][] g) {
		this.setGrid(g);
		setGridAsString(GameOfLife.arrayToString(g));
	}
	
	//Methods
	/**
	 * Access for the board in the current state.
	 * @return The 2d boolean array of the board.
	 */
	public boolean[][] getGrid() {
		return grid;
	}
	/**
	 * Setter for the board.
	 * @param grid The new board as a 2d boolean array.
	 */
	public void setGrid(boolean[][] grid) {
		this.grid = grid;
	}
	public String getGridAsString() {
		return gridAsString;
	}
	public void setGridAsString(String gridAsString) {
		this.gridAsString = gridAsString;
	}
	/**
	 * Given a row and column number of a cell, this function returns the next state for that cell.
	 * Assumes grid is not null.
	 * @param r Row number - [0, grid.length)
	 * @param c Column number - [0, grid[0].length)
	 * @return The next state for the cell as a boolean - T for alive, F for dead
	 */
	private boolean newStateCell(int r, int c) {
		int liveCount = 0;
		boolean topOK = false;
		boolean bottomOK = false;
		boolean leftOK = false;
		boolean rightOK = false;
		
		//Take care of borders
		if (r != 0) {
			topOK = true;			
		}
		if (r != this.grid.length-1) {
			bottomOK = true;
		}
		if (c != 0) {
			leftOK = true;
		}
		if (c != this.grid[0].length-1) {
			rightOK = true;
		}
		
		//Count alive neighbors
		if (topOK && leftOK && this.grid[r-1][c-1]) {
			liveCount++;
		}
		if (topOK && this.grid[r-1][c]) {
			liveCount++;
		}
		if (topOK && rightOK && this.grid[r-1][c+1]) {
			liveCount++;
		}
		if (leftOK && this.grid[r][c-1]) {
			liveCount++;
		}
		if (rightOK && this.grid[r][c+1]) {
			liveCount++;
		}
		if (bottomOK && leftOK && this.grid[r+1][c-1]) {
			liveCount++;
		}
		if (bottomOK && this.grid[r+1][c]) {
			liveCount++;
		}
		if (bottomOK && rightOK && this.grid[r+1][c+1]) {
			liveCount++;
		}
		
		//Assess
		if (liveCount < 2) {
			return false;
		}
		if (liveCount > 3) {
			return false;
		}
		if (liveCount == 3 && !this.grid[r][c]) {
			return true;
		}
		if ((liveCount == 2 || liveCount == 3) && this.grid[r][c]) {
			return true;
		}
		return false;
	}
	/**
	 * This method updates a new state to this.grid using the current this.grid as the old state.
	 * Returns and updates the string version of the new grid.
	 * @return The new state as a string or null if grid is null.
	 */
	public String setNewStateGrid() {
		if (this.grid == null) {
			return null;
		}
		
		String ans = "";
		boolean[][] newGrid = new boolean[this.grid.length][this.grid[0].length];
		
		for (int i = 0; i < this.grid.length; i++) {
			for (int j = 0; j < this.grid[0].length; j++) {
				newGrid[i][j] = this.newStateCell(i, j);
				if (newGrid[i][j]) {
					ans += "o ";
				}
				else {
					ans += ". ";
				}
			}
			if (i != this.grid.length-1) {
				ans += "\n";
			}
		}
		this.setGrid(newGrid);
		this.setGridAsString(ans);
		return ans;
	}
	/**
	 * This method initializes the board by user input and makes sure the board is valid.
	 */
	public void initializeGrid() {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		
		System.out.print("How many rows does your board have? ");
		int rowNum = s.nextInt();
		System.out.print("How many columns? ");
		int colNum = s.nextInt();
		System.out.println("Enter your board - 'o' is a live cell, '.' is a dead cell. Hit enter to start the next row.");
		
		String stringAsOfNow = "";
		String rowString = null;
		int currentRow = 0;
		boolean[][] boardAsBool = new boolean[rowNum][colNum];
		String rowError = "Row invalid. Try that row again.";
		s.nextLine();
		
		while (currentRow != rowNum) {
			rowString = s.nextLine();
			if (rowString.length() != colNum) {
				System.out.println(rowError);
				System.out.println(stringAsOfNow);
				continue;
			}
			boolean error = false;
			for (int i = 0; i < colNum; i++) {
				if (rowString.charAt(i) == 'o') {
					boardAsBool[currentRow][i] = true;
				}
				else if (rowString.charAt(i) == '.') {
					boardAsBool[currentRow][i] = false;
				}
				else {
					System.out.println(rowError);
					System.out.println(stringAsOfNow);
					error = true;
					break;
				}
			}
			if (error) {
				continue;
			}
			stringAsOfNow += "\n" + rowString;
			currentRow++;
		}
		this.setGrid(boardAsBool);
		this.setGridAsString(GameOfLife.arrayToString(boardAsBool));
		System.out.println("Board generated.");
	}
	/**
	 * Translates the given 2d boolean array to a string for the user to view.
	 * @param g Board to translate
	 * @return Board as string - 'o' for alive, '.' for dead
	 */
	public static String arrayToString(boolean[][] g) {
		String ans = "";
		
		for (int i = 0; i < g.length; i++) {
			for (int j = 0; j < g[0].length; j++) {
				if (g[i][j]) {
					ans += "o ";
				}
				else {
					ans += ". ";
				}
			}
			if (i != g.length-1) {
				ans += "\n";
			}
		}
		return ans;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		GameOfLife g = new GameOfLife();
		boolean done = false;
		g.initializeGrid();
		
		while (!done) {
			System.out.print("Type 'b' to create a new board, 'n' for the next state of the current board, or 'f' if you are finished:");
			String response = s.nextLine();
			if (response.toLowerCase().equals("b")) {
				g.initializeGrid();
			} 
			else if (response.toLowerCase().equals("n")) {
				System.out.println(g.setNewStateGrid());
			}
			else if (response.toLowerCase().equals("f")) {
				done = true;
			}
		}
		
		
	}
	


}
