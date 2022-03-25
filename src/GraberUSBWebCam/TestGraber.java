/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GraberUSBWebCam;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;

/**
 *
 * @author nazarov
 * 
 * Не хера не работает грабер тут
 */
public class TestGraber {
     public static void main(String[] args) {
        FrameGrabber grabber;
        try {
            grabber = FrameGrabber.createDefault(0);
            grabber.start();

            // Frame to capture
            Frame frame = null;

            CanvasFrame cFrame = new CanvasFrame("Some Title", CanvasFrame.getDefaultGamma() / grabber.getGamma());
            while ((frame = grabber.grab()) != null) {

                if (cFrame.isVisible()) {
                    cFrame.showImage(frame);
                }
            }

        } catch (FrameGrabber.Exception ex) {
            Logger.getLogger(TestGraber.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
