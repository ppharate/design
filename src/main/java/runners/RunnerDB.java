package main.java.runners;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * A class to fetch runner information from the Derby database.
 */
public class RunnerDB implements RunnerDAO
{
   /**
	 * Gets a connection to the Derby database and returns it.
	 * @return           the connection
	 */
    private Connection connect()
    {
        Connection connection = null;
        try
        {
            String dbDirectory = "Resources";   // resources dir
            System.setProperty("derby.system.home", dbDirectory);

            String url = "jdbc:derby:RunnersDB";
            String user = "";
            String password = "";
            connection = DriverManager.getConnection(url, user, password);
        }
        catch(SQLException sqle)
        {
        	sqle.printStackTrace(); 
            System.err.println("Error loading database driver: " + sqle);
        }
        return connection;
    }

    /**
     * Reads the database and creates Runners from the records.
 	 * @return           an ArrayList consisting of the Runner objects, null on error
 	 */
    public ArrayList<Runner> getRunners()
    {
        try
        {
            Connection connection = connect();
            ArrayList<Runner> runners = new ArrayList<Runner>();

            // select statement to fetch all the records from the database.
            String query = "SELECT Name, RunnersSpeed, RestPercentage "
                         + "FROM RunnersStats";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            // create one Runner object for every record 
            while(rs.next())
            {
            	String name = rs.getString("Name");
                int runnersSpeed = rs.getInt("RunnersSpeed");
                int restPercentage = rs.getInt("RestPercentage");
                Runner r = new Runner(name, runnersSpeed, restPercentage);
                runners.add(r);  // add the Runner object to the ArrayList
            }
            rs.close();
            ps.close();
            connection.close();
            return runners;
        }
        catch(SQLException sqle)
        {
            sqle.printStackTrace();
            return null;
        }
    }  
}