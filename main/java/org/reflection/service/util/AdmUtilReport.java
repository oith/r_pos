package org.reflection.service.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.reflection.model.com.AdmParam;
import org.reflection.model.com.AdmReport;
import org.reflection.model.com.AdmReportDetail;
import org.reflection.model.com.enums.ReportFormat;
import org.reflection.model.com.enums.WidgetType;
import org.reflection.model.com.enums.ZoneType;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public final class AdmUtilReport extends AdmUtil {

    public AdmUtilReport() {
    }

    public static Map<String, String> getReportPageMap(final AdmReport admReport) {

        Map<String, String> objMap = new HashMap();

        for (AdmReportDetail admReportDetail : admReport.getAdmReportDetails()) {

            if (!admReportDetail.getAdmParam().getIsActive()) {
//            if (admProcDtl.getAdmZoneType() != ZoneType.SEARCH || !admProcDtl.getAdmParam().getIsActive()) {
                continue;
            }
            String defaultValue = admReportDetail.getAdmParam().getDefaultVal();
            String widgetIdentity = admReportDetail.getAdmParam().getCode();

            objMap.put(widgetIdentity, defaultValueHandller(defaultValue));
        }

        decoReportPageMap(admReport, objMap);

        return objMap;
    }

    public static void decoReportPageMap(final AdmReport admReport, final Map<String, String> mapper) {

        Map<String, String> rootMap = new HashMap();
        String searchContent = "";
        String fixedParam = "";
        String reportButton = "";
        String queryParam = "";

//        ObjectMapper mapperx = new ObjectMapper();
        try {
//            Map<String, Object> procBtn = mapperx.readValue(admReport.getReportBtns(), 
//                    new TypeReference<Map<String, Object>>() {});

            for (ReportFormat object : admReport.getSupportFormats()) {
//            for (Object object : procBtn.keySet()) {
                reportButton += "<button type='button' class='btn btn-primary' name='" + object + "' id='" + object + "' " + " onclick='executeReport(this.id)'> " + object + "</button>";
            }

        } catch (Exception e) {
            System.out.println("jjrye64356hjfhs6:" + e);
        }

        for (AdmReportDetail admReportDetail : admReport.getAdmReportDetails()) {
            AdmParam admParam = admReportDetail.getAdmParam();
            ZoneType admZoneType = ZoneType.SEARCH;// admReportDetail.getAdmZoneType();
            Boolean isMandatory = admParam.getIsMandatory();

            String paramTitle = admParam.getFullName();
            String helpText = admParam.getHelpText();
            String defaultValue = admParam.getDefaultVal();
            WidgetType admWidgetType = admParam.getWidgetType();
            String paramCmd = admParam.getCmd();
            String paramName = admParam.getCode();
            String reqIndication = "";
            String req = "";
            String reqlab = "";
            String strdef = defaultValueHandller(defaultValue);

            try {
                if (admZoneType == ZoneType.PARAM_QU) {
                    queryParam += paramName + ",";
                }
            } catch (Exception ec) {
            }

            if (isMandatory != null) {
                if (isMandatory) {
                    reqIndication = "*";
                    req = " required='required' ";
                }
            }

            if (paramTitle != null) {
                reqlab = paramTitle;
            }

            if (helpText == null) {
                helpText = reqlab;
            }

            String rrrrrr;
            if (reqIndication.equals("*")) {
                rrrrrr = "<span class='required-indicator'> " + reqlab + reqIndication + "</span>";
            } else {
                rrrrrr = reqlab;
            }

            if (admZoneType == ZoneType.SEARCH && admWidgetType != null) {
                rootMap.put(paramName, admWidgetType.name());
                searchContent += "<div class='col-xs-12 col-sm-6 col-md-6 col-lg-6'><div class='form-group'>" + "<label for='" + reqlab + "'>" + "<span'>" + reqlab + reqIndication + "</span></label>";
            } else if (admZoneType == ZoneType.PARAM_FIXED && admWidgetType != WidgetType.UUID) {// && !(widgetType.equals(WidgetType.QU_PARAM_INVISIBLE.toString()) || widgetType.equals(WidgetType.QU_PARAM_VISIBLE.toString()))) {
                fixedParam += "<div class='col-xs-12 col-sm-6 col-md-6 col-lg-6'><div class='form-group'>" + "<label for='" + reqlab + "'>" + rrrrrr + "</label>";
            }

            if (admWidgetType == WidgetType.PASSWORD) {
                if (admZoneType == ZoneType.SEARCH) {
                    searchContent += "<input class='form-control' type='" + admWidgetType + "' name='" + paramName + "' id='" + paramName + "' value='" + strdef + "' " + req + "/>";
                } else if (admZoneType == ZoneType.PARAM_FIXED || admZoneType == ZoneType.PARAM_QU) {
                    fixedParam += "<input class='form-control' type='" + admWidgetType + "' name='" + paramName + "' id='" + paramName + "' value='" + strdef + "' " + req + "/>";
                }
            } else if (admWidgetType == WidgetType.TEXT) {
                if (admZoneType == ZoneType.SEARCH) {
                    searchContent += "<input class='form-control' type='" + admWidgetType + "' name='" + paramName + "' id='" + paramName + "' value='" + strdef + "' " + req + "/>";
                } else if (admZoneType == ZoneType.PARAM_FIXED || admZoneType == ZoneType.PARAM_QU) {
                    fixedParam += "<input class='form-control' type='" + admWidgetType + "' name='" + paramName + "' id='" + paramName + "' value='" + strdef + "' " + req + "/>";
                }
            } else if (admWidgetType == WidgetType.DATE) {
                if (admZoneType == ZoneType.SEARCH) {
                    searchContent += "<input class='form-control dtp-date' ";
                    searchContent += "name='";
                    searchContent += paramName;
                    searchContent += "' id='";
                    searchContent += paramName;
                    searchContent += "' value='";
                    searchContent += strdef;
                    searchContent += "'/>";
                } else if (admZoneType == ZoneType.PARAM_FIXED || admZoneType == ZoneType.PARAM_QU) {
                    fixedParam += "<input class='form-control dtp-date' ";
                    fixedParam += " name='";
                    fixedParam += paramName;
                    fixedParam += "' id='";
                    fixedParam += paramName;
                    fixedParam += "' value='";
                    fixedParam += strdef;
                    fixedParam += "'/>";
                }
            } else if (paramCmd != null) {
                if (admWidgetType == WidgetType.LIST || admWidgetType == WidgetType.LIST_MULTI_SELECT) {
                    List<Map<String, String>> optionList = cmdHandller(paramCmd);

                    String optSb = new String();
                    optSb += "<option value=''>Select</option>";

                    List mayy = Arrays.asList(strdef.split(","));

                    if (optionList != null) {
                        for (Map p : optionList) {
                            Object idx = p.get("id");
                            Object showx = p.get("show");

                            if (mayy.contains(idx)) {
                                optSb += "<option " + SELECTED + " value='" + idx + "'>" + showx + "</option>";
                            } else {
                                optSb += "<option value='" + idx + "'>" + showx + "</option>";
                            }
                        }
                    }

                    String hhhh = "";
                    if (admWidgetType == WidgetType.LIST_MULTI_SELECT) {
                        hhhh = " multiple='multiple' ";
                    }

                    if (admZoneType.equals(ZoneType.SEARCH)) {
                        searchContent += "<select class='form-control' name='" + paramName + "' id='" + paramName + "' " + req + hhhh + ">" + optSb + "</select>";
                    } else if (admZoneType.equals(ZoneType.PARAM_FIXED) || admZoneType.equals(ZoneType.PARAM_QU)) {
                        fixedParam += "<select class='form-control' name='" + paramName + "' id='" + paramName + "' " + req + hhhh + ">" + optSb + "</select>";
                    }
                }
            }

            if (admZoneType == ZoneType.SEARCH) {
                searchContent += "</div></div>";
            } else if (!fixedParam.isEmpty() && (admZoneType.equals(ZoneType.PARAM_FIXED) || admZoneType.equals(ZoneType.PARAM_QU))) {
                fixedParam += "</div></div>";
            }
        }

        if (!searchContent.isEmpty()) {
            searchContent = "<label for='reportParameter'>\n"
                    + "<span><h4><spring:message code='reportParameter' text='Report Parameter'/></h4></span>\n"
                    + "</label>\n"
                    + searchContent;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String searcherIdsxSTR = "";
        try {
            searcherIdsxSTR = objectMapper.writeValueAsString(rootMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mapper.put("searcherIds", searcherIdsxSTR);
        mapper.put("searchContent", searchContent);
        mapper.put("fixedParam", fixedParam);
        mapper.put("reportButton", reportButton);

        try {
            queryParam = queryParam.substring(0, queryParam.length() - 1);
            mapper.put("qparams", queryParam);
        } catch (Exception e) {
        }
    }

}
