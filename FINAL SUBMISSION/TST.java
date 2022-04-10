import java.util.*;
public class TST {
	public static ArrayList<Integer> allNames = new ArrayList<>(); //This is use to track lineids
	TSTNode root;

	protected TST()
	{
		root = null;
	}

	protected int get(String key)
	{
		if (key.isEmpty())
		{
			return -1;
		}
		key = key.toUpperCase(); 
		return get(root, key);
	}


	protected int get(TSTNode node, String key)
	{
		char c = key.charAt(0);
		if (node == null)
		{   
			return -1;
		}
		else if (key.length() > 1)
		{
			if (c == node.data)
			{   
				return get(node.mid, key.substring(1));
			}
			else if (c > node.data)
			{   
				return get(node.right, key);
			}
			else
			{   
				return get(node.left, key);
			}
		}
		else
		{   

			if (c == node.data)
			{   
				if (node.value != null)
				{   
					return node.value;
				}
				else
				{   
					traverse(node, "");
					return 0;
				}
			}
			else if (c > node.data)
			{
				if (node.value == null)
				{   
					return get(node.right, key);
				}
				return -1; 
			}
			else
			{
				if (node.value == null)
				{   
					return get(node.left, key);
				}
				return -1;
			}
		}
	}


	private void traverse(TSTNode node, String string)
	{
		if (node != null)
		{
			traverse(node.left, string);
			string = string + node.data;
			if (node.value != null)
			{   
				allNames.add(node.value);
			}
			traverse(node.mid, string);
			string = string.substring(0, string.length() - 1);
			traverse(node.right, string);
		}
	}


	protected void put(String key, int value)
	{
		if (key.isEmpty())
		{
			System.out.println("invalid input to TST.put()!");
			return;
		}
		key = key.toUpperCase();
		root = put(root, key, value);
	}


	protected TSTNode put(TSTNode node, String key, int value)
	{
		char c = key.charAt(0);
		if (key.length() > 1)
		{
			if (node == null)
			{   
				node = new TSTNode(c, null);
				node.mid = put(node.mid, key.substring(1), value);
				return node;
			}
			else if (c == node.data)
			{   
				node.mid = put(node.mid, key.substring(1), value);
				return node;
			}
			else if (c < node.data)
			{   
				node.left = put(node.left, key, value);
				return node;
			}
			else
			{   
				node.right = put(node.right, key, value);
				return node;
			}
		}
		else
		{
			if (node == null)
			{
				return new TSTNode(c, value);
			}
			else
			{   
				return node;
			}
		}
	}

}
