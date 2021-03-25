

import java.util.Scanner;

public class HelperUserInput 
{
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
	
	// return Console input	as integer
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
