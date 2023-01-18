import java.util.Scanner;

public class Main
{
	public static void main(String args[])
	{
		Scanner scanner = new Scanner(System.in);

		int playerCnt;
		while (!(2 <= playerCnt && playerCnt <= 4)) {
			System.out.println("Number of players (2-4): ");
			playerCnt = scanner.nextInt();
		}

		int boardSz;
		while (!(3 <= boardSz && boardSz <= 6)) {
			System.out.println("Board size (3-6): ");
			boardSz = scanner.nextInt();
		}

		TicTacToe ticTacToe = new TicTacToe(playerCnt, boardSz);
		ticTacToe.runGame();
	}
}
