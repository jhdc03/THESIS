package thesis;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author harve
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;



/**
 * @author Mike Very basic logger. To use, reference the log method in a static
 *         context i.e. Logger.log(). Use a EventManager as the only parameter.
 *         Logger relies on the getLogString() functionality provided by the
 *         EventManager. The logger is a primary consumer of events dispatched
 *         through the output handler. As such, it implements the DARSConsumer
 *         interface. Use the getInstance() method to reference the logger in a
 *         DARSConsumer context.
 */
public class Logger {

  public static String newline       = System.getProperty("line.separator");

  public static synchronized void log(EventManager e) {

    // if file handle is not init, do it
    if (fstream == null) {
      try {
        deleteLogFile();
        fstream = new FileWriter(Utilities.getTmpLogPath());
      } catch (IOException e2) {
        Utilities.showError("(Fatal) Could not write to the DARS temporary file due to an IO exception :" + e2.getMessage());
        System.exit(1);
      }
      out = new BufferedWriter(fstream);
      // append the head of the DARS log file
      try {
        out.append(EventManager.getLogHeader() + newline);
      } catch (IOException e1) {
        Utilities.showError("(Fatal) Could not write to the DARS temporary file due to an IO exception :" + e1.getMessage());
        System.exit(1);
      }
      
      //Arrange for the file to be deleted on exit
      File tmpFile = new File(Utilities.getTmpLogPath());
      tmpFile.deleteOnExit();
    }

    try {
      out.append(e.getLogString());
    } catch (IOException e1) {
      Utilities.showError("(Fatal) Could not write to the DARS temporary file due to an IO exception :" + e1.getMessage());
      System.exit(1);
    }

  }

  public static void deleteLogFile() {
    // Make sure the file handle is closed.
    closeLogFile();
    File tmp = null;
    try {
      tmp = new File(Utilities.getTmpLogPath());
      if (tmp.exists()) {
        tmp.delete();
      }
    } catch (Exception e) {
      // Fail quietly since the file doesn't exist yet
    }

  }

  private static void closeLogFile() {
    if (fstream != null) {
      try {
        if (out != null) {
          out.flush();
        }
        fstream.close();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    fstream = null;
  }

  public void flushLogFile() {
    if (fstream != null) {
      try {
        if (out != null) {
          out.flush();
        }
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  // Fulfills the DARSConsumer contract
  public void consumeOutput(EventManager e) {
    

    // log the event
    Logger.log(e);
    
    
    switch (e.eventType) {

    case OUT_STOP_SIM:
      // Close the log file
      closeLogFile();
      break;
    }



  }

  public static Logger getInstance() {
    return instance_;
  }

  private static Logger         instance_ = new Logger();
  private static FileWriter     fstream;
  private static BufferedWriter out;

  private Logger() {
  }

  public void consumeInput(EventManager e) {
    Logger.log(e);

  }
}