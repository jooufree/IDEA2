import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
public class Main {

    public final static String PATH_WINDOWS =  "";
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        BMPrw bm = new BMPrw(in.nextLine());
        bm.readBMP();
        try{
            File input = new File(PATH_WINDOWS + "plain.txt");
            File output = new File(PATH_WINDOWS + "encrypted.txt");
            File result = new File(PATH_WINDOWS + "result.txt");
            if(!input.canRead() || (!output.canRead() && !output.canWrite()) || !result.canWrite()){
                if(!input.canRead())
                    System.err.println("Can not read input file. Check file!");
                if(!output.canRead())
                    System.err.println("Can not read output file. Check file!");
                if(!output.canWrite())
                    System.err.println("Can not write output file. Check file!");
                if(!output.canWrite())
                    System.err.println("Can not write result file. Check file!");
                throw new Exception("Check files!");
            }

            else{
                String keyStr = "";
                try{
                    FileInputStream key = new FileInputStream(PATH_WINDOWS + "key.txt");
                    BufferedReader br = new BufferedReader(new InputStreamReader(key));
                    String strLine;
                    while ((strLine = br.readLine()) != null){
                        keyStr += strLine;

                    }
                }catch (IOException e){
                }
                FileCipher task;
                boolean encrypt = true;
                task = new FileCipher(input.getPath(), output.getPath(), keyStr, encrypt, OperationMode.Mode.ECB);
                task.cryptFile();
                bm.writeBMP(PATH_WINDOWS + "encrypted.txt", PATH_WINDOWS + "encrypted.bmp");
                encrypt = false;
                FileCipher task2 = new FileCipher(output.getPath(), result.getPath(), keyStr, encrypt, OperationMode.Mode.ECB);
                task2.cryptFile();
                bm.writeBMP(PATH_WINDOWS + "result.txt", PATH_WINDOWS + "result.bmp");
            }
        }catch (Exception e){
            System.err.println("File error");
        }
    }
}
