package main.java.runners;

import java.util.ArrayList;

/*
 * A class denoting two hard-coded runners - Hare and Tortoise.
 */
public final class RunnerTwoRunners implements RunnerDAO
{
    private ArrayList<Runner> runners = null;
    private final int numberOfRunners = 2;
    // Values for the 2 runners are hard-coded here
    private final String[] runnerNames = {"Tortoise", "Hare"};
    private final int[] runnerSpeeds = {10, 100};
    private final int[] runnerRestPercentages = {0, 00};
    
    /**
     * A default constructor that initializes the class variables.
 	 */
    public RunnerTwoRunners()
    {
        runners = this.getRunners();
    }

    /**
     * Creates Runners from the hard-coded values.
 	 * @return           an ArrayList consisting of the Runner objects, null on error
 	 */
    public ArrayList<Runner> getRunners()
    {
        if (runners != null)
            return runners;        

        runners = new ArrayList<>();        
        
        for (int i = 0; i < numberOfRunners; i++)
        {
            Runner p = new Runner(
                runnerNames[i], runnerSpeeds[i], runnerRestPercentages[i]);

            runners.add(p);     
        }
        return runners;            
    }
}