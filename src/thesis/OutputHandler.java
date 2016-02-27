
package thesis;

import java.util.ArrayList;
import java.util.HashSet;


public class OutputHandler {

  // List of consumers
  private static ArrayList<OutputConsumer> consumers = new ArrayList<OutputConsumer>();

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
  
  private static final HashSet<EventManager.EventType> filteredEvents = new HashSet<EventManager.EventType>();
  public static void addFilteredEvent(EventManager.EventType eType) {
    filteredEvents.add(eType);
  }
  
  public static void removeFilteredEvent(EventManager.EventType eType) {
    filteredEvents.remove(eType);
  }

}