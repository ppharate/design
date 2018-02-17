package main.java.runners;

/*
 * A class to return a DAO object of the appropriate type.
 */
public class DAOFactory
{
	/**
	 * Maps the RunnersDAO interface to the appropriate data storage mechanism.
	 * Accepts a string which denotes the type of database.
	 * 
	 * @param  strDatabase the type of database
	 * @return             the DAO object
	 */
    public static RunnerDAO getRunnerDAO(String strDatabase)
    {
        RunnerDAO rDAO = null; 
    		switch (strDatabase) {
    		case "xml":
    	        rDAO = new RunnerXMLFile();
    			break;
    		case "derby":
    	        rDAO = new RunnerDB();
    			break;
    		case "txt":
    			rDAO = new RunnerTextFile();
    			break;
    		case "2runners":
    		default:
    	        rDAO = new RunnerTwoRunners();
    		}
        return rDAO;
    }
}