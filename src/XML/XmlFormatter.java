/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XML;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * Utility Class for formatting XML
 *
 * @author Pankaj
 *
 */
public class XmlFormatter {

    public String format(String input) {
        return prettyFormat(input, "3"); // числа это как глубоко будет отступ
    }

    // --- тут он должен отступы делать не делает -
    public static String prettyFormat(String input, String indent) {
        Source xmlInput = new StreamSource(new StringReader(input));
        StringWriter stringWriter = new StringWriter();
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", indent); // ИМЕННО ЭТот аттрибут за отступы влияет он Должен стоять первей чем Transformer
            
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes");
            transformer.setOutputProperty("{https://xml.apache.org/xslt}indent-amount", indent);
            transformer.transform(xmlInput, new StreamResult(stringWriter));

            return stringWriter.toString().trim();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // --- это из другого примера (работает )---
    //https://coderoad.ru/139076/%D0%9A%D0%B0%D0%BA-%D0%BA%D1%80%D0%B0%D1%81%D0%B8%D0%B2%D0%BE-%D0%BD%D0%B0%D0%BF%D0%B5%D1%87%D0%B0%D1%82%D0%B0%D1%82%D1%8C-XML-%D0%B8%D0%B7-Java
    public static String prettyFormat(String input, int indent) {
        try {
            Source xmlInput = new StreamSource(new StringReader(input));
            
            StringWriter stringWriter = new StringWriter();
            StreamResult xmlOutput = new StreamResult(stringWriter);
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", indent);
            
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(xmlInput, xmlOutput);
            
            return xmlOutput.getWriter().toString();
        } catch (Exception e) {
            throw new RuntimeException(e); // simple exception handling, please review it
        }
    }

    public static String prettyFormat(String input) {
        return prettyFormat(input, 2);
    }

    public static void main(String args[]) {
        XmlFormatter formatter = new XmlFormatter();
        String book = "<?xml version=\"1.0\"?><catalog><book id=\"bk101\"><author>Gambardella, Matthew</author><title>XML Developers Guide</title><genre>Computer</genre><price>44.95</price><publish_date>2000-10-01</publish_date><description>An in-depth look at creating applications with XML.</description></book><book id=\"bk102\"><author>Ralls, Kim</author><title>Midnight Rain</title><genre>Fantasy</genre><price>5.95</price><publish_date>2000-12-16</publish_date><description>A former architect battles corporate zombies, an evil sorceress, and her own childhood to become queen of the world.</description></book></catalog>";
        String book_1 = "<FB Name=\"GPATBaseAlarm_129\" Type=\"TBaseAlarm\" TypeUUID=\"M5I7H7I6EWWUHLAZF4QEN5YXQM\" UUID=\"FB50122ABED345B9B8D5F4EA18B171EE\" X=\"350\" Y=\"0\">\n" +
"<VarValue Type=\"STRING\" TypeUUID=\"38FDDE3B442D86554C56C884065F87B7\" Value=\"'Насос циркуляции масла редуктора неисправен'\" Variable=\"Name\"/>\n" +
"<VarValue Type=\"STRING\" TypeUUID=\"38FDDE3B442D86554C56C884065F87B7\" Value=\"'CNR_nRdy'\" Variable=\"NameAlg\"/>\n" +
"<VarValue Type=\"STRING\" TypeUUID=\"38FDDE3B442D86554C56C884065F87B7\" Value=\"'APS.'\" Variable=\"PrefStr\"/>\n" +
"<VarValue Type=\"STRING\" TypeUUID=\"38FDDE3B442D86554C56C884065F87B7\" Value=\"'APSCNR_nRdy'\" Variable=\"TagID\"/>\n" +
"<VarValue Type=\"DINT\" TypeUUID=\"\" Value=\"10700\" Variable=\"Category\"/>\n" +
"<VarValue Type=\"STRING\" TypeUUID=\"\" Value=\"'ВО'\" Variable=\"Source\"/>\n" +
"<VarValue Type=\"STRING\" TypeUUID=\"\" Value=\"'VOb'\" Variable=\"TAG\"/>\n" +
"</FB>";
        String book_2 = "<Data>    		             <CompositeFBType Name=\"T_GPA_Alarm_1\" UUID=\"SV63AUOIW7GE5G7C2XFE64DZ7A\">        <InterfaceList>            <InputVars>                <VarDeclaration InitialValue=\"''\" Name=\"NameRU\" Type=\"STRING\" UUID=\"2TRL7CWGTXZUXKQMBEX4OKO2S4\"/>                <VarDeclaration InitialValue=\"''\" Name=\"PrefAb\" Type=\"STRING\" UUID=\"OFVQBP34MUKUHHAUN7XQCUVIPY\"/>            </InputVars>        </InterfaceList>            <FBNetwork> <DataConnections/></FBNetwork></CompositeFBType></Data>";
        
        book_2 = book_2.replaceAll("\n", ""); // Удаляю символ новой строки
        System.out.println(formatter.format(book_2));
        System.out.println(formatter.prettyFormat(book_2)); // этот метод работает на отступы
    }
}
