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
public class Medium extends Entity{
    
    private float bandwidth;
    private float reliability;
    private boolean VariableState;

    /**
     * @return the bandwidth
     */
    
    public void gettest() {
        System.out.println("This is a test");
    }
    
    public float getBandwidth() {
        return bandwidth;
    }

    /**
     * @param bandwidth the bandwidth to set
     */
    public void setBandwidth(float bandwidth) {
        this.bandwidth = bandwidth;
    }

    /**
     * @return the reliability
     */
    public float getReliability() {
        return reliability;
    }

    /**
     * @param reliability the reliability to set
     */
    public void setReliability(float reliability) {
        this.reliability = reliability;
    }

    /**
     * @return the VariableState
     */
    public boolean isVariableState() {
        return VariableState;
    }

    /**
     * @param VariableState the VariableState to set
     */
    public void setVariableState(boolean VariableState) {
        this.VariableState = VariableState;
    }
}
