import java.util.*;
import java.io.*;
public class digraph {
	 private Map<String, Integer> stops;
	    //This Map will holds key value pairs of a stops ID and its associated index in the adjacency list.
	    private Map<Integer, Integer> stopIndexes;
	    //This Map will hold key value pairs of an index in the array and its associated stop name (necessary for getting the correct stops from the graph).
	    private Map<Integer, String> indexToName;
	    //The Array-list below will be an adjacency list for representing the graph.
	    private ArrayList<edge>[] adj;
	    private int V;
	    

    public digraph(String stopsFile, String stopTimesFile, String transfersFile) throws IOException {
    	 stops = new HashMap<String, Integer>();
         stopIndexes = new HashMap<Integer, Integer>();
         indexToName = new HashMap<Integer, String>();
         
         BufferedReader reader = new BufferedReader(new FileReader(stopsFile));
         reader.readLine();
         String line = null;
         String[] splitLine = null;
         int vertex = 0;
         int ID = 0;
         String name = null;
         while((line = reader.readLine()) != null)
         {
             splitLine = line.split(",");                       //split the line based on commas
             name = splitLine[2];                               // get the name of the stop  
             ID = Integer.parseInt(splitLine[0]);               // get the stop id
             stops.put(name, ID);                               // put stop id and name  
             stopIndexes.put(ID, vertex);                       // put the ID and vertex(count)
             indexToName.put(vertex, name);                     // relate the vertex to the name 
             vertex++;
         }
        		reader.close();
        		V = vertex;                                     // total number of vertices
        		
        		
    }

}
