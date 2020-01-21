import obj_lib.AquaProcess;
import obj_lib.Aquarium;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        AquaProcess process;

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
        Queue<AquaProcess> processes = new LinkedList<>();

        // Получаем список файлов "программ"
        File programFolder = new File("programFolder");
        File[] programs = programFolder.listFiles();

        assert programs != null;
        // для каждого файла создаем процесс и добавляем его в конец очереди
        for (File program: programs) {
            process = new AquaProcess();
            process.readProgram(program, aquarium);
            processes.add(process);
        }

        // бесконечный цикл работы
        // стартуем с первой программы в очередии и 1 тика
        int tick = 1;
        AquaProcess currentProc;
        byte priority;
        String currentCode;
        while (tick <= 1000) {
            currentCode = "";
            // берем программу из очереди
            currentProc = processes.peek();

            // Стереть все что на экране
            System.out.println("\033[2J");
            // Какой тик
            System.out.println("Tick:" + tick);
            if (currentProc != null && currentProc.getCommandCounter() < currentProc.getCode().size()) {
                // Какая программа
                System.out.println("Name: " + currentProc.getName() + "(" + currentProc.getX() + ", " + currentProc.getY() + ")");
                // узнаем ее приоритет
                priority = currentProc.getPriority();
                // Какой у программы приоритет
                System.out.println("Priority: " + priority);
                // выполняем инструкции согласно приоритета
                int size = currentProc.getCode().size();
                for (int i = 0; i < size && i < priority; i++) {
                    byte commandIndex = currentProc.getCommandCounter();
                    if (commandIndex < size) {
                        currentCode = currentCode + currentProc.getCode().get(commandIndex) + " ";
                        currentProc.runCode(aquarium);
                    } else {
                        processes.remove(currentProc);
                        currentProc = null;
                        break;
                    }
                }
                // Какое действие
                System.out.println("CODE: " + currentCode);
                System.out.println("Coordinates (" + currentProc.getX() + ", " + currentProc.getY() + ")");
                if (currentProc != null) {
                    // этот процесс уже отработал, нужно его убрать из головы очереди
                    processes.remove(currentProc);
                    //задаем переход к другому процессу в конце тика
                    processes.add(currentProc);
                }
            }
            // Выводим поле
            aquarium.showField();

            tick++;

            // замораживаем программу на 2 секунды для наглядности
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
