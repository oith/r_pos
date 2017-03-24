package org.reflection.service.etc;

import org.reflection.model.sample.ZxEmp;
import org.reflection.service.ZxEmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service("xmlFileUploadService")
public class XmlFileUploadServiceImpl implements XmlFileUploadService {

    @Autowired
    private ZxEmpService zxEmpService;

    @Override
    public void upload(InputStream inputStream) {
        List<Map<String, Object>> datam = getData(inputStream);

        for (Map<String, Object> entry : datam) {

            String code = (String) entry.get("CODE");
            String fullName = (String) entry.get("FULL_NAME");
            Double salary = (Double) entry.get("SALARY");
            String email = (String) entry.get("EMAIL");
            Boolean isActive = (Boolean) entry.get("IS_ACTIVE");
            String remarks = (String) entry.get("REMARKS");

            ZxEmp c = new ZxEmp(code, fullName, salary, email, isActive, remarks);

            zxEmpService.create(c);
        }
    }

    @Override
    public List<Map<String, Object>> getData(InputStream inputStream) {
        try {
            //File fXmlFile = new File("D:\\r_pos\\pos_repo\\repositories\\uploads\\attn_dec16.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputStream);

            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("zxEmp");

            System.out.println("----------------------------");
            List<Map<String, Object>> listData = new ArrayList();
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    Map<String, Object> map = new LinkedHashMap();
                    map.put("CODE", eElement.getAttribute("CODE"));
                    map.put("FULL_NAME", eElement.getElementsByTagName("FULL_NAME").item(0).getTextContent());
                    map.put("SALARY", Double.parseDouble(eElement.getElementsByTagName("SALARY").item(0).getTextContent()));
                    map.put("EMAIL", eElement.getElementsByTagName("EMAIL").item(0).getTextContent());
                    map.put("IS_ACTIVE", Boolean.parseBoolean(eElement.getElementsByTagName("IS_ACTIVE").item(0).getTextContent()));
                    map.put("REMARKS", eElement.getElementsByTagName("REMARKS").item(0).getTextContent());

                    System.out.println("map: " + map);

                    listData.add(map);
                }
            }

            return listData;
        } catch (Exception e) {
            System.out.println("err vi parlam na: " + e);
            return null;
        }

    }
}
