package thesis;

public class Medium {

    private boolean used;
    public int packetDrop=0;
    public int total=0;
    public Message message;
    public boolean ACK;
    
    public void setStatACK(boolean ACK){
        this.ACK=ACK;
       
    }
    
    
    public boolean getStatACK(){
        return ACK;
    }
    
    public void setMessage(Message message){
       this.message=message;
       
    }
    
    public Message getMessage(){
        return message;
    }
    
    public int delay(){
    int size= 100;
    int distance=10;
    int bandwidth= 10;
    int SPL=299792458;
    int pDelay=(size/bandwidth)+(distance/SPL);
    return pDelay;
    }
    
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
