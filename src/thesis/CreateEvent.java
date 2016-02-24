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

    //private final EventManager e;
    CreateEvent(double time, EventManager em) {
        this.time=time;
        this.em=em;
    }


    public void execute(Simulator simulator) {
        
        SimEngine s = new SimEngine();
        s.consumeInput(em);
        System.out.println("The time is "+time + "\n" );
        
    }

    @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}