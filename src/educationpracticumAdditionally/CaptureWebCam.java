/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package educationpracticumAdditionally;

import org.bytedeco.javacv.*;
import org.bytedeco.opencv.opencv_core.IplImage; // библиотека компьютерного зрения с открытым исходным кодом)

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.bytedeco.opencv.global.opencv_core.cvFlip;
import static org.bytedeco.opencv.helper.opencv_imgcodecs.cvSaveImage;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author nazarov захват изображения с камера или файла видео
 */
// ===== Захват изображения с камеры или видео файла =====
public class CaptureWebCam {

    final int INTERVAL = 100;///you may use interval
    CanvasFrame canvas = new CanvasFrame("Web Cam");

    public CaptureWebCam() {
        canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    }

    public void Start() {

        try {
            FrameGrabber grabber = new OpenCVFrameGrabber(0); // 1 for next camera (Так на ноуте работает)
            //FrameGrabber grabber = new FFmpegFrameGrabber("SampleVideo_1280x720_10mb.mp4"); // 1 for next camera
            OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
            //OpenCVFrameGrabber grabber = new OpenCVFrameGrabber("SampleVideo_1280x720_10mb.mp4"); 
            IplImage img = null;
            int i = 0;

            grabber.start();

            Frame frame = grabber.grab();

            while ((frame = grabber.grab()) != null) {
                System.out.println("frame grabbed at " + grabber.getTimestamp());
                img = converter.convert(grabber.grab());
                if (img != null) {
                    System.out.println("IMG not null ");
                    break;
                }
            }
                //the grabbed frame will be flipped, re-flip to make it right
            //cvFlip(img, img, 1);// l-r = 90_degrees_steps_anti_clockwise

            //save
            cvSaveImage("-aa.jpg", img);
            canvas.showImage(converter.convert(img));

        } catch (FrameGrabber.Exception ex) {
            Logger.getLogger(CaptureWebCam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        CaptureWebCam gs = new CaptureWebCam();
        gs.Start();

    }
}
