/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thesis;
import java.util.ArrayList;
/**
 *
 * @author harve
 */
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
  
  private static final ArrayList<InputConsumer> inputConsumers = new ArrayList<InputConsumer>();

}

