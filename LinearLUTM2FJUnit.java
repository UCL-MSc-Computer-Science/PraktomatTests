import org.junit.Test;
import static org.junit.Assert.*;


public class LinearLUTM2FTest4 {

    LinearLUTM2F<Integer> myLut;

    // function setup
    public void addToLUTMany(int... values) throws LUTKeyException{
        for (int i : values){
            myLut.insert("Value " + i, i);
        }
    }

    // If you don't pass at least one test, the config is wrong
    @Test
    public void shouldAlwaysPass() {
        assertEquals("","");
    }

    @Test (expected = LUTKeyException.class)
    public void invalidRemovalFromLUT() throws LUTKeyException{
        myLut = new LinearLUTM2F<Integer>();
        addToLUTMany(1);
        assertEquals("Value 1:1, ", myLut.toString());
        myLut.remove("Value 10");
        fail("Invalid removal did not fail");
    }

    @Test
    public void insertToEmptyLUT(){
        myLut = new LinearLUTM2F<Integer>();

        try{
            addToLUTMany(0);
            assertEquals("Value 0:0, ", myLut.toString());
        }
        catch (Exception e){
            fail("Failed to insert into empty LUT");
        }
    }

    @Test
    public void insertToNonEmptyLUT(){
        myLut = new LinearLUTM2F<Integer>();

        try{
            addToLUTMany(0,1);
            assertEquals("Value 1:1, Value 0:0, ", myLut.toString());
        }
        catch (Exception e){
            fail("Failed to insert into non-empty LUT");
        }
    }

    @Test
    public void validRemovalFromLUT(){
        myLut = new LinearLUTM2F<Integer>();

        try{
            addToLUTMany(1,0);
            
            myLut.remove("Value 0");
            assertEquals("Value 1:1, ", myLut.toString());
        }
        catch (Exception e){
            fail("Failed a valid removal from LUT");
        }
    }

    @Test
    public void validRetrievalFromLUT(){
        myLut = new LinearLUTM2F<Integer>();

        try{
            addToLUTMany(1,2,3,4,5);

            assertEquals(myLut.retrieve("Value 2"), new Integer(2));
            assertEquals("Value 2:2, Value 5:5, Value 4:4, Value 3:3, Value 1:1, ", myLut.toString());
        }
        catch (Exception e){
            fail("Failed a valid retrieval");
        }
    }

    @Test (expected = LUTKeyException.class)
    public void invalidRetrievalFromLUT() throws LUTKeyException{
        myLut = new LinearLUTM2F<Integer>();
        addToLUTMany(1,3,4,5,2);
        
        myLut.retrieve("Value 10");
        fail("Invalid retrieval did not fail");
    }

    @Test
    public void validUpdateToLUT(){
        myLut = new LinearLUTM2F<Integer>();

        try{
            addToLUTMany(1,3,4,5,2);

            myLut.update("Value 4", 20);
            assertEquals("Value 4:20, Value 2:2, Value 5:5, Value 3:3, Value 1:1, ", myLut.toString());
        }
        catch (Exception e){
            fail("Failed valid update");
        }
    }

    @Test (expected = LUTKeyException.class)
    public void invalidUpdateToLUT() throws LUTKeyException{
        myLut = new LinearLUTM2F<Integer>();

        try {
            addToLUTMany(1, 3, 5, 2, 4);
            myLut.update("Value 4", 20);
        }
        catch(Exception e){
            fail("Check valid update to LUT");
        }

        myLut.update("Value 10", 20);
        fail("Invalid update did not fail");
    }
}
