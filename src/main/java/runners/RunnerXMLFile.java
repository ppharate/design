package main.java.runners;

import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import javax.xml.stream.*;  // StAX API

/*
 * A class to fetch runner information from the XML file.
 */
public class RunnerXMLFile implements RunnerDAO
{
    private Path runnerPath = null;
    private ArrayList<Runner> runners = null;

    /**
     * A default constructor that initializes the class variables.
 	 */
    public RunnerXMLFile()
    {
        runnerPath = Paths.get("FinalXMLData.xml");
        runners = this.getRunners();
    }

    /**
     * Reads the XML file and creates Runners from the records.
 	 * @return           an ArrayList consisting of the Runner objects, null on error
 	 */
    public ArrayList<Runner> getRunners()
    {
        // if the XML file has already been read, don't read it again
        if (runners != null)
            return runners;        

        runners = new ArrayList<>();        
        Runner r = null;        
        if (Files.exists(runnerPath))  // prevent the FileNotFoundException
        {
            // create the XMLInputFactory object
            XMLInputFactory inputFactory = XMLInputFactory.newFactory();
            try
            {
                // create a XMLStreamReader object
                FileReader fileReader =
                    new FileReader(runnerPath.toFile());
                XMLStreamReader reader =
                    inputFactory.createXMLStreamReader(fileReader);

                // read the runner information from the file
                while (reader.hasNext())
                {
                    int eventType = reader.getEventType();
                    switch (eventType)
                    {
                        case XMLStreamConstants.START_ELEMENT:
                            String elementName = reader.getLocalName();
                            if (elementName.equals("Runner"))
                            {
                                r = new Runner();
                                String name = reader.getAttributeValue(0);
                                r.setName(name);
                            }
                            if (elementName.equals("RunnersMoveIncrement"))
                            {
                                String speedStr = reader.getElementText();
                                int speed = Integer.parseInt(speedStr);
                                r.setSpeed(speed);
                            }
                            if (elementName.equals("RestPercentage"))
                            {
                                String restPercStr = reader.getElementText();
                                int restPerc = Integer.parseInt(restPercStr);
                                r.setRestPerc(restPerc);
                            }
                            break;
                        case XMLStreamConstants.END_ELEMENT:
                            elementName = reader.getLocalName();
                            if (elementName.equals("Runner"))
                            {
                                runners.add(r);
                            }
                            break;
                        default:
                            break;
                    }
                    reader.next();
                }
            }
            catch (IOException | XMLStreamException e)
            {
                System.out.println(e);
                return null;
            }
        }
        return runners;
    }
}