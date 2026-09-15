import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class FileHasher {

  public static String hashFile(String filePath) {
    File path = new File(filePath);
    String read = new String();
    String hex = new String();
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
      read = br.readLine();
    } catch (Exception e) {
      System.out.println("Error: No file was found at the given path");
      return "";
    }
    try {
      MessageDigest dig = MessageDigest.getInstance("SHA-256");
      byte[] bytes = dig.digest(read.getBytes(StandardCharsets.UTF_8));
      hex = HexFormat.of().formatHex(bytes);
    } catch (NoSuchAlgorithmException e) {
      // TODO Auto-generated catch block
      // e.printStackTrace();
    }
    return hex;
  }
  public static void main(String[] args) {
    File dir = new File("./JavaFileSystem");
    File notes = new File("./JavaFileSystem/notes.txt");
    File data = new File("./JavaFileSystem/data.txt");
    File log = new File("./JavaFileSystem/log.txt");

    dir.mkdir();
    try {
      notes.createNewFile();
      data.createNewFile();
      log.createNewFile();
    } catch (Exception e) {
      System.err.println(e);
    }
    File[] files = {notes, data, log};

    for (File file : files) {
      try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
      bw.write("this is the " + file.getName() + " file");
      } catch (Exception e) {
        // TODO: handle exception
      }
    }

    StringBuilder bak = new StringBuilder();
    for (File file : files) {
      try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String read = br.readLine();
        // System.out.println(read);
        bak.append(read);
      } catch (Exception e) {
        // TODO: handle exception
      }
    }

    File backupDir = new File("./JavaFileSystem/Backup");
    File backup = new File("./JavaFileSystem/Backup/backup.txt");
    backupDir.mkdir();
    try {
      backup.createNewFile();
    } catch (Exception e) {
      System.err.println(e);
    }

    try (BufferedWriter bw = new BufferedWriter(new FileWriter(backup))) {
      bw.write(bak.toString());
      } catch (Exception e) {
        // TODO: handle exception
      }

    // for (File file : files) {
    //   try {
    //     System.out.println(hashFile("./JavaFileSystem/" + file.getName()));
    //   } catch (Exception e) {
        
    //   }
    // }
    System.out.println(hashFile("./JavaFileSystem/" + notes.getName()));
  }
  
}