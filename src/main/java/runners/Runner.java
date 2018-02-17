package main.java.runners;

/*
 * A class to denote a runner. It stores the basic information of a runner - 
 * the name, speed and rest percentage.
 */
public class Runner
{
    private String name;
    private int speed;
    private int restPerc;

	/**
	 * The default constructor. It sets default values for the name, speed and rest percentage.
	 */
    public Runner()
    {
        this("", 0, 0);
    }

	/**
	 * Another constructor. It sets values for the name, speed and rest percentage
	 * according to values passed to it.
	 * @param  name     the name of the runner
	 * @param  speed    the speed of the runner
	 * @param  restPerc the rest percentage of the runner
	 */
    public Runner(String name, int speed, int restPerc)
    {
        this.name = name;
        this.speed = speed;
        this.restPerc = restPerc;
    }

	/**
	 * Sets a value for the name of the runner.
	 * @param  name     the name of the runner
	 */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
	 * Returns the name of the runner.
	 * @return           the name of the runner
	 */
    public String getName()
    {
        return name;
    }

	/**
	 * Sets a value for the speed of the runner.
	 * @param  speed    the speed of the runner
	 */
    public void setSpeed(int speed)
    {
        this.speed = speed;
    }

    /**
	 * Returns the speed of the runner.
	 * @return           the speed of the runner
	 */
    public int getSpeed()
    {
        return speed;
    }

	/**
	 * Sets a value for the rest percentage of the runner.
	 * @param  restPerc the rest percentage of the runner
	 */
    public void setRestPerc(int restPerc)
    {
        this.restPerc = restPerc;
    }
    
	/**
	 * Returns the rest percentage of the runner.
	 * @return           the rest percentage of the runner
	 */
    public int getRestPerc()
    {
        return restPerc;
    }
}