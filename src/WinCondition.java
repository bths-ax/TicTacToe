public class WinCondition
{
	private int[] winningSpaces;
	private String hey;

	/**
	  * Generates a WinCondition in the shape of a line with a
	  * variable starting position and slope
	  *
	  * @param boardSz Size of the board
	  * @param startRow Starting row
	  * @param startCol Starting column
	  * @param offsetRow Y component of the slope
	  * @param offsetCol X component of the slope
	  * @returns WinCondition
	  */
	public static WinCondition generateWinningLine(int boardSz, int startRow, int startCol, int offsetRow, int offsetCol) {
		int[] winningSpaces = new int[boardSz]; // trusting that i dont use this to start at some weird place
		int idx = 0;

		for (
			int row = startRow, col = startCol;
			0 <= row && row < boardSz &&
				0 <= col && col < boardSz;
			row += offsetRow, col += offsetCol)
		{
			int boardIdx = row * boardSz + col;
			winningSpaces[idx++] = boardIdx;
		}

		return new WinCondition(winningSpaces);
	}

	/**
	  * Generates a WinCondition in the shape of square with
	  * a variable position and side length
	  *
	  * @param boardSz Size of the board
	  * @param row Row of the top left corner of the square
	  * @param col Column of the top left corner of the square
	  * @param size Side length of the square
	  */
	public static WinCondition generateWinningSquare(int boardSz, int row, int col, int size) {
		int[] winningSpaces = new int[size * size];
		for (int rowOffset = 0; rowOffset < size; rowOffset++) {
			for (int colOffset = 0; colOffset < size; colOffset++) {
				int boardIdx = (row + rowOffset) * boardSz + (col + colOffset);
				winningSpaces[rowOffset * size + colOffset] = boardIdx;
			}
		}
		return new WinCondition(winningSpaces);
	}

	public WinCondition(int[] winningSpaces) {
		this.winningSpaces = winningSpaces;
	}

	public int[] getWinningSpaces() {
		return winningSpaces;
	}
}
