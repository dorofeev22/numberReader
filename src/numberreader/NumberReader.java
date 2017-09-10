package numberreader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
            //TODO пока читаем простой файл, и сортируем
            BufferedReader bufferedReader = 
                    new BufferedReader(new InputStreamReader(new FileInputStream("C:\\_numberReader\\numbers.txt")));
            String line;
            StringBuilder notIntegerValies = new StringBuilder();
            // все максимальные числа будем скадывать в массив
            List<Integer> maxNums = new ArrayList<>();
            int allNumbers = 0;
            while ((line = bufferedReader.readLine()) != null) {
                if (isInteger(line)) {
                    Integer currentNumber = Integer.parseInt(line); // считаное число из файла
                    if (maxNums.size() <= numberCount) {
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
                    if (notIntegerValies.length() > 0) {
                    // если уже есть невалидные значения, то добавим разделитель
                        notIntegerValies.append(", ");
                    }
                    notIntegerValies.append(line);
                }
                allNumbers++;
            }
            // По окончания чтения файла ввыводим список маскимальных чисел и ошибки, если есть
            StringBuilder maxNumbersInfo = new StringBuilder();
            maxNumbersInfo.append(numberCount > allNumbers ? allNumbers : numberCount)
                    .append(" максимальных чисел в файле:\n");
            // сортируем список максимальных чисел
            Collections.sort(maxNums);
            if (sortOrder.equals(ReferenceSortOrder.desc)) {
                Collections.reverse(maxNums);
            }
            for (Integer maxNum : maxNums) {
                maxNumbersInfo.append(maxNum.toString()).append(" ");
            }
            maxNumbersInfo.append("\nВсего записей в файле - ").append(allNumbers);
            System.out.println(maxNumbersInfo.toString());
            if (notIntegerValies.length() > 0) {
                System.out.println(
                        "Следующие числа в файле не являются числами:\n" + notIntegerValies.toString());
            }
        }
    }
    
    /**
     * Проверка строкового выражения на число.
     * @param value строквое выражение
     * @return true если число
     */
    private static boolean isInteger(String value) {
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
