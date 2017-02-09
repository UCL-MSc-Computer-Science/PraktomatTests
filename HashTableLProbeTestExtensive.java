import java.util.ArrayList;
import java.util.Random;

/**
 * @author michaelscott
 * @since 9 Feb 2017
 * @version 1.0
 */

public class HashTableLProbeTestExtensive 
{
	public static void main(String[] args) 
	{
		////////////////////////////////////////////////////////Balanced Tree (Random) Part//////////////////////////////////////////////////////////////////
		for(int j =0; j < 20000 ; j++)
		{
			Random random = new Random();
			int noOfEntries = random.nextInt(50);
			int sizeOfTable = noOfEntries + random.nextInt(50);
			int maxLength = random.nextInt(10) + 3; //Can't use a maxLength less than 3 otherwise the generate names method can't generate enough unique names, and loops for ever. Would have to increase this with even more nodes
	
				try {
		        	//Intialize Binary Tree
					HashTableLProbe<Integer> myLUT = new HashTableLProbe<Integer>(sizeOfTable);
		        	//Initiliaze Random used to randomly pick nodes to delete
		            Random rand = new Random();
		            //Or generate list of random names. First parameter is number of names to generate, second is max name length
		            ArrayList<String> names = generateNames(noOfEntries, maxLength);
		            //Add names from names to LUT Entry value of 15 is irrelevant
		    		for(int i = 0; i<names.size(); i++){
		    			myLUT.insert(names.get(i), new Integer(15));
		    		}
		    		//Go through LUT removing nodes with the keys randomly chosen from the names list
		    		while(names.size() > 0){
		    			//Pick random name/ key to delete
		    			int index = rand.nextInt(names.size());
		    			String key = names.get(index);
		    			//Remove from LUT and from names list 
		    			myLUT.delete(key);
		    			names.remove(index);
		    		}
		    		//Test to see if tree is empty
	    			if(!isTableEmpty(myLUT)){
	    				throw new Exception("Non-empty table when all entries should have been deleted");
	    			}
	    			else
	    			{
	    				if(j%100 == 0){
							System.out.println("Iteration = " + j + " successfully completed.");
						}
	    			}
		        } catch (Exception e) {
		        	e.printStackTrace();
		        }
			}
			System.out.println("All Iterations complete. If no errors or exceptions were thrown, you're probably good.");
	}
	
	
	/////////////////////////////////////////////Test to see if table is empty////////////////////////////////////////////////////////////////////////////
	public static boolean isTableEmpty(HashTableLProbe<Integer>  table){
		boolean isEmpty = true;
		for(int i = 0; i<table.entries.length; i++){
			if(table.entries[i] != null){
				System.out.println(" i = " + i);
				isEmpty = false;
			}
		}
		return isEmpty;
	}
	/////////////////////////////////////////////Generate List of "random names" (i.e. alphabetical strings) /////////////////////////////////////////////
	    
	//Check if String name is in a list of names names
	public static boolean inList(ArrayList<String> names, String name)
	{
		for(int i = 0; i<names.size(); i++)
		{
			if(name.equals(names.get(i)))
			{
				return true;
			}
		}
		return false;
	}
	
	//Generate a list noOfNames long, containing Strings with only lowercase alphabetical characters. With max String length maxLengthOfNames + 1
	public static ArrayList<String> generateNames(int noOfNames, int maxLengthOfNames)
	{
		ArrayList<String> names = new ArrayList<String>(noOfNames);
		Random rand = new Random();
		for(int i = 0; i<noOfNames; i++)
		{
			//Generate a pseudo random name with length between 1 and (maxLengthOfNames +1)
			String newName = generateString(rand.nextInt(maxLengthOfNames - 1) + 1);
			//If name already in list, decrement i, i.e. repeat
			if(inList(names, newName)){
				i--;
			}
			//Else add to list
			else{
				names.add(newName);
			}
		}
		return names;
	}
	//Generate a random string of lowercase alphabetical characters, of length length
	public static String generateString(int length)
	{
		Random rand = new Random();
		//List of possible chars
		String characters = "abcdefghijklmnopqrtsuvwxyz";
		//Char array for storing String
		char[] text = new char[length];
		for(int i = 0; i<length; i++){
			//Select random char from characters array
			text[i] = characters.charAt(rand.nextInt(characters.length()));
		}
		return new String(text);
	}
    
}
