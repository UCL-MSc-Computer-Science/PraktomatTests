import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Peter Bryan on 2/2/17.
 */

class LinearLUTM2FTest9000 {

    LinearLUTM2F<Integer> myLut;

    @Before
    public void setup() {
        myLut = new LinearLUTM2F<Integer>();
    }

    @Test
    public void insertIntoEmptyLUT() {
        assertEquals("", myLut.toString());
        //myLut.insert("Value " + 0, 0);
    }

    @Test
    public void insetToNonEmptyLUT() {

    }

    @Test
    public void validRemovalFromLUT() {

    }

    @Test
    public void invalidRemovalFromLUT() {

    }

    @Test
    public void validUpdateToLUT() {

    }

    @Test
    public void invalidUpdateToLUT() {

    }
}