/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thesis;


public class Message {
    
   public String message;
   public String destinationId;
   public String originId;
/**
 * This Message constructor takes 3 parameters, if the DestinationId is set 
 * to the value contained in BCAST_STRING a broadcast message will be assumed.
 * @param DestinationId
 * @param OriginId
 * @param Message
 */
   public Message(String DestinationId, String OriginId, String Message){
       message = Message;
       destinationId = DestinationId;
       originId = OriginId;
   }
   
   /**
    * Broadcast Destination ID
    * 
    * A Destination ID that is set to this string will be assumed to be a broadcast message.
    */
   public static final String BCAST_STRING = "BROADCAST";
   
}