package main.java.runners.util;

import java.util.Scanner;

/*
 * A class to perform validations on input entered by the user.
 */
public class Validator
{
	/**
	 * Gets the user input from the console.
	 * Accepts only a non-empty strings. Pressing only the return key causes
	 * this method to display an error and prompts the user to try again.
	 * 
	 * @param  sc     the Scanner object
	 * @param  prompt the prompt to display
	 * @return        the user input 
	 */
	public static String getRequiredString(Scanner sc, String prompt)
	{
		String s = "";
		boolean isValid = false;
		while (isValid == false)
		{
			System.out.print(prompt);
			s = sc.nextLine();
			if (s == null || s.equals(""))
				System.out.println("Error! This entry is required. Try again.");
			else
				isValid = true;
		}
		return s;
	}

	/**
	 * Asks the user to enter a value and checks that the value is an int. If not,
	 * keeps asking the same in a loop until a valid int value is entered.
	 * 
	 * @param  sc     the Scanner object
	 * @param  prompt the prompt to display
	 * @return        the user input 
	 */
	public static int getInt(Scanner sc, String prompt)
	{
		int i = 0;
		boolean isValid = false;
		while (isValid == false)
		{
			System.out.print(prompt);
			if (sc.hasNextInt())
			{
				i = sc.nextInt();
				isValid = true;
			}
			else
			{
				System.out.println("Error! Invalid integer value. Try again.");
			}
			sc.nextLine();  // discard any other data entered on the line
		}
		return i;
	}

	/**
	 * Asks the user to enter a value and checks that the value is an int and falls
	 * within the range of min and max. If not, it keeps asking the same in a loop until
	 * a valid int value is entered.
	 * 
	 * @param  sc     the Scanner object
	 * @param  prompt the prompt to display
	 * @param  min    the minimum acceptable value
	 * @param  max    the maximum acceptable value
	 * @return        the user input 
	 */
	public static int getIntWithinRange(Scanner sc, String prompt, int min, int max)
	{
		int i = 0;
		boolean isValid = false;
		while (isValid == false)
		{
			i = getInt(sc, prompt);
			if (i < min)
				System.out.println(
					"Error! Number must be greater than or equal to " + min);
			else if (i > max)
				System.out.println(
					"Error! Number must be less than or equal to " + max);
			else
				isValid = true;
		}
		return i;
	}
}