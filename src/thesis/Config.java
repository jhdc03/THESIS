
package thesis;


public class Config{
    
    //Global
    private long mediumBandwidth;
    private String L2Protocol;
    private String L3Protocol;
    private long simulationTime;
    private float energyConsumptionTransmitState;
    private float energyConsumptionReceiveState;
    private float energyConsumptionIdleState;
    private float WirelessMediumReliability;
    
    public void Init(){
    
    
    
    }
    
    public void AddHardcodedNodes(){
        
    
    
    }
    /**
     * @return the mediumBandwidth
     */
    public long getMediumBandwidth() {
        return mediumBandwidth;
    }

    /**
     * @param mediumBandwidth the mediumBandwidth to set
     */
    public void setMediumBandwidth(long mediumBandwidth) {
        this.mediumBandwidth = mediumBandwidth;
    }

    /**
     * @return the L2Protocol
     */
    public String getL2Protocol() {
        return L2Protocol;
    }

    /**
     * @param L2Protocol the L2Protocol to set
     */
    public void setL2Protocol(String L2Protocol) {
        this.L2Protocol = L2Protocol;
    }

    /**
     * @return the L3Protocol
     */
    public String getL3Protocol() {
        return L3Protocol;
    }

    /**
     * @param L3Protocol the L3Protocol to set
     */
    public void setL3Protocol(String L3Protocol) {
        this.L3Protocol = L3Protocol;
    }

    /**
     * @return the simulationTime
     */
    public long getSimulationTime() {
        return simulationTime;
    }

    /**
     * @param simulationTime the simulationTime to set
     */
    public void setSimulationTime(long simulationTime) {
        this.simulationTime = simulationTime;
    }

    /**
     * @return the energyConsumptionTransmitState
     */
    public float getEnergyConsumptionTransmitState() {
        return energyConsumptionTransmitState;
    }

    /**
     * @param energyConsumptionTransmitState the energyConsumptionTransmitState to set
     */
    public void setEnergyConsumptionTransmitState(float energyConsumptionTransmitState) {
        this.energyConsumptionTransmitState = energyConsumptionTransmitState;
    }

    /**
     * @return the energyConsumptionReceiveState
     */
    public float getEnergyConsumptionReceiveState() {
        return energyConsumptionReceiveState;
    }

    /**
     * @param energyConsumptionReceiveState the energyConsumptionReceiveState to set
     */
    public void setEnergyConsumptionReceiveState(float energyConsumptionReceiveState) {
        this.energyConsumptionReceiveState = energyConsumptionReceiveState;
    }

    /**
     * @return the energyConsumptionIdleState
     */
    public float getEnergyConsumptionIdleState() {
        return energyConsumptionIdleState;
    }

    /**
     * @param energyConsumptionIdleState the energyConsumptionIdleState to set
     */
    public void setEnergyConsumptionIdleState(float energyConsumptionIdleState) {
        this.energyConsumptionIdleState = energyConsumptionIdleState;
    }

    /**
     * @return the WirelessMediumReliability
     */
    public float getWirelessMediumReliability() {
        return WirelessMediumReliability;
    }

    /**
     * @param WirelessMediumReliability the WirelessMediumReliability to set
     */
    public void setWirelessMediumReliability(float WirelessMediumReliability) {
        this.WirelessMediumReliability = WirelessMediumReliability;
    }


    /*
    public void addHardcodedNode () {
        //Node 1
        e.addNode("00:00:00:00:00:00", "node1", "sleep", 0, 0, 0, 1);
        //Node 2
        e.addNode("00:00:00:00:00:00", "node2", "awake", 10, 0, 10, 2);
        //Node 3
        e.addNode("00:00:00:00:00:00", "node3", "sleep", 0, 10, 100, 3);
        //Node 4
        e.addNode("00:00:00:00:00:00", "node4", "sleep", 10, 10, 100, 4);
    }
    */
}
