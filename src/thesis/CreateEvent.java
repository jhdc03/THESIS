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
public class CreateEvent extends  Event {
    CreateEvent(double time, int eventID) {
    }


    public void execute(Simulator simulator) {
        /*
        
        ApplicationLayer al = new ApplicationLayer();
        Medium m = new Medium();
        Node n = new Node();
        Entity entity = new Entity(); 
        //AODV aodv= new AODV();
        switch(eventID)
        {
        case 1: m.gettest();
                simulator.insert( new CreateEvent(7.0, 2, e));; break;
        case 2: e.PrintNodeList();break;
        case 3: n.getNodeInfo(e);break;
        //case 4: aodv.sendPacket(srcID, destID, al.generateData());
                
        }
        
        */
        System.out.println("The time is "+time + "\n" );
        
    }

    @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}