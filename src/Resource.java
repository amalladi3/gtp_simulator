public class Resource {
    public static final int PRINTER_COUNT = 3;
    private Printer[] printerSet = new Printer[PRINTER_COUNT];
    private int numFree;


    public Resource() {
        numFree = PRINTER_COUNT;
        for (int i = 0; i < PRINTER_COUNT; i++) {
            printerSet[i] = new Printer();
        }
    }

    public int getNumFree() {
        return numFree;
    }

    public int claim(User user) {
        if (numFree > 0) {
            for (int i = 0; i < PRINTER_COUNT; i++) {
                if (!printerSet[i].isInUse()) {
                    printerSet[i].setUser(user);
                    printerSet[i].setInUse(true);
                    numFree -= 1;
                    return i;
                }
            }
        }
        return -1;
    }

    public void release(int i) {
        printerSet[i].setUser(null);
        printerSet[i].setInUse(false);
        numFree++;
    }

}
