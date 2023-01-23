import java.util.Scanner;

public class TicTacToe
{
	public static boolean singleplayer = false;

	private Player[] players;
	private Board board;

	/**
	 * Creates a tic tac toe game with a variable
	 * number of players and board size
	 *
	 * @param playerCnt Number of players
	 * @param boardSz Size of the board as NxN
	 * @param singleplayer Singleplayer mode
	 */
	public TicTacToe(int playerCnt, int boardSz)
	{
		singleplayer = playerCnt == 1;

		players = new Player[playerCnt];
		for (int i = 0; i < playerCnt; i++)
			players[i] = new Player(Player.SYMBOL_LIST[i]);

		board = new Board(boardSz);
		board.drawBoard();
	}

	/**
	 * A loop will run the game until the board is full.
	 * Inside the loop, each player will take a turn, starting with p1/
	 * If either player's turn ends the game (the takeTurn() method returns true), break out of the loop.
	 */
	public void runGame()
	{
		boolean gameOver = false;

		// repeat until either the board is full (i.e. all spaces have been selected),
		// or the game is over because someone won, which is true if takeTurn returns true
		while (!board.isFull() && !gameOver)
		{ 
			// each time through the while loop, iterate over the players array (2 players);
			// we add a break just in case the first player in array wins the game,
			// to prevent the next player from getting another turn  
			for (int i = 0; i < players.length; i++)
			{
				if(takeTurn(players[i]))
				{
					gameOver = true;
					break;  // breaks out of the for-loop -- NOT the while loop
				}
			}

			// Special case for singleplayer mode
			if (players.length == 1) {
				if (computerTurn()) {
					gameOver = true;
					break;
				}
			}
		}
		System.out.println("Thanks for playing!");
	}

	/** @return true if the GAME is over, false if the TURN is over but the game is not over */
	private boolean checkWinner() {
		// redraw the board, which will include the newly placed X or O as updated via recordMove
		board.drawBoard();

		// check to see if the board reveals a winning condition for either X or O
		String winner = board.checkWinner();

		if (!winner.equals(Space.BLANK))
		{
			System.out.println(winner + " won!");
			return true;
		}

		if (board.isFull())
		{
			System.out.println("The game is a tie!");
			return true;
		}

		return false;
	}

	/**
	 * Allows the current player to take a turn.
	 * Print a message saying whose turn it is: X or O
	 * Draw the board for player reference.
	 * Allow the appropriate player to enter the space number they want to occupy and record the move.
	 * If the player has won the game, print a message and return true.
	 * If the board is full, print a message and return true.
	 * Otherwise, the game is not yet over and return false.
	 *
	 * @param p  the player taking the turn.
	 * @return true if the GAME is over, false if the TURN is over but the game is not over
	 */	
	public boolean takeTurn(Player player)
	{
		Scanner scanner = new Scanner(System.in);
		boolean selectedValidSpace = false;

		// repeat until player selects a valid space, which occurs when recordMove returns true;
		// this occurs when the player has selected a numbered "blank" space.
		while (!selectedValidSpace)
		{
			System.out.print("Player " + player.getSymbol() + "'s turn! Choose a space: ");
			int chosenSpace = scanner.nextInt();
			selectedValidSpace = board.recordMove(chosenSpace, player);
		}

		return checkWinner();
	}

	/**
	  * Calculates a good position to place an opposing move
	  * on the current state of the game board
	  *
	  * @return true if the game is over
	  */
	public boolean computerTurn() {
		Player fakePlayer = new Player(Player.SYMBOL_LIST[1]);
		int boardSz = board.getBoardSize();

		int chosenSpaceIdx = -1;

		// Place move at space with highest threat value if threatened
		int maxThreatSpaceIdx = 0;
		int maxThreatValue = -1;

		for (int spaceIdx = 0; spaceIdx < boardSz * boardSz; spaceIdx++) {
			int threatValue = board.calculateThreatValue(players[0], spaceIdx);
			if (threatValue > maxThreatValue && !board.spaceTaken(spaceIdx)) {
				maxThreatValue = threatValue;
				maxThreatSpaceIdx = spaceIdx;
			}
		}

		if (maxThreatValue > Math.min(boardSz / 2, 2)) {
			chosenSpaceIdx = maxThreatSpaceIdx;
		}

		// Otherwise place move at space that threatens the opponent (user) the most
		maxThreatSpaceIdx = 0;
		maxThreatValue = -1;

		for (int spaceIdx = 0; spaceIdx < boardSz * boardSz; spaceIdx++) {
			int threatValue = board.calculateThreatValue(fakePlayer, spaceIdx);
			if (threatValue > maxThreatValue && !board.spaceTaken(spaceIdx)) {
				maxThreatValue = threatValue;
				maxThreatSpaceIdx = spaceIdx;
			}
		}

		if (chosenSpaceIdx == -1) {
			chosenSpaceIdx = maxThreatSpaceIdx;
		}

		// Actually place move
		board.recordMove(chosenSpaceIdx + 1, fakePlayer);

		System.out.println();
		System.out.println("Computer chose its move: " + (chosenSpaceIdx + 1));

		return checkWinner();
	}
}
