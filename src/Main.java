
import java.util.Arrays;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

public class Main
{
	public static HelperEnum gameMode;
	public static int kiSleeptime = 800;
	
	
	public static void main(String[] args) 
	{
		checkAndActivateMainParam(args);
		chooseGameMode();	
		startTictacToe();
	}
	
	// use default values along no or incorrect param, the the order passed does not matter
	private static void checkAndActivateMainParam(String[] args) 
	{
		// short information
		System.out.println("<.<\n");
		System.out.println("Parameter 1) changes the console clear size. \t Default value: " + HelperText.getClearRows() + " rows");
		System.out.println("Parameter 2) changes the KI think time\t\t Default value: " + Main.kiSleeptime + " mili seconds" );
		System.out.println("\nThe order passed does not matterfor example:");
		System.out.println("start java -jar Tic_Tac_To_With_Ki_One.jar clearSize50 thinkSize500");
		
		try 
		{
			Thread.sleep(4000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		HelperText.clearConsole(true);HelperText.clearConsole(true);
		
		// check parameter
		String compareClearSize 	= "clearSize";
		String compareKiThinkSize 	= "kiThinkSize";
		String emptyString 			= "";
		//String toCompare 			= "[1-9]+"; // only allows positive numbers 1,2,3,4,5,6,7,8,9...
		
		if(args.length != 0)
		{		
			for (int i = 0; i < args.length; i++) 
			{
				String temp = args[i];
				Boolean correctValue = false;
				
				if(temp.contains(compareClearSize))
				{
					temp = temp.replace(compareClearSize, emptyString);
					//correctValue = Pattern.matches(toCompare, temp);	// occurs once or any number of times
					
					if(HelperUserInput.getUserInputInteger(temp) != -1)	// negative values are bad
					{
						HelperText.setClearRows(Integer.parseInt(temp));	
					}
				}
				
				if(temp.contains(compareKiThinkSize) == true)
				{
					temp = temp.replace(compareKiThinkSize, emptyString);
					//correctValue = Pattern.matches(toCompare, temp);	// occurs once or any number of times
					
					if(HelperUserInput.getUserInputInteger(temp) != -1) // negative values are bad
					{
						Main.kiSleeptime = Integer.parseInt(temp);
					}
				}	
			}
		}
		
	}

	private static void chooseGameMode()
	{
		LinkedList<String> questionWhichGameMode = new LinkedList<String>();
		questionWhichGameMode.add(HelperText.questionGameMode);
		
		HelperEnum userAsswer = askUserQuestion(questionWhichGameMode, "1", "y", "2", "n", "3", "x");
		if(userAsswer == HelperEnum.yes)
		{
			gameMode = HelperEnum.gameModeNormal;
		}
		else if(userAsswer == HelperEnum.no)
		{
			gameMode = HelperEnum.gamemodeKiEasyMode;
		}
		else
		{
			closeTicTacTo(0);
		}
	}
	

	private static void startTictacToe()
	{
		System.out.println("Lernen durch neue Wege benutzen. Einfach leern man weniger! ");
		
		initial();		
		work();
		
		LinkedList<String> questionStartNewGameOrExit = new LinkedList<String>();
		questionStartNewGameOrExit.add(HelperText.questionStartNewGame);
		
		HelperEnum userAnswer = askUserQuestion(questionStartNewGameOrExit);
		if(userAnswer == HelperEnum.yes)
		{
			startTictacToe();
		}
		if(userAnswer == HelperEnum.no)
		{
			closeTicTacTo(0);
		}
	}
	
	private static void closeTicTacTo(int exitCode)
	{
		HelperText.clearConsole(true);
		System.out.println("Bis zum naechstenmal :)");
		System.exit(exitCode);
	}


	private static void initial() 
	{
		HelperText.initialPlayerField();
		HelperText.initialPlayerFieldMarker();
		HelperText.viewPlayerField();
	}

	private static void work() 
	{
		// initial start
		boolean firstStart		 = true;			
		
		// termination criterion
		boolean applicationExit = false;	// true if player will exit application
		boolean gameFinish 		= false;	// true if one player win
		HelperEnum theWinnerWas; // = enumNull;
		
		// currently player
		HelperEnum currentlyPlayyer = HelperEnum.playerOneX;
		
		// play as long nobody wins
		while(gameFinish == false)
		{
			// check somebody or nobody winds the game
			// continue if game fresh start
			if(firstStart == true)
			{
				firstStart = false;
			}
			else
			{
				// to do check winners
				HelperTupel whatNow = checkSomebodyOrNobadyWinsTheGame();
				if(whatNow.getIsGameCompleet() == HelperEnum.gameFinish)
				{
					gameFinish = true;
					theWinnerWas = whatNow.getTheWinnerIs();
					// view the winner in console
					
					HelperText.clearConsole(true);
					HelperText.viewPlayerField();
					viewWinner(theWinnerWas);
				}
				else if(whatNow.getIsGameCompleet() == HelperEnum.gameCurrentlyNotFinish)
				{
					//
				}
			}
			
			// xxxx this code
			if(gameFinish == false)
			{	
				// Step 1) ask user player field market
				// if input was incorrect not number 0-8 ask try again not
				// if (y) repeat Step1)
				// if (n) close application
				HelperEnum userInput = HelperEnum.yes; 
				while (userInput == HelperEnum.yes || userInput == HelperEnum.no)	// if input was incorrect...
				{
					if(userInput == HelperEnum.no)
					{
						closeTicTacTo(0);			// close this application
					}
					else if(userInput == HelperEnum.yes)
					{
						viewStepOne(currentlyPlayyer);
						
						// if normal mode
						userInput = getuserInput(currentlyPlayyer);
					}				
				}		
				
				// update player field with new player market
				updatePlayerField(currentlyPlayyer, userInput);
				
				// switch player Name
				currentlyPlayyer = switchPlayer(currentlyPlayyer);
			}
		}
	}
	
	
	// last step view winners in console
	public static void viewWinner(HelperEnum winnerName) 
	{
		switch(winnerName)
		{
			case playerOneXWin:
			{
				viewMessage(HelperText.playerNameOneX + HelperText.playerWinner);
				break;
			}
			case playerTwoOWin:
			{
				viewMessage(HelperText.playerNameTwoO + HelperText.playerWinner);
				break;
			}
			case playerKiEasyWin:
			{
				viewMessage(HelperText.playerKiEasy + HelperText.playerWinner);
				break;
			}
			case nobodyWins:
			{
				if(gameMode == HelperEnum.gameModeNormal)
				{
					viewMessage(HelperText.playerNobodyModeNormal + HelperText.playerWinner);
				}
				else if(gameMode == HelperEnum.gamemodeKiEasyMode)
				{
					viewMessage(HelperText.playerNobodyModeKiEasy + HelperText.playerWinner);
				}
				break;
			}
			default:
			{
				// to do
			}
		}
	}
	
	
	// check whether once player wins the game or not
	private static HelperTupel checkSomebodyOrNobadyWinsTheGame() 				// very important	to do 
	{
		LinkedList<String> playereField =  HelperText.getPlayerField();			// updated player field
		HashMap<HelperEnum, HelperEnum> usedFieldMark = HelperText.getUsedFieldMark();	// get updated player market
		
		/* 
		 * check 1) either wins playerOneX or playerTwoO
		 * check 2)	nobody wins, so hashMap have the same count like player Field
		 */	
		
		// check 1)
		LinkedList<HelperEnum[]> toInvestigate  = new LinkedList<HelperEnum[]>();
		// Horizontal
		toInvestigate.add(new HelperEnum[] {HelperEnum.aOne, HelperEnum.aTwo, HelperEnum.aThree});
		toInvestigate.add(new HelperEnum[] {HelperEnum.bOne, HelperEnum.bTwo, HelperEnum.bThree});
		toInvestigate.add(new HelperEnum[] {HelperEnum.cOne, HelperEnum.cTwo, HelperEnum.cThree});
		// vertical
		toInvestigate.add(new HelperEnum[] {HelperEnum.aOne, HelperEnum.bOne, HelperEnum.cOne});
		toInvestigate.add(new HelperEnum[] {HelperEnum.aTwo, HelperEnum.bTwo, HelperEnum.cTwo});
		toInvestigate.add(new HelperEnum[] {HelperEnum.aThree, HelperEnum.bThree, HelperEnum.cThree});
		// diagonal
		toInvestigate.add(new HelperEnum[] {HelperEnum.aOne, HelperEnum.bTwo, HelperEnum.cThree});
		toInvestigate.add(new HelperEnum[] {HelperEnum.aThree, HelperEnum.bTwo, HelperEnum.cOne});
		 
		
		for (int i = 0; i < toInvestigate.size(); i++) 
		{
			HelperEnum[] toCompare = toInvestigate.get(i);	// index 0 = HelperEnum.aOne, HelperEnum.aTwo, HelperEnum.aThree
			LinkedList<HelperEnum> foundKeys = new LinkedList<HelperEnum>();
			LinkedList<HelperEnum> foundValues = new LinkedList<HelperEnum>();
			
			for(Map.Entry<HelperEnum, HelperEnum> x : usedFieldMark.entrySet())
			{
				HelperEnum key = x.getKey();		// input 7 = HelperEnum.aOne
				HelperEnum value = x.getValue();	// input 7 = HelperEnum.playerOneX
				
				for (HelperEnum lol : toCompare) 
				{
					if(key == lol)					// if HelperEnum.aOne = HelperEnum.aOne...
					{
						foundValues.add(value); 	// if true only save playerName
						foundKeys.add(key);			// if true only save player field key
					}
				}
			}
			
			// if count == 3 then this three player field market are claimed by player
			// if all three market have the same player Value like (X) or (O) then we found a winner
			if(foundValues.size() == 3)
			{
				int alleKeysAreTheSameValue = 0;	// set true if tempKey[1], [2] & [3]  the same vale
				
				// ERROR WHY NOT KNOW
				HelperEnum values = usedFieldMark.get(foundKeys.get(0)); // 0/1/2  all the same key...
				//HelperEnum values = foundValues.get(0); 
				
				for (HelperEnum tempValue : foundValues) 	// foundKeys = HelperEnum.aOne...
				{
					if(values == tempValue)
					{
						alleKeysAreTheSameValue += 1;
					}
				}
								
				if(alleKeysAreTheSameValue == 3)	// a player wins
				{
					if(values == HelperEnum.playerOneX)
					{
						HelperTupel ret = new HelperTupel(HelperEnum.gameFinish, HelperEnum.playerOneXWin);
						return ret;
					}
					else if(values == HelperEnum.playerTwoO)
					{
						HelperTupel ret = new HelperTupel(HelperEnum.gameFinish, HelperEnum.playerTwoOWin);
						return ret;
					}
					else if(values == HelperEnum.playerKiEasy)
					{
						HelperTupel ret = new HelperTupel(HelperEnum.gameFinish, HelperEnum.playerKiEasyWin);
						return ret;
					}

				}
			}
			else	// wont work not all player field market are claimed by player
			{
				// wont work so
				//nobodyWInsCounter +=1;
			}	
		}
		
		
		
		// check 2)
		// check 2) counter if all market claimed by player but nobody wins	
		if(usedFieldMark.size() == 9)
		{
			HelperTupel ret = new HelperTupel(HelperEnum.gameFinish, HelperEnum.nobodyWins);
			return ret;
		}
	
		HelperTupel ret = new HelperTupel(HelperEnum.gameCurrentlyNotFinish, HelperEnum.nobodyWins);
		return ret;
	}
	

	//  switch players for each other
	// player playerOneX will ever be player 1
	// only playerNameTwoO or gamemodeKiEasyMode change between different game mode
	private static HelperEnum switchPlayer(HelperEnum currentlyPlayyer) 
	{
		if(currentlyPlayyer != HelperEnum.playerOneX)
		{
			return HelperEnum.playerOneX;
		}
		else if(gameMode == HelperEnum.gameModeNormal)
		{
			return HelperEnum.playerTwoO;
		}	
		else //if(gameMode == HelperEnum.gamemodeKiEasyMode)
		{
			return HelperEnum.playerKiEasy;
		}
	}

	// clear console and ask user with parameter which mark should market !!
	private static void viewStepOne(HelperEnum playername)
	{
		HelperText.clearConsole(true);
		HelperText.viewPlayerField();
		
		switch(playername)
		{
			case playerOneX:
			{
				viewMessage(HelperText.playerOneXItsYourTurn);
				break;
			}
			case playerTwoO:
			{
				viewMessage(HelperText.playerTwoOItsYourTurn);
				break;
			}
			case playerKiEasy:
			{
				viewMessage(HelperText.playerKiEasyItsYourTurn);
				break;
			}
			default:	// to do change
			{
				viewMessage("Faölscher Spieler gewählt");
				break;
			}
		}
	
	}
	
	// check player market already forgive
	// return -3 if player field market is forgiven
	// return 0 if player market not claimed
	private static int checkPlayerMarketAllreadyTaken(HelperEnum currentlyPlayyer, HelperEnum playerFieldKey)	// to do
	{
		HashMap<HelperEnum, HelperEnum> usedFieldMark = HelperText.getUsedFieldMark();
		
		for(Map.Entry<HelperEnum, HelperEnum> x : usedFieldMark.entrySet() )
		{
			HelperEnum key = x.getKey();
			//HelperEnum value = x.getValue();
			
			//if(key == playerFieldKey && value == currentlyPlayyer)
			if(key == playerFieldKey)
			{
				return -3;	// -3 = error
			}		
		}
		return 0;	// 0 = all fine
	}
	
	// create thread for realism waiting
	private static void sleepAWhile()
	{
		try 
		{
			Thread.sleep(Main.kiSleeptime);
		} 
		 catch (InterruptedException e) 
		 {
			e.printStackTrace();
		}
	}
	
	// to do important 
	// get user input from player or from the KI too
	private static HelperEnum getuserInput(HelperEnum currentlyPlayyer)
	{
		// only if KiEasy game mode and it is KI turn
		if(gameMode == HelperEnum.gamemodeKiEasyMode && currentlyPlayyer == HelperEnum.playerKiEasy)
		{
			int forgivenOrNot = -3;
			int ret = -3;
			while(forgivenOrNot == -3)
			{
				sleepAWhile();	
				Random randomValue = new Random();
				ret = 1 + randomValue.nextInt(9);	// 1-9
				
				// if player field market forgive, its returns -3 
				forgivenOrNot = checkPlayerMarketAllreadyTaken(currentlyPlayyer, HelperEnum.createEnumFromInteger(ret));
			}
			
			// if user input was 7
			// must return HelperEnum.aOne
			return HelperEnum.createEnumFromInteger(ret);	// if input was correct  
		}

		
		// if normal game mode
		LinkedList<String> messages = new LinkedList<String>();
		
		// i is -1 if user input was not a number
		int i = HelperUserInput.getUserInputInteger(HelperUserInput.getUserInputString()); 
		if(i == -1)
		{
			messages.clear();
			messages.add(HelperText.playerInputWsWrong);
			messages.add(HelperText.questionRepeatUserInput);
		}
		
		// check 1)
		// check if integer // if input was not between (1,2,3,4,5,6,7,8,9)
		int[] toDoBetter = new int[] {1,2,3,4,5,6,7,8,9};
		boolean iIsFromOneUntiNineFound = false;
		for (int j = 0; j < toDoBetter.length; j++) 
		{
			if (i == toDoBetter[j])
			{
				iIsFromOneUntiNineFound = true;	// one on right number
			}
		}
		if(i != -1 && iIsFromOneUntiNineFound == false)
		{
			i = -2;
			
			messages.clear();
			messages.add(HelperText.pleaseTakeUserInput);
			messages.add(HelperText.questionRepeatUserInput);
		}
		
		
		// check 3)
		// check above market not have another player
		// if player field market already set to a player with (X or O)  update error message!!	
		if(i != -1 && iIsFromOneUntiNineFound == true)
		{	
			// i = <-- (a , b)	a = X / Y    b =  if i = 7 its HelperEnum.aOne
			
			int forgivenOrNot = i;	// if not, i will change even if true
			forgivenOrNot = checkPlayerMarketAllreadyTaken(currentlyPlayyer, HelperEnum.createEnumFromInteger(i));
			
			if(forgivenOrNot == -3)	
			{
				messages.clear();
				messages.add(HelperText.playerMarkAllreadyTaken);
				messages.add(HelperText.questionRepeatUserInput);
				i = forgivenOrNot;	// important if not, code work as fine but in real player market forgiven
			}
		}

		// incorrect value	ask continue or exit application
		if(i == -1 || i == -2 || i == -3)
		{
			HelperEnum userAnswer = askUserQuestion(messages);	// ask user question as long answer is (y) or (n)
			
			if(userAnswer == HelperEnum.yes)		// continue
			{
				return HelperEnum.yes;				// recursion if user want to try another input value
			}
			else if(userAnswer == HelperEnum.no)	// exit application
			{
				return HelperEnum.no;
			}
		}
		
		// if user input was 7
		// must return HelperEnum.aOne
		return HelperEnum.createEnumFromInteger(i);	// if input was correct  
		
	}
	
	// view message in console and ask user question as long answer is (y) or (n) and return it as enum
	private static HelperEnum askUserQuestion(LinkedList<String> messagesAnrOrQuestions)
	{
		String userAnswer = "";
		String yes = "y";
		String no = "n";
		
		while(!yes.equals(userAnswer.toLowerCase()) && !no.equals(userAnswer.toLowerCase()) )
		{
			HelperText.clearConsole(false);		
			for (String message : messagesAnrOrQuestions) 
			{
				viewMessage(message);
			}
			
			userAnswer = HelperUserInput.getUserInputString();
		}
		
		if(userAnswer.equals(yes))
		{
			return HelperEnum.yes;
		}
		else
		{
			return HelperEnum.no;
		}
	}
	
	// well this will replace later the the old One
	// view message in console and ask user question as long answer is (y) or (n) and return it as enum
	private static HelperEnum askUserQuestion(LinkedList<String> messagesAnrOrQuestions, String ... keysWords)
	{
		Boolean finish = false;
		String userAnswer = "";

		while(finish == false)
		{
			for (int i = 0; i < keysWords.length; i++) 
			{
				if(userAnswer.toLowerCase().equals(keysWords[i].toLowerCase()))
				{
					finish = true;
				}
			}
			
			if(finish == false)
			{
				HelperText.clearConsole(false);		
				for (String message : messagesAnrOrQuestions) 
				{
					viewMessage(message);
				}
				userAnswer = HelperUserInput.getUserInputString();
			}
		}
		
		return HelperEnum.createEnumFromAskUserQuestionString(userAnswer);
	}
	
	private static void viewMessage(String message) 
	{
		System.out.println(message);
	} 
	

	//#####################################################################################
	
	private static void updatePlayerField(HelperEnum currentlyPlayer, HelperEnum fieldMark)
	{
		// get currently player field
		LinkedList<String> playereField =  HelperText.getPlayerField();
		
		// change player field	// can be get problem
		String markValue 	= HelperEnum.createStringFromEnum(currentlyPlayer); // X or O (player dependent)
		int fieldIndex 		= HelperEnum.createIndexFromEnum(fieldMark);		// for example aOne
		
		
		/*	currentlyPlayer = playerOneX = x
		 *  fieldMark 		=  user Input like keyboard = 7
		 * 
		 *  set list = index , x oder o
		 *  
		 *  index for 7 = 0 (enum value aOne)
		 *  
		 *  set list = 0 , x
		 */
		
		playereField.set(fieldIndex, markValue);
		// save updated player field
		HelperText.setPlayerField(playereField);
		
		
		// get and update used field mark list
		HashMap<HelperEnum, HelperEnum> retMerket = HelperText.getUsedFieldMark();
		// key =  for example aOne
		// value = for example playerOneX
		retMerket.put(fieldMark ,currentlyPlayer);
		HelperText.setUsedFieldMark(retMerket);
	}
	
	
}
