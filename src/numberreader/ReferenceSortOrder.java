package numberreader;

/**
 * Справочник типов сортировки.
 */
public enum ReferenceSortOrder {
    
    asc,
    desc;
    
    /**
     * Преобразование строкового значения порядка сортировки в справочное.
     * @param inputOrder введенное значение.
     * @return null если не соответствует условленным.
     */
    public static ReferenceSortOrder createSortOrder(String inputOrder) {
        ReferenceSortOrder sortOrder;
        try {
            sortOrder = ReferenceSortOrder.valueOf(inputOrder);
        } catch (IllegalArgumentException ex) {
            sortOrder = null;
        }
        return sortOrder;
    }

}
