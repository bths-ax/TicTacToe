public class Board
{
	private int boardSz;
	private Space[] spaces;
	private WinCondition[] winningConfigs;

	/**
	 * Creates a tic tac toe game board with a variable size
	 * @param boardSz Size of the board as NxN
	 */
	public Board(int boardSz)
	{
		this.boardSz = boardSz;
		this.spaces = new Space[boardSz * boardSz];
		for (int i = 0; i < spaces.length; i++)
			this.spaces[i] = new Space();

		// TODO: Fix for var sz board
		winningConfigs = new WinCondition[8];
		winningConfigs[0] = new WinCondition(0, 1, 2);
		winningConfigs[1] = new WinCondition(3, 4, 5);
		winningConfigs[2] = new WinCondition(6, 7, 8);
		winningConfigs[3] = new WinCondition(0, 3, 6);
		winningConfigs[4] = new WinCondition(1, 4, 7);
		winningConfigs[5] = new WinCondition(2, 5, 8);
		winningConfigs[6] = new WinCondition(0, 4, 8);
		winningConfigs[7] = new WinCondition(2, 4, 6);
	}

	// getter method; note that there is
	// a parameter, which allows caller to specify
	// which specific Space (of the 9) to return
	public Space getSpace(int idx)
	{
		if (idx >= 0 && idx < spaces.length)
		{
			return spaces[idx];
		}  
		return null;
	}

	/**
	 * Draws the tic-tac-toe board so that the user can see what is happening.
	 * The empty board should look like this:
	 *
	 *   1|2|3
	 *   -----
	 *   4|5|6
	 *   -----
	 *   7|8|9
	 *
	 * As the players choose space numbers, those spaces will be filled in with Xs and Os.
	 *
	 *   O|2|3
	 *   -----
	 *   4|X|6
	 *   -----
	 *   7|8|9
	 *
	 * This method goes through each space on the board and checks for BLANKS.
	 * If the space is BLANK, it prints the appropriate number.
	 * Otherwise, it prints the appropriate symbol.
	 */
	public void drawBoard()
	{
		System.out.println();

		int spacePadding = (int)Math.log10(boardSz * boardSz) + 1;

		for (int i = 0; i < spaces.length; i++)
		{
			Space space = spaces[i];

			// Horizontal dividers
			if (i % boardSz == 0)
				System.out.println("\n" + "-".repeat(spacePadding * boardSz + (boardSz - 1)));

			// Vertical dividers
			else
				System.out.print("|");

			if (space.getSymbol() == Space.BLANK) {
				System.out.print(String.format("%"+spacePadding+"d", i + 1));
			} else {
				System.out.print(String.format("%"+spacePadding+"s", space.getSymbol()));
			}
		}

		System.out.println();	
	}	

	/**
	 * Updates a space based on a player's move by changing it from a blank space to either
	 * a an X or O (whichever symbol is associated with player)
	 * The method uses an integer (spaceIdx) to determine which space is going to be updated.
	 * Method returns TRUE if the space was successfully "occupied", which occurs via the occupySpace
	 * method on the space object; if the space was NOT successfully occupied (either because the selected
	 * space was outside the 1-9 range, OR the space was ALREADY occupied by the opposite player), then
	 * return false to indicate the space was NOT occupied.
	 *
	 * @param spaceNum  the index of the space to be occupied by player.
	 * @param player  the Player taking the turn and attempting to "occupy" the space.
	 * @return  true if the move was successful and the space occupied; return false otherwise.
	 */
	public boolean recordMove(int spaceIdx, Player player)
	{
		// since spaces are numbered [1, N], subtract 1 to correlate to [0, N) index range
		int adjustedSpace = spaceIdx - 1;

		// if user chooses a valid space, try to occupy it, which updates the symbol
		// and returns true if the operation was successful
		if (adjustedSpace >= 0 && adjustedSpace < spaces.length)
		{
			boolean spaceOccupied = spaces[adjustedSpace].occupySpace(player.getSymbol());
			return spaceOccupied;
		}     
		return false;
	}

	/**
	 * Uses the checkConfiguration() method to determine whether or not there is a winner.
	 * By checking each of the various WinConditions into checkConfiguration,
	 * this method will know whether or not the game has been won, and if so, by who (X or O).
	 *
	 * @return  if there IS a winning condition on the board, appropriates returns either "X" or "O",
	 whichever the winning symbol is; if there is NO winning condition and no
	 current winner, this method returns BLANK!
	 */
	public String checkWinner()
	{
		for (WinCondition config : winningConfigs)
		{
			// if a player HAS achieved a particular win configuration on the board...
			if (checkConfiguration(config))
			{
				// if the config is a winning condition, then all three Spaces
				// making up that configuration have the same symbol (i.e. 3 X's in a row
				// or 3 O's in a row), so arbitrarily choose the first int value
				// in the array (index 0) to get the winning symbol
				int spaceWithWinningSymbol = config.getWinningSpaces()[0];

				String winningSymbol = spaces[spaceWithWinningSymbol].getSymbol();

				return winningSymbol;
			}
		}
		return Space.BLANK;	
	}

	/**
	 * Determines whether or not all 3 received spaces have the same symbol.
	 *
	 * @param comboToCheck  The WinCondition containing the three indices of a win condition
	 * @return  true if the spaces all have the same symbol, i.e. it's a "WIN"
	 */
	public boolean checkConfiguration(WinCondition comboToCheck)
	{
		int[] winningSpaces = comboToCheck.getWinningSpaces();
		int s1 = winningSpaces[0];
		int s2 = winningSpaces[1];
		int s3 = winningSpaces[2];

		return spaces[s1].getSymbol() == spaces[s2].getSymbol() && spaces[s1].getSymbol() == spaces[s3].getSymbol();
	}

	/**
	 *Determines whether or not the board is full (has no BLANK spaces).
	 *
	 *@return True if there are no BLANKs in any spaces.
	 */	
	public boolean isFull()
	{
		for (int i = 0; i < spaces.length; i++)
		{
			if(spaces[i].getSymbol() == Space.BLANK)
			{
				return false;
			}
		}
		return true;	
	}
}
