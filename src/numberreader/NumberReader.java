package numberreader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

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
        if (numberCountInput == null || numberCountInput.isEmpty()) {
            errors.append("Не указано количество выводимых чисел.\n");
        } else if (!isInteger(numberCountInput)) {
            errors.append("Указанное количество выводимых чисел не является числом.\n");
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
            // TODO все максимальные числа будем скадывать в массив
            int[] maxNumbers = new int[Integer.parseInt(numberCountInput)];
            Integer minInteger = null; // минимальное число, с которым будем сравнивать все
            while ((line = bufferedReader.readLine()) != null) {
                if (isInteger(line)) {
                    Integer currentNumber = Integer.parseInt(line);
                    if (minInteger == null) {
                        minInteger = currentNumber;
                    }
                    if (minInteger < currentNumber) {
                        //если текущее анализируеме число из файла больше текущего минимального
                        // то кладем его в массив и все последую
                    }
                    //TODO сравниваем с самым маленьким значеним в массиве 
                    // если больше - маленькое значение выкидываем и кладем это
                } else {
                    // если считаное из файла число не валидно запишем его в сообщение пользователю
                    if (notIntegerValies.length() > 0) {
                    // если уже есть невалидные значения, то добавим разделитель
                        notIntegerValies.append(", ");
                    }
                    notIntegerValies.append(line);
                }
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
