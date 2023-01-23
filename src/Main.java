import java.util.Scanner;

public class Main
{
	public static void main(String args[])
	{
		Scanner scanner = new Scanner(System.in);

		int playerCnt = -1;
		while (!(1 <= playerCnt && playerCnt <= 4)) {
			System.out.print("Number of players (1-4): ");
			playerCnt = scanner.nextInt();
		}

		int boardSz = -1;
		while (!(3 <= boardSz && boardSz <= 6)) {
			System.out.print("Board size (3-6): ");
			boardSz = scanner.nextInt();
		}

		if (playerCnt == 1) {
			System.out.println("Because you chose to play with a bot, "
					+ "extra credit addon #1 (overriding rule) will be disabled.");
			System.out.println("Why? Because you'll never win if it was on (lol)");
			System.out.println("Mad? Send regards to wedontcare@gmail.com");
		}

		TicTacToe ticTacToe = new TicTacToe(playerCnt, boardSz);
		ticTacToe.runGame();
	}
}
