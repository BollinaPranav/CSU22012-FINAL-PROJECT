import java.util.*;
import java.io.*;
public class digraph {
	
	    //The Array-list below will be an adjacency list for representing the graph.
	    public Map <Integer,List<edge>> adjs;
	    int vertices = 12480;
	    
	public digraph(String stopsFile, String stopTimesFile, String transfersFile) throws IOException {
		// TODO Auto-generated constructor stub
		
   	 
    		
     BufferedReader reader = new BufferedReader(new FileReader(stopTimesFile));
    		reader.readLine();
    		String line = null;
    		String[] splitLine = null;
    		adjs = new HashMap<>();
            int lastTripID = -1;
            int currentTripID = 0;
            int toID = 0;
            int fromID = 0;
            int fromIndex = 0;
            int toIndex = 0;
            while((line = reader.readLine())!=null)
            {
            	splitLine = line.split(",");
            	currentTripID = Integer.parseInt(splitLine[0]);
            	toID = Integer.parseInt(splitLine[3]);
            	if(currentTripID == lastTripID)
                {
                   
            		List<edge> adjList = adjs.getOrDefault(fromID, new ArrayList<>());
                    adjList.add(new edge(fromID,toID,1.0));
                    adjs.put(fromID, adjList);
                }
                lastTripID = currentTripID;
                fromID = toID;
            }
            reader.close();
            
            reader = new BufferedReader(new FileReader(transfersFile));
             toID = 0;
             fromID = 0;
             double weight =0;
             reader.readLine();
            line = null;
     		splitLine = null;
     		while((line = reader.readLine())!=null)
            {
     			
     			splitLine = line.split(",");
     			fromID = Integer.parseInt(splitLine[0]);
     			toID = Integer.parseInt(splitLine[1]);
     			if(Integer.parseInt(splitLine[2])==0)
     			{
     				weight = 2;
     			}
     			else
     			{
     				weight = Double.parseDouble(splitLine[3])/100;
     			}
     			List<edge> adjList = adjs.getOrDefault(fromID, new ArrayList<>());
                adjList.add(new edge(fromID,toID,weight));
                adjs.put(fromID, adjList);
            }
     		reader.close();
            
            System.out.println("hello");
	}
    

}
