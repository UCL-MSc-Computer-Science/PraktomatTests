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
    public void invalidRemovalFromLUT() {
        myLut = new LinearLUTM2F<Integer>();

        try {
            assertEquals("Value 1:1, ", myLut.toString());
            myLut.remove("Value 10");
            fail("Invalid removal did not fail");
        }
        catch (Exception e){
            fail("Failed with error other than LUTKeyException");
        }
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
            addToLUTMany(0,1);
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
            myLut.retrieve("Value 2");
            assertEquals("Value 2:2, Value 5:5, Value 4:4, Value 3:3, Value 1:1, ", myLut.toString());
        }
        catch (Exception e){
            fail("Failed a valid retrieval");
        }
    }

    @Test (expected = LUTKeyException.class)
    public void invalidRetrievalFromLUT() {
        myLut = new LinearLUTM2F<Integer>();

        try {
            addToLUTMany(2,5,4,3,1);
            myLut.retrieve("Value 10");
        }
        catch (Exception e){
            fail("Failed with error other than LUTKeyException");
        }
    }

    @Test
    public void validUpdateToLUT(){
        myLut = new LinearLUTM2F<Integer>();

        try{
            addToLUTMany(2,5,4,3,2,1);
            myLut.update("Value 4", 20);
            assertEquals("Value 4:20, Value 2:2, Value 5:5, Value 3:3, Value 1:1, ", myLut.toString());
        }
        catch (Exception e){
            fail("Failed valid update");
        }
    }

    @Test (expected = LUTKeyException.class)
    public void invalidUpdateToLUT(){
        myLut = new LinearLUTM2F<Integer>();

        try {
            addToLUTMany(4, 2, 5, 3, 1);
            myLut.update("Value 4", 20);
        }
        catch(Exception e){
            fail("Check valid update to LUT");
        }

        try{
            myLut.update("Value 10", 20);
        }
        catch (Exception e){
            fail("Failed with error other than LUTKeyException");
        }
    }
}