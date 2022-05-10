import org.junit.Before;
import org.junit.Test;

public class ClockTest {
    public Clock c;
    @Before
    public void setUp() throws Exception {
        c = new Clock(500.00F);
    }

    @Test
    public void peekElapsedCycleTest() {
        c.peekElapsedCycle();
    }
}
