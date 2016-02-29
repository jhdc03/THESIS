
package thesis;
import java.util.concurrent.CopyOnWriteArrayList;

public class InputHandler {
  public static void dispatch(EventManager e){
	for(InputConsumer c : inputConsumers){
	  c.consumeInput(e);
    }
  } 
    
  public static void addInputConsumer(InputConsumer c) {
	  inputConsumers.add(c);
  }
  
  public static void removeInputConsumer(InputConsumer c) {
	  inputConsumers.remove(c);
  }
  
  private static final CopyOnWriteArrayList<InputConsumer> inputConsumers = new CopyOnWriteArrayList<InputConsumer>();

}

