package org.reflection.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Controller
@RequestMapping(value = "/queryCenter")
public class _QueryCenterController {

    @Autowired
    private EntityManagerFactory entityManagerFactory;
    @Autowired
    private DataSource dataSource;

    @GetMapping("")
    public String getQueryRunnerPage() {
        return "etc/queryCenter";
    }

    @GetMapping("/execPureNativeQuery")
    public
    @ResponseBody
    String execPureNativeQuery(
            @RequestParam(value = "query") String query,
            @RequestParam(value = "returnRecs") String returnRecs
    ) {
        System.out.println("finding execPureNativeQuery: query: " + query + " returnRecs: " + returnRecs);

        try {
            Connection conn = dataSource.getConnection();
            return "Pure Native result: " + getTableOnly(conn, query, Integer.parseInt(returnRecs));
        } catch (Exception e) {
            return "err: " + e;
        }
    }

    @GetMapping("/execNativeQuery")
    public
    @ResponseBody
    String execNativeQuery(
            @RequestParam(value = "query") String query,
            @RequestParam(value = "returnRecs") String returnRecs
    ) {
        System.out.println("finding execNativeQuery: query: " + query + " returnRecs: " + returnRecs);

        try {
            EntityManager conn = entityManagerFactory.createEntityManager();
            List gg = conn.createNativeQuery(query).setMaxResults(Integer.parseInt(returnRecs)).getResultList();

//            Connection conn = (Connection) entityManagerFactory.createEntityManager().getDelegate();//dataSource.getConnection();
            //return "Native result: " + yu;//getTableOnly(conn, query);
            return "Native result: " + getTableOnly(gg);
        } catch (Exception e) {
            return "err: " + e;
        }
    }

    @GetMapping("/execOrmQuery")
    public
    @ResponseBody
    String execOrmQuery(
            @RequestParam(value = "query") String query,
            @RequestParam(value = "returnRecs") String returnRecs
    ) {

        System.out.println("finding execOrmQuery: query: " + query + " returnRecs: " + returnRecs);

        try {
            EntityManager em = entityManagerFactory.createEntityManager();//openSession();
            javax.persistence.Query query1 = em.createQuery(query);
            List gg = query1.setMaxResults(Integer.parseInt(returnRecs)).getResultList();
            em.close();
            return "ORM result: " + gg.size() + "" + getTableOnly(gg);
        } catch (Exception e) {
            return "err: " + e;
        }
    }

    public String getTableOnly(List query) {
        String tableHead = new String();
        String tableRow = new String();

//        int numberOfColumns = metaData.getColumnCount();
//
//        for (int column = 1; column <= numberOfColumns; column++) {
//            String tableHeadx = metaData.getColumnLabel(column);
//            tableHeadx = tableHeadx.toUpperCase();
//            tableHead += "<th>" + tableHeadx + "</th>";
//        }
        for (Object object : query) {
            tableRow += "<tr>";
            if (object instanceof Object[]) {
                Object[] mmm = (Object[]) object;

                for (Object object1 : mmm) {
                    tableRow += "<td>" + object1 + "</td>";
                }
            } else {
                tableRow += "<td>" + object + "</td>";
            }
            tableRow += "</tr>";
        }
        //return "<table class=\"style-table\">" + tableHead + tableRow + "</table>";
        return "<table width=\"100%\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\" class=\"style-table\">" + tableHead + tableRow + "</table>";
    }

    public String getTableOnly(Connection conn, String query, int returnRecs) {
        String tableHead = new String();
        String tableRow = new String();

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();

            for (int column = 1; column <= numberOfColumns; column++) {
                String tableHeadx = metaData.getColumnLabel(column);
                tableHeadx = tableHeadx.toUpperCase();
                tableHead += "<th>" + tableHeadx + "</th>";
            }

            int cnt = 1;
            while (resultSet.next()) {
                tableRow += "<tr>";
                for (int i = 1; i <= numberOfColumns; i++) {
                    tableRow += "<td>" + resultSet.getObject(i) + "</td>";
                }
                tableRow += "</tr>";

                if (cnt == returnRecs) {
                    break;
                }
                cnt++;
            }

            resultSet.close();
            statement.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        //return "<table width=\"100%\" border=\"2\" cellspacing=\"0\" cellpadding=\"0\" class=\"style-table\">" + tableHead + tableRow + "</table>";
        return "<table class=\"style-table\">" + tableHead + tableRow + "</table>";
    }
}
