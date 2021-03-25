


import java.util.HashMap;
import java.util.LinkedList;

public class HelperText 
{
	// public attributes
	public static final String playerNameOneX = "Hanz";
	public static final String playerNameTwoO = "Peter";
	// to do
	public static final String playerKiEasy = "Duke der Einfache";	
	// to do 
	public static final String playerNobodyModeNormal = "Weder " + playerNameOneX + " noch " + playerNameTwoO;
	// to do
	public static final String playerNobodyModeKiEasy = "Weder " + playerNameOneX + " noch " + playerKiEasy;
	public static final String playerWinner = " hat das Spiel gewonnen! :) Gute gemacht!";
	
	// it is the turn of the player
	public static final String playerOneXItsYourTurn = "Spieler :" + playerNameOneX + " du bist dran.";
	public static final String playerTwoOItsYourTurn = "Spieler :" + playerNameTwoO + " du bist dran.";
	public static final String playerKiEasyItsYourTurn = "Spieler :" + playerKiEasy + " du bist dran.";
	// to do
	public static final String pleaseTakeUserInput = "Bitte geben Sie eine Zahl zwischen 1-9 ein.";
	
	// which player wins
	public static final String playerOneXWin = "Spieler: " + playerNameOneX + " hat gewonnen.";
	public static final String playerTwoOWin = "Spieler: " + playerNameTwoO + " hat gewonnen.";
	// to do
	public static final String playerKiEasysOWin = "Spieler: " + playerKiEasy + " haben gewonnen.";
	
	// error message
	public static final String playerMarkAllreadyTaken = "Dein ausgesuchtes Feld ist bereits belegt!";
	public static final String playerInputWsWrong = "Ihre Eingabe war falch!";
	
	// ask user questions
	public static final String questionStartNewGame = "Neues Spiel starten (y) oder Aplication verlassen (n).";
	public static final String questionRepeatUserInput = "Eingabe wiederholen (y) oder Aplication verlassen (n).";
	// to do
	public static final String questionGameMode = "Gegen zwei Personen spielen (1)/(y), einzeln gegen KI Einfach spielen (2)/(n), oder jetzt schon Application verlassen (3)/(x) ?";
	
	//private attributes
	private static LinkedList<String> playerField = new LinkedList<String>();
	
	// key = 
	private static HashMap<HelperEnum, HelperEnum> usedFieldMark = new HashMap<HelperEnum, HelperEnum>();

	// clear rows change with main String args
	private static int clearRows = 5;

	// CONSTRUCTORS
	public HelperText()
	{
	}
	
//	public HelperText(String playerNameOne, String playerNameTwo)
//	{
//		playerNameOneX = playerNameOne;
//		playerNameTwoO = playerNameTwo;
//	}
	
	// GETTERS AND SETTERS
	
	// player field
	public static LinkedList<String> getPlayerField() 
	{
		return playerField;
	}

	// player field
	public static void setPlayerField(LinkedList<String> playerField) 
	{
		HelperText.playerField = playerField;
	}
	
	
	
	// used Field Mark
	public static HashMap<HelperEnum, HelperEnum> getUsedFieldMark() 
	{
		return usedFieldMark;
	}

	// used Field Mark
	public static void setUsedFieldMark(HashMap<HelperEnum, HelperEnum> usermark) 
	{
		HelperText.usedFieldMark = usermark;
	}
	
	public static int getClearRows()
	{
		return clearRows;
	}

	public static void setClearRows(int clearRows) 
	{
		HelperText.clearRows = clearRows;
	}
	
	
	// METHODS
	public static void clearConsole(boolean clear)
	{	
		if(clear == true)
		{
			for (int i = 0; i < HelperText.clearRows; i++) 
			{
				System.out.println("");
			}
		}
		if(clear == false)
		{
			System.out.println("\n");
		}
	}
	


	// initial player field
	public static void initialPlayerFieldMarker()
	{
		HashMap<HelperEnum, HelperEnum> clearFieldMark = new HashMap<HelperEnum, HelperEnum>();
		
		setUsedFieldMark(clearFieldMark);
	}
	
	// initial player field
	public static void initialPlayerField()
	{
		// old one	LinkedList<String> playereField =  HelperText.getPlayerField();
		LinkedList<String> playereField =  new LinkedList<String>();
		
		int playerFieldSize = 9;
		for (int i=0; i < playerFieldSize; i++) 
		{
			playereField.add("?");	
		}
		
		setPlayerField(playereField);
	}
	
	
	// view player field
	public static void viewPlayerField()
	{
		LinkedList<String> playereField =  HelperText.getPlayerField();
		String place = "\n";
		String space = " ";
		String tab = "\t";
		
		System.out.println("Momentanes Spielfeld:");
		System.out.println(place + playereField.get(0) + space + playereField.get(1) + space + playereField.get(2));
		System.out.println(place + playereField.get(3) + space + playereField.get(4) + space + playereField.get(5));
		System.out.println(place + playereField.get(6) + space + playereField.get(7) + space + playereField.get(8));
		System.out.println(place);
	}
	
//	public static void changePlayerField(int valueToChangeOnPlayerField)
//	{
//		LinkedList<String> playereField =  HelperText.getPlayerField();
//		
//		// to do change value
//		
//		setPlayerField(playereField);
//	}
	
}
