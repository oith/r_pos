package org.reflection.controller;

import org.reflection.service.etc.ExcelFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
public class _ExcelFileUploadController {

    @Autowired
    private ExcelFileUploadService excelFileUploadService;

    @GetMapping("/excelFileUpload")
    public String getQueryRunnerPage() {
        return "etc/excelFileUpload";
    }

    @PostMapping("/excelFileUpload")
    public String upload(
            ModelMap model,
            RedirectAttributes attributes,
            MultipartHttpServletRequest request) {

        MultipartFile multipartFile = request.getFile("file");

        Map hhh = null;
        if (multipartFile != null && multipartFile.getSize() > 0) {

            System.out.println("ok file got" + multipartFile);
            try {
                excelFileUploadService.upload(multipartFile.getInputStream());
            } catch (Exception e) {
                System.out.println("ok file err" + e);
            }
        }

        model.addAttribute("result", hhh);
        return "etc/excelFileUpload";
    }
}
