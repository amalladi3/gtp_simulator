public class User {
    private boolean shortJob;
    private boolean colorJob;

    private boolean waiting;
    private int entryTime;
    private int exitTime;

    private int printerID;

    public User(boolean shortJob, boolean colorJob, int entryTime) {
        this.shortJob = shortJob;
        this.colorJob = colorJob;
        this.entryTime = entryTime;
    }

    public boolean isShortJob() {
        return shortJob;
    }

    public boolean isColorJob() {
        return colorJob;
    }

    public boolean isWaiting() {
        return waiting;
    }

    public void setWaiting(boolean waiting) {
        this.waiting = waiting;
    }

    public int getExitTime() {
        return exitTime;
    }

    public void setExitTime(int exitTime) {
        this.exitTime = exitTime;
    }

    public int getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(int entryTime) {
        this.entryTime = entryTime;
    }

    public int getPrinterID(){
        return printerID;
    }
    public void setPrinterID(int id) {
        printerID = id;
    }

}
