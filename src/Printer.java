public class Printer {
    private boolean inUse;
    private User user;
    private int endTime;
    public Printer() {
        inUse = false;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse){
        this.inUse = inUse;
    }

}
