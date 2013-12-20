// code-examples/AppDesign/annotations/FilePrinterMain.java

import java.io.*;

public class FilePrinterMain {
  public static void main(String[] args) {
    for (String fileName: args) {
      try {
        File file = new File(fileName);
        new FilePrinter(file).print();
      } catch (IOException ioe) {
        System.err.println("IOException for file " + fileName);
        System.err.println(ioe.getMessage());
      }
    }
  }
}