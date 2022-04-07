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
    		String[] lineArr = null;
    		adjs = new HashMap<>();
            int prevTripID = -1;
            int currentTripID = 0;
            int toID = 0;
            int fromID = 0;
            int fromIndex = 0;
            int toIndex = 0;
            while((line = reader.readLine())!=null)
            {
            	lineArr = line.split(",");
            	currentTripID = Integer.parseInt(lineArr[0]);
            	toID = Integer.parseInt(lineArr[3]);
            	if(currentTripID == prevTripID)
                {
                   
            		List<edge> adjList = adjs.getOrDefault(fromID, new ArrayList<>());
                    adjList.add(new edge(fromID,toID,1.0));
                    adjs.put(fromID, adjList);
                }
                prevTripID = currentTripID;
                fromID = toID;
            }
            reader.close();
            
            reader = new BufferedReader(new FileReader(transfersFile));
             toID = 0;
             fromID = 0;
             double weight =0;
             reader.readLine();
            line = null;
     		lineArr = null;
     		while((line = reader.readLine())!=null)
            {
     			
     			lineArr = line.split(",");
     			fromID = Integer.parseInt(lineArr[0]);
     			toID = Integer.parseInt(lineArr[1]);
     			if(Integer.parseInt(lineArr[2])==0)
     			{
     				weight = 2;
     			}
     			else
     			{
     				weight = Double.parseDouble(lineArr[3])/100;
     			}
     			List<edge> adjList = adjs.getOrDefault(fromID, new ArrayList<>());
                adjList.add(new edge(fromID,toID,weight));
                adjs.put(fromID, adjList);
            }
     		reader.close();
            
            System.out.println("hello");
	}
    

}
