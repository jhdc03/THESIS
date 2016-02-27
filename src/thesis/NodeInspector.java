
package thesis;

import javax.swing.JDialog;

public interface NodeInspector {
  public NodeAttributes getNodeAttributes(String nodeId);
  
  public JDialog getNodeDialog(String nodeId);
  
  public void updateNodeDialog(String nodeId, JDialog dialog);
}