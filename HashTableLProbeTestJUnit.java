import static org.junit.Assert.*;
/*
 * If this import cannot be resolved:
 * https://stackoverflow.com/questions/15105556/the-import-org-junit-cannot-be-resolved
 */

import org.junit.Test;

public class HashTableLProbeTestJUnit {
	
	private String[] keys = {
			"Lorelai",
			"Rory",
			"Emily",
			"Richard",
			"Christopher",
			"Luke",
			"Max",
			"Dean",
			"Jess",
			"Logan",
			"Kirk",
			"Taylor",
			"Patty"
	};
	
	private void populateHT(HashTableLProbe<Integer> ht, int noOfEntries) {
		if (noOfEntries > keys.length) {
			System.out.println("Only " + keys.length + 
					" entries available to populate hash table.");
			noOfEntries = keys.length;
		}
		for (int i = 0 ; i < noOfEntries ; i++) {
			try {
				ht.insert(keys[i], i);
			} catch (LUTKeyException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	@Test
	public void shouldAlwaysPass() {
		assertEquals("", "");
	}
	
	
	/*
	 *  Delete tests
	 */
	
	// Delete only item in table
	@Test
	public void deleteOnlyItem() {
		try {
			HashTableLProbe<Integer> ht = new HashTableLProbe<Integer>(5);
			populateHT(ht, 1);
			ht.delete(keys[0]);
			assertEquals("0: null\n" +
					"1: null\n" +
					"2: null\n" +
					"3: null\n" +
					"4: null\n", 
					ht.toString());
		} catch (LUTKeyException lke) {
			fail("LUTKeyException should not be thrown");
		} catch (Exception e) {
			fail("An Exception other than LUTKeyException was thrown");
		}
	}
	
	
	// Delete from full table
	@Test
	public void deleteFromFullTable() {
		try {
			HashTableLProbe<Integer> ht = new HashTableLProbe<Integer>(5);
			populateHT(ht, 5);
			ht.delete(keys[0]);
			assertEquals("Most likely cause: rehashing after deletion is not working",
					"0: null\n" +
					"1: Richard 3\n" +
					"2: Emily 2\n" +
					"3: Rory 1\n" +
					"4: Christopher 4\n", 
					ht.toString());
		} catch (LUTKeyException lke) {
			fail("LUTKeyException should not be thrown");
		} catch (Exception e) {
			fail("An Exception other than LUTKeyException was thrown");
		}
	}
	
	// Delete with tombstones
	@Test
	public void deleteWithTombstones() {
		try {
			HashTableLProbe<Integer> ht = new HashTableLProbe<Integer>(5);
			populateHT(ht, 5);
			ht.remove(keys[1]);
			ht.delete(keys[2]);
			assertEquals("Most likely cause: rehashing after deletion is not working",
					"0: null\n" +
					"1: Richard 3\n" +
					"2: Lorelai 0\n" +
					"3: Christopher 4\n" +
					"4: null\n", 
					ht.toString());
		} catch (LUTKeyException lke) {
			fail("LUTKeyException should not be thrown");
		} catch (Exception e) {
			fail("An Exception other than LUTKeyException was thrown");
		}
	}
	
	// Invalid deletion (invalid key)
	@Test (expected = LUTKeyException.class)
	public void invalidKeyDeletion() throws LUTKeyException {
		HashTableLProbe<Integer> ht = new HashTableLProbe<Integer>(5);
		populateHT(ht, 5);
		ht.delete(keys[10]);
		assertEquals("0: null\n" +
				"1: Richard 3\n" +
				"2: Emily 2\n" +
				"3: Rory 1\n" +
				"4: Christopher 4\n", 
				ht.toString());
		fail("Invalid deletion should have thrown LUTKeyException");
	}

	
	/*
	 *  Resize tests
	 */
	
	// Resize an empty table
	@Test
	public void resizeEmptyTable() {
		try {
			HashTableLProbe<Integer> ht = new HashTableLProbe<Integer>(5);
			ht.resize(1);
			assertEquals("0: null\n", ht.toString());
		} catch (Exception e) {
			fail("Table did not resize successfully");
		}
	}
	
	// Resize a full table
	@Test
	public void resizeFullTable() {
		try {
			HashTableLProbe<Integer> ht = new HashTableLProbe<Integer>(5);
			populateHT(ht, 5);
			ht.resize(10);
			assertEquals("0: null\n" +
					"1: Richard 3\n" +
					"2: Lorelai 0\n" +
					"3: Christopher 4\n" +
					"4: Emily 2\n" +
					"5: null\n" +
					"6: null\n" +
					"7: null\n" +
					"8: Rory 1\n" +
					"9: null\n", 
					ht.toString());
		} catch (LUTKeyException lke) {
			fail("Most likely cause: rehashing after resizing is not working");
		} catch (Exception e) {
			fail("An Exception other than LUTKeyException was thrown");
		}
	}
	
	// Invalid resize (fewer spaces than entries)
	@Test (expected = LUTKeyException.class)
	public void resizeToSmallerThanRequired() throws LUTKeyException {
		HashTableLProbe<Integer> ht = new HashTableLProbe<Integer>(5);
		populateHT(ht, 5);
		ht.resize(3);
		fail("Invalid resize should have thrown LUTKeyException");
	}
	
	// Resize when table contains tombstones
	@Test
	public void resizeWithTombstones() {
		try {
			HashTableLProbe<Integer> ht = new HashTableLProbe<Integer>(10);
			populateHT(ht, 8);
			ht.remove(keys[0]);
			ht.remove(keys[2]);
			ht.remove(keys[4]);
			ht.remove(keys[6]);
			ht.resize(5);
			assertEquals("0: null\n" +
					"1: Richard 3\n" +
					"2: Luke 5\n" +
					"3: Dean 7\n" +
					"4: Rory 1\n", 
					ht.toString());
		} catch (LUTKeyException lke) {
			fail("Most likely cause: tombstones were added to the new table");
		} catch (Exception e) {
			fail("An Exception other than LUTKeyException was thrown");
		}
	}
	
}
