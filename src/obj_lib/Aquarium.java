package obj_lib;

import java.io.IOException;

public final class Aquarium {
    private static volatile Aquarium instance;

    private int[][] field;

    private Aquarium(int[][] field) {
        this.field = field;
    }

    public static Aquarium getInstance(int[][] field) {
        Aquarium result = instance;
        if (result != null) {
            return result;
        }

        synchronized (Aquarium.class) {
            if (instance == null) {
                instance = new Aquarium(field);
            }
            return instance;
        }
    }

    public void showField() {
        // Стереть все что на экране
        System.out.println("\033[2J");

        // тут будут служебные строки
        System.out.println("Несколько");
        System.out.println("строк");
        System.out.println("для");
        System.out.println("статистической");
        System.out.println("информации");

        // форматируем и выводим поле
        StringBuilder text = new StringBuilder();

        for (int[] line : this.field){
            for (int cell: line) {
                String fmt =  switch (cell) {
                    case 0 -> "\033[34m";
                    case 1 -> "\033[33m";
                    case 2 -> "\033[32m";
                    case 3 -> "\033[30;1m";
                    default -> "\033[0m";
                };
                text.append(fmt).append(cell).append(" \033[0m");
            }
            text.append("\n");
        }
        System.out.println(text);
    }
}
