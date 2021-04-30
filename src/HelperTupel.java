
public class HelperTupel 
{
	private HelperEnum isGameCompleet;
	private HelperEnum theWinnerIs;
	
	// constructor
	
	/**
	 * Ram save class for :
	 * 
	 * @param pGameFinish	tells is the game finish or not
	 * @param pTheWinnerIs	tells which person win the game
	 */
	public HelperTupel(HelperEnum pGameFinish, HelperEnum pTheWinnerIs)
	{
		this.isGameCompleet  = pGameFinish;
		this.theWinnerIs = pTheWinnerIs;
	}

	
	// getter and setter
	public HelperEnum getIsGameCompleet() {
		return isGameCompleet;
	}

//	public void setIsGameCompleet(HelperEnum isGameCompleet) {
//		this.isGameCompleet = isGameCompleet;
//	}

	public HelperEnum getTheWinnerIs() {
		return theWinnerIs;
	}

//	public void setTheWinnerIs(HelperEnum theWinnerIs) {
//		this.theWinnerIs = theWinnerIs;
//	}
	
	
}
