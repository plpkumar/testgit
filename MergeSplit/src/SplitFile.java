import java.io.FileInputStream;
import java.io.FileOutputStream;

class SplitFile {

  public static void main(String args[]) throws Exception {
	  String filePath = "C:\\Pavan\\SPNEGO.txt";
	  String fileNew = "C:\\Pavan\\SplitTst\\KTRSTest";
	  FileInputStream fis = new FileInputStream(filePath);
    int size = 1024;
    byte buffer[] = new byte[size];

    int count = 0;
    while (true) {
      int i = fis.read(buffer, 0, size);
      System.out.println(i);
      if (i == -1)
        break;

      String filename = fileNew + count+".txt";
      FileOutputStream fos = new FileOutputStream(filename);
      fos.write(buffer, 0, i);
      fos.flush();
      fos.close();

      ++count;
    }
  }
  
  
}