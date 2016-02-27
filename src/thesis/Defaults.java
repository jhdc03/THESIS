
package thesis;
import java.awt.Color;
import java.awt.Font;
import javax.swing.plaf.FontUIResource;
import static thesis.NodeCreator.NodeType.AODV;


public class Defaults {
  public static final int X = 100;
  public static final int Y = 100;
  public static final boolean IS_PROMISCUOUS = false;
  public static final float ENERGY = 100;
  public static final int RANGE = 150;
  public static final int SIM_TIME_END = 100;
  public static final String TITLE_STRING = "Wireless Sensor Network Simulator";
  public static final int LOG_AREA_BUF_SIZE = 131072;
  public static final float BANDWIDTH = 100;
  //public static final NodeCreator.NodeType L2PROTOCOL = 802.15;
  public static final NodeCreator.NodeType L3PROTOCOL = AODV;
  public static final float ENERGY_CONSUMPTION_TRANSMISSION_STATE = 0;
  public static final float ENERGY_CONSUMPTION_RECIEVE_STATE = 0;
  public static final float ENERGY_CONSUMPTION_IDLE_STATE =0;
  public static final float WIRELESS_MEDIUM_RELIABILITY= 100;
}
