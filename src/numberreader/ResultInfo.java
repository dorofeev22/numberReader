package numberreader;

import java.util.List;

/**
 * Контейнер для резальтатов чтения файла.
 */
public class ResultInfo {

    public ResultInfo(List<Integer> maxNums, StringBuilder notIntegerValues, int allNumbers) {
        this.maxNums = maxNums;
        this.notIntegerValues = notIntegerValues;
        this.allNumbers = allNumbers;
    }
    
    private List<Integer> maxNums;
    private StringBuilder notIntegerValues;
    private int allNumbers;

    /**
     * @return the maxNums
     */
    public List<Integer> getMaxNums() {
        return maxNums;
    }

    /**
     * @param maxNums the maxNums to set
     */
    public void setMaxNums(List<Integer> maxNums) {
        this.maxNums = maxNums;
    }

    /**
     * @return the notIntegerValues
     */
    public StringBuilder getNotIntegerValues() {
        return notIntegerValues;
    }

    /**
     * @param notIntegerValues the notIntegerValues to set
     */
    public void setNotIntegerValues(StringBuilder notIntegerValues) {
        this.notIntegerValues = notIntegerValues;
    }

    /**
     * @return the allNumbers
     */
    public int getAllNumbers() {
        return allNumbers;
    }

    /**
     * @param allNumbers the allNumbers to set
     */
    public void setAllNumbers(int allNumbers) {
        this.allNumbers = allNumbers;
    }
    
}
