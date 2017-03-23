package org.reflection.service.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.reflection.model.com.AdmProcess;
import org.reflection.model.com.AdmProcessDetail;
import org.reflection.model.com.enums.WidgetType;
import org.reflection.model.com.enums.ZoneType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class AdmUtil {

    protected static final String SELECTED = "SELECTED";

    private static EntityManagerFactory emf;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public AdmUtil() {
    }

    public static List<Map<String, String>> getListFromDB(final String query) {
        EntityManager em = emf.createEntityManager();
        List<Map<String, String>> ret = new ArrayList<>();
        try {

            List koto = em.createQuery(query).getResultList();
            if (!koto.isEmpty()) {
                if (koto.get(0) instanceof Object[]) {

                    for (Object object : koto) {
                        Object[] objects = (Object[]) object;
                        Map<String, String> mm = new LinkedHashMap();
                        mm.put("id", objects[0] + "");
                        mm.put("show", objects[1] + "");
                        ret.add(mm);
                    }

                } else {
                }
            }
        } catch (Exception e) {
            System.out.println("macsay: err db menu drop: " + e);
        } finally {

            em.close();

        }
        return ret;
    }

    public static String getSingleValFromDB(final String coll) {
        EntityManager em = emf.createEntityManager();
        Object kk = "";
        try {
            Query query = em.createQuery(coll);
            kk = query.getSingleResult();
            System.out.println("ywwwwww" + kk);
        } catch (Exception e) {
            System.out.println("er def val: " + e);
        } finally {
            em.close();
        }
        return kk.toString();
    }

    protected static List<Map<String, String>> cmdHandller(String cmd) {
        List<Map<String, String>> strdef = new ArrayList();
        if (cmd != null && !(cmd = cmd.trim()).isEmpty()) {

            if ((cmd.toLowerCase().startsWith("select"))) {
                strdef = getListFromDB(cmd);
            } else if ((cmd.startsWith("{") && cmd.endsWith("}"))) {
                ObjectMapper mapper = new ObjectMapper();
                try {
                    strdef = mapper.readValue(cmd, new TypeReference<List<Map<String, String>>>() {
                    });
                } catch (Exception e) {
                }
            } else if ((cmd.startsWith("[") && cmd.endsWith("]"))) {

                ObjectMapper mapper = new ObjectMapper();
                try {
                    strdef = mapper.readValue(cmd, new TypeReference<List<String>>() {
                    });
                } catch (Exception e) {
                }

            } else if ((cmd.contains(","))) {
                String[] lstFixed = cmd.split(",");

                for (String xxx : lstFixed) {
                    Map<String, String> dd = new LinkedHashMap();

                    if (xxx.contains("~")) {
                        String[] tld = xxx.split("~");

                        dd.put("id", tld[0]);
                        dd.put("show", tld[1]);

                    } else {
                        dd.put("id", xxx);
                        dd.put("show", xxx);
                    }
                    strdef.add(dd);
                }
            }
        }
        return strdef;
    }

    protected static String defaultValueHandller(String defaultValue) {
        String strdef = "";
        if (defaultValue != null && !(defaultValue = defaultValue.trim()).isEmpty()) {

            if ((defaultValue.toLowerCase().startsWith("select"))) {
                strdef = getSingleValFromDB(defaultValue);
            } else {
                strdef = defaultValue;
            }
        }
        return strdef;
    }

    public static Map<String, String> getTableOnly(final AdmProcess admProcess, final Map<String, Object> objMap) { //, String currSprId, String empId, String deptId) {

        Map<String, String> tblMap = new HashMap();
        String tableHead = new String();
        String tableRow = new String();
        String queryCustomized = null;
        String queryAlias = null;

        List<String> invisColParm = new ArrayList();
        List<String> inputColParm = new ArrayList();
        List<String> colParma = new ArrayList();

        try {
            queryCustomized = admProcess.getQuery();
            queryAlias = admProcess.getQueryAlias();
            for (AdmProcessDetail resultSet : admProcess.getAdmProcessDetails()) {

                if (resultSet.getZoneType() == ZoneType.PARAM_QU) {
                    continue;
                }
                String widgetIdentity = resultSet.getAdmParam().getCode();
                WidgetType widgetType = resultSet.getAdmParam().getWidgetType();

                colParma.add(widgetIdentity);

                if (widgetType.equals(WidgetType.HIDE)) {
                    invisColParm.add(widgetIdentity);
                } else if (widgetType.equals(WidgetType.INPUT)) {
                    inputColParm.add(widgetIdentity);
                }
            }
        } catch (Exception e) {
            System.out.println("MacDynamo Err 0149:" + invisColParm);
        }

        for (String jkjk : objMap.keySet()) {

            Object vvv = objMap.get(jkjk);
            String orpn = "null";
            if (vvv != null) {

                if (vvv instanceof String) {
                    if (vvv.toString().trim().length() == 0) {
                        orpn = "null";
                    } else {
                        orpn = "'" + vvv + "'";
                    }
                } else if (vvv instanceof Date) {
                    // System.out.println("rrrrrrrrrrrrrrrr vvv"+((Date) vvv));
                    SimpleDateFormat klkl = new SimpleDateFormat("dd/MM/yyyy");
                    orpn = "TO_DATE('" + klkl.format((Date) vvv) + "','DDMMYYYY')";
                } else if (vvv instanceof Number) {
                    orpn = vvv + "";
                }
            }
            queryCustomized = queryCustomized.replaceAll(":" + jkjk, orpn);

        }
        //queryCustomized = queryCustomized.replaceAll(":P_CURR_USER_ID", userId.toString());

        tableHead += "<th class='center bold'>Select All<br><input type='checkbox' id='checkAll'/></th>";

        SortedSet<String> colAll = new TreeSet<>();
        Map<String, Integer> mapColIndx = new LinkedHashMap<>();

        if (queryAlias != null) {
            int cntt = 0;
            for (String colxd : queryAlias.split(",")) {
                colAll.add(colxd);
                mapColIndx.put(colxd, cntt);
                cntt++;
            }
        }

        for (String colx : colAll) {
            if (invisColParm.contains(colx)) {
                continue;
            }
            tableHead += "<th class='center bold'>" + colx + "</th>";
        }

        int j = 1;

        EntityManager em = emf.createEntityManager();
        Query jjkk = em.createQuery(queryCustomized);
        for (Object colxd : jjkk.getResultList()) {

            Object[] colx = (Object[]) colxd;

            tableRow
                    += "<tr>"
                    + "   <td class='center'>"
                    + "       <input type='checkbox' name='row." + j + "' class='checkBoxTouchAll' value='row." + j + "'/>"
                    + "   </td>";
            for (String colname : colAll) {

                int index = mapColIndx.get(colname);

                String isHedn = "";
                String hdnOrtxt = "text";
                if (invisColParm.contains(colname)) {
                    isHedn = "style='display:none'";
                    hdnOrtxt = "hidden";
                }

                String ident = "";
                String valx = null;
                if (colParma.contains(colname)) {
                    ident = "id='" + colname + j + "'";
                    valx = " value='" + colx[index] + "'";
                }

                if (inputColParm.contains(colname)) {
                    tableRow += "<td " + isHedn + ">" + "<input " + ident + valx + " type='" + hdnOrtxt + "'></input></td>";
                } else if (valx != null) {
                    tableRow += "<td style=' display:none'>" + "<input " + ident + valx + " type='hidden'></input></td>";
                } else if (colname.toUpperCase().equals("URL")) {
                    String dd = "/" + colx[index];
                    String urlLink = "<a href='${request.contextPath}" + dd + "' target='_blank'>" + "Show Details" + "</a>";
                    tableRow += "<td " + isHedn + " " + ident + " >" + urlLink + "</td>";
                } else {
                    Object tdValue = colx[index];
                    if (tdValue == null) {
                        tdValue = "";
                    }
                    tableRow += "<td " + isHedn + " " + ident + " >" + tdValue + "</td>";
                }
            }
            tableRow += "</tr>";

            j++;
        }
        em.close();
        String dataTable = "<table id='procTable' class='dataListTable table-bordered table-striped table-hover' style='width:50%'> " + tableHead + tableRow + "</table>";

        tblMap.put("TOTAL_RECORD", Integer.toString(j - 1));
        tblMap.put("DATA_TABLE", dataTable);

        return tblMap;
    }

    public static String getInitCap(final String dfg) {
        String sss = "";
        String strUpper = dfg.toUpperCase();
        if (strUpper.contains("DOB") || strUpper.contains("DOJ")) {
            return strUpper;
        }

        if (dfg.contains("_")) {
            String hhhh[] = dfg.split("_");

            for (String jjj : hhhh) {
                jjj = jjj.trim();
                sss += getInitCapWhile(jjj) + " ";
            }
        } else {
            return getInitCapWhile(dfg);
        }

        sss = sss.trim();
        return sss;
    }

    public static String getInitCapWhile(final String dfg) {
        if (dfg.length() >= 2) {
            return Character.toUpperCase(dfg.charAt(0)) + dfg.substring(1).toLowerCase();
        }
        return dfg;
    }

    @PostConstruct
    public void initStaticDao() {
        emf = this.entityManagerFactory;
    }
}
