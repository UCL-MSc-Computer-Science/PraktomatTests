import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

//import BinaryTreeLUT.BSTreeNode; 


/*Generates a tree full of randomly chosen String keys and then deletes them in a random order.
 *Number of nodes and max length of key can be changed by changing the parameters of the generateNames function
 *I reckon deleting nodes randomly from several different sized trees should throw up any errors with the new delete method
 *You'll need to have ensured the delete method throws a LUTKeyException if the key to delete can't be found, otherwise you won't know anything is wrong
 *At the moment this needs the printLevelOrder() (brints breadth first) to be defined in your BinaryTreeLUT, which is included in BinaryTreeLUT_Printable
 *Or you could just remove that line and use you own print method */
/*Added lookForDuplicates() method to BinaryTreeLUT, couldn't add it to test files because importing inner BSTreeNode class wouldn't work
 *  So if you want to use it you have to add the appropriate methods (which are in BinaryTreeLUT_Printable) to your BinaryTreeLUT class.
 *  You should probably then delete them again before you hand in.*/
public class BinaryTreeLUTTestA {
    public static void main(String[] args) {
        try {
        	//Intialize Binary Tree
        	BinaryTreeLUT<Integer> myLUT = new BinaryTreeLUT<Integer>();
        	//Initiliaze Random used to randomly pick nodes to delete
            Random rand = new Random();
            
            //Generate List of names to create LUT from
            //Either load names from previous session
            /*ArrayList<String> names = load("names.bin");*/
            //Or generate list of random names. First parameter is number of names to generate, second is max name length
            ArrayList<String> names = generateNames(40, 7);
            //Save the names list automatically so that it can be reloaded next session if you want to rerun the same tree twice
            save(names, "names.bin");
            
            //Add names from names to LUT Entry value of 15 is irrelevant
    		for(int i = 0; i<names.size(); i++){
    			myLUT.insert(names.get(i), new Integer(15));
    		}
    		//Print intial Tree
    		System.out.println(myLUT);
    		//Print tree breadth first, shows which nodes are on which level, but not the connections between the nodes
    		myLUT.printLevelOrder(); 
    		//Go through LUT removing nodes with the keys randomly chosen from the names list
    		while(names.size() > 0){
    			//Pick random name/ key to delete
    			int index = rand.nextInt(names.size());
    			String key = names.get(index);
    			System.out.println("Removing " + key);
    			//Remove from LUT and from names list 
    			myLUT.remove(key);
    			////////////////////CHECK FOR DUPLICATES//////////////////
    			/////////////////////////////////////////////////////////
    			//UnComment this out if you have added lookForDuplicates() method to BinaryTreeLUT
    			/*if(myLUT.lookForDuplicates()){
    				System.out.println("Duplicate key. This is bad.");
    			}
    			else
    			{
    				System.out.println("Removed " + key + " successfully without leaving duplicates.");
    			}*/
    			//////////////////////////////////////////////////////////
    			//////////////////////////////////////////////////////////	
    			names.remove(index);
    			//Print tree after deletion
    			System.out.println(myLUT);
    			myLUT.printLevelOrder(); 
    		}
            System.out.println(myLUT);
            if(myLUT.root == null){
            	System.out.println("The tree is empty. (i.e. the root is null)");
            }
            else{
            	System.out.println("root != null. Things are not as they should be.");
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
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
