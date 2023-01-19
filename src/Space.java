public class Space
{
	// static variable
	public static final String BLANK = " ";

	// instance variable
	private String symbol; 

	/** Constructor; all Space objects start off with a blank symbol
	*/
	public Space()
	{
		symbol = BLANK; 
	}

	public String getSymbol()
	{
		return symbol;
	}

	/**
	 * Changes the symbol on the space to symbolOfOccupant and returns true
	 *
	 * @param symbolOfOccupant  the new symbol for the space.
	 * @return  true if the space was successfully changed, return false otherwise
	 */
	public boolean occupySpace(String symbolOfOccupant)
	{
		symbol = symbolOfOccupant;
		return true; // now i could remove this but im a bit too lazy
	}
}
