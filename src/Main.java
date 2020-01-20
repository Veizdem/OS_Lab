import obj_lib.AquaProcess;
import obj_lib.Aquarium;

import java.io.*;
import java.util.Arrays;
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

        // читаем наши "программы" в процессы и инициируем их выполнение
        //создаем нашу очередь
        AquaProcess[] processes = new AquaProcess[30];

        // бесконечный цикл работы
        // стартуем с первой программы в очередии и 1 тика
        int start = 0;
        int tick = 1;
        AquaProcess currentProc;
        byte priority;
        String currentCode = null;
        while (tick <= 1000) {
            // берем программу из очереди
            currentProc = processes[start];
            // узнаем ее приоритет
            priority = currentProc.getPriority();
            // выполняем инструкции согласно приоритета
            for (int i = 0; i < currentProc.getCode().length; i++) {
                currentCode = currentProc.getCode()[i];
                currentProc.runCode();
            }
            // Стереть все что на экране
            System.out.println("\033[2J");
            // Какой тик
            System.out.println("Tick:" + tick);
            // Какая программа
            System.out.println("Name: " + Arrays.toString(currentProc.getName()));
            // Какой у программы приоритет
            System.out.println("Priority: " + priority);
            // Какое действие
            System.out.println("CODE: " + currentCode);
            // Выводим поле
            aquarium.showField();

            tick++;
            //задаем переход к другому процессу в конце тика
            if (start < processes.length) {
                start++;
            } else {
                start = 0;
            }

            // замораживаем программу на 2 секунды для наглядности
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
