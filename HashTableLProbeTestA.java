import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author michaelscott
 * @since 9 Feb 2017
 * @version 1.0
 */

public class HashTableLProbeTestA {
	public static void main(String[] args) {
	////////////////////////////////////////////////////////Balanced Tree (Random) Part//////////////////////////////////////////////////////////////////
			try {
				int sizeOfTable = 50;
				int noOfEntries = 40;
				int maxLength = 7;
	        	//Intialize Binary Tree
				HashTableLProbe<Integer> myLUT = new HashTableLProbe<Integer>(sizeOfTable);
	        	//Initiliaze Random used to randomly pick nodes to delete
	            Random rand = new Random();
	            
	            //Generate List of names to create LUT from list of names
	            //Either load names from previous session
	            /*ArrayList<String> names = load("names.bin");*/
	            //Or generate list of random names. First parameter is number of names to generate, second is max name length
	            ArrayList<String> names = generateNames(noOfEntries, maxLength);
	            //Save the names list automatically so that it can be reloaded next session if you want to rerun the same tree twice
	            save(names, "names.bin");
	         
	            //Add names from names to LUT Entry value of 15 is irrelevant
	    		for(int i = 0; i<names.size(); i++){
	    			myLUT.insert(names.get(i), new Integer(15));
	    		}
	    		//Print intial Tree
	    		System.out.println(myLUT);
	    		//Go through LUT removing nodes with the keys randomly chosen from the names list
	    		while(names.size() > 0){
	    			//Pick random name/ key to delete
	    			int index = rand.nextInt(names.size());
	    			String key = names.get(index);
	    			System.out.println("Removing " + key);
	    			//Remove from LUT and from names list 
	    			myLUT.delete(key);
	    			names.remove(index);
	    			//Print tree after deletion
	    			System.out.println(myLUT);
	    		}
	    		//Test to see if tree is empty
    			if(!isTableEmpty(myLUT)){
    				throw new Exception("Non-empty table when all entries should have been deleted");
    			}
    			else
    			{
    				System.out.println("Table successfully emptied");
    			}
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
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
	
 /////////////////////////////////////////////SAVE AND LOAD METHODS TO REDO SPECIFIC TREE /////////////////////////////////////////////
    
    public static void save(ArrayList<String> names, String filename){
    	try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename))) {
            // Write entire arrayList
            os.writeObject(names);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }
    //Load arrayList to redo problematic LUT
    public static ArrayList<String> load(String filename)
    {
    	try (ObjectInputStream os = new ObjectInputStream(new FileInputStream(filename))) 
    	{
            // Read entire arrayList
            @SuppressWarnings("unchecked")
            ArrayList<String> people = (ArrayList<String>)os.readObject();
            return people;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    	return null;
    }
    
}
