/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraberUSBWebCam;

import java.io.File;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.bytedeco.javacv.VideoInputFrameGrabber;

import static org.bytedeco.opencv.global.opencv_core.cvFlip;
import static org.bytedeco.opencv.helper.opencv_imgcodecs.cvSaveImage;
import org.bytedeco.opencv.opencv_core.IplImage;

/**
 *
 * @author nazarov захват изображения с камера или файла видео
 *
 * Такие параметры были в настройках VM
 * -Djava.library.path="C:\Users\Nazarov\Downloads\opencv-4.5.3-vc14_vc15_Windows\opencv\build\java\x64" -Xmx4000m
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
            for (String s: VideoInputFrameGrabber.getDeviceDescriptions()) { // Названия устройств которые являются камерами
                System.out.println(s);
            }

            FrameGrabber grabber = new OpenCVFrameGrabber(0); // 1 for next camera (Так на ноуте работает)
            //FrameGrabber grabber = new FFmpegFrameGrabber("SampleVideo_1280x720_10mb.mp4"); 
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
