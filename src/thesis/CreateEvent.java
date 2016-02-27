
package thesis;

public class CreateEvent extends  Event {
    CreateEvent(long time, EventManager em) {
        this.time = time;
        this.em= em;
    }


    public void execute(SimEngine simulator) {
        simulator.consumeInput(em);
        System.out.println("The time is "+time + "\n" );
        
    }

    @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}