import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class BMPrw {
    private byte[] title = new byte[224];
    private File file;
    BMPrw(String path) {
        file = new File(path);
    }
    public void readBMP(){
        try{
            BufferedImage originalImage = ImageIO.read(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write( originalImage, "bmp", baos );
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            byte[] pixels = new byte[imageInByte.length - 224];
            System.arraycopy(imageInByte,0, title,0,224);
            System.arraycopy(imageInByte,224, pixels,0,imageInByte.length - 224);
            baos.close();
            FileOutputStream fos = new FileOutputStream(new File("plain.txt"));
            fos.write(pixels);
            fos.close();
        }   catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void writeBMP(String input, String output){
        try{
            File file = new File(input);
        InputStream is = new FileInputStream(file);
        byte[] bytes = new byte[(int)file.length()];
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }
            is.close();

        byte[] nbytes = new byte[bytes.length + title.length];
        System.arraycopy(title, 0, nbytes, 0, title.length);
        System.arraycopy(bytes, 0, nbytes, title.length, bytes.length);
        InputStream innn = new ByteArrayInputStream(nbytes);
        BufferedImage image = ImageIO.read(innn);
        File outputFile = new File(output);
        ImageIO.write(image, "BMP", outputFile);
        }   catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
