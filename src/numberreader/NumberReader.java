package numberreader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Приложение сортирующее числа из файла
 */
public class NumberReader {

    /**
     * @param args параметры: количество показываемых чисел на экране и порядок сортировки asc/desc
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String numberCountInput = args[0];
        String sortOrderInput = args[1];
        String fileName = args[2];
        // валидируем введенные параметры
        StringBuilder errors = new StringBuilder();
        int numberCount = 0;
        if (numberCountInput == null || numberCountInput.isEmpty()) {
            errors.append("Не указано количество выводимых чисел.\n");
        } else if (!isInteger(numberCountInput)) {
            errors.append("Указанное количество выводимых чисел не является числом.\n");
        } else {
            numberCount = Integer.parseInt(numberCountInput);
        }
        // Обработаем тип сортировки и проверим его
        ReferenceSortOrder sortOrder = ReferenceSortOrder.createSortOrder(sortOrderInput);
        if (sortOrder == null) {
            errors.append("Неправильно указаны параметры сортировки. Укажите asc или desc");
        }
        String errorMessage = errors.toString();
        if (!errorMessage.isEmpty()) {
            System.out.println(errorMessage);
        } else {
            // Читаем файл и отбираем максимальные числа
            ResultInfo resultInfo = analiseFile(numberCount, fileName);
            // По окончании чтения файла ввыводим список маскимальных чисел и ошибки, если есть
            StringBuilder maxNumbersInfo = new StringBuilder();
            int allNumbers = resultInfo.getAllNumbers();
            maxNumbersInfo.append(numberCount > allNumbers ? allNumbers : numberCount)
                    .append(" максимальных чисел в файле:\n");
            // сортируем список максимальных чисел
            List<Integer> maxNums = resultInfo.getMaxNums();
            Collections.sort(maxNums);
            if (sortOrder.equals(ReferenceSortOrder.desc)) {
                Collections.reverse(maxNums);
            }
            for (Integer maxNum : maxNums) {
                maxNumbersInfo.append(maxNum.toString()).append(" ");
            }
            maxNumbersInfo.append("\nВсего записей в файле - ").append(allNumbers);
            System.out.println(maxNumbersInfo.toString());
            StringBuilder notIntegerValues = resultInfo.getNotIntegerValues();
            if (notIntegerValues.length() > 0) {
                System.out.println(
                        "Следующие числа в файле не являются числами:\n" + notIntegerValues.toString());
            }
        }
    }
    
    /**
     * Анализ файла и построение массива максимальных чисел.
     * @param maxNums массив для максимальных чисел.
     * @param numberCount необходимое количество максимальных чисел.
     * @param notIntegerValues переменная для ошибочных, не числовых значений в файле.
     * @param allNumbers счетчик общего количества записей в файле
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private static ResultInfo analiseFile(
            int numberCount, String fileName) throws FileNotFoundException, IOException {
        StringBuilder notIntegerValues = new StringBuilder(); // переменная для не числовых значений в файле
        List<Integer> maxNums = new ArrayList<>(); // все максимальные числа будем скадывать в массив
        int allNumbers = 0; // счетчик всех записей в файле
        FileInputStream fileInputStream = null;
        Scanner scanner = null;
        try {
            fileInputStream = new FileInputStream(fileName);
            scanner = new Scanner(fileInputStream);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (isInteger(line)) {
                    Integer currentNumber = Integer.parseInt(line); // считаное число из файла
                    if (maxNums.size() < numberCount) {
                        // если список максимальных не больше требуемого размера, то запишем в него текущее
                        maxNums.add(currentNumber);
                    } else {
                        Integer minNumber = Collections.min(maxNums); // минимальное число в списке в данной итерации
                        int minNumberIndex = maxNums.indexOf(minNumber); // его индекс
                        if (currentNumber > minNumber) {
                            // если текущее число больше минимального в списке, то заменим им минимальное
                            maxNums.set(minNumberIndex, currentNumber);
                        }
                    }
                } else {
                    // если считаное из файла число не валидно запишем его в сообщение пользователю
                    if (notIntegerValues.length() > 0) {
                    // если уже есть невалидные значения, то добавим разделитель
                        notIntegerValues.append(", ");
                    }
                    notIntegerValues.append(line);
                }
                allNumbers++;
            }
            if (scanner.ioException() != null) {
                throw scanner.ioException();
            }
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (scanner != null) {
                scanner.close();
            }
        }
        return new ResultInfo(maxNums, notIntegerValues, allNumbers);
    }
    
    /**
     * Проверка строкового выражения на число.
     * @param value строковое выражение
     * @return true если число
     */
    public static boolean isInteger(String value) {
        boolean isInteger;
        try {
            Integer.parseInt(value);
            isInteger = true;
        } catch (NumberFormatException ex){
            isInteger = false;
        }
        return isInteger;
    }
    
}
