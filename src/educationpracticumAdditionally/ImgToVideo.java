/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package educationpracticumAdditionally;

import java.awt.List;
import java.io.File;
import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import static org.bytedeco.opencv.helper.opencv_imgcodecs.cvLoadImage;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoWriter;



/**
 *
 * @author nazarov
 * 
 * с помощью компьютерного зрения собираем из картинок видео
 * 
 * не хера не собирается видео, хз что ему нужно 
 */
public class ImgToVideo {

    public static void main(String[] args) {
        //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        ImgToVideo run = new ImgToVideo();
        //run.saveImage(mat, "test.jpeg");
        run.writeVideo2();
    }
    
    
    private void writeVideo(){
        /*этот не хера не работает*/
        
       // Mat mat = Mat.eye(300, 300, CvType.CV_8UC1);
       // System.out.println("mat = " + mat.dump());
       // Size frameSize = mat.size();
        Size frameSize = new Size(100, 100);
        //Size frameSize = new Size((int) videoCapture.get(Videoio.CAP_PROP_FRAME_WIDTH), (int) videoCapture.get(Videoio.CAP_PROP_FRAME_HEIGHT));
        byte b0 = 'X';
        byte b1 = '2';
        byte b2 = '6';
        byte b3 = '4';
        //int fourcc = VideoWriter.fourcc(b0, b1, b2, b3); // какого то хера так только заработало
        
        int fourcc = VideoWriter.fourcc('X', '2', '6', '4'); // какого то хера так только заработало
        //int fourcc = VideoWriter.fourcc('M', 'J', 'P', 'G');
        //int fourcc = VideoWriter.fourcc('F','M','P','4');
//          int fourcc = VideoWriter.fourcc('D','I','V','X');

        VideoWriter writer = new VideoWriter("vid.avi", fourcc, 30 ,frameSize, true);
        
        String dirToIMG = "E:\\tmp\\unity\\LifeGame\\IMG";
        LinkedList<String> listIMGInDirectory = getListDirectory(dirToIMG);
        for (String s : listIMGInDirectory) {
           // MatOfByte frame = loadImage(dirToIMG + File.separator + s);
           // writer.write(frame);
        }     
        writer.release();
    }
   
    private void writeVideo2(){
        String dirToIMG = "E:\\tmp\\unity\\LifeGame\\IMG_not_true_virus_but_work";
        LinkedList<String> listIMGInDirectory = getListDirectory(dirToIMG);
        sortingListIMGNumber(listIMGInDirectory);
        IplImage img = null;
        int widthIMG = 0;
        int heightIMG = 0;
        if (listIMGInDirectory.size() > 0) {
            String s = listIMGInDirectory.get(0);
            img = cvLoadImage(dirToIMG + File.separator + s);
            widthIMG = img.width();
            heightIMG = img.height();
        } else {
            System.out.println("No image in folder " + dirToIMG);
            return;
        }

        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder("E:\\tmp\\unity\\LifeGame\\vid.avi", widthIMG, heightIMG);
        OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
        try {
            recorder.setVideoCodec(avcodec.AV_CODEC_ID_MPEG4);
            //recorder.setCodecID(avcodec.AV_CODEC_ID_H263);
            recorder.setFormat("mp4");
            recorder.setPixelFormat(avutil.AV_PIX_FMT_YUV420P);
            recorder.startUnsafe();

            for (int i = 0; i < listIMGInDirectory.size(); i++) {
                String s = listIMGInDirectory.get(i);
//                    if(i > 100){
//                        break;
//                    }
                System.out.println(dirToIMG + File.separator + s);
                img = cvLoadImage(dirToIMG + File.separator + s);
                Frame convertFrame1 = converter.convert(img);
                recorder.record(convertFrame1);
                //recorder.record(converter.convert(cvLoadImage(dirToIMG + File.separator + s))); // сжираем память и умираем
            }
            recorder.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void sortingListIMGNumber(LinkedList<String> listIMGInDirectory) {
        /*сортирует Лист по именам файлов как числа
         как добиться что бы не только LinkedList*/
        Collections.sort(listIMGInDirectory, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String separ = "\\.";
                String[] arro1 = o1.split(separ);
                String[] arro2 = o2.split(separ);
                int o1Int = 0;
                int o2Int = 0;
                try {
                    if (arro1.length > 0) {
                        o1Int = Integer.parseInt(arro1[0]);
                    }
                    if (arro2.length > 0) {
                        o2Int = Integer.parseInt(arro2[0]);
                    }
                } catch (NumberFormatException e) { // не смогли спарсить просто пропустим
                    return 0;
                }
                
                if (o1Int < o2Int) {
                    return -1;
                }
                if (o1Int > o2Int) {
                    return 1;
                }
                if (o1Int == o2Int) {
                    return 0;
                }
                return 0;
                //return Collator.getInstance().compare(o1, o2);
            }
        });
    }
    
    public static Mat loadImage(String imagePath) {
        Imgcodecs imageCodecs = new Imgcodecs();
        return imageCodecs.imread(imagePath);
    }

    public static void saveImage(Mat imageMatrix, String targetPath) {
        Imgcodecs imgcodecs = new Imgcodecs();
        imgcodecs.imwrite(targetPath, imageMatrix);
    }
    

    
    private LinkedList<String> getListDirectory(String path){
     LinkedList<String> listIMG = new LinkedList<>();
        File f = new File(path);
        if (f.isDirectory()) {
            String[] children = f.list();
            for (int i = 0; i < children.length; i++) {
                File interFile = new File(children[i]);
                if (interFile.isDirectory() == false) {
                    listIMG.add(children[i]);
                }
            }
        }
        return listIMG;
    }
}
