package main.java.runners;

import main.java.runners.util.Validator;

import java.util.Scanner;
import java.util.ArrayList;

/*
 * The main class of the application. It contains the main method that shows the menu
 * and creates the DAO object based on the user input. It then creates the threads 
 * to simulate the race.
 */
public class RunnerApp
{
    // declare the class variables
    private static RunnerDAO runnerDAO = null;
    private static ArrayList<Runner> runners = null;
    private static ArrayList<Thread> threads = null;
    private static Scanner sc = null;
    
	/**
	 * The main method of the application. It displays the menu, gets the user input
	 * and starts the threads to simulate the race.
	 * @param  args     the command-line args
	 */
    public static void main(String args[])
    {
        System.out.println("Welcome to the Marathon Race Runner Program\n");

        // if the user provided the database type in command line arguments
        // use it, otherwise default to text file database
        String databaseType = (args.length >= 1) ? args[0]: "txt";
        sc = new Scanner(System.in);
        
        // display the command menu
        displayMenu(databaseType);

        // Get the user input and perform the action. Repeat until the user enters 5.
        int action = 0;
        while (action != 5)
        {
            // get the input from the user
            action = Validator.getIntWithinRange(sc, "Enter your choice: ", 1, 5);
            System.out.println();

            if (action == 1)
                databaseType = "derby";
            else if (action == 2)
            	databaseType = "xml";
            else if (action == 3)
            	databaseType = "txt";
            else if (action == 4)
            	databaseType = "2runners";
            else if (action == 5)
            {
                System.out.println("Thank you for using my Marathon Race Program\n");
	            break;
            }
            
            runnerDAO = DAOFactory.getRunnerDAO(databaseType);
            runners = runnerDAO.getRunners();
            if (runners == null)
            {
            	System.out.println("Oops, something went wrong, exiting");
            	break;
            }
            
            // Start the threads
            System.out.println("Get set...Go!");
            startThreads();

            // Sleep 2 seconds
        	try
        	{
        	    Thread.sleep(2000);
        	}
        	catch(InterruptedException ex)
        	{
        	    Thread.currentThread().interrupt();
        	}
        	
        	// wait for the user to press Enter
        	System.out.println("\nPress any key to continue...");
        	String s = "dummy";
            while (!s.equals(""))     
                s = sc.nextLine();
            
            displayMenu(databaseType);
        }
    }
    
  	/**
  	 * Method to display the menu.
  	 * @param  databaseType   the type of the database to get the values from
  	 */
    public static void displayMenu(String databaseType)
    {
        System.out.println("Select your data source:\n");
        System.out.println("1.  Derby database");
        System.out.println("2.  XML file");
        System.out.println("3.  Text file");
        System.out.println("4.  Default two runners");
        System.out.println("5.  Exit\n");
    }
    
 	/**
  	 * Starts one thread for very runner. The runner name is set 
  	 * as the name of the thread.
  	 */
    public static void startThreads()
    {
    	threads = new ArrayList<Thread>();
        for (int i = 0; i < runners.size(); i++)   // create the Runner threads
        {
        	Runner runner = runners.get(i);
        	ThreadRunner t = new ThreadRunner(runner.getName(),
        			runner.getSpeed(), runner.getRestPerc());
        	t.setName(runner.getName());
            t.start();
            threads.add(t);
        }
    }
    
 	/**
  	 * Method to interrupt all other threads, except the thread that won the race.
  	 * This method is called by the ThreadRunner objects.
  	 * @param  threadName   the name of the thread that won the race
  	 */
    public static void finished(String threadName)
    {
    	System.out.println("\nThe race is over! The " + threadName + " is the winner.\n");
    	
    	// interrupt all threads except the one that finished first
    	for (int i = 0; i < threads.size(); i++)
        {
        	Thread thread = threads.get(i);
        	if (!thread.getName().equals(threadName))
        		thread.interrupt();
        }
    }
}