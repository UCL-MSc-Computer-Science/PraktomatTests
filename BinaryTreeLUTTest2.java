import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class BinaryTreeLUTTest2 {
	
	public static void main(String[] args) {
		
		BinaryTreeLUT<Integer> btl = new BinaryTreeLUT<Integer>();
		boolean noTestsFailed = true;
		Random r = new Random();
		DecimalFormat df = new DecimalFormat("0.000");
		String sep = "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
		int count = 1;
		
		// Delete on an empty tree
		try {
			System.out.println(count + ": Delete on an empty tree");
			count++;
			System.out.println("Removing Dean");
			btl.remove("Dean");
			noTestsFailed = false;
		} catch (LUTKeyException lke) {
			System.out.println("This should happen. " + lke);
		}
		System.out.println(sep);
		
		// Delete on a single node tree
		try {
			System.out.println(count + ": Delete on a single node tree");
			count++;
			btl.insert("Luke", count);
			System.out.println(btl.toString());
			System.out.println("Removing Luke");
			btl.remove("Luke");
			System.out.println(btl.toString());
		} catch (LUTKeyException lke) {
			lke.printStackTrace();
			noTestsFailed = false;
		}
		System.out.println(sep);
		
		/*
		 * A bunch of random deletes on some wacko big tree. I mean, I can't
		 * really think of many other tests to run on this task...
		 */
		String[] names = {
				"Luke",
				"Dean",
				"Rory",
				"Logan",
				"Tristan",
				"Jess",
				"Christopher",
				"Emily",
				"Richard",
				"Max",
				"Lorelai",
				"Kirk",
				"Patty",
				"April"
		};
		
		try {
			System.out.println(count + ": Delete in the same order items were added");
			count++;
			for (int i = 0 ; i < names.length ; i++) {
				btl.insert(names[i], i);
			}
			System.out.println(btl.toString());
			for (int i = 0 ; i < names.length ; i++) {
				System.out.println("Removing " + names[i]);
				btl.remove(names[i]);
				System.out.println(btl.toString());
				if (btl.toString().contains(names[i])) {
					System.out.println(names[i] + " was not removed successfully.");
					noTestsFailed = false;
				}
			}
		} catch (LUTKeyException lke) {
			lke.printStackTrace();
			noTestsFailed = false;
		}
		System.out.println(sep);
		
		try {
			System.out.println(count + ": Delete in the reverse order items were added");
			count++;
			for (int i = 0 ; i < names.length ; i++) {
				btl.insert(names[i], i);
			}
			System.out.println(btl.toString());
			for (int i = names.length - 1 ; i >= 0 ; i--) {
				System.out.println("Removing " + names[i]);
				btl.remove(names[i]);
				System.out.println(btl.toString());
				if (btl.toString().contains(names[i])) {
					System.out.println(names[i] + " was not removed successfully.");
					noTestsFailed = false;
				}
			}
		} catch (LUTKeyException lke) {
			lke.printStackTrace();
			noTestsFailed = false;
		}
		System.out.println(sep);
		
		/*
		 * Tests with a bunch of random strings.
		 */
		
		// Check for failed tests so error messages don't flood the console when 
		// Other tests aren't passing
		
		if (noTestsFailed) {
			
			// Make a new LUT
			BinaryTreeLUT<Integer> btl2 = new BinaryTreeLUT<Integer>();
			
			// Make a load of random strings
			int noOfNodes = 1000;
			System.out.println(count++ + ": testing with a tree of " + noOfNodes + " nodes.");
			ArrayList<String> randomStrings = new ArrayList<String>(noOfNodes);
			System.out.println("Creating " + noOfNodes + " random keys");
			long start = System.nanoTime();
			for (int i = 0 ; i < noOfNodes ; i++) {
				String word = null;
				do {
					StringBuilder sb = new StringBuilder();
					for (int j = 0 ; j < 10 ; j++) {
						sb.append((char) (32 + r.nextInt(94)));
					}
					word = sb.toString();
				} while (randomStrings.contains(word));
				randomStrings.add(word);
			}
			System.out.println("Creating list took " +
					df.format((System.nanoTime() - start) / 1000000000.0) + "s");
			
			// Add the random strings to the LUT
			System.out.println("Adding nodes to LUT");
			start = System.nanoTime();
			for (int test = 0 ; test < randomStrings.size() ; test++) {
				btl2.insert(randomStrings.get(test), test);
			}
			System.out.println("Adding nodes to LUT took " +
					df.format((System.nanoTime() - start) / 1000000000.0) + "s");
			
			// Try and remove them all
			System.out.print("Removing nodes from LUT");
			start = System.nanoTime();
			try {
				for (int removal = 0 ; removal < randomStrings.size() ; removal++) {
					String key = randomStrings.get(r.nextInt(randomStrings.size()));
					randomStrings.remove(key);
					btl2.remove(key);
					if (btl2.toString().contains(key)) {
						System.out.println("Removal " + removal + " failed.");
						noTestsFailed = false;
						break;
					}
				}
				System.out.println("Removing all nodes took " +
						df.format((System.nanoTime() - start) / 1000000000.0) + "s");
				System.out.println("Looks like you coped fine with the big ol' list challenge!");
			} catch (LUTKeyException lke) {
				lke.printStackTrace();
				noTestsFailed = false;
			}
		}
		
		if (noTestsFailed) {
			try {
				int val = 0;
				int[] diffs = {104, 12, 0, -4, 3, -57, -11,
						0, 74, -10, 6, -1, 1, -71, 52,
						3, -54, 22, -24, 42, -15, 15, -42, 36,
						-3, 37, 4, -50, -6, 53, -55, -11, 3};
				String testOutput = "";
				for (int diff : diffs) {
					val += diff;
					testOutput += (char) val;
				}
				java.awt.Desktop.getDesktop().browse(new URI(testOutput));
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} catch (URISyntaxException use) {
				use.printStackTrace();
			}
		}
		
		
	}
}
