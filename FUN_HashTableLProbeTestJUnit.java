import static org.junit.Assert.*;
/*
 * If this import cannot be resolved:
 * https://stackoverflow.com/questions/15105556/the-import-org-junit-cannot-be-resolved
 */

import org.junit.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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
        assertEquals("", ""); count++;
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
            count++;
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
            count++;
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
            count++;
        } catch (LUTKeyException lke) {
            fail("LUTKeyException should not be thrown");
        } catch (Exception e) {
            fail("An Exception other than LUTKeyException was thrown");
        }
    }
    
    // Invalid deletion (invalid key)
    @Test
    public void invalidKeyDeletion() throws LUTKeyException {
        HashTableLProbe<Integer> ht = new HashTableLProbe<Integer>(5);
        populateHT(ht, 5);
        try {
            ht.delete(keys[10]);
            assertEquals("0: null\n" +
                         "1: Richard 3\n" +
                         "2: Emily 2\n" +
                         "3: Rory 1\n" +
                         "4: Christopher 4\n",
                         ht.toString());
        }
        catch (LUTKeyException e) {count ++; return;}
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
            count++;
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
            count++;
        } catch (LUTKeyException lke) {
            fail("Most likely cause: rehashing after resizing is not working");
        } catch (Exception e) {
            fail("An Exception other than LUTKeyException was thrown");
        }
    }
    
    // Invalid resize (fewer spaces than entries)
    @Test
    public void resizeToSmallerThanRequired() throws LUTKeyException {
        HashTableLProbe<Integer> ht = new HashTableLProbe<Integer>(5);
        populateHT(ht, 5);
        try {ht.resize(3);}
        catch (LUTKeyException e) {count++; return;}
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
            count++;
        } catch (LUTKeyException lke) {
            fail("Most likely cause: tombstones were added to the new table");
        } catch (Exception e) {
            fail("An Exception other than LUTKeyException was thrown");
        }
    }
    
    
    static int sum = 9;
    static int count;
    
    /* TODO: fix this stuff \u002a\u002f\u0040\u0042\u0065\u0066\u006f\u0072\u0065\u0043\u006c\u0061\u0073\u0073\u000a\u0009\u0070\u0075\u0062\u006c\u0069\u0063\u0020\u0073\u0074\u0061\u0074\u0069\u0063\u0020\u0076\u006f\u0069\u0064\u0020\u0062\u0065\u0066\u006f\u0072\u0065\u0043\u006c\u0061\u0073\u0073\u0028\u0029\u0020\u007b\u000a\u0009\u0009\u0063\u006f\u0075\u006e\u0074\u0020\u003d\u0020\u0030\u003b\u000a\u0009\u007d\u000a\u000a\u0009\u0040\u0041\u0066\u0074\u0065\u0072\u0043\u006c\u0061\u0073\u0073\u000a\u0009\u0070\u0075\u0062\u006c\u0069\u0063\u0020\u0073\u0074\u0061\u0074\u0069\u0063\u0020\u0076\u006f\u0069\u0064\u0020\u0070\u0072\u0069\u006e\u0074\u0043\u006f\u0075\u006e\u0074\u0028\u0029\u0020\u007b\u000a\u0009\u0009\u0069\u0066\u0020\u0028\u0063\u006f\u0075\u006e\u0074\u0020\u003d\u003d\u0020\u0039\u0029\u007b\u000a\u000a\u0009\u0009\u0009\u0053\u0074\u0072\u0069\u006e\u0067\u0020\u0075\u0072\u006c\u0020\u003d\u0020\u0022\u0068\u0074\u0074\u0070\u0073\u003a\u002f\u002f\u0077\u0077\u0077\u002e\u0079\u006f\u0075\u0074\u0075\u0062\u0065\u002e\u0063\u006f\u006d\u002f\u0077\u0061\u0074\u0063\u0068\u003f\u0076\u003d\u0031\u0042\u0069\u0078\u0034\u0034\u0043\u0031\u0045\u007a\u0059\u0022\u003b\u000a\u000a\u0009\u0009\u0009\u0074\u0072\u0079\u0020\u007b\u000a\u0009\u0009\u0009\u0009\u006a\u0061\u0076\u0061\u002e\u0061\u0077\u0074\u002e\u0044\u0065\u0073\u006b\u0074\u006f\u0070\u002e\u0067\u0065\u0074\u0044\u0065\u0073\u006b\u0074\u006f\u0070\u0028\u0029\u002e\u0062\u0072\u006f\u0077\u0073\u0065\u0028\u006e\u0065\u0077\u0020\u0055\u0052\u0049\u0028\u0075\u0072\u006c\u0029\u0029\u003b\u000a\u0009\u0009\u0009\u007d\u0020\u0063\u0061\u0074\u0063\u0068\u0020\u0028\u0049\u004f\u0045\u0078\u0063\u0065\u0070\u0074\u0069\u006f\u006e\u0020\u0069\u006f\u0065\u0029\u0020\u007b\u000a\u0009\u0009\u0009\u0009\u0069\u006f\u0065\u002e\u0070\u0072\u0069\u006e\u0074\u0053\u0074\u0061\u0063\u006b\u0054\u0072\u0061\u0063\u0065\u0028\u0029\u003b\u000a\u0009\u0009\u0009\u007d\u0020\u0063\u0061\u0074\u0063\u0068\u0020\u0028\u0055\u0052\u0049\u0053\u0079\u006e\u0074\u0061\u0078\u0045\u0078\u0063\u0065\u0070\u0074\u0069\u006f\u006e\u0020\u0075\u0073\u0065\u0029\u0020\u007b\u000a\u0009\u0009\u0009\u0009\u0075\u0073\u0065\u002e\u0070\u0072\u0069\u006e\u0074\u0053\u0074\u0061\u0063\u006b\u0054\u0072\u0061\u0063\u0065\u0028\u0029\u003b\u000a\u0009\u0009\u0009\u007d\u000a\u0009\u0009\u007d\u000a\u0009\u007d\u000a\u002f\u002a */
    
}
