package com.coderbd.conn;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
 * @author Rajail Islam
 */
public class AccessConnection {

    public static void main(String[] args) {
     
            getAccessConnection();
       
    }

    public static void accessDBToXml() {
        String user = "";
        String pass = "";

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element results = doc.createElement("StudentAttendanceList");
            doc.appendChild(results);
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            //Connection con=DriverManager.getConnection("jdbc:ucanaccess://C://Users//sayonti//Documents/att2000.mdb",user,pass);

            Connection con = DriverManager.getConnection("jdbc:ucanaccess://E:\\git\\jdbc\\AccessConnection/att2000.mdb", user, pass);
            ResultSet rs = con.createStatement().executeQuery("SELECT  userinfo.badgenumber, checkinout.checktime, userinfo.instituteID FROM userinfo INNER JOIN checkinout"
                    + " ON userinfo.userid=checkinout.userid");

            ResultSetMetaData rsmd = rs.getMetaData();
            int colCount = rsmd.getColumnCount();

            while (rs.next()) {
                Element row = doc.createElement("StudentPunchdetails");
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
                    new StreamResult(new FileOutputStream("studentsAttendance.xml"));
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

    public static Connection getAccessConnection() {
        String user = "";
        String pass = "";
        Connection con = null;
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            con = DriverManager.getConnection("jdbc:ucanaccess://E:\\git\\jdbc\\AccessConnection/att2000.mdb", user, pass);
            System.out.println(":::Connected:::");
         }catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }
}
