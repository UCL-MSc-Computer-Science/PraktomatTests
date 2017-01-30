
public class LinearLUTM2FTest2 {

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
			myLut.insert("Value " + valueNo, valueNo++);
			System.out.println("After:\n" + myLut);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(sep);

		// Insert to non-empty LUT
		try {
			System.out.println(testNo++ + ": Insert to non-empty LUT");
			System.out.println("Before:\n" + myLut);
			myLut.insert("Value " + valueNo, valueNo++);
			System.out.println("After:\n" + myLut);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(sep);

		// Valid removal from LUT
		try {
			System.out.println(testNo++ + ": Valid removal from LUT");
			System.out.println("Before:\n" + myLut);
			myLut.remove("Value 0");
			System.out.println("After:\n" + myLut);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(sep);

		// Invalid removal from LUT (no such key)
		try {
			System.out.println(testNo++ + ": Invalid removal from LUT (no such key)");
			System.out.println("Before:\n" + myLut);
			myLut.remove("Value 10");
		} catch (LUTKeyException lke) {
			System.out.println(lke);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("After:\n" + myLut);
		System.out.println(sep);

		// Adding items for next test
		try {
			System.out.println("Adding items for next test...");
			System.out.println("Before:\n" + myLut);
			myLut.insert("Value " + valueNo, valueNo++);
			myLut.insert("Value " + valueNo, valueNo++);
			myLut.insert("Value " + valueNo, valueNo++);
			myLut.insert("Value " + valueNo, valueNo++);
			System.out.println("After:\n" + myLut);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(sep);

		// Valid retrieval. Should move retrieved element to beginning of list.
		try {
			System.out.println(testNo++ + ": Valid retrieval from LUT");
			System.out.println("Before:\n" + myLut);
			myLut.retrieve("Value 2");
			System.out.println("After:\n" + myLut);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(sep);

		// Invalid retrieval from LUT (no such key)
		try {
			System.out.println(testNo++ + ": Invalid retrieval from LUT (no such key)");
			System.out.println("Before:\n" + myLut);
			myLut.retrieve("Value 10");
		} catch (LUTKeyException lke) {
			System.out.println(lke);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("After:\n" + myLut);
		System.out.println(sep);
		
		// Valid update. Should move updated element to beginning of list.
		try {
			System.out.println(testNo++ + ": Valid update to LUT");
			System.out.println("Before:\n" + myLut);
			myLut.update("Value 4", 20);
			System.out.println("After:\n" + myLut);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(sep);
		
		// Invalid update to LUT (no such key)
		try {
			System.out.println(testNo++ + ": Invalid update to LUT (no such key)");
			System.out.println("Before:\n" + myLut);
			myLut.update("Value 10", 20);
		} catch (LUTKeyException lke) {
			System.out.println(lke);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("After:\n" + myLut);
		System.out.println(sep);

	}

}
