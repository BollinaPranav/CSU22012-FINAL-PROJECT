import java.util.*;
import java.io.*;
public class finalproject {

	 static digraph digraph;
	 public static ArrayList<Integer>path;
	 public static Integer[]edgeTo;
	public static void main(String[]Args)
	{
		int response = 0;
		
		try {
			
			boolean endprogramme = false;
			
			System.out.println("   \r\n"
					+ "               _                            _                                                            _               \r\n"
					+ " __      _____| | ___ ___  _ __ ___   ___  | |_ ___   __   ____ _ _ __   ___ ___  _   ___   _____ _ __  | |__  _   _ ___ \r\n"
					+ " \\ \\ /\\ / / _ | |/ __/ _ \\| '_ ` _ \\ / _ \\ | __/ _ \\  \\ \\ / / _` | '_ \\ / __/ _ \\| | | \\ \\ / / _ | '__| | '_ \\| | | / __|\r\n"
					+ "  \\ V  V |  __| | (_| (_) | | | | | |  __/ | || (_) |  \\ V | (_| | | | | (_| (_) | |_| |\\ V |  __| |    | |_) | |_| \\__ \\\r\n"
					+ "   \\_/\\_/ \\___|_|\\___\\___/|_| |_| |_|\\___|  \\__\\___/    \\_/ \\__,_|_| |_|\\___\\___/ \\__,_| \\_/ \\___|_|    |_.__/ \\__,_|___/\r\n"
					+ "                                                               _                   _                                     \r\n"
					+ "   _ __ ___   __ _ _ __   __ _  __ _  ___ _ __ ___   ___ _ __ | |_   ___ _   _ ___| |_ ___ _ __ ___                      \r\n"
					+ "  | '_ ` _ \\ / _` | '_ \\ / _` |/ _` |/ _ | '_ ` _ \\ / _ | '_ \\| __| / __| | | / __| __/ _ | '_ ` _ \\                     \r\n"
					+ "  | | | | | | (_| | | | | (_| | (_| |  __| | | | | |  __| | | | |_  \\__ | |_| \\__ | ||  __| | | | | |                    \r\n"
					+ "  |_| |_| |_|\\__,_|_| |_|\\__,_|\\__, |\\___|_| |_| |_|\\___|_| |_|\\__| |___/\\__, |___/\\__\\___|_| |_| |_|                    \r\n"
					+ "                               |___/                                     |___/                                           \r\n"
					+ "");
			while(!endprogramme)
			{
			Scanner input = new Scanner(System.in);
			System.out.println("\nPlease press '1' if you want to find the shortest route between two bus stops ");
			System.out.println("Please press '2' if you want to search for a bus stop by its name ");
			System.out.println("Please press '3' if you want to search for all the trips with a given arrival time ");
			System.out.println("Please press '4' if you want to exit");
			System.out.print("YOUR RESPONSE: ");
			try {
			response = input.nextInt();
			}  catch (InputMismatchException e) {


		     }
			if(response==1)
			{
			digraph = new digraph ("stops.txt","stop_times.txt","transfers.txt");
			System.out.print("Input from bus stopID: ");
			int start_id = input.nextInt();
			System.out.print("\nInput to bus stopID: ");
			double[] distances = DijkstraShortestPath(start_id);
			int stop_id = input.nextInt();
			System.out.println("The cost of going from bus stop ID: "+start_id+" to stop ID: "+stop_id+" is "+distances[stop_id]);
			getPath(stop_id);
			response=0;
			}
			else if(response==3)
			{
			System.out.print("Input arrival time: ");
			String time = input.next();
			boolean status = checkvalidtime(time);
			if(status==false)
			{
				System.out.println("The input has to be of format HH:mm:ss");
			}
			else
			{
			digraph = new digraph(time);
			response=0;
			}
			}
			else if(response==2)
			{
			
			 boolean runUserQuery2 = true;
             while (runUserQuery2)
             {   //Receive the user input.
                 System.out.print("Please enter the name of the bus stop you would like to search for: ");
                 String searchQuery = input.next();
                 searchQuery += input.nextLine();
                 //Make a TST and calculate output.
                 part2 TST = new part2("stops.txt");
                 int returnValue = TST.ourTST.get(searchQuery);
                 if (returnValue >= 0)
                 {
                 	//We have a dedicated function to print out the stop information for each stop matching the search criteria.
                 	part2.printStopNamesMatchingCriteria(TST);
                 }
                 else
                 {
                     System.out.println("No search result found, please try again");
                 }
                 runUserQuery2 = false;
             }
			response=0;
			}
			else if(response==4)
			{
				
				endprogramme = true;
				System.out.println("\r\n"
						+ "████████╗██╗  ██╗ █████╗ ███╗   ██╗██╗  ██╗    ██╗   ██╗ ██████╗ ██╗   ██╗\r\n"
						+ "╚══██╔══╝██║  ██║██╔══██╗████╗  ██║██║ ██╔╝    ╚██╗ ██╔╝██╔═══██╗██║   ██║\r\n"
						+ "   ██║   ███████║███████║██╔██╗ ██║█████╔╝      ╚████╔╝ ██║   ██║██║   ██║\r\n"
						+ "   ██║   ██╔══██║██╔══██║██║╚██╗██║██╔═██╗       ╚██╔╝  ██║   ██║██║   ██║\r\n"
						+ "   ██║   ██║  ██║██║  ██║██║ ╚████║██║  ██╗       ██║   ╚██████╔╝╚██████╔╝\r\n"
						+ "   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝       ╚═╝    ╚═════╝  ╚═════╝ \r\n"
						+ "                                                                          \r\n"
						+ "");
			}
			else
			{
				System.out.println("Please provide a number between 1-3 or enter 4 to exit");
			}
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static boolean checkvalidtime(String giventime) {
		// TODO Auto-generated method stub
		String [] timeArr = null;
		try {
			int Value = Integer.parseInt(giventime);
		}catch(NumberFormatException e) {
			return false;
		}
		if(!giventime.contains(":"))
		{
			return false;
		}
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
		System.out.println("The bus stops which one would encounter on this path are: ");
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

