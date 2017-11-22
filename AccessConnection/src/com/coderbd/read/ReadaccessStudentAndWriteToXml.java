package com.coderbd.read;

import com.coderbd.conn.AccessConnection;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Rajaul Islam
 */
public class ReadaccessStudentAndWriteToXml {
    public static void main(String[] args) {
        accessDBToXml();
    }
     public static void accessDBToXml() {
     

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element results = doc.createElement("testlist");
            doc.appendChild(results);
           

            Connection con = AccessConnection.getAccessConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * from urmi");

            ResultSetMetaData rsmd = rs.getMetaData();
            int colCount = rsmd.getColumnCount();

            while (rs.next()) {
                Element row = doc.createElement("testlist");
                results.appendChild(row);
                for (int i = 1; i <= colCount; i++) {
                    String columnName = rsmd.getColumnName(i);
                    Object value = rs.getObject(i);
                    Element node = doc.createElement(columnName);
                    node.appendChild(doc.createTextNode(value.toString()));
                    row.appendChild(node);
                }
            }

            //write xml
            TransformerFactory transformerFactory
                    = TransformerFactory.newInstance();
            Transformer transformer
                    = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result
                    = //  new StreamResult(new File("../AttnSolutions/xml/studentsAttendance.xml"));
                    new StreamResult(new FileOutputStream("testlist.xml"));
            transformer.transform(source, result);
            // Output to console for testing
            StreamResult consoleResult
                    = new StreamResult(System.out);
            transformer.transform(source, consoleResult);

            con.close();
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
