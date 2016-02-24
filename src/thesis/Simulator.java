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


class Simulator {
    OrderedSet EventQueue;
    double time;
    double now() {
        return time;
    }
    void doAllEvents() {
        Event e;
        while ( (e= (Event) EventQueue.removeFirst()) != null) {
            time = e.time;
            e.execute(this);
        }
    }
    void insert(Event e) {
        
        EventQueue.insert(e);
    }
    Event cancel(Event e)  {
        throw new java.lang.RuntimeException("Method not implemented");
    }
}


