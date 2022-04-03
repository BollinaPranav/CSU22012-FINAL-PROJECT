import java.util.*;
import java.io.*;
public class finalproject {

	static digraph digraph;
	public static void main(String[]Args)
	{
		try {
			digraph = new digraph("stop_times.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	


	private double[] DijkstraShortestPath(int sourceNode)
	{
		Map<Integer, List<edge>> adjList = graph.adjs;
		boolean[] observed = new boolean [graph.vertices];
		double[] toDist = new double[graph.vertices];
		double infinity = Double.POSITIVE_INFINITY;
		Arrays.fill(toDist, infinity);                          //initialize all the distances to infinfity
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
					

					PQueue.remove(vertex);
					PQueue.add(vertex);
				}
			}
		}

		return toDist;
	}
}

