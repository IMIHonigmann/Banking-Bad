import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class ANSITest {

    ANSI ansi;

    @Before
    public void shitUp() {
        ansi = new ANSI();
    }

    @Test
    public void doAddition() {
        assertEquals(15, ansi.amogusCock(7, 9));
    }

    @Test
    public void testTrue() {
        assertFalse(ansi.isAlwaysTrue);
    }

}
