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

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import thesis.NodeCreator.NodeType;

public class Utilities {
  public static void setSwingFont(javax.swing.plaf.FontUIResource f) {
    java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
    while (keys.hasMoreElements()) {
      Object key = keys.nextElement();
      Object value = UIManager.get(key);
      if (value instanceof javax.swing.plaf.FontUIResource)
        UIManager.put(key, f);
    }
  }

  public static void showError(String error) {
    JOptionPane.showMessageDialog(null, error, "Error",
        JOptionPane.ERROR_MESSAGE);
  }

  public static void showInfo(String info, String title) {
    JOptionPane.showMessageDialog(null, info, title,
        JOptionPane.INFORMATION_MESSAGE);
  }

  
  public static String popupAskUser(String question, String[] answers, String title) {
    int answer = JOptionPane.showOptionDialog(null,
        question, title, 0,
        JOptionPane.QUESTION_MESSAGE, null, answers, answers[0]);
    // Return null if the user closed the dialog box
    if (answer == JOptionPane.CLOSED_OPTION) {
      return null;
    }

    // Return their selection
    return answers[answer];

  }
  
  public static NodeType popupAskNodeType() {
    // Get every node type
    NodeType nTypes[] = getNodeTypes();

    int answer = JOptionPane.showOptionDialog(null,
        "Select a simulation type.", "Select a simulation type.", 0,
        JOptionPane.QUESTION_MESSAGE, null, nTypes, nTypes[0]);

    // Return null if the user closed the dialog box
    if (answer == JOptionPane.CLOSED_OPTION) {
      return null;
    }

    // Return their selection
    return nTypes[answer];
  }

  public static String getTmpLogPath() {
    String tmpDir = System.getProperty("java.io.tmpdir");

    // On some JVMs, a trailing file separator doesn't exist. Correct this.
    if (!tmpDir.endsWith(System.getProperty("file.separator"))) {
      tmpDir = tmpDir + System.getProperty("file.separator");
    }

    return tmpDir + "darslog.tmp";
  }


  private static final NodeType[] nodeTypes = NodeType.values();
  public static NodeType[] getNodeTypes() {
    return nodeTypes;
  }

  static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
  public static String timeStamp() {
    Calendar cal = Calendar.getInstance();
    return sdf.format(cal.getTime());
  }

}
