/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsingHtmlJsoup;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.MimeUtility;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author nazarov
 *
 * Парсинг html библиотекой jsoup проблема в том что у нас не html
 * https://stackoverflow.com/questions/3230305/how-to-read-or-parse-mhtml-mht-files-in-java
 */
public class ParsingFile {

    private static final String USED_CHARSET = "windows-1251";

    public static void main(String[] args) throws Exception {
        ParsingFile run = new ParsingFile();
        String filePathTrnsform = run.getTableFromMHTMTL("C:\\Users\\Nazarov\\Documents\\NetBeansProjects\\JavaApplication5\\data_23.09.2021\\Архив событий.mht");
        run.parsTableFileHtml(filePathTrnsform);
    }

    private LinkedList<String[]> parsTableFileHtml(String str) {
        LinkedList<String[]>  parceData = new LinkedList<>();
        File file = new File(str);
        try {
            Document document = Jsoup.parse(file, USED_CHARSET, "test.ru");
            Elements P = document.getElementsByTag("table");
            Elements rows = document.getElementsByTag("TR");
            for (Element e : rows) {
                Elements columns = e.getElementsByTag("TD");
                
                if(columns.size() <= 1  ){
                    continue;
                }
                String[] dataRows = new String[columns.size()];
                
                for (int i = 0; i < columns.size(); i++) {
                    Element c = columns.get(i);
                    dataRows[i] = decoderString(c.text());
                }
                parceData.add(dataRows);
            }
        } catch (IOException ex) {
            Logger.getLogger(ParsingFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return parceData;
    }

    private String decoderString(String original){
        String encoded = null;
        String result = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {            
            ByteArrayInputStream byteOutStream = new ByteArrayInputStream(original.getBytes());
            InputStream decode = MimeUtility.decode(byteOutStream, "quoted-printable");
            Scanner s = new Scanner(decode, "Cp1251").useDelimiter("\\A");
            result = s.hasNext() ? s.next() : "";            
        } catch (MessagingException ex) {
            Logger.getLogger(ParsingFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }

    private String encoderString(String original){
        /*Странно кодирует что обратно не могу преобразовать русский английский может( вообще не кодирует)*/
        String encoded = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            OutputStream encodedOut = MimeUtility.encode(baos, "quoted-printable"); // на оборот это шифрование
            baos.flush();
            encoded = new String(baos.toByteArray(), StandardCharsets.UTF_8); // for UTF-8 encoding
            encodedOut.write(original.getBytes());
            encoded = baos.toString();

        } catch (MessagingException ex) {
            Logger.getLogger(ParsingFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ParsingFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return encoded;
    }
    
    private String getTableFromMHTMTL(String pathFile) {
        /*выборка таблиицы из MHTMTL
        так как парсер Jsoup не понимает формат данных*/
        String identData = "TBODY";
        String startData = "<" + identData + ">";
        String endData = "</" +  identData + ">";
        String nameOutFile = pathFile + "-Table.html";
        InputStreamReader inputsream = null;
        FileWriter fileWriter = null;
        File fileTransform = new File(nameOutFile);
        
        if(fileTransform.exists()){
            fileTransform.delete();
        }
        try {
            //inputsream = new InputStreamReader(new FileInputStream(pathFile), "quoted-printable");
            fileWriter = new FileWriter(fileTransform, true);
            inputsream = new InputStreamReader(new FileInputStream(pathFile), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputsream);
            String line = bufferedReader.readLine();
            boolean findData = false;
            while (line != null) {
                int findIndexEndata = line.indexOf(endData);
                if (findIndexEndata > -1) {
                    findData = false;
                    fileWriter.write(line.substring(0, findIndexEndata) + "</table>" + "\n");
                    fileWriter.write("</BODY>" + "\n");
                }
                
                if(findData){
                    fileWriter.write(line + "\n");
                }
                
                int findIndexStartData = line.indexOf(startData);
                if (findIndexStartData > -1) {
                    findData = true;
                     fileWriter.write("<HTML>\n"
                            + "<HEAD>\n"
                            + "</HEAD>\n"
                            + "<BODY>" + "\n");
                    fileWriter.write("<table>" + line.substring(findIndexStartData + startData.length(), line.length()) + "\n");
                }
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            fileWriter.close();
        
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ParsingFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ParsingFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ParsingFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inputsream.close();
            } catch (IOException ex) {
                Logger.getLogger(ParsingFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return nameOutFile;
    }

    private void viewNodesElement(Elements links) {
        for (Element link : links) {
            String nodeName = link.nodeName();
            //System.out.println(nodeName);
            System.out.println("\t " + nodeName + " childs --> ");
            for (Element el : link.getAllElements()) {
                String childName = el.nodeName();
                System.out.println(childName);
                System.out.println(el.text());
                if (childName.indexOf("TABLE=20") > -1) {
                    for (Attribute a : el.attributes()) { // пройти по аттрибутам
                        System.out.println(a.getKey());
                        System.out.println(a.getValue());
                    }

                    Elements rows = el.select("b");
                    for (Element el20 : rows) {
                        for (Element eb : el20.getAllElements()) {
                            System.out.println(eb.nodeName());
                            System.out.println(eb.text());
                        }
                    }
                    int sizeT = rows.size();
                    //viewNodesElement(table20);
                }
            }
        }

    }

    private void getChildNode(Element node) {
        Elements links = node.getAllElements();
        String rootNameNode = node.nodeName();
        System.out.println("\t " + rootNameNode + " Her childs --> ");

        for (Element link : links) {
            String nodeName = link.nodeName();
            System.out.println(nodeName);
        }

        for (Element link : links) {
            String nodeName = link.nodeName();
            if (nodeName.equals(rootNameNode) == false) {
                getChildNode(link);
            }
        }
    }
}
