/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thesis;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
/**
 *
 * @author harve
 */

public class OutputHandler {

  // List of consumers
  private static CopyOnWriteArrayList<OutputConsumer> consumers = new CopyOnWriteArrayList<OutputConsumer>();

  public static void removeOutputConsumer(OutputConsumer c) {
    consumers.remove(c);
  }

  public static void addOutputConsumer(OutputConsumer c) {
    consumers.add(c);
  }

  public static void dispatch(EventManager e) {
    //Apply event filter
    if(filteredEvents.contains(e.eventType)) {
      return;
    }
    for (OutputConsumer c : consumers) {
      c.consumeOutput(e);
    }
  }
  
  private static final CopyOnWriteArraySet<EventManager.EventType> filteredEvents = new CopyOnWriteArraySet<EventManager.EventType>();
  public static void addFilteredEvent(EventManager.EventType eType) {
    filteredEvents.add(eType);
  }
  
  public static void removeFilteredEvent(EventManager.EventType eType) {
    filteredEvents.remove(eType);
  }

}