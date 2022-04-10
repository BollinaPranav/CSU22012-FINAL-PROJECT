import java.util.*;
import java.io.*;
public class finalproject {

	 static digraph digraph;
	 public static ArrayList<Integer>path;
	 public static Integer[]edgeTo;
	public static void main(String[]Args)
	{
		
		try {
			digraph = new digraph ("stops.txt","stop_times.txt","transfers.txt");
			System.out.print("Input from bus stopID: ");
			Scanner input = new Scanner(System.in);
			int start_id = input.nextInt();
			System.out.print("\nInput to bus stopID: ");
			double[] distances = DijkstraShortestPath(start_id);
			int stop_id = input.nextInt();
			System.out.println(distances[stop_id]);
			getPath(stop_id);
			System.out.print("Input arrival time: ");
			String time = input.next();
			digraph = new digraph(time);
			
			 boolean runUserQuery2 = true;
             while (runUserQuery2)
             {   //Receive the user input.
                 System.out.print("Please enter the name of the bus stop you would like to search for: ");
                 String searchQuery = input.next();
                 searchQuery += input.nextLine();
                 //Make a TST and calculate output.
                 part2 q2TST = new part2("stops.txt");
                 int returnValue = q2TST.ourTST.get(searchQuery);
                 if (returnValue >= 0)
                 {
                 	//We have a dedicated function to print out the stop information for each stop matching the search criteria.
                 	part2.printStopNamesMatchingCriteria(q2TST);
                 }
                 else
                 {
                     System.out.println("No search result found, please try again");
                 }
                 runUserQuery2 = false;
             }
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static ArrayList<Integer> getPath(int stopID) {
		// TODO Auto-generated method stub
		
		 path = new ArrayList<Integer>();
		int current = stopID;
		path.add(current);
		while(edgeTo[current]!=null)
		{
			path.add(edgeTo[current]);
			current = edgeTo[current];
		}
		Collections.reverse(path);
		System.out.println(Arrays.toString(path.toArray()));
		return path;
		
		
	}

	private static double[] DijkstraShortestPath(int sourceNode)
	{
		Map<Integer, List<edge>> adjList = digraph.adjs;
		boolean[] observed = new boolean [digraph.vertices];
		double[] toDist = new double[digraph.vertices];
		edgeTo = new Integer[digraph.vertices];
		double infinity = Double.POSITIVE_INFINITY;
		Arrays.fill(toDist, infinity);                          //initialize all the distances to infinfity
		Arrays.fill(edgeTo, null);
		toDist[sourceNode] = 0;                                 // source node's distance to itself is 0  
		Queue<Integer>PQueue = new PriorityQueue<>(Comparator.comparing(vertex -> toDist[vertex]));    //make a priority queue that sorts vertices based on their distances to source node
		PQueue.add(sourceNode);
		while(!PQueue.isEmpty())
		{
			int current = PQueue.remove();
			observed[current] = true;                                //removes the node from priority queue and returns it to current
			for(edge adjacent : adjList.getOrDefault(current,new ArrayList<>()))
			{
				int vertex = adjacent.ToEdge;
				if(!observed[vertex])
				{
					double newDist = toDist[current]+adjacent.cost;
					if(newDist< toDist[vertex])
					{
						toDist[vertex] = newDist;
						
					}
					edgeTo[vertex] = current;

					PQueue.remove(vertex);
					PQueue.add(vertex);
				}
			}
		}

		return toDist;
	}
	
	
}

