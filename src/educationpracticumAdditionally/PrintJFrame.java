/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package educationpracticumAdditionally;

import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import javax.swing.*;

// печать на принтере что есть на панели

/** @see http://stackoverflow.com/questions/8192204 */
public class PrintJFrame extends JPanel implements Printable {

    public PrintJFrame() {
        this.setLayout(new GridLayout());
        JTree tree = new JTree();
        this.add(new JScrollPane(tree));
        for (int i = 0; i < tree.getRowCount(); i++) {
            tree.expandRow(i);
        }
    }

    @Override
    public int print(Graphics g, PageFormat pf, int i) throws PrinterException {
        if (i > 0) {
            return NO_SUCH_PAGE;
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        PrintJFrame.this.printAll(g2d);
        return Printable.PAGE_EXISTS;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                JFrame f = new JFrame();
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                final PrintJFrame pt = new PrintJFrame();
                f.add(pt, BorderLayout.CENTER);
                JButton b = new JButton(new AbstractAction("Print") {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        PrinterJob pj = PrinterJob.getPrinterJob();
                        PageFormat pf = pj.pageDialog(pj.defaultPage());
                        pj.setPrintable(pt, pf);
                        if (pj.printDialog()) {
                            try {
                                pj.print();
                            } catch (PrinterException pe) {
                                pe.printStackTrace(System.err);
                            }
                        }
                    }
                });
                JPanel p = new JPanel();
                p.add(b);
                f.add(p, BorderLayout.SOUTH);
                f.pack();
                f.setVisible(true);
            }
        });
    }
}