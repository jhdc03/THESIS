
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
    for (OutputConsumer c : consumers) {
      c.consumeOutput(e);
    }
  }
  
}