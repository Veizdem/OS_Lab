package obj_lib;

import java.io.IOException;

public final class Aquarium {
    private static volatile Aquarium instance;

    private byte[][] field;

    private Aquarium(byte[][] field) {
        this.field = field;
    }

    public static Aquarium getInstance(byte[][] field) {
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

    public byte getFieldValue(int i, int j){
        synchronized (Aquarium.class) {
            return field[i][j];
        }
    }

    public void setFieldValue(int i, int j, byte val){
        synchronized (Aquarium.class) {
            this.field[i][j] = val;
        }
    }

    public byte[][] getField() {
        synchronized (Aquarium.class) {
            return field;
        }
    }

    public void setField(byte[][] field) {
        synchronized (Aquarium.class) {
            this.field = field;
        }
    }

    public void showField() {
        // форматируем и выводим поле
        StringBuilder text = new StringBuilder();

        for (byte[] line : this.field){
            for (byte cell: line) {
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
