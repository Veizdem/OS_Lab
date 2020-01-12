package obj_lib;

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
        for (int[] line : this.field){
            for (int cell: line) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}
