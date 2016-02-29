package thesis;

public class Medium {

    private boolean used;

    public void setUsed(boolean used) {
        this.used = used;
    }

    public boolean isUsed() {
        return used;
    }

    public boolean drop() {
        float drop = (float) (Math.random() * 100);
        if (drop < Defaults.WIRELESS_MEDIUM_RELIABILITY) // 0-79
            return false;
        else
            return true;
    }

}
