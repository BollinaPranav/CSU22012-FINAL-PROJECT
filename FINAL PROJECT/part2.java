import java.util.*;
import java.util.stream.Stream;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
public class part2 {

	TST ourTST;
	String namesFile;

	public part2 (String stopsfile) throws IOException {
		ourTST = new TST();
		namesFile = stopsfile;
		int lineID = 2;
		TST.allNames.clear();
		BufferedReader reader = new BufferedReader(new FileReader(stopsfile));
		reader.readLine();
		String thisLine;
		while ((thisLine = reader.readLine()) != null)
		{
			String[] tokens = thisLine.split(",");
			String stopName = tokens[2];
			String[] test = stopName.split(" ");
			List<String> t = Arrays.asList(test);
			LinkedList<String> temp = new LinkedList<>(t);
			while (temp.get(0).equals("NB") || temp.get(0).equals("SB") ||
					temp.get(0).equals("WB") || temp.get(0).equals("EB") ||
					temp.get(0).equals("FLAGSTOP"))
			{
				String s = temp.remove(0);
				temp.add(s);
			}
			String stopNameFormatted = temp.toString();
			stopNameFormatted = stopNameFormatted.replaceAll("\\p{P}", "");
			//Puts stop name into TST along with line number.
			ourTST.put(stopNameFormatted, lineID);
			lineID++;
		}
	}


	protected ArrayList<String> queryNameWithReturn(String query) throws IOException
	{
		int returnValue = ourTST.get(query);
		if(returnValue >= 0)
		{
			ArrayList<String> results = new ArrayList<>();
			for (int i = 0; i < TST.allNames.size(); i++)
			{
				String output;
				int lineNumber = TST.allNames.get(i);
				try (Stream<String> lines = Files.lines(Paths.get(namesFile)))
				{
					output = lines.skip(lineNumber - 1).findFirst().get(); //subtracat 1 as we skipped the first line.
					String[] outTokens = output.split(",");
					results.add(outTokens[2]);
				}
			}
			TST.allNames.clear();
			return results;
		}
		else return null;
	}

	
	protected static void printStopNamesMatchingCriteria(part2 q2TST) throws IOException {
		for (int i = 0; i <= TST.allNames.size() - 1; i++)
		{
			String output;
			int lineNumber = TST.allNames.get(i);
			try (Stream<String> lines = Files.lines(Paths.get(q2TST.namesFile)))
			{
				output = lines.skip(lineNumber - 1).findFirst().get(); 
				String[] outTokens = output.split(",");
				for(int j = 0; j < outTokens.length; j++){
					if(outTokens[j].equals(" ")){
						outTokens[j] = "null";
					}
				}
				System.out.println("\nID: " + outTokens[0]);
				System.out.println("Code: " + outTokens[1]);
				System.out.println("Name: " + outTokens[2]);
				System.out.println("Description: " + outTokens[3]);
				System.out.println("Latitude: " + outTokens[4]);
				System.out.println("Longitude: " + outTokens[5]);
				System.out.println("Zone ID: " + outTokens[6]);
				System.out.println("URL: " + outTokens[7]);
				System.out.println("Location Type: " + outTokens[8]);
				if (outTokens.length > 9)
				{
					System.out.println("Parent Station: " + outTokens[9]);
				}
				
			}
		}
	}


}
