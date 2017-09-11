package numberreader;

import org.junit.Assert;
import org.junit.Test;

/**
 * Тесты методов справочника сортировки.
 */
public class ReferenceSortOrderTest {
    
    @Test
    public void createSortOrderTest() {
        ReferenceSortOrder sortOrder = ReferenceSortOrder.createSortOrder("asc");
        Assert.assertEquals(ReferenceSortOrder.asc, sortOrder);

        sortOrder = ReferenceSortOrder.createSortOrder("desc");
        Assert.assertEquals(ReferenceSortOrder.desc, sortOrder);
        
        sortOrder = ReferenceSortOrder.createSortOrder("abcd");
        Assert.assertEquals(null, sortOrder);
        
    }
    
}
