/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PrintingPrinter;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.metadata.IIOMetadata;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.ResolutionSyntax;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.Fidelity;
import javax.print.attribute.standard.Media;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.PrintQuality;
import javax.print.attribute.standard.PrinterResolution;

/**
 *
 * @author nazarov
 */
public class PrintA4 {
 
    public static void main(String[] args) throws IOException{
        PrintA4 _printA4 = new PrintA4();
        //_printA4.printBufferedImage(_printA4.openIMG("myimage_5.png"));
        BufferedImage originalImage = ImageIO.read(new File("myimage_5.png"));
        _printA4.printBufferedImage(originalImage);
    }
    
    public BufferedImage openIMG(String pathIMG) {
          BufferedImage originalImage = null;
        try {
            
            originalImage = ImageIO.read(new File(pathIMG));
            
            int afterResizeWIDTH = originalImage.getWidth(null) / 1; // пока уменьшим в два раза
            int afterResizeHEIGHT = originalImage.getHeight(null) / 1;
        
            Image image = originalImage.getScaledInstance(afterResizeWIDTH, afterResizeHEIGHT, Image.SCALE_SMOOTH);
            BufferedImage bi = new BufferedImage(afterResizeWIDTH, afterResizeHEIGHT, BufferedImage.TYPE_INT_ARGB);
            bi.getGraphics().drawImage(image, 0, 0, afterResizeWIDTH, afterResizeHEIGHT, null);
            
            return bi;
        } catch (IOException ex) {
            Logger.getLogger(PrintA4.class.getName()).log(Level.SEVERE, null, ex);
        }
        return originalImage;
    }
    
    
     //  ---  Тестовый принт ---
    public void printBufferedImage(BufferedImage image) {
        PrinterJob printJob = PrinterJob.getPrinterJob();
        
         PrintRequestAttributeSet attr = new HashPrintRequestAttributeSet(); // аттрибуты работают только хз как 
//      attr.add(new PrinterResolution(image.getWidth(), image.getHeight(), PrinterResolution.DPI));
        int width = Math.round(MediaSize.ISO.A4.getX(MediaSize.MM));
        int height = Math.round(MediaSize.ISO.A4.getY(MediaSize.MM));
        attr.add(new MediaPrintableArea(5, 0, width - 5, height - 5, MediaPrintableArea.MM));
        attr.add(MediaSizeName.ISO_A4);
        attr.add(OrientationRequested.LANDSCAPE);
        attr.add(PrintQuality.HIGH);
        
        //getListPrinterOnPC(printJob);        
        printJob.setPrintable(new Printable() {            
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {                            
                
                if (pageIndex != 0) {
                    return NO_SUCH_PAGE;
                }
//                System.out.println(pageFormat.getImageableWidth()); // ширина и высота a4 или что это?
//                System.out.println(pageFormat.getImageableHeight());
//                
//                System.out.println(image.getWidth());
//                System.out.println(image.getHeight());

                System.out.println(" width= " + graphics.getClipBounds().width + "height = " + graphics.getClipBounds().height);
                
                float deference = 1;
                if(image.getWidth() > image.getHeight()){
                    deference = (float)graphics.getClipBounds().width / (float) image.getWidth();
                }else{
                    deference = (float)graphics.getClipBounds().height / (float) image.getHeight();
                }                
                Graphics2D g = (Graphics2D) graphics;
                g.scale(deference, deference);
                
                
                graphics.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
                return PAGE_EXISTS;
            }
        });
             
       
        try {
            printJob.print(attr);
        } catch (PrinterException e1) {
            e1.printStackTrace();
        }
    }
    
    
    private void getListPrinterOnPC(PrinterJob pj){
        // Список принтеров
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        System.out.println("Number of printers configured: " + printServices.length);
        for (PrintService printer : printServices) {
            System.out.println("Printer: " + printer.getName());
            
            PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
            aset.add(MediaSizeName.NA_5X7); 
            Media[] printableArea = 
                    (Media[]) printer.getSupportedAttributeValues(MediaPrintableArea.class, null, null); // должен вывести площадь печати но чет ни как
            
            
            if (printer.getName().equals("Foxit Reader PDF Printer")) {
                try {
                    pj.setPrintService(printer); // Выбор печати на этот принтер
                } catch (PrinterException ex) {
                }
            }
        }
    }
}
