

import java.util.Scanner;

public class HelperUserInput 
{
	/**
	 * Return user console input as a String.
	 * @return String from console input
	 */
	public static String getUserInputString()
	{
		try
		{
			String userInput = "";
			Scanner userInputScann = new Scanner(System.in);
			userInput = userInputScann.next();
			return userInput;
		}
		catch(Exception e)
		{	
			return "-1";
		}
	}
	
	/**
	 * Return user console input as a integer.
	 * @param String
	 * @return integer from console input
	 */
	public static int getUserInputInteger(String content)
	{
		try
		{		
			int ret = Integer.parseInt(content);
			return ret;
		}
		catch(Exception e)
		{
			return -1;
		}
	}
}
