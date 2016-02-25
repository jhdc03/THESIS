/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thesis;
import thesis.NodeCreator.NodeType;
/**
 *
 * @author harve
 */

public class Defaults {
  public static final boolean DEBUG_ENABLED = false;
  public static final int X = 100;
  public static final int Y = 100;
  public static final boolean IS_PROMISCUOUS = false;
  public static final int RANGE = 150;
  public static final int SELECTED_NODE_RANGE_INDICATOR_THICKNESS = 3;
  public static final int CNTRLMSG_THICKNESS = 1;
  public static final int NARRMSG_THICKNESS = 4;
  public static final int BROADCAST_THICKNESS = 2;
  public static final String TITLE_STRING = "Dynamic Ad-hoc Routing Simulator";
  public static final int LOG_AREA_BUF_SIZE = 131072;
  public static final int MAXFPS = 50;
  public static final int MINFPS = 3;
  private final NodeStore store = new NodeStore();
  
  //Animation speeds. Total time for one cycle of animation. Smaller is faster.
  public static final int BROADCAST_ANISPEED_MILLISECONDS = 700;
  public static final int MESSAGE_ANISPEED_MILLISECONDS = 1000;

    /**
     * @return the store
     */
    public NodeStore getStore() {
        return store;
    }
}

