
public enum HelperEnum
{
	
	// for tupel helper
	enumNull,		// currently not in use// currently not in use
	
	// user questions answer
	yes, no, other,
	
	// players
	playerOneX, playerTwoO, playerKiEasy, initialMarket, 
	
	// GameMode
	gameModeNormal, gamemodeKiEasyMode,
	
	// winners
	playerOneXWin, playerTwoOWin, playerKiEasyWin, nobodyWins, 
	
	// game finish or not
	gameFinish, gameCurrentlyNotFinish,  
	
	// matchfield
	aOne, aTwo, aThree,
	bOne, bTwo, bThree,
	cOne, cTwo, cThree,	wrongMarkerInput,
	
	// playing field condition
	conditionNormal, conditionPlayerOneX, conditionPlayerTwoO;
	
	
	// methods
	public static void test()
	{
		System.out.println("Das geht");
	}
	
	// new with KI game mode
	public static HelperEnum createEnumFromAskUserQuestionString(String content)
	{
		switch(content)
		{
			case "1", "y":
			{
				return HelperEnum.yes;
			}
			case "2", "n":
			{
				return HelperEnum.no;
			}
			case "3", "x":
			{
				return HelperEnum.other;
			}
			default:
			{
				return HelperEnum.enumNull;
			}
		}
	}
	
	public static String createStringFromEnum(HelperEnum contentString)
	{
		switch(contentString)
		{
			case playerOneX:
			{
				return "X";
			}
			case playerTwoO, playerKiEasy:
			{
				return "O";
			}
			case initialMarket:
			{
				return "?";
			}
			default:
			{
				return "Erro: HelperEnum.whichEnumValue";
			}
		}
		
	}
	
	public static int createIndexFromEnum(HelperEnum fieldMark)
	{
		switch(fieldMark)
		{
			// a = row 1 
			case aOne:
			{
				return 0;
			}
			case aTwo:
			{
				return 1;
			}
			case aThree:
			{
				return 2;
			}
			// c = row two
			case bOne:
			{
				return 3;
			}
			case bTwo:
			{
				return 4;
			}
			case bThree:
			{
				return 5;
			}
			// c = row 3
			case cOne:
			{
				return 6;
			}
			case cTwo:
			{
				return 7;
			}
			case cThree:
			{
				return 8;
			}
			default:
			{
				return -1;
			}
		}	
	}
	
	public static HelperEnum createEnumFromInteger(int value)
	{
		switch(value)
		{
			// a = row 1 
			case 7:
			{
				return aOne;
			}
			case 8:
			{
				return aTwo;
			}
			case 9:
			{
				return aThree;
			}
			// c = row two
			case 4:
			{
				return bOne;
			}
			case 5:
			{
				return bTwo;
			}
			case 6:
			{
				return bThree;
			}
			// c = row 3
			case 1:
			{
				return cOne;
			}
			case 2:
			{
				return cTwo;
			}
			case 3:
			{
				return cThree;
			}
			default:
			{
				return wrongMarkerInput;
			}
		}
	}
	
	
	
}
