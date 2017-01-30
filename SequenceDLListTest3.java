import java.util.Random;

public class SequenceDLListTest3 {
	
	public static void main(String[] args) {
		
		SequenceDLList<String> myList = new SequenceDLList<String>();
		int testNo = 1;
		boolean pPrint = true;
		
		// Insert first when empty
		System.out.println(testNo + ": Insert first when empty");
		try {
			myList.insertFirst("Test " + testNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (pPrint) myList.prettyPrint();
		System.out.println("\n");
		testNo++;
		
		// Insert first when not empty
		System.out.println(testNo + ": Insert first when not empty");
		try {
			myList.insertFirst("Test " + testNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (pPrint) myList.prettyPrint();
		System.out.println("\n");
		testNo++;
		
		// Insert last when empty
		System.out.println(testNo + ": Insert last when empty");
		try {
			myList.clear();
			myList.insertLast("Test " + testNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (pPrint) myList.prettyPrint();
		System.out.println("\n");
		testNo++;
		
		// Insert last when not empty
		System.out.println(testNo + ": Insert last when not empty");
		try {
			myList.insertLast("Test " + testNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (pPrint) myList.prettyPrint();
		System.out.println("\n");
		testNo++;
		
		// Insert with invalid index
		System.out.println(testNo + ": Insert with invalid index (too low)");
		try {
			myList.insert("Test " + testNo, -1);
		} catch (SequenceException se) {
			System.out.println(se);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (pPrint) myList.prettyPrint();
		System.out.println("\n");
		testNo++;
		
		System.out.println(testNo + ": Insert with invalid index (too high)");
		try {
			myList.insert("Test " + testNo, 10);
		} catch (SequenceException se) {
			System.out.println(se);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (pPrint) myList.prettyPrint();
		System.out.println("\n");
		testNo++;
		
		// Insert to empty list
		System.out.println(testNo + ": Insert to empty list");
		try {
			myList.clear();
			myList.insert("Test " + testNo, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (pPrint) myList.prettyPrint();
		System.out.println("\n");
		testNo++;
		
		// Insert at index 0
		System.out.println(testNo + ": Insert at index 0");
		try {
			myList.insert("Test " + testNo, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (pPrint) myList.prettyPrint();
		System.out.println("\n");
		testNo++;
		
		// Insert in the middle of the list
		System.out.println(testNo + ": Insert in the middle of the list");
		try {
			myList.insert("Test " + testNo, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (pPrint) myList.prettyPrint();
		System.out.println("\n");
		testNo++;
		
		// Insert at the end of the list
		System.out.println(testNo + ": Insert at the end of the list");
		try {
			myList.insert("Test " + testNo, myList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (pPrint) myList.prettyPrint();
		System.out.println("\n");
		testNo++;
		
		// Delete first on an empty list
		System.out.println(testNo + ": Delete first on an empty list");
		try {
			myList.clear();
			myList.deleteFirst();
		} catch (SequenceException se) {
			System.out.println(se);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (pPrint) myList.prettyPrint();
		System.out.println("\n");
		testNo++;
		
		// Delete first when size == 1
		System.out.println(testNo + ": Delete first when size == 1");
		try {
			myList.insertFirst("Test " + testNo);
			if (pPrint) myList.prettyPrint();
			myList.deleteFirst();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (pPrint) myList.prettyPrint();
		System.out.println("\n");
		testNo++;
		
		// Delete first when size > 1
		System.out.println(testNo + ": Delete first when size > 1");
		try {
			myList.insertFirst("Test " + testNo);
			myList.insertFirst("Test " + testNo + " (2)");
			if (pPrint) myList.prettyPrint();
			myList.deleteFirst();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (pPrint) myList.prettyPrint();
		System.out.println("\n");
		testNo++;
		
		// Delete last on an empty list
		System.out.println(testNo + ": Delete last on an empty list");
		try {
			myList.clear();
			myList.deleteLast();
		} catch (SequenceException se) {
			System.out.println(se);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (pPrint) myList.prettyPrint();
		System.out.println("\n");
		testNo++;
		
		// Delete last when size == 1
		System.out.println(testNo + ": Delete last when size == 1");
		try {
			myList.insertFirst("Test " + testNo);
			if (pPrint) myList.prettyPrint();
			myList.deleteFirst();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (pPrint) myList.prettyPrint();
		System.out.println("\n");
		testNo++;
		
		// Delete last when size > 1
		System.out.println(testNo + ": Delete last when size > 1");
		try {
			myList.insertFirst("Test " + testNo);
			myList.insertFirst("Test " + testNo + " (2)");
			if (pPrint) myList.prettyPrint();
			myList.deleteLast();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (pPrint) myList.prettyPrint();
		System.out.println("\n");
		testNo++;
		
		// Delete on an empty list
		System.out.println(testNo + ": Delete on an empty list");
		try {
			myList.clear();
			myList.delete(0);
		} catch (SequenceException se) {
			System.out.println(se);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (pPrint) myList.prettyPrint();
		System.out.println("\n");
		testNo++;
		
		// Delete on an invalid index
		System.out.println(testNo + ": Delete on an invalid index (too low)");
		try {
			myList.delete(-1);
		} catch (SequenceException se) {
			System.out.println(se);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (pPrint) myList.prettyPrint();
		System.out.println("\n");
		testNo++;
		
		System.out.println(testNo + ": Delete on an invalid index (too high)");
		try {
			myList.delete(10);
		} catch (SequenceException se) {
			System.out.println(se);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (pPrint) myList.prettyPrint();
		System.out.println("\n");
		testNo++;
		
		// Delete when size == 1
		System.out.println(testNo + ": Delete when size == 1");
		try {
			myList.insertFirst("Test " + testNo);
			if (pPrint) myList.prettyPrint();
			myList.delete(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (pPrint) myList.prettyPrint();
		System.out.println("\n");
		testNo++;
		
		// Delete with index == 0
		System.out.println(testNo + ": Delete with index = 0");
		try {
			myList.insertFirst("Test " + testNo);
			myList.insertFirst("Test " + testNo + " (2)");
			if (pPrint) myList.prettyPrint();
			myList.delete(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (pPrint) myList.prettyPrint();
		System.out.println("\n");
		testNo++;
		
		// Delete at the end of the list
		System.out.println(testNo + ": Delete at the end of the list");
		try {
			myList.insertFirst("Test " + testNo);
			myList.insertFirst("Test " + testNo + " (2)");
			myList.insertFirst("Test " + testNo + " (3)");
			if (pPrint) myList.prettyPrint();
			myList.delete(myList.size() - 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (pPrint) myList.prettyPrint();
		System.out.println("\n");
		testNo++;
		
		// Delete in the middle of the list
		System.out.println(testNo + ": Delete in the middle of the list");
		try {
			myList.insertFirst("Test " + testNo);
			myList.insertFirst("Test " + testNo + " (2)");
			myList.insertFirst("Test " + testNo + " (3)");
			if (pPrint) myList.prettyPrint();
			myList.delete(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (pPrint) myList.prettyPrint();
		System.out.println("\n");
		testNo++;
		
		// Populate list for element test
		System.out.println("Populating list for element test");
		myList.clear();
		for (int i = 0 ; i < 20 ; i++) {
			myList.insertLast("Test " + testNo + " (" + i + ")");
		}
		myList.prettyPrint();
		
		// Element when index == 0
		System.out.println(testNo + ": Element when index == 0");
		try {
			System.out.println(myList.element(0));;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (pPrint) myList.prettyPrint();
		System.out.println("\n");
		testNo++;
		
		// Element from middle of list
		System.out.println(testNo + ": Element from the middle of the list");
		try {
			for (int i = 1 ; i < 10 ; i++) {
				System.out.print("Fetching element at index " + i + ": ");
				System.out.println(myList.element(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (pPrint) myList.prettyPrint();
		System.out.println("\n");
		testNo++;
		
		// Element from end of list
		System.out.println(testNo + ": Element from the end of the list");
		try {
			System.out.println(myList.element(myList.size() - 1));;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (pPrint) myList.prettyPrint();
		System.out.println("\n");
		testNo++;
		
		// Size when list is not empty
		System.out.println(testNo + ": Size when list is not empty");
		try {
			System.out.println(myList.size());;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (pPrint) myList.prettyPrint();
		System.out.println("\n");
		testNo++;
		
		// Empty when list is not empty
		System.out.println(testNo + ": Empty when list is not empty");
		try {
			System.out.println(myList.empty());;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (pPrint) myList.prettyPrint();
		System.out.println("\n");
		testNo++;
		
		// Size when list is empty
		System.out.println(testNo + ": Size when list is empty");
		try {
			myList.clear();
			System.out.println(myList.size());;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (pPrint) myList.prettyPrint();
		System.out.println("\n");
		testNo++;
		
		// Empty when list is empty
		System.out.println(testNo + ": Empty when list is empty");
		try {
			System.out.println(myList.empty());;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (pPrint) myList.prettyPrint();
		System.out.println("\n");
		testNo++;
		
		// Large lists
		myList.clear();
		int noOfNodes = 10000000;
		System.out.println("Testing with " + noOfNodes + " nodes.");
		long start = System.nanoTime();
		for (Integer i = new Integer(0) ; i < noOfNodes ; i++) {
			myList.insertLast(i.toString());
		}
		System.out.println("Time to create list = " +
				((System.nanoTime() - start) / 1000000000.0) + "s\n");
		
		Random r = new Random();
		int noOfTests = 20;
		start = System.nanoTime();
		for (int i = 1 ; i <= noOfTests ; i++) {
			int x = r.nextInt(noOfNodes);
			try {
				System.out.println("Element at index " + x + " = " + myList.element(x));
			} catch (SequenceException se) {
				se.printStackTrace();
			}
		}
		System.out.println("Time to retrieve elements = " +
				(((System.nanoTime() - start) / 1000000000.0) / noOfTests) + "s per retrieval\n");
		
		start = System.nanoTime();
		try {
			myList.element(noOfNodes / 2);
		} catch (SequenceException e) {
			e.printStackTrace();
		}
		System.out.println("Time for retrieval from middle of list = " +
				((System.nanoTime() - start) / 1000000000.0) + "s");
		
		start = System.nanoTime();
		try {
			myList.element(noOfNodes - 2);
		} catch (SequenceException e) {
			e.printStackTrace();
		}
		System.out.println("Time for retrieval from end of list = " +
				((System.nanoTime() - start) / 1000000000.0) + "s");
	}

}
