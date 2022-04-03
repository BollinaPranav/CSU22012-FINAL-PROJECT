import java.util.*;
import java.io.*;
public class digraph {
	public int vertices;
    public Map<String, List<edge>> adjs;
    public boolean isInvalid;

    public digraph(String FileName) throws IOException {
        String EmptyStr = "";
        if (FileName == null || EmptyStr.equals(FileName))
            return;
        adjs = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(FileName));
        reader.readLine();         
        String line = reader.readLine();                             // reads next line
        line=line.replace(" ", "");                                        // replaces all the spaces 
        System.out.println(line);    
        for(int i=0;i<3;i++)
        {
        	String prevline = line;
        	line = reader.readLine();                                //reads next line
        	 line=line.replace(" ", "");
        	if(line!=null)
        	{
        		String[] elementsprevline = prevline.split(",");
        		String[] elements = line.split(",");
        		int trip_id = Integer.parseInt(elementsprevline[0]);
        		String arrival_time = elementsprevline[1];
        		String departure_time = elementsprevline[2];
        		int vertexFrom = Integer.parseInt(elementsprevline[3]);
        		int vertexTo = Integer.parseInt(elements[3]);
        		int stop_sequence = Integer.parseInt(elementsprevline[4]);
        		String stop_headsign = elementsprevline[5];
        		int pickup_type = Integer.parseInt(elementsprevline[6]);
        		int drop_off_type = Integer.parseInt(elementsprevline[7]);
        		double shape_dist_traveled = Double.parseDouble(elementsprevline[8]);
        		
        		//System.out.println(arrival_time);
        		double cost = 1;
        		List<edge> adjList = adjs.getOrDefault(arrival_time, new ArrayList<>());
        		 adjList.add(new edge(trip_id, arrival_time, departure_time, vertexFrom, vertexTo, stop_sequence, stop_headsign, pickup_type, drop_off_type, shape_dist_traveled,cost));
                 adjs.put(arrival_time, adjList);
                 line = reader.readLine();
          
        	
        	}
        	 
        	
        }
        
        
         reader.close();
    }

}
