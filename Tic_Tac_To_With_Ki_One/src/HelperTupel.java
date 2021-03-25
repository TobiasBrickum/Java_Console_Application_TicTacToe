
public class HelperTupel 
{

	private HelperEnum isGameCompleet;
	private HelperEnum theWinnerIs;
	
	public HelperTupel(HelperEnum pGameFinish, HelperEnum pTheWinnerIs)
	{
		this.isGameCompleet  = pGameFinish;
		this.theWinnerIs = pTheWinnerIs;
	}

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
