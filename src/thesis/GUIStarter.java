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
class GUIStarter implements Runnable {
  private GUI g;
  public GUI getGui() { return g; }
  public void run() {
    g = new GUI(); 
  }
}