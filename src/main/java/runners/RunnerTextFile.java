package main.java.runners;

import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

/*
 * A class to fetch runner information from the text file.
 */
public final class RunnerTextFile implements RunnerDAO
{
    private ArrayList<Runner> runners = null;
    private Path runnersPath = null;
    private File runnersFile = null;
    private final String FIELD_SEP = "\t";   // separator for the fields

    /**
     * A default constructor that initializes the class variables.
 	 */
    public RunnerTextFile()
    {
        runnersPath = Paths.get("runners.txt");
        runnersFile = runnersPath.toFile();
        runners = this.getRunners();
    }

    /**
     * Reads the text file and creates Runners from the records.
 	 * @return           an ArrayList consisting of the Runner objects, null on error
 	 */  
    public ArrayList<Runner> getRunners()
    {
        // if the runners file has already been read, don't read it again
        if (runners != null)
            return runners;        

        runners = new ArrayList<>();        
        
        if (Files.exists(runnersPath))  // prevent the FileNotFoundException
        {
            try (BufferedReader in = 
                     new BufferedReader(
                     new FileReader(runnersFile)))
            {
                // read all runners stored in the file
                // into the array list
                String line = in.readLine();
                while(line != null)
                {
                    String[] columns = line.split(FIELD_SEP);
                    String name = columns[0];
                    String speed = columns[1];
                    String restPerc = columns[2];

                    // Create a Runner object with the data
                    Runner p = new Runner(
                        name, Integer.parseInt(speed), Integer.parseInt(restPerc));

                    // add to ArrayList
                    runners.add(p);

                    line = in.readLine();                    
                }
            }
            catch(IOException e)
            {
                System.out.println(e);
                return null;
            }
        }
        return runners;            
    }
}