/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thesis;


import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class NodeAttributesArea extends JPanel {

  private JComboBox                nodeSelectorComboBox    = new JComboBox();
  private JButton                  nodeAttributesButton    = new JButton("Attributes");
  private JCheckBox                promiscuousModeCheckBox = new JCheckBox("Promiscous Mode");
  private boolean                  blockChangeEvents       = false;
  private Vector<String>           nodeList                = new Vector<String>();
  private HashMap<String, JDialog> openNodeDialogs         = new HashMap<String, JDialog>();

  public NodeAttributesArea() {

  

    nodeAttributesButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        // If the attributes button is clicked while nothing is selected return
        if (nodeSelectorComboBox.getSelectedItem() == null) {
          return;
        }
        // If the node attributes window has already been opened check to see if it 
        // is still visible
        if (openNodeDialogs.containsKey((nodeSelectorComboBox.getSelectedItem()
            .toString()))) {
          JDialog dialog = openNodeDialogs.get((nodeSelectorComboBox
              .getSelectedItem().toString()));
          // If it is not still visible, show it.
          if (dialog.isVisible() == false) {
            dialog.setVisible(true);
            return;
          }
          return;
        }
        JDialog dialog = nodeInspector.getNodeDialog(nodeSelectorComboBox
            .getSelectedItem().toString());
        dialog.setVisible(true);
        openNodeDialogs.put(nodeSelectorComboBox.getSelectedItem().toString(),
            dialog);
      }
    });

  }

  private static final long serialVersionUID = 1L;



  public void nodeAdded(String nodeId) {
    nodeSelectorComboBox.addItem(nodeId);
    nodeList.add(nodeId);
  }

  public void nodeDeleted(String nodeId) {
    nodeSelectorComboBox.removeItem(nodeId);
    nodeList.remove(nodeId);
    JDialog jd = openNodeDialogs.get(nodeId);
    if (jd == null) {
      return;
    }
    openNodeDialogs.remove(nodeId);
    jd.dispose();
  }



  private NodeAttributes getAttributes(String id) {
    // Use the node inspector interface to view the properties of the node
    return nodeInspector.getNodeAttributes(id);
  }

  private void setAttributes(NodeAttributes n) {
    if (n == null) {
      return;
    }
    blockChangeEvents = true;
    
    nodeSelectorComboBox.setSelectedItem(n.id);
    promiscuousModeCheckBox.setSelected(n.isPromiscuous);
    
    
    
    blockChangeEvents = false;
  }

  public void setNodeInspector(NodeInspector ni) {
    this.nodeInspector = ni;
  }


  public Vector<String> getNodeList() {
    return nodeList;
  }


  public void setNodeById(String id) {
    setAttributes(getAttributes(id));
  }


  public void simPaused() {

  }


  public void openNodeDialog(String nodeID) {
    // If the node attributes window is already open return
    if (openNodeDialogs.containsKey(nodeID)) {
      return;
    }
    JDialog dialog = nodeInspector.getNodeDialog(nodeID);
    dialog.setVisible(true);
    openNodeDialogs.put(nodeID, dialog);
  }

  public void updateNodeDialogs() {
    String nodeId;
    JDialog dialog;
    Iterator<String> iter = openNodeDialogs.keySet().iterator();
    while (iter.hasNext()) {
      nodeId = iter.next();
      dialog = openNodeDialogs.get(nodeId);
      if (dialog.isVisible() == false) {
        iter.remove();
        continue;
      }
      nodeInspector.updateNodeDialog(nodeId, dialog);
    }
  }

  private NodeInspector nodeInspector;


}