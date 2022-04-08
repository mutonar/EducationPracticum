/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PrintingPrinter;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import static javax.print.attribute.ResolutionSyntax.DPI;

/**
 *
 * @author nazarov
 * 
 * Тут прикручивается DPI нет части кода прикручивающего это к изображению
 */
public class PrinterImage2Ver {
 
    
    private void setDPI(IIOMetadata metadata) {
        double INCH_2_CM = 2.54;

        // for PMG, it's dots per millimeter
        double dotsPerMilli = 1.0 * DPI / 10 / INCH_2_CM;

        IIOMetadataNode horiz = new IIOMetadataNode("HorizontalPixelSize");
        horiz.setAttribute("value", Double.toString(dotsPerMilli));

        IIOMetadataNode vert = new IIOMetadataNode("VerticalPixelSize");
        vert.setAttribute("value", Double.toString(dotsPerMilli));

        IIOMetadataNode dim = new IIOMetadataNode("Dimension");
        dim.appendChild(horiz);
        dim.appendChild(vert);

        IIOMetadataNode root = new IIOMetadataNode("javax_imageio_1.0");
        root.appendChild(dim);

        try {
            metadata.mergeTree("javax_imageio_1.0", root);
        } catch (IIOInvalidTreeException ex) {
            Logger.getLogger(PrinterImage2Ver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
