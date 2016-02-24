/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thesis;
import java.util.Random; 

/*
 *
 * @author harve
 */



public class ApplicationLayer extends Simulator{
    
    
    Entity e = new Entity();
    //generate random number of bits in the packet
    public int generateData() {
        return new Random().nextInt(524280 - 416 + 1) + 416;
    }
    
 
    
    }

    