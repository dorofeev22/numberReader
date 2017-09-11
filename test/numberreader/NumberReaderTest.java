package numberreader;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 * Тесты методов приложения.
 */
public class NumberReaderTest {

    @Test
    public void isIntegerTest() {
        boolean result = NumberReader.isInteger("1");
        Assert.assertEquals(true, result);

        result = NumberReader.isInteger("a");
        Assert.assertEquals(false, result);

    }
    
    @Test()
    public void analiseFileTest() throws IOException {
        List<Integer> maxNums = Arrays.asList(2138445889, 2137067883, 2136027720, 2141361948, 2145498106);
        ResultInfo resultInfo = NumberReader.analiseFile(5, "test/testNumbers.txt");
        
        Assert.assertEquals(resultInfo.getMaxNums(), maxNums);
    }
    
}
