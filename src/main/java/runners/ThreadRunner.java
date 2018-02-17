package main.java.runners;

/*
 * A thread class that represents one runner.
 */
public class ThreadRunner extends Thread
{
    private String name;
    private int speed;
    private int restPerc;    // rest percentage of this runner
    private int raced;       // meters raced by this runner so far
    private static final int raceLength = 1000;   // length of the race in meters
    
    /**
     * A constructor that initializes the class variables.
     * @param  name       the name of the runner
     * @param  speed      the name of the runner
     * @param  restPerc   the name of the runner   
 	 */
    public ThreadRunner(String name, int speed, int restPerc)
    {
        this.name = name;
        this.speed = speed;
        this.restPerc = restPerc;
        raced = 0;
    }

    /**
     * The overridden run method. This simulates running for 1000 meters for 
     * this runner, after which the runner declares that it has won.
 	 */
    @Override
    public void run()
    {
    	Thread ct = Thread.currentThread();
        while ((raced < raceLength) && (!ct.isInterrupted()))
        {
        	int randomNum = (int) (Math.random() * 100);
        	boolean run = randomNum > restPerc ? true : false;
        	
            if (run)
            {
            	raced += speed;
            	System.out.println(name + ": " + raced);
            	if (raced >= raceLength)
            	{
            		System.out.println(name + ": I finished!");
            		RunnerApp.finished(name);
            	}
            }
            try
            {
                Thread.sleep(100);          // delay 100 milliseconds
            }
            catch (InterruptedException e)
            {
            	System.out.println(name + " : You beat me fair and square.");
            	break;
            }
        }
    }
}