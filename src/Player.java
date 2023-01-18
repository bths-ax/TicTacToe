public class Player
{
	public final static String[] SYMBOL_LIST = { "X", "O", "F", "M" };

	private final String symbol;

	public Player (String symbol)
	{
		this.symbol = symbol;
	}

	public String getSymbol()
	{
		return symbol;
	}
}
