package createlargefile;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Утилита, создающаяя файл с числами.
 */
public class CreateLargeFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        String numberCountInput = args[0];
        Integer numberCount;
        try {
            numberCount = Integer.parseInt(numberCountInput);
            Writer writer = null;
            try {
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("numbers.txt"), "utf-8"));
                // создадим файл с 1 млрд чисел (для суровости)
                for (int i = 0; i < numberCount; i++) {
                    int randomNum = ThreadLocalRandom.current().nextInt();
                    writer.write(String.valueOf(randomNum));
                    writer.write("\n");
                }
            } catch (IOException ex) {
                System.out.println("Не удалось записать файл на диск" + ex.getMessage());
            } finally {
                try {
                    writer.close();
                } catch (Exception ex) {
                    System.out.println("Не удалось записать файл на диск - " + ex.getMessage());
                }
            }
        } catch (IllegalArgumentException ex) {
            System.out.println("Введенное значение не является числом - " + ex.getMessage());
        }
    }
    
}
