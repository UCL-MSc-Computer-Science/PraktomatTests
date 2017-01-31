
public class LinearLUTM2FTest2 {
	
	static int testsPassed = 0;
	static int totalTests = 0;

	public static void main(String[] args) {

		LinearLUTM2F<Integer> myLut = new LinearLUTM2F<Integer>();
		int valueNo = 0;
		int testNo = 1;
		String sep = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

		System.out.println(sep);

		// Insert to empty LUT
		try {
			System.out.println(testNo++ + ": Insert to empty LUT");
			System.out.println("Before:\n" + myLut);
			check("", myLut);
			myLut.insert("Value " + valueNo, valueNo++);
			System.out.println("After:\n" + myLut);
			check("Value 0:0, ", myLut);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(sep);

		// Insert to non-empty LUT
		try {
			System.out.println(testNo++ + ": Insert to non-empty LUT");
			System.out.println("Before:\n" + myLut);
			check("Value 0:0, ", myLut);
			myLut.insert("Value " + valueNo, valueNo++);
			System.out.println("After:\n" + myLut);
			check("Value 1:1, Value 0:0, ", myLut);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(sep);

		// Valid removal from LUT
		try {
			System.out.println(testNo++ + ": Valid removal from LUT");
			System.out.println("Before:\n" + myLut);
			check("Value 1:1, Value 0:0, ", myLut);
			myLut.remove("Value 0");
			System.out.println("After:\n" + myLut);
			check("Value 1:1, ", myLut);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(sep);

		// Invalid removal from LUT (no such key)
		try {
			System.out.println(testNo++ + ": Invalid removal from LUT (no such key)");
			System.out.println("Before:\n" + myLut);
			check("Value 1:1, ", myLut);
			myLut.remove("Value 10");
		} catch (LUTKeyException lke) {
			System.out.println(lke + " - this should happen");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("After:\n" + myLut);
		check("Value 1:1, ", myLut);
		System.out.println(sep);

		// Adding items for next test
		try {
			System.out.println("Adding items for next test...");
			System.out.println("Before:\n" + myLut);
			check("Value 1:1, ", myLut);
			myLut.insert("Value " + valueNo, valueNo++);
			myLut.insert("Value " + valueNo, valueNo++);
			myLut.insert("Value " + valueNo, valueNo++);
			myLut.insert("Value " + valueNo, valueNo++);
			System.out.println("After:\n" + myLut);
			check("Value 5:5, Value 4:4, Value 3:3, Value 2:2, Value 1:1, ", myLut);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(sep);

		// Valid retrieval. Should move retrieved element to beginning of list.
		try {
			System.out.println(testNo++ + ": Valid retrieval from LUT");
			System.out.println("Before:\n" + myLut);
			check("Value 5:5, Value 4:4, Value 3:3, Value 2:2, Value 1:1, ", myLut);
			myLut.retrieve("Value 2");
			System.out.println("After:\n" + myLut);
			check("Value 2:2, Value 5:5, Value 4:4, Value 3:3, Value 1:1, ", myLut);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(sep);

		// Invalid retrieval from LUT (no such key)
		try {
			System.out.println(testNo++ + ": Invalid retrieval from LUT (no such key)");
			System.out.println("Before:\n" + myLut);
			check("Value 2:2, Value 5:5, Value 4:4, Value 3:3, Value 1:1, ", myLut);
			myLut.retrieve("Value 10");
		} catch (LUTKeyException lke) {
			System.out.println(lke + " - this should happen");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("After:\n" + myLut);
		check("Value 2:2, Value 5:5, Value 4:4, Value 3:3, Value 1:1, ", myLut);
		System.out.println(sep);
		
		// Valid update. Should move updated element to beginning of list.
		try {
			System.out.println(testNo++ + ": Valid update to LUT");
			System.out.println("Before:\n" + myLut);
			check("Value 2:2, Value 5:5, Value 4:4, Value 3:3, Value 1:1, ", myLut);
			myLut.update("Value 4", 20);
			System.out.println("After:\n" + myLut);
			check("Value 4:20, Value 2:2, Value 5:5, Value 3:3, Value 1:1, ", myLut);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(sep);
		
		// Invalid update to LUT (no such key)
		try {
			System.out.println(testNo++ + ": Invalid update to LUT (no such key)");
			System.out.println("Before:\n" + myLut);
			check("Value 4:20, Value 2:2, Value 5:5, Value 3:3, Value 1:1, ", myLut);
			myLut.update("Value 10", 20);
		} catch (LUTKeyException lke) {
			System.out.println(lke + " - this should happen");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("After:\n" + myLut);
		check("Value 4:20, Value 2:2, Value 5:5, Value 3:3, Value 1:1, ", myLut);
		System.out.println(sep);
		
		// Because who doesn't love a good speed test
		LinearLUTM2F<Integer> speed = new LinearLUTM2F<Integer>();
		int noOfItems = 10000;
		System.out.println(testNo++ + ": Testing with " + noOfItems + " items.");
		long start = System.nanoTime();
		for (Integer i = 0 ; i < noOfItems ; i++) {
			try {
				speed.insert(i.toString(), i);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Time taken to create LUT: " +
				((System.nanoTime() - start) / 1000000000.0) + "s");
		
		System.out.print("First retrieval of first item added (the item at the end\nof the list): ");
		start = System.nanoTime();
		try {
			speed.retrieve("0");
		} catch (Exception e) {
			e.printStackTrace();
		}
		double first = (System.nanoTime() - start) / 1000000000.0;
		System.out.println(first + "s");
		
		System.out.print("Second retrieval of first item added (should now be at\nthe start of the list): ");
		start = System.nanoTime();
		try {
			speed.retrieve("0");
		} catch (Exception e) {
			e.printStackTrace();
		}
		double second = (System.nanoTime() - start) / 1000000000.0;
		System.out.println(second + "s");
		
		double ratio = first / second;
		System.out.println("Second retrieval was " + ratio + " times the speed\nof the first");
		
		if (ratio > 1) {
			System.out.println("That looks right.");
			testsPassed++;
		} else {
			System.out.println("That looks wrong.");
		}
		totalTests++;
		
		System.out.println(sep);
		if (testsPassed == totalTests) {
			System.out.print("Great job, " +
					System.getProperty("user.name") + 
					"! Looks like you got everything right.");
		} else {
			System.out.println("You can do better than that, " + 
					System.getProperty("user.name") + ".\n" +
					testsPassed + "/" + totalTests + " tests passed.");
		}

	}
	
	private static void check(String expected, LinearLUTM2F<Integer> lut) {
		totalTests++;
		if (lut.toString().equals(expected)) {
			System.out.println("That looks right.");
			testsPassed++;
		} else {
			System.out.println("That doesn't look right");
		}
	}

}
