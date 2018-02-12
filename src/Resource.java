public class Resource {
    public static final int PRINTER_COUNT = 3;
    private Printer[] printerSet = new Printer[PRINTER_COUNT];
    private int numFree;


    public Resource() {
        numFree = PRINTER_COUNT;
    }


    private class Printer {
        private boolean inUse;
        public Printer() {
            inUse = false;
        }


    }
}
