/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package educationpracticumAdditionally;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RotateIMG {

    public static BufferedImage rotateImage(BufferedImage imageToRotate) {
        int widthOfImage = imageToRotate.getWidth();
        int heightOfImage = imageToRotate.getHeight();
        int typeOfImage = imageToRotate.getType();

        BufferedImage newImageFromBuffer = new BufferedImage(heightOfImage, widthOfImage, typeOfImage);

        Graphics2D graphics2D = newImageFromBuffer.createGraphics();

        graphics2D.rotate(Math.toRadians(90), widthOfImage / 2 , heightOfImage);
        
        graphics2D.drawImage(imageToRotate, null, 0, 0);

        return newImageFromBuffer;
    }

     
    public static BufferedImage rotateImageByDegrees(BufferedImage img, double angle) {
        /* --- фукция поворота изображения на угол ---
            нормально вертит как хочет в отличие от первого
        */
        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);

        int x = w / 2;
        int y = h / 2;

        at.rotate(rads, x, y);
        g2d.setTransform(at);
        g2d.drawImage(img, 0, 0, null);
        g2d.setColor(Color.RED); // для чего это делается
        g2d.drawRect(0, 0, newWidth - 1, newHeight - 1);
        g2d.dispose();

        return rotated;
    }
    
    public static void main(String[] args) {
        try {

            String nameFileIMG = "myimage_4.png";
            String pathFileIMG = "imgRoteta" + File.separator + nameFileIMG;
            BufferedImage originalImage = ImageIO.read(new File(pathFileIMG));
            ResizerDataIMG resizerDataIMG = new ResizerDataIMG();
            
            //BufferedImage resizeIMG = resizerDataIMG.getDataResisengScreen(originalImage, 100, 100); // уменьшение размера
            BufferedImage subImage = rotateImageByDegrees(originalImage, 30.0D);

            File rotatedImageFile = new File("imgRoteta" +  File.separator + "Rotate_"  + nameFileIMG);

            //ImageIO.write(subImage, "png", rotatedImageFile);
            ImageIO.write(subImage, "png", rotatedImageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}