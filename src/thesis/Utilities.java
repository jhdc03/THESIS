/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thesis;



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
