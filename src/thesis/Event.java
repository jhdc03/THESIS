
package thesis;


abstract class  Event implements Comparable {
    long time;
    EventManager em= new EventManager();
    
    public boolean lessThan(Comparable y) {
        Event e = (Event) y;  // Will throw an exception if y is not an Event
        return this.time < e.time;
    }
    abstract void execute(SimEngine simulator);
}