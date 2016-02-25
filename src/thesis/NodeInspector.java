/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thesis;

import javax.swing.JDialog;
/**
 *
 * @author harve
 */
public interface NodeInspector {
  public NodeAttributes getNodeAttributes(String nodeId);
  
  public JDialog getNodeDialog(String nodeId);
  
  public void updateNodeDialog(String nodeId, JDialog dialog);
}