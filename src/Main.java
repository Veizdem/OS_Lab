import obj_lib.Aquarium;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Лабораторная работа по операционным системам");

        String filename = "aquarium.txt";
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("Генерация аквариума...");

            try {
                FileWriter fileWriter = new FileWriter(filename);
                Random random = new Random();

                for (int i = 0; i < 20; i++) {
                    for (int j = 0; j < 80; j++) {
                        fileWriter.write(String.valueOf(random.nextInt(4)));
                    }
                    if (i < 19) {
                        fileWriter.write("\n");
                    }
                }
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Читаем данные аквариума...");
        int[][] arr = new int[20][80];
        try {
            FileReader fileReader = new FileReader(filename);
            Scanner scanner = new Scanner(fileReader);
            for (int i = 0; i < 20; i++) {
                char[] sym = scanner.nextLine().toCharArray();
                for (int j = 0; j < sym.length; j++) {
                    arr[i][j] = Integer.parseInt(String.valueOf(sym[j]));
                }
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Aquarium aquarium = Aquarium.getInstance(arr);

        System.out.println("Аквариум выглядит так:");
        aquarium.showField();
    }
}
