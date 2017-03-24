package org.reflection.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.reflection.dto._DualDTO;
import org.reflection.exception.AdmReportNotFoundException;
import org.reflection.model.com.AdmReport;
import org.reflection.service.AdmModuleService;
import org.reflection.service.AdmReportService;
import org.reflection.service.util.AdmUtilReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.math.BigInteger;
import java.util.*;

@Controller
@RequestMapping(value = "/reportCenter")
public class _ReportCenterController extends _BaseController {

    private static final String REPORT_ROOT_DIR = "reports";

    @Autowired
    private DataSource dataSource;
    @Autowired
    private AdmReportService admReportService;
    @Autowired
    private AdmModuleService admModuleService;

    @GetMapping("/{code}")
    public String indexCode(@PathVariable("code") String code, ModelMap model) {

        AdmReport bbb;
        try {
            bbb = admReportService.findByCode(code);
        } catch (AdmReportNotFoundException ex) {
            bbb = null;
        }

        if (bbb != null) {
            model.addAttribute("reportFullName", bbb.getFullName());
            model.addAttribute("reportId", bbb.getId());
        }
        return "etc/dynamicFormReport";
    }

    @GetMapping(value = "/getReport", produces = "text/plain;charset=UTF-8")
    public
    @ResponseBody
    ResponseEntity<String> getReport(@RequestParam(value = "module") BigInteger moduleId, final Locale locale) {

        StringBuilder sb = new StringBuilder();
        String uuuu = messageSource.getMessage("default.combo.select.one.label", null, locale);

        System.out.println("finding getReport: code: >" + moduleId + "<" + locale + " vv " + uuuu);

        sb.append("<option value='${null}'>").append(uuuu).append("</option>");

        if (moduleId == null) {
            for (AdmReport bbb : admReportService.findAll()) {
                sb.append("<option value='").append(bbb.getId()).append("'>").append(bbb.getAdmModule()).append('-').append(bbb).append("</option>");
            }
        } else {
            for (AdmReport bbb : admReportService.findAll()) {
                if (bbb.getAdmModule().getId().equals(moduleId)) {
                    sb.append("<option value='").append(bbb.getId()).append("'>").append(bbb).append("</option>");
                }
            }
        }
        //final HttpHeaders headers = new HttpHeaders();
        //headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(sb.toString(), HttpStatus.CREATED);
    }

    @GetMapping("/getDynamicContent")
    public
    @ResponseBody
    ResponseEntity<String> getDynamicContent(@RequestParam(value = "reportId") BigInteger reportId) {

        System.out.println("finding getDynamicContent: code: >" + reportId + "<");
        AdmReport admReport;
        try {
            admReport = admReportService.findById(reportId);
        } catch (Exception e) {
            admReport = null;
        }

        Map<String, String> allMap = AdmUtilReport.getReportPageMap(admReport);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return new ResponseEntity<>(objectMapper.writeValueAsString(allMap), headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @GetMapping("")
    public String index(ModelMap model) {

        List<_DualDTO> kk = new ArrayList();
        for (AdmReport bbb : admReportService.findAll()) {
            _DualDTO bbbm = new _DualDTO(bbb.getId(), bbb.getAdmModule() + "-" + bbb);
            kk.add(bbbm);
        }

        model.addAttribute("admModules", admModuleService.findAll());
        model.addAttribute("admReports", kk);
        return "etc/reportCenter";
    }

    // @GetMapping("/executeReport/{code}.{format}")
    @GetMapping("/executeReport")
    public
    @ResponseBody
    ResponseEntity<byte[]> executeReport(
            @RequestParam(value = "reportFormat") String reportFormat,
            @RequestParam(value = "reportParams") String reportParams,
            @RequestParam(value = "reportId") BigInteger reportId,
            HttpServletRequest request) {
//    ResponseEntity<byte[]> executeReport(@PathVariable("code") String code, @PathVariable("format") String format, HttpServletRequest request) {

        System.out.println("ppppp453 reportParams: " + reportParams + " reportFormat: " + reportFormat + " reportId: " + reportId);

        Map<String, String> realMap = new HashMap();
        if (reportParams != null && reportParams.isEmpty()) {
            try {
                realMap
                        = new ObjectMapper().readValue(reportParams, HashMap.class);
            } catch (Exception e) {
                System.out.println("err never show :" + e);
            }
        }

        AdmReport admReport;
        try {
            admReport = admReportService.findById(reportId);
        } catch (Exception e) {
            admReport = null;
        }

        File parent = getOuterParentPath(request);
        File file = new File(parent, "repositories" + File.separator + REPORT_ROOT_DIR + File.separator + admReport.getFileName() + ".jrxml");

        if (!file.exists()) {
            System.out.println("file real: " + file);
            return null;
        }

        List kkk = getAutoReportDetails(file);

        System.out.println("getAutoReportDetails : " + kkk);

        Map params = new HashMap();

        params.put("REPORT_PATH", file.getParent() + "\\");
        if (reportFormat.equals("XLS")) {
            params.put("IS_DETECT_CELL_TYPE", Boolean.FALSE);
            params.put("IS_IGNORE_PAGINATION", Boolean.TRUE);
            params.put("net.sf.jasperreports.export.xls.ignore.graphics", Boolean.TRUE);
        }

        for (String object : realMap.keySet()) {
            if (object.endsWith("DATE")) {
                try {
                    params.put(object, DATE_FORMAT.parse(realMap.get(object)));
                } catch (Exception e) {
                    System.out.println("err in report calling date format : " + e);
                }
            } else {
                params.put(object, realMap.get(object));
            }
        }

        System.out.println("macsay: Report path: " + file + " name:" + admReport.getFileName());

        ByteArrayOutputStream baos = null;
        try {

            // Create a JasperDesign object from the JRXMl file
            // You can also load the template by directly adding the actual path, i.e.
            JasperDesign jd = JRXmlLoader.load(file);

            // Compile our report layout
            JasperReport jr = JasperCompileManager.compileReport(jd);

            // It needs a JasperReport layout and a datasource
            JasperPrint jp = JasperFillManager.fillReport(jr, params, dataSource.getConnection());

            // Create our output byte stream
            // This is the stream where the data will be written
            baos = new ByteArrayOutputStream();

            // Export to output stream
            // The data will be exported to the ByteArrayOutputStream baos
            // We delegate the exporting  to a custom Exporter instance
            // The Exporter is a wrapper class I made. Feel free to remove or modify it
            // Create a JRXlsExporter instance
            if (reportFormat.equals("PDF")) {
                JasperExportManager.exportReportToPdfStream(jp, baos);

                //System.out.println("res 204: " + response + " baos size: " + baos.size());
            } else if (reportFormat.equals("HTML")) {

                JasperExportManager.exportReportToHtmlFile(jp, "D:\\admm.html");

                // response.setHeader("Content-disposition", "attachment; filename=" + admReport.getFullName() + ".pdf");
                // response.setContentType("application/pdf");
                //System.out.println("res 204: " + response + " baos html  size: " + baos.size());
            } else if (reportFormat.equals("XLS")) {

                JRXlsExporter exporter = new JRXlsExporter();

                // Here we assign the parameters jp and baos to the exporter
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

                // Retrieve the exported report in XLS format
                exporter.exportReport();

                //  response.setHeader("Content-disposition", "attachment; filename=" + admReport.getFullName() + ".xls");
                //  response.setContentType("application/vnd.ms-excel");
            }

        } catch (Exception e) {
            System.out.println("err report gen: " + e);
        }

        if (baos != null) {
            try {

                HttpHeaders headers = new HttpHeaders();

                headers.setContentType(MediaType.parseMediaType("application/pdf"));
                String filename = admReport.getFullName() + ".pdf";
                headers.setContentDispositionFormData(filename, filename);
                // headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
                // ResponseEntity<byte[]> response = new ResponseEntity(baos.toByteArray(), headers, HttpStatus.OK);
                System.out.println("okkkkkk" + headers + " datam: " + baos.toByteArray().length);
                return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.CREATED);
                //return response;

//                ServletOutputStream out = response.getOutputStream();
//
//                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(out);
//
//                response.setContentLength(baos.size());
//                response.setContentType("application/pdf");
//                response.setHeader("Content-Disposition", "inline; filename=" + admReport.getFullName() + ".pdf");
//
//                // response.setHeader("Cache-Control", "cache, must-revalidate");
//                // response.setHeader("Pragma", "public");
//                bufferedOutputStream.write(baos.toByteArray());
                // System.out.println("done report");
            } catch (Exception e) {
                System.out.println("err report write to: " + e);
            }
        }
        return null;
    }

    List getAutoReportDetails(File fXmlFile) {
        List hhh = new ArrayList();
        try {
            //File fXmlFile = new File("D:\\HRMS\\web-app\\reports\\payroll_register.jrxml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("parameter");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    String isOk = eElement.getAttribute("isForPrompting");

                    if (isOk.equals("true") || isOk.equals("")) {
                        System.out.println("name id : " + eElement.getAttribute("name"));
                        System.out.println("class id : " + eElement.getAttribute("class"));

                        hhh.add(eElement.getAttribute("name"));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("err: " + e);
        }
        return hhh;
    }

    @RequestMapping(value = "/loadReport", method = RequestMethod.GET)
    public void loadReport(HttpServletRequest request) {
        Iterable<AdmReport> admReports = admReportService.findAll();
        File parent = getOuterParentPath(request);

        for (AdmReport admReport : admReports) {
            File file = new File(parent, "repositories" + File.separator + REPORT_ROOT_DIR + File.separator + admReport.getFileName() + ".jrxml");

            if (!file.exists()) {
                return;
            }

            List kkk = getAutoReportDetails(file);
            System.out.println(admReport.getFileName() + " aaaaaaaaaaa: " + kkk);
        }
    }
}
