import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
public class Main {
    List<String> FileListDir = new ArrayList<String>();
    public static void main(String[] args) {
        String InputDirZip = "D:\\Google Disk\\Zálohy\\Dokumenty" + ".zip";
        File dir = new File("D:\\Dokumenty");
        Main Main = new Main();
        Main.Zipping(dir, InputDirZip);
    }
    private void Zipping(File dir, String zip){
        try {
            FileList(dir);
            FileOutputStream FileOut = new FileOutputStream(zip);
            ZipOutputStream ZipOut = new ZipOutputStream(FileOut);
            for(String p : FileListDir){
                ZipEntry z = new ZipEntry(p.substring(dir.getAbsolutePath().length() + 1, p.length()));
                ZipOut.putNextEntry(z);
                FileInputStream fl = new FileInputStream(p);
                byte[] bt = new byte[1024];
                int l;
                while((l = fl.read(bt)) > 0){
                    ZipOut.write(bt, 0, l);
                }
                ZipOut.closeEntry();
                fl.close();
            }
            ZipOut.close();
            FileOut.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private void FileList(File dir) throws IOException {
        File[] files = dir.listFiles();
        for(File file : files){
            if(file.isFile()) FileListDir.add(file.getAbsolutePath());
            else FileList(file);
        }
    }

}
