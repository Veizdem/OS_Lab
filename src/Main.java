import obj_lib.Aquarium;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // имя для файла с игровым полем
        String filename = "aquarium.txt";
        File file = new File(filename);

        // проверяем наличие файла
        if (!file.exists()) {
            // если не существует - пытаемся сгенерировать и записать
            try {
                FileWriter fileWriter = new FileWriter(filename);
                Random random = new Random();

                // записываем на лету случайные числа в пределах индексов статических объектов
                for (int i = 0; i < 20; i++) {
                    for (int j = 0; j < 80; j++) {
                        fileWriter.write(String.valueOf(random.nextInt(4)));
                    }
                    if (i < 19) {
                        fileWriter.write("\n");
                    }
                }

                // закрываем поток записи
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // массив для инициализации игрового поля
        byte[][] arr = new byte[20][80];

        // пытаемся прочитать файл, ведь он гарантированно существует
        try {
            FileReader fileReader = new FileReader(filename);
            Scanner scanner = new Scanner(fileReader);

            // читаем посимвольно в массив
            for (int i = 0; i < 20; i++) {
                char[] sym = scanner.nextLine().toCharArray();
                for (int j = 0; j < sym.length; j++) {
                    arr[i][j] = Byte.parseByte(String.valueOf(sym[j]));
                }
            }

            // закрываем поток чтения
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // пытаемся получить экземпляр игрового поля, если его нет оно создается на основе прочтенного файла
        // игровое поле может существовать только в елдиничном экземпляре, потому применен паттерн Singleton
        Aquarium aquarium = Aquarium.getInstance(arr);

        // Выводим на экран данные игрового поля
        aquarium.showField();
    }
}
