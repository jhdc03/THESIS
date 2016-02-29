package thesis;

public class Medium {

    private boolean used;
    public int packetDrop=0;
    public int total=0;
    
    public void setUsed(boolean used) {
        this.used = used;
    }

    public boolean isUsed() {
        return used;
    }

    public boolean drop() {
        float drop = (float) (Math.random() * 100);
        total++;
        if (drop < Defaults.WIRELESS_MEDIUM_RELIABILITY) // 0-79
            return false;
        else
        {packetDrop++;
            return true;}
        
    }

    public int getpacketDrop() {
        return packetDrop/total;
    }
    
    

}
