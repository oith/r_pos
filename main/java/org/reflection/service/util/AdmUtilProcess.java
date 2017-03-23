package org.reflection.service.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.reflection.model.com.AdmParam;
import org.reflection.model.com.AdmProcess;
import org.reflection.model.com.AdmProcessDetail;
import org.reflection.model.com.enums.WidgetType;
import org.reflection.model.com.enums.ZoneType;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public final class AdmUtilProcess extends AdmUtil {

    public AdmUtilProcess() {
    }

    public static Map<String, String> getProcessPageMap(final AdmProcess admProcess) {

        Map<String, String> objMap = new HashMap();

        //Hibernate.initialize(admProcess.getAdmProcessDetails());
        for (AdmProcessDetail admProcessDetail : admProcess.getAdmProcessDetails()) {

            if (admProcessDetail.getZoneType() != ZoneType.SEARCH || !admProcessDetail.getAdmParam().getIsActive()) {
                continue;
            }
            String defaultValue = admProcessDetail.getAdmParam().getDefaultVal();
            String widgetIdentity = admProcessDetail.getAdmParam().getCode();

            objMap.put(widgetIdentity, defaultValueHandller(defaultValue));
        }

        decoProcessPageMap(admProcess, objMap);

        return objMap;
    }

    public static void decoProcessPageMap(final AdmProcess admProcess, final Map<String, String> mapper) {

        Map<String, String> rootMap = new HashMap();
        String searchContent = "";
        String fixedParam = "";
        String processButton = "";
        String queryParam = "";

        ObjectMapper mapperx = new ObjectMapper();

        try {
            Map<String, Object> procBtn = mapperx.readValue(
                    admProcess.getProcessBtns(),
                    new TypeReference<Map<String, Object>>() {
                    }
            );

            for (Object object : procBtn.keySet()) {
                processButton += "<button type='button' class='btn btn-primary' name='" + object + "' id='" + object + "' " + " onclick='executeProcess(this.id)'> " + procBtn.get(object) + "</button>";
                //processButton += "<button type='button' class='btn btn-warning' name='" + paramName + "' id='" + paramName + "' title='" + helpText + "' " + disable + " onclick='executeProcess(this.id)'> " + paramTitle + "</button>";
            }

        } catch (Exception e) {
            System.out.println("jjrye64356hjfhs6:" + e);
        }

        for (AdmProcessDetail admProcessDetail : admProcess.getAdmProcessDetails()) {
            AdmParam admParam = admProcessDetail.getAdmParam();
            ZoneType admZoneType = admProcessDetail.getZoneType();
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
            searchContent = "<label for='searchParameter'>\n"
                    + "<span><h4><spring:message code='searchParameter' text='Search Parameter'/></h4></span>\n"
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
        mapper.put("processButton", processButton);

        if (queryParam.length() >= 1) {
            queryParam = queryParam.substring(0, queryParam.length() - 1);
        }
        mapper.put("qparams", queryParam);

    }

}
