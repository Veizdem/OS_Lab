package obj_lib;

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
}
