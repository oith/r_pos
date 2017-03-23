package mactest;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ReadXMLFile {

    public static void main(String argv[]) {

        try {

            File fXmlFile = new File("D:\\r_pos\\pos_repo\\repositories\\uploads\\attn_dec16.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("attnSummary");

            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    System.out.println("employeeCode         : " + eElement.getAttribute("employeeCode"));
                    System.out.println("fromDate             : " + eElement.getElementsByTagName("fromDate").item(0).getTextContent());
                    System.out.println("toDate               : " + eElement.getElementsByTagName("toDate").item(0).getTextContent());
                    System.out.println("daysPresent          : " + eElement.getElementsByTagName("daysPresent").item(0).getTextContent());
                    System.out.println("daysWeeklyOffHolidays: " + eElement.getElementsByTagName("daysWeeklyOffHolidays").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
