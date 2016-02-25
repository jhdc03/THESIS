package thesis;

import javax.swing.*;

import javax.swing.border.Border;


import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Queue;

public class GUI extends JFrame implements OutputConsumer {

  /**
   * 
   */
  private static final long  serialVersionUID    = 1L;
  private JPanel             simPanel            = new JPanel();
  private JPanel             logPanel            = new JPanel();
  private JPanel             bottomRightPanel = new JPanel();

  private LogArea            logArea             = new LogArea();
  private NodeAttributesArea nodeAttributesArea  = new NodeAttributesArea();
  private SimArea            simArea             = new SimArea();


  public GUI() {
    super(Defaults.TITLE_STRING);

    // Close the program when the frame is exited
    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e){
        System.exit(0);
      }
    });
    
    //Initialize images
    //ImageFactory.checkInit();
   
    //Add a listener for resize events
    this.addComponentListener(new java.awt.event.ComponentAdapter() 
    {
        public void componentResized(ComponentEvent e)
        {
            setSizes();
        }
    });


    
    // Setup a new layout for the outermost part of the frame. We'll use the
    // border layout.
    this.setLayout(new BorderLayout());

    
    
    // Allocate as follows:
    /*
     * _________________ | | | | | | | CENTER | <-|-- EAST | | | | | |
     * |_____________|___|
     */
    // Add a center panel, this will serve us merely in a layout capacity.
    JPanel subpanel = new JPanel();
   
    
    this.add(subpanel, BorderLayout.CENTER);


    /*
     * Elaborate upon the layout of the subpanel. Do this: ______________ | | |
     * | | CENTER | |_____________| | | |__SOUTH______|
     */
    // Use another borderlayout for the subpanel.
    subpanel.setLayout(new BorderLayout());

    // Add the GuiCanvas to the Center part
    simPanel.setLayout(new BorderLayout());
    simPanel.add(simArea, BorderLayout.CENTER);
    subpanel.add(simPanel, BorderLayout.CENTER);

    // Add the Status log panel to the bottom part.
    logPanel.setLayout(new BorderLayout());
    logPanel.add(logArea, BorderLayout.CENTER);
    logPanel.add(bottomRightPanel, BorderLayout.EAST);
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
    logPanel.setPreferredSize(new Dimension(logPanel.getPreferredSize().width, 210));
    simPanel.setPreferredSize(new Dimension((int) (windowSize.width),
        (int) (windowSize.height * .8)));
  }

  private void setBorders() {
    Border raisedBevel, loweredBevel, compound;
    raisedBevel = BorderFactory.createRaisedBevelBorder();
    loweredBevel = BorderFactory.createLoweredBevelBorder();
    compound = BorderFactory.createCompoundBorder(raisedBevel, loweredBevel);
    simPanel.setBorder(compound);

  }

  private void coupleComponents() {

   
    // The node attributes panel and DARSAppMenu needs be able to augment the sim area
    // (selecting a new node, etc)
    nodeAttributesArea.setSimArea(simArea);
    //menuArea.setSimArea(simArea);
    //simArea.setNodeAttributesArea(nodeAttributesArea);
  }

  public void setNodeInspector(NodeInspector ni) {
    // Give it to the nodeAttributesArea instance
    nodeAttributesArea.setNodeInspector(ni);

  }
  public void test(){
       /*  System.out.print("test create node");
       // Add the node
        simArea.addNewNode(12, 13, 100, "A");
        nodeAttributesArea.nodeAdded("A");
        System.out.print("test create node2");
        //select the node
        simArea.selectNode("A");
        nodeAttributesArea.setNodeById("A");   
       // logArea.appendLog("SIM INFO" , "");
       nodeAttributesArea.setVisible(true);
       
       System.out.print("test create node3");
       */
   //if (nodeAttributesArea != null) {
    //        nodeAttributesArea.openNodeDialog(/*gnode.getId()*/ "A");
     //     }
  }
   
  public void consumeOutput(EventManager e) {
    // schedule the event to be processed later so as to not disturb the gui's
    // event thread
switch (e.eventType) {
      case OUT_ADD_NODE:
        nodeAttributesArea.nodeAdded(e.nodeId);
        nodeAttributesArea.setNodeById(e.nodeId);   
        logArea.appendLog("SIM INFO" , e.informationalMessage, e.currentQuantum);
        nodeAttributesArea.openNodeDialog(e.nodeId);
        break;

    
      case OUT_MSG_RECEIVED:
        logArea.appendLog("SIM INFO" , e.informationalMessage, e.currentQuantum);
        JOptionPane.showMessageDialog(null, "Successful Message Transmission!\n" +
                                            "Source Node: "+ e.sourceId + "\n" + 
                                            "Destination Node: " + e.destinationId + "\n" +
                                            "Message: " + e.transmittedMessage);
        break;
      
      case OUT_INSERT_MESSAGE:
        logArea.appendLog("SIM INFO" , e.informationalMessage, e.currentQuantum);
        break;
        
  
      case OUT_NARRMSG_RECEIVED:
        // Animate the event
        //simArea.traceMessage(e.sourceId, e.destinationId, Defaults.NARRMSG_COLOR, 5, Defaults.NARRMSG_THICKNESS, 1);
        logArea.appendLog("NODE INFO" , e.informationalMessage, e.currentQuantum);
        break;

      
      case OUT_NARRMSG_TRANSMITTED:
      case OUT_CONTROLMSG_TRANSMITTED:
        //If the destination is BROADCAST, animate it.
        if(e.destinationId.equals(Message.BCAST_STRING)){
          //simArea.nodeBroadcast(e.sourceId);
          logArea.appendLog("NODE INFO", e.informationalMessage, e.currentQuantum);
        }
        break;
        
        
      case OUT_CONTROLMSG_RECEIVED:
        // Animate the event
        //simArea.traceMessage(e.sourceId, e.destinationId, Defaults.CNTRLMSG_COLOR,1, Defaults.CNTRLMSG_THICKNESS,0);
        logArea.appendLog("NODE INFO", e.informationalMessage, e.currentQuantum);
        break;

        
      case OUT_DEL_NODE:
        // Remove the node
       //simArea.deleteNode(e.nodeId);
        nodeAttributesArea.nodeDeleted(e.nodeId);
        logArea.appendLog("SIM INFO", e.informationalMessage, e.currentQuantum);
        break;

      case OUT_DEBUG:
        logArea.appendLog("SIM DEBUG" , e.informationalMessage, e.currentQuantum);
        break;
      case OUT_ERROR:
        logArea.appendLog("SIM ERROR" , e.informationalMessage, e.currentQuantum);
        break;
      case OUT_NODE_INFO:
        logArea.appendLog("NODE INFO" , e.informationalMessage, e.currentQuantum);
        break;
        
      case OUT_QUANTUM_ELAPSED:
        //menuArea.quantumElapsed();
        nodeAttributesArea.updateNodeDialogs();
        break;


      
    }

  }

 }


 
