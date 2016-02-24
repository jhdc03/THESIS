/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thesis;

/**
 *
 * @author harve
 */
public class Logs1 {
    
    private String packetid;
    private boolean indicator;
    private String source;
    private String Destination;
    private float senttime;
    private float receivetime;
    private boolean successindicator;
    private float averagedelay;
    private float averagepacketdroprate;
    private float averagethroughput;
    private float energyconsumption;

    /**
     * @return the packetid
     */
    public String getPacketid() {
        return packetid;
    }

    /**
     * @param packetid the packetid to set
     */
    public void setPacketid(String packetid) {
        this.packetid = packetid;
    }

    /**
     * @return the indicator
     */
    public boolean isIndicator() {
        return indicator;
    }

    /**
     * @param indicator the indicator to set
     */
    public void setIndicator(boolean indicator) {
        this.indicator = indicator;
    }

    /**
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return the Destination
     */
    public String getDestination() {
        return Destination;
    }

    /**
     * @param Destination the Destination to set
     */
    public void setDestination(String Destination) {
        this.Destination = Destination;
    }

    /**
     * @return the senttime
     */
    public float getSenttime() {
        return senttime;
    }

    /**
     * @param senttime the senttime to set
     */
    public void setSenttime(float senttime) {
        this.senttime = senttime;
    }

    /**
     * @return the receivetime
     */
    public float getReceivetime() {
        return receivetime;
    }

    /**
     * @param receivetime the receivetime to set
     */
    public void setReceivetime(float receivetime) {
        this.receivetime = receivetime;
    }

    /**
     * @return the successindicator
     */
    public boolean isSuccessindicator() {
        return successindicator;
    }

    /**
     * @param successindicator the successindicator to set
     */
    public void setSuccessindicator(boolean successindicator) {
        this.successindicator = successindicator;
    }

    /**
     * @return the averagedelay
     */
    public float getAveragedelay() {
        return averagedelay;
    }

    /**
     * @param averagedelay the averagedelay to set
     */
    public void setAveragedelay(float averagedelay) {
        this.averagedelay = averagedelay;
    }

    /**
     * @return the averagepacketdroprate
     */
    public float getAveragepacketdroprate() {
        return averagepacketdroprate;
    }

    /**
     * @param averagepacketdroprate the averagepacketdroprate to set
     */
    public void setAveragepacketdroprate(float averagepacketdroprate) {
        this.averagepacketdroprate = averagepacketdroprate;
    }

    /**
     * @return the averagethroughput
     */
    public float getAveragethroughput() {
        return averagethroughput;
    }

    /**
     * @param averagethroughput the averagethroughput to set
     */
    public void setAveragethroughput(float averagethroughput) {
        this.averagethroughput = averagethroughput;
    }

    /**
     * @return the energyconsumption
     */
    public float getEnergyconsumption() {
        return energyconsumption;
    }

    /**
     * @param energyconsumption the energyconsumption to set
     */
    public void setEnergyconsumption(float energyconsumption) {
        this.energyconsumption = energyconsumption;
    }
}
