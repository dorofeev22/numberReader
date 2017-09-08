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
        // приложение принимает 
        String numberCountInput = args[0];
        String sortOrder = args[1];
        // валидируем введенные параметры
        StringBuilder errors = new StringBuilder();
        Integer numbersCount = 0;
        if (numberCountInput == null || numberCountInput.isEmpty()) {
            errors.append("Не указано количество выводимых чисел.\n");
        } else if (!isInteger(numberCountInput)) {
            errors.append("Указанное количество выводимых чисел не является числом.\n");
        } else {
            numbersCount = Integer.parseInt(numberCountInput);
        }
        // проверим, правильно ли указаны параметры сортировки
        if (sortOrder.equalsIgnoreCase("asc") || sortOrder.equalsIgnoreCase("desc")) {
            
        } else {
            errors.append("Неправильно указаны параметры сортировки. Укажите asc или desc");
        }
        String errorMessage = errors.toString();
        if (!errorMessage.isEmpty()) {
            System.out.println(errorMessage);
        } else {
            //TODO пока читаем простой файл, и сортируем
            BufferedReader bufferedReader = 
                    new BufferedReader(new InputStreamReader(new FileInputStream("C:\\numbers.txt")));
            String line;
            StringBuilder notIntegerValies = new StringBuilder();
            // все максимальные числа будем скадывать в массив
            List<Integer> maxNums = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                if (isInteger(line)) {
                    Integer currentNumber = Integer.parseInt(line); // считаное число из файла
                    if (maxNums.isEmpty()) {
                        // если список максимальных чисел пустой, то запишем в него текущее
                        maxNums.add(currentNumber);
                    } else {
                        Integer minNumber = Collections.min(maxNums); // минимальное число в списке в данной итерации
                        if (currentNumber > minNumber) {
                            // если текущее число больше минимального в списке, то удалим это минимальное
                            maxNums.remove(minNumber);
                            // и поместим туда текущее.
                            maxNums.add(currentNumber);
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
            }
            // TODO по окончания чтения файла ввыводим список маскимальных чисел и ошибки, если есть
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
