/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package educationpracticum;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import static javax.swing.Spring.height;
import static javax.swing.Spring.width;

/**
 *
 * @author nazarov
 *
 * скрины экрана
 *
 */
public class CaptureScreen {

    public static void main(String[] args) {
        CaptureScreen testScreen = new CaptureScreen();
        
        String nameDirToIMG = "Test665";
        nameDirToIMG = System.getProperty("user.home") + File.separator
                + "Desktop" + File.separator + nameDirToIMG + File.separator;
        System.out.println(nameDirToIMG);
        testScreen.clearDir(nameDirToIMG); //  очищаем директорию
        testScreen.captureScreen1(nameDirToIMG); // вызов скриншота

    }

    // скриншот 1 экрана видимо активного
    public void captureScreen1(String dir) {
        try {
            Robot robot = new Robot();
            Rectangle screenSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage image = null;
            int i = 0;
            while (true) {
                image = robot.createScreenCapture(screenSize);
                ImageIO.write(image, "png", new File(dir + "ScreenCapture" + Integer.toString(i) + ".png"));
                ++i;
            }

        } catch (AWTException ex) {
            Logger.getLogger(CaptureScreen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CaptureScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // --- (затирание файлов в папке)---
    public File clearDir(String dir) {
        // создаем или очищаем директорию(не очищает)
        File f = null;
        try {
            f = new File(dir);
            if (!f.mkdirs()) { //
                if (f.isDirectory()) {
                    String[] children = f.list();
                    for (int i = 0; i < children.length; i++) {
                        File fPick = new File(f, children[i]);
                        fPick.delete();
                        //System.out.println(fPick.getAbsolutePath());
                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(f.getAbsolutePath());
        return f;

    }
}
