package numberreader;

import org.junit.Assert;
import org.junit.Test;

/**
 * Тесты методов приложения.
 */
public class NumberReaderTest {

    @Test
    public void isIntegerTest() {
        Boolean result = NumberReader.isInteger("1");
        Assert.assertEquals(true, result);
    }
    
}
