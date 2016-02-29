
package thesis;

import java.util.concurrent.CopyOnWriteArrayList;


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
    for (OutputConsumer c : consumers) {
      c.consumeOutput(e);
    }
  }
  
}