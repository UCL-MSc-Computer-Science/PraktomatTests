import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*Repeats BinaryTreeLUTTestA for 2000 different trees where each tree has a random number of nodes between 0 and 50 
 * All but one print statement has been commented out, because it would be unreadable
 * If it reaches finished without any problems, It probably works.
 * Have also randomised max length of the names, although that's not strictly neccessary
 * And I realised if the noOfNodes is increased the maxLength of the keys may also need to be increased. 
 * Because if the maxLength is too small it is not possible to create enough unique keys to fill the tree and the generate names method will loop forever
 * Consequently with noOfNodes at 50 I have set a minimum maxLength of 3 (i.e. 26**3)*/
/*Second half generates a unbalanced tree (i.e. very tall) and then deletes randomly from that
 * as all the random trees in the first half are very unlikely to be skew, so I thought I'd test that too.*/
public class BinaryTreeLUTTestA1 
{
    public static void main(String[] args) 
    {
    	////////////////////////////////////////////////////////Balanced Tree (Random) Part//////////////////////////////////////////////////////////////////
    	for(int j =0; j < 20000 ; j++)
    	{
    		Random random = new Random();
    		int noOfNodes = random.nextInt(50);
    		int maxLength = random.nextInt(10) + 3; //Can't use a maxLength less than 3 otherwise the generate names method can't generate enough unique names, and loops for ever. Would have to increase this with even more nodes
    		
	        try {
	        	//Intialize Binary Tree
	        	BinaryTreeLUT<Integer> myLUT = new BinaryTreeLUT<Integer>();
	        	//Initiliaze Random used to randomly pick nodes to delete
	            Random rand = new Random();
	            
	            //Generate List of names to create LUT from
	            //Either load names from previous session
	            //ArrayList<String> names = load("names.bin");
	            //Or generate list of random names. First parameter is number of names to generate, second is max name length
	            ArrayList<String> names = generateNames(noOfNodes, maxLength);
	            //Save the names list automatically so that it can be reloaded next session if you want to rerun the same tree twice
	            //save(names, "names.bin");
	            
	            //Add names from names to LUT Entry value of 15 is irrelevant
	    		for(int i = 0; i<names.size(); i++){
	    			myLUT.insert(names.get(i), new Integer(15));
	    		}
	    		//Print intial Tree
	    		System.out.println(myLUT);
	    		//Print tree breadth first, shows which nodes are on which level, but not the connections between the nodes
	    		//myLUT.printLevelOrder(); 
	    		//Go through LUT removing nodes with the keys randomly chosen from the names list
	    		while(names.size() > 0){
	    			//Pick random name/ key to delete
	    			int index = rand.nextInt(names.size());
	    			String key = names.get(index);
	    			//System.out.println("Removing " + key);
	    			//Remove from LUT and from names list 
	    			myLUT.remove(key);
	    			names.remove(index);
	    			//Print tree after deletion
	    			//System.out.println(myLUT);
	    			//myLUT.printLevelOrder(); 
	    		}
	            //System.out.println(myLUT);
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
    	}
    	System.out.println("FINISHED RANDOM PART \n \n \n");
    	
    	////////////////////////////////////////////////////////Unbalanced Tree Part//////////////////////////////////////////////////////////////////
    	for(int k = 1; k<20; k++){
    		try 
	       	 {
	       		 int size = k;
	       		 Random randy = new Random();
	       		//Intialize Binary Tree
	   	    	BinaryTreeLUT<Integer> myLUT = new BinaryTreeLUT<Integer>();
	   	    	ArrayList<String> orderedNames = generateInOrderNames(size);
	   	    	for(int i = 0; i<orderedNames.size(); i++)
	   	    	{
	   				myLUT.insert(orderedNames.get(i), new Integer(15));
	   			}
	           	//Print intial Tree
	       		//Print tree breadth first, shows which nodes are on which level, but not the connections between the nodes
	       		myLUT.printLevelOrder(); 
	       		while(orderedNames.size() > 0)
	       		{
	       			//Pick random name/ key to delete
	       			int index = randy.nextInt(orderedNames.size());
	       			String key = orderedNames.get(index);
	       			System.out.println("Removing " + key);
	       			//Remove from LUT and from orderedNames list 
	       			myLUT.remove(key);
	       			orderedNames.remove(index);
	       			//Print tree after deletion
	       			myLUT.printLevelOrder(); 
	       		}
	   	        	
	       	 }catch (Exception e) 
	       	 {
	   	        	e.printStackTrace();
	   	     }
    	}
    	System.out.println("FINISHED ORDERED PART");
    	 
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
	public static ArrayList<String> generateNames(int noOfNames, int maxLengthOfNames){
		ArrayList<String> names = new ArrayList<String>(noOfNames);
		Random rand = new Random();
		for(int i = 0; i<noOfNames; i++)
		{
			//Generate a pseudo random name with length between 1 and (maxLengthOfNames +1)
			String newName = generateString(rand.nextInt(maxLengthOfNames-1) + 1);
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
	
/////////////////////////////////////////////Generate ordered names etc. /////////////////////////////////////////////
	
	public static ArrayList<String> generateInOrderNames(int noOfNames) throws Exception
	{
		ArrayList<String> orderedNames = new ArrayList<String>(noOfNames);
		for(int i = 0; i< noOfNames; i++)
		{
			String newName = generate(i);

			if(inList(orderedNames, newName))
			{
				throw new Exception("This name already in ordered list, this should not happen, but it is a problem with the test not the Tree implementation.");
			}
			else{
				orderedNames.add(newName);
			}
			
		}
		return orderedNames;
	}
	
	public static String generate(int position)
	{
		String characters = "abcdefghijklmnopqrtsuvwxyz";
		ArrayList<Integer> intList = new ArrayList<Integer>();
		int n = position;
		while (n > 0) {
		  int d = n / 10;
		  int k = n - d * 10;
		  n = d;
		  intList.add(k);
		}
		char[] text = new char[intList.size()];
		for(int i = 0; i< intList.size(); i++)
		{
			char toAdd = characters.charAt(intList.get(i));
			text[i] = toAdd;
		}
		char[] flipped = reverseString(text);
		return new String(flipped);
	}
	
	public static char[] reverseString(char[] arr){
		int length = arr.length;
		char[] reversed = new char[length];
		for(int i = 0; i< length; i++){
			reversed[i] = arr[length-i-1];
		}
		return reversed;
	}
	
	public static <E> void printList(List<E> list)
	{
		for(int i = 0; i<list.size() - 1; i++){
			System.out.print(list.get(i) + " - ");
		}
			System.out.println(list.get(list.size() -1 ));
	}
	
}
