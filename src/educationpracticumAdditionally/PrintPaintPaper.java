/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package educationpracticumAdditionally;

/**
 *
 * @author nazarov
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.print.PageFormat;
import static java.awt.print.PageFormat.LANDSCAPE;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.Size2DSyntax;
import javax.print.attribute.standard.Chromaticity;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;

// --- Класс печати на принтер с преобразованием изображения ---
// какой то косяк с размером и качеством =(
public class PrintPaintPaper {

    public static void main(String[] args) throws Exception {
        DocFlavor flavor = DocFlavor.INPUT_STREAM.JPEG;
        //PrintService printService = PrintServiceLookup.lookupDefaultPrintService();

        // так было до этого
        /*
         PrinterJob pj = PrinterJob.getPrinterJob();
         PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
         aset.add(Chromaticity.MONOCHROME);
         MediaSize isoA4Size = MediaSize.getMediaSizeForName(MediaSizeName.ISO_A4);
         float[] size = isoA4Size.getSize(Size2DSyntax.INCH);

       
         PageFormat pf = pj.defaultPage();
         Paper paper = new Paper();
         double margin = 36; // half inch
         //paper.setImageableArea(margin, margin, paper.getWidth() - margin * 2, paper.getHeight() - margin * 2);
         paper.setSize(size[0] * 72.0, size[1] * 72.0);
         paper.setImageableArea(0.0, 0.0, size[0] * 72.0, size[1] * 72.0);
         pf.setPaper(paper);
         */
//        PrinterJob pj = PrinterJob.getPrinterJob();
//        PageFormat pf = pj.pageDialog(pj.defaultPage());
//        
//        pj.setPrintable(new MyPrintable(), pf);
//        if (pj.printDialog()) {
//            try {
//                pj.print();
//            } catch (PrinterException e) {
//                System.out.println(e);
//            }
//        }
        BufferedImage img = ImageIO.read(new File("myimage1.png"));
        analizeImg(img);
//        printImg(img);//вызов тестового метода с изображение
//          BufferedImage img = ImageIO.read(new File("myimage.png"));
//          ImageIO.write(scaleIMG(img), "png", new File("myimage_2.png")); // Да файл читает
        // writeIMG();
    }

    
    // --- анализ изображения(мешаю цвета и размер) ---
    private static void analizeImg(BufferedImage image) {
        System.out.println("orig_size: " + image.getWidth() + "_" + image.getHeight() + "half_size: " + image.getWidth() / 2 + "_" + image.getHeight() / 2);
        BufferedImage buffImage = new BufferedImage(image.getWidth() / 2, image.getHeight() / 2, BufferedImage.TYPE_INT_RGB);
        final int w = image.getWidth();
        final int h = image.getHeight();
        // в 4 раза уменьшает а не в 2
        for (int y = 0; y < h; ++y) {
            if (y % 2 != 0) continue;
            if (y > h/ 2) break;
            for (int x = 0; x < w; ++x) {
                if (x % 2 != 0) continue;
                if (x > w / 2) break;
                // сокращаем в два раза
                int c = image.getRGB(x, y);
                int red = (c & 0x00ff0000) >> 16;
                int green = (c & 0x0000ff00) >> 8;
                int blue = c & 0x000000ff;
                Color color = null;
                    //Color color = new Color(red, green, blue); // оригинальный цвет
                //Color color = new Color(green, red, blue);
                //Color color = new Color(blue, green, red);
                color = new Color(red, blue, green);
                    //System.out.println(color);
                if(x == 0 & y ==0){
                    buffImage.setRGB(x, y, color.getRGB());
                }else{
                if(x == 0){
                  buffImage.setRGB(x, y / 2, color.getRGB());
                }
                if(y == 0){
                  buffImage.setRGB(x / 2, y, color.getRGB());
                }
                if(x != 0  & y != 0){
                  buffImage.setRGB(x / 2, y / 2, color.getRGB());
                }
                
                }
            }
        }
        try {
            ImageIO.write(buffImage, "png", new File("myimage_6.png"));
        } catch (IOException ex) {
            Logger.getLogger(PrintPaintPaper.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //  ---  Тестовый принт (не дает выбрать принтер)---
    private static void printImg(BufferedImage image) {
        PrinterJob printJob = PrinterJob.getPrinterJob();
        printJob.setPrintable(new Printable() {
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                if (pageIndex != 0) {
                    return NO_SUCH_PAGE;
                }
                System.out.println(pageFormat.getImageableWidth()); // ширина и высота a4
                System.out.println(pageFormat.getImageableHeight());

                graphics.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
                return PAGE_EXISTS;
            }
        });
        try {
            printJob.print();
        } catch (PrinterException e1) {
            e1.printStackTrace();
        }
    }

    // --- маштабирование изображения (тоже нечего не видно)---
    public static BufferedImage scaleIMG(BufferedImage image) {
        final int w = image.getWidth();
        final int h = image.getHeight();
        BufferedImage scaledImage = new BufferedImage((w / 10), (h / 10), BufferedImage.TYPE_INT_ARGB);
        final AffineTransform at = AffineTransform.getScaleInstance(0.1, 0.1);
        final AffineTransformOp ato = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
        scaledImage = ato.filter(image, scaledImage);
        return scaledImage;
    }

    //  --- пишим данные изображение в файл ---
    public static void writeIMG() throws IOException {

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("myimage.png"));
        } catch (IOException e) {
        }
        //Graphics2D g2 = (Graphics2D) img.createGraphics();
        BufferedImage buffImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) buffImage.createGraphics();
        g2.drawImage(buffImage, null, 0, 0); // поместить изображение в g2 ?
        g2.setFont(new Font("Serif", Font.PLAIN, 42));
        g2.setPaint(Color.GREEN);
        g2.drawString("www.java2s.com..........................................................................................................................................", 100, 100);

        Image tmp = img.getScaledInstance(img.getWidth() / 2, img.getHeight() / 2, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(img.getWidth() / 2, img.getHeight() / 2, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        int imgWidth = img.getWidth();
        int imgHeight = img.getHeight();

        double zoom = 20;
        double width = img.getWidth();
        double height = img.getHeight();

        double zoomWidth = width * zoom;
        double zoomHeight = height * zoom;

        double anchorx = (width - zoomWidth) / 2;
        double anchory = (height - zoomHeight) / 2;

        AffineTransform at = new AffineTransform();
        at.translate(anchorx, anchory);
        at.scale(zoom, zoom);
        at.translate(-100, -100);

        g2.setTransform(at);
        // g2.drawImage(img, 0, 0, this);

        // в файлы так сохраняет но не печатает
        File file = new File("test.png");
        ImageIO.write(dimg, "png", file);

        g2.dispose();

    }
}

class MyPrintable implements Printable {

    public int print(Graphics g, PageFormat pf, int pageIndex) {
        try {
            if (pageIndex != 0) {
                return NO_SUCH_PAGE;
            }
//            
//            // Graphics2D g2 = (Graphics2D) g;
            int sizeX = (int) pf.getImageableWidth(); // ширина и высота a4
            int sizeY = (int) pf.getImageableHeight();

            /* Paper p1 = new Paper();
             p1.setImageableArea(0, 0, 419, 1000); 
             pf.setPaper(p1);
             */
            System.out.println(sizeX + " : " + sizeY);
//
//            BufferedImage buffImage = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
//            //g2.drawImage(buffImage, null, 0, 0); // поместить изображение в g2 ?
//            Graphics2D g2 = (Graphics2D) buffImage.createGraphics();
//
//            AffineTransform oldAT = g2.getTransform(); // какие то размеры(сохраняем их)
//            g2.scale(3.0, 4.0); // изменяем шкалу
//            g2.translate(10.0, 20.0);
//
//            g2.setFont(new Font("Serif", Font.PLAIN, 42));
//            g2.setPaint(Color.GREEN);
//            g2.drawString("www.java2s.com..........................................................................................................................................", 100, 100);
//            Rectangle2D outline = new Rectangle2D.Double(pf.getImageableX(), pf.getImageableY(), pf
//                    .getImageableWidth(), pf.getImageableHeight());
//            g2.draw(outline);
//            g2.dispose();
//
//            // в файлы так сохраняет но не печатает
//            File file = new File("myimage.png");
//            ImageIO.write(buffImage, "png", file);
//
//            g2.setTransform(oldAT); // возращаем размер
//            // Save as JPEG
//            file = new File("myimage.jpg");
//            ImageIO.write(buffImage, "jpg", file);
//                  

            // занимаюсь извратом с закачиваю изображение обратно
            BufferedImage img = null;
            try {
                img = ImageIO.read(new File("myimage.png"));
                //ImageIO.write(img, "png",  new File("myimage_1.png")); // Да файл читает
            } catch (IOException e) {
            }
            int imgWidth = img.getWidth();
            int imgHeight = img.getHeight();
            System.out.println("Before " + imgWidth + " : " + imgHeight);
            //pf.setOrientation(LANDSCAPE); // по горизонту (тут тупняк и не работает)

            if (imgWidth > imgHeight) { // если ширина больше высоты то поворачиваем
                img = rotateImageByDegrees(img, 90);
                ImageIO.write(img, "png", new File("myimage_1.png")); // Да файл читает
            }
            imgWidth = img.getWidth(); // тут после поворота новые данные
            imgHeight = img.getHeight();
            System.out.println("After " + imgWidth + " : " + imgHeight);

            // приводим к соотношению сторон изображения и формата A4
//            int scaleIMG = imgHeight / sizeY ;// коэффициент  разности A4 и IMG(пока не использую лажа какая то)
//                System.out.println("scaleIMG: " + scaleIMG);
//            img = BufferedImage(img, imgWidth / scaleIMG  , sizeY); // преобразовываю размер(так впишем изображение в формат a4)
            //img = BufferedImage(img, img.getWidth() / 2, img.getHeight() / 2); // преобразовываю размер(так впишем изображение в формат a4)
            ImageIO.write(img, "png", new File("myimage_2.png")); // Да файл читает

            Graphics2D g2 = (Graphics2D) g;// теперь опять переприсваиваю
            g2.drawImage(img, null, 0, 0);  // прикручиваю изображение так не работает
            g2.scale(0.2, 0.2);
            g2.dispose();
            ImageIO.write(img, "png", new File("myimage_2.png")); // Да файл читает

            return PAGE_EXISTS;
        } catch (IOException ex) {
            Logger.getLogger(MyPrintable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return PAGE_EXISTS;
    }

    // --- Метод преобразовывания изображения ( размер , вырезать и добавить данные)---
    private BufferedImage BufferedImage(BufferedImage img, int newW, int newH) {

        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    // --- фукция поворота изображения на угол ---
    public BufferedImage rotateImageByDegrees(BufferedImage img, double angle) {

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
        g2d.setColor(Color.RED);
        g2d.drawRect(0, 0, newWidth - 1, newHeight - 1);
        g2d.dispose();

        return rotated;
    }
}
