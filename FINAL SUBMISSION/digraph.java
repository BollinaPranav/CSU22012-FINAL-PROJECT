import java.util.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class digraph {
	
	    //The Array-list below will be an adjacency list for representing the graph.
	    public Map <Integer,List<edge>> adjs;
	    public Map <String, List<tripEdge>>adj2;
	    
	    List<tripEdge>adjlist= new ArrayList<>();
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
            
            
	}
	
	public digraph(String time) throws IOException {
		// TODO Auto-generated constructor stub



		BufferedReader reader = new BufferedReader(new FileReader("stop_times.txt"));
		reader.readLine();
		String line = null;
		String[] lineArr = new String[9];
		adj2 = new HashMap<>();
		String [] tempArr;
		Arrays.fill(lineArr, null);
		int tripID = 0;
		String arrtime = null;
		String deptime = null;
		int stopID = 0;
		int stopseq = 0;
		String stophs = null;
		int pickup = 0;
		int dropoff = 0;
		String shape = null;
		boolean truetime = false;
		boolean truetime2 = false;
		while((line = reader.readLine())!=null)
		{
			line = line.replace(" ", "");
			tempArr = line.split(",");
			for(int i=0; i<tempArr.length; i++)
			{
				lineArr[i]=tempArr[i];
			}

			arrtime = lineArr[1];
			deptime = lineArr[2];
			truetime = checkvalidtime(arrtime);
			truetime2 = checkvalidtime(deptime);


			if(truetime && truetime2)
			{
				tripID = Integer.parseInt(lineArr[0]);
				stopID = Integer.parseInt(lineArr[3]);
				stopseq = Integer.parseInt(lineArr[4]);
				stophs = lineArr[5];
				pickup = Integer.parseInt(lineArr[6]);
				dropoff = Integer.parseInt(lineArr[7]);
				shape = lineArr[8];
				List<tripEdge> adjList2 = adj2.getOrDefault(arrtime, new ArrayList<>());
				adjList2.add(new tripEdge(tripID, arrtime, deptime, stopID, stopseq, stophs, pickup, dropoff, shape));
				adjlist.add(new tripEdge(tripID, arrtime, deptime, stopID, stopseq, stophs, pickup, dropoff, shape));
				adj2.put(arrtime,adjList2);

			}
			else
			{
				reader.readLine();
			}
		}
		reader.close();
		ArrayList<Integer>tripIDs = new ArrayList<Integer>();

		for(tripEdge adjacent : adj2.getOrDefault(time,new ArrayList<>()))               //this is done to print out in sorted tripid form
		{
			tripIDs.add(adjacent.trip_id);
		}
		Collections.sort(tripIDs);
		int i=0;
		do
		{
			for(tripEdge adjacent: adj2.getOrDefault(time, new ArrayList<>()))
			{
				if(adjacent.trip_id == tripIDs.get(i))
				{
					System.out.println(adjacent.trip_id+"  " + adjacent.arrival_time+"  "+ adjacent.departure_time+"  "+adjacent.stop_id);
					i++;
				}
				if(i==tripIDs.size())
				{
					break;
				}
			}
		}while(i<tripIDs.size());



	}

	private boolean checkvalidtime(String giventime) {
		// TODO Auto-generated method stub
		String [] timeArr = null;
		
		timeArr = giventime.split(":");
    	
    	if(Integer.parseInt(timeArr[0])>23)
    	{		
    		return false;
    		
    	}
    	else if(Integer.parseInt(timeArr[0])==23 && (Integer.parseInt(timeArr[1])>59 || Integer.parseInt(timeArr[2])>59))
    	{
    		return false;
    		
    	}
		
		return true;
	}
    

	 
		 
	 
	
	
}
