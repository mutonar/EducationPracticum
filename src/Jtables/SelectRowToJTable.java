/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Jtables;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class SelectRowToJTable extends JFrame {

     public static void createGUI() {
          JFrame frame = new JFrame("Test frame");
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

          String[] columnNames = {
                    "Name",
                    "Last modified",
                    "Type",
                    "Size"
          };
          
          String[][] data = {
                    {"addins", "02.11.2006 19:15", "Folder", ""},
                    {"AppPatch", "03.10.2006 14:10", "Folder", ""},
                    {"assembly", "02.11.2006 14:20", "Folder", ""},
                    {"Boot", "13.10.2007 10:46", "Folder", ""},
                    {"Branding", "13.10.2007 12:10", "Folder", ""},
                    {"Cursors", "23.09.2006 16:34", "Folder", ""},
                    {"Debug", "07.12.2006 17:45", "Folder", ""},
                    {"Fonts", "03.10.2006 14:08", "Folder", ""},
                    {"Help", "08.11.2006 18:23", "Folder", ""},
                    {"Help", "08.11.2006 18:23", "Folder", ""},
                    {"Help", "08.11.2006 18:23", "Folder", ""},
                    {"Help", "08.11.2006 18:23", "Folder", ""},
                    {"Help", "08.11.2006 18:23", "Folder", ""},
                    {"Help", "08.11.2006 18:23", "Folder", ""},
                    {"Help", "08.11.2006 18:23", "Folder", ""},
                    {"Help", "08.11.2006 18:23", "Folder", ""},
                    {"Help", "08.11.2006 18:23", "Folder", ""},
                    {"Help", "08.11.2006 18:23", "Folder", ""},
                    {"Help", "08.11.2006 18:23", "Folder", ""},
                    {"Help", "08.11.2006 18:23", "Folder", ""},
                    {"Help", "08.11.2006 18:23", "Folder", ""},
                    {"Help", "08.11.2006 18:23", "Folder", ""},
                    {"Help", "08.11.2006 18:23", "Folder", ""},
                    {"Help", "08.11.2006 18:23", "Folder", ""},
                    {"Help", "08.11.2006 18:23", "Folder", ""},
                    {"Help", "08.11.2006 18:23", "Folder", ""},
                    {"Help", "08.11.2006 18:23", "Folder", ""},
                    {"Help", "08.11.2006 18:23", "Folder", ""},
                    {"Help", "08.11.2006 18:23", "Folder", ""},
                    {"Help", "08.11.2006 18:23", "Folder", ""},
                    {"Help", "08.11.2006 18:23", "Folder", ""},
                    {"Help", "08.11.2006 18:23", "Folder", ""},
                    {"Help", "08.11.2006 18:23", "Folder", ""},
                    {"Help", "08.11.2006 18:23", "Folder", ""},
                    {"Help", "08.11.2006 18:23", "Folder", ""},
                    {"Help", "08.11.2006 18:23", "Folder", ""},
                    {"Help", "08.11.2006 18:23", "Folder", ""}
          };
          
          JPanel mainPanel = new JPanel();
          mainPanel.setLayout(new BorderLayout());
          
          final JTable table = new JTable(new DefaultTableModel(data, columnNames));          
          JScrollPane scrollPane = new JScrollPane(table);
          mainPanel.add(scrollPane, BorderLayout.CENTER);
          
          JPanel bottomPanel = new JPanel();
          bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT));          
          
          JLabel selLabel = new JLabel("Selected:");
          bottomPanel.add(selLabel);
          
          final JLabel currentSelectionLabel = new JLabel("");
          currentSelectionLabel.setAutoscrolls(true);          
          bottomPanel.add(currentSelectionLabel);
          
          mainPanel.add(bottomPanel, BorderLayout.SOUTH);
          
          // прикручиваю кнопку 
          JButton addStr = new JButton("добавить строку");
          mainPanel.add(addStr, BorderLayout.SOUTH);
          addStr.addActionListener(new java.awt.event.ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  int row = table.getSelectedRow() + 1;
                  System.out.println(row);
                  DefaultTableModel tm = (DefaultTableModel) table.getModel();
                  tm.insertRow(row,  new String[]{"666", "08.11.2006 18:23", "Folder", ""});
              }

        });
        
          
//          ListSelectionModel selModel = table.getSelectionModel();
//          
//          selModel.addListSelectionListener(new ListSelectionListener() {               
//               public void valueChanged(ListSelectionEvent e) {
//                    String result = "";
//                    int[] selectedRows = table.getSelectedRows();
//                    for(int i = 0; i < selectedRows.length; i++) {
//                         int selIndex = selectedRows[i];
//                         TableModel model = table.getModel();
//                         Object value = model.getValueAt(selIndex, 0);
//                         result = result + value;
//                         if(i != selectedRows.length - 1) {
//                               result += ", ";
//                         }
//                    }
//                    currentSelectionLabel.setText(result);                    
//               }               
//          });
//          
           table.getSelectionModel().setSelectionInterval(15, 15); // выделить строку программно
           table.scrollRectToVisible(table.getCellRect(15,0, true));  // скролю на нужное


          
          frame.getContentPane().add(mainPanel);
          frame.setPreferredSize(new Dimension(550, 200));
          frame.pack();
          frame.setLocationRelativeTo(null);
          frame.setVisible(true);
     }
     
     

     public static void main(String[] args) {
          javax.swing.SwingUtilities.invokeLater(new Runnable() {
               public void run() {
                    JFrame.setDefaultLookAndFeelDecorated(true);
                    createGUI();
               }
          });
     }
}
