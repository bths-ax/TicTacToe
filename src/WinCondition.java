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
		int winningSpacesCnt = 0;
		for (
			int row = startRow, col = startCol;
			0 <= row && row < boardSz &&
				0 <= col && col < boardSz;
			row += offsetRow, col += offsetCol) winningSpacesCnt++;

		int[] winningSpaces = new int[winningSpacesCnt];
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

	public WinCondition(int[] winningSpaces) {
		this.winningSpaces = winningSpaces;
	}

	public int[] getWinningSpaces() {
		return winningSpaces;
	}
}
