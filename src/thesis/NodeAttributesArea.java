/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thesis;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;



public class NodeAttributesArea extends JPanel {

  private Vector<String>           nodeList                = new Vector<String>();
  private HashMap<String, JDialog> openNodeDialogs         = new HashMap<String, JDialog>();



/*JDialog dialog = nodeInspector.getNodeDialog(nodeSelectorComboBox
            .getSelectedItem().toString());
        dialog.setVisible(true);
        openNodeDialogs.put(nodeSelectorComboBox.getSelectedItem().toString(),
            dialog);*/

  public void nodeAdded(String nodeId) {
    nodeList.add(nodeId);
  }


  private NodeAttributes getAttributes(String id) {
    // Use the node inspector interface to view the properties of the node
    return nodeInspector.getNodeAttributes(id);
  }


  public void setNodeInspector(NodeInspector ni) {
    this.nodeInspector = ni;
  }


  public Vector<String> getNodeList() {
    return nodeList;
  }

  public void nodeDeleted(String nodeId) {
    nodeList.remove(nodeId);
    JDialog jd = openNodeDialogs.get(nodeId);
    if (jd == null) {
      return;
    }
    openNodeDialogs.remove(nodeId);
    jd.dispose();
  }

  public void openNodeDialog(String nodeID) {
    // If the node attributes window is already open return
    if (openNodeDialogs.containsKey(nodeID)) {
      return;
    }
   
    JDialog dialog = nodeInspector.getNodeDialog(nodeID);
    //dialog.setModal(true);
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