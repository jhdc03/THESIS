package thesis;

import javax.swing.*;

import javax.swing.border.Border;


import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI extends JFrame implements OutputConsumer {

  /**
   * 
   */
  private static final long  serialVersionUID    = 1L;
  private JPanel             logPanel            = new JPanel();
  private LogArea            logArea             = new LogArea();
  private NodeAttributesArea nodeAttributesArea  = new NodeAttributesArea();


  public GUI() {
    super(Defaults.TITLE_STRING);

    // Close the program when the frame is exited
    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e){
        System.exit(0);
      }
    });
   
    //Add a listener for resize events
    this.addComponentListener(new java.awt.event.ComponentAdapter() 
    {
        public void componentResized(ComponentEvent e)
        {
            setSizes();
        }
    });

    this.setLayout(new BorderLayout());
    JPanel subpanel = new JPanel();
    this.add(subpanel, BorderLayout.CENTER);
    subpanel.setLayout(new BorderLayout());


    // Add the Status log panel to the bottom part.
    logPanel.setLayout(new BorderLayout());
    logPanel.add(logArea, BorderLayout.CENTER);
    subpanel.add(logPanel, BorderLayout.SOUTH);

    // initialize communication paths between the gui objects
    coupleComponents();

    // setup the borders
    setBorders();

    // pack everything
    pack();

  }

  //This method is called when the window is "realized" onto the screen. Perfect time
  //to setup the relative sizes of components.
  @Override
  public void addNotify(){
    super.addNotify();
    setSizes();
  }
  private void setSizes() {
    GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment
        .getLocalGraphicsEnvironment();
    Rectangle r = graphicsEnvironment.getMaximumWindowBounds();
    setMaximizedBounds(r);
    Dimension windowSize = new Dimension(r.width, r.height);

    this.setPreferredSize(windowSize);
    logPanel.setPreferredSize(new Dimension(logPanel.getPreferredSize().width, 800));
  }

  private void setBorders() {
    Border raisedBevel, loweredBevel, compound;
    raisedBevel = BorderFactory.createRaisedBevelBorder();
    loweredBevel = BorderFactory.createLoweredBevelBorder();
    compound = BorderFactory.createCompoundBorder(raisedBevel, loweredBevel);

  }

  private void coupleComponents() {

  }

  public void setNodeInspector(NodeInspector ni) {
    // Give it to the nodeAttributesArea instance
    nodeAttributesArea.setNodeInspector(ni);
  }
  
  public void test(){

  }
   
  public void consumeOutput(EventManager e) {
    // schedule the event to be processed later so as to not disturb the gui's
switch (e.eventType) {
    
      case OUT_ADD_NODE:
        nodeAttributesArea.nodeAdded(e.nodeId);
        logArea.appendLog("SIM INFO" , e.informationalMessage, e.time);
        break;
        
      case OUT_DISPLAY_NODE:
        nodeAttributesArea.nodeAdded(e.nodeId);
        logArea.appendLog("SIM INFO" , e.informationalMessage, e.time);
        //test
        nodeAttributesArea.openNodeDialog(e.nodeId);
        break;
      case OUT_SIM_RESULTS:
        logArea.appendLog("SIM INFO" , e.informationalMessage, e.time);
        break;  
      case OUT_MSG_RECEIVED:
        logArea.appendLog("SIM INFO" , e.informationalMessage, e.time);
        break;
      
      case OUT_PACKET_DROPPED:
        logArea.appendLog("SIM INFO" , e.informationalMessage, e.time);
        break;
        
      case OUT_INSERT_MESSAGE:
        logArea.appendLog("SIM INFO" , e.informationalMessage, e.time);
        break;
        
  
      case OUT_NARRMSG_RECEIVED:
        logArea.appendLog("NODE INFO" , e.informationalMessage, e.time);
        break;
        
      case OUT_CONTROLMSG_TRANSMITTED:
        if(e.destinationId.equals(Message.BCAST_STRING)){
          logArea.appendLog("NODE INFO", e.informationalMessage, e.time);
        }
        break;
        
      case OUT_CONTROLMSG_RECEIVED:
        logArea.appendLog("NODE INFO", e.informationalMessage, e.time);
        break;

      case OUT_DEBUG:
        logArea.appendLog("SIM DEBUG" , e.informationalMessage, e.time);
        break;
      case OUT_ERROR:
        logArea.appendLog("SIM ERROR" , e.informationalMessage, e.time);
        break;
      case OUT_NODE_INFO:
        logArea.appendLog("NODE INFO" , e.informationalMessage, e.time);
        break;
        
      case OUT_QUANTUM_ELAPSED:
        //menuArea.quantumElapsed();
        nodeAttributesArea.updateNodeDialogs();
        break;


      
    }

  }

 }


 
