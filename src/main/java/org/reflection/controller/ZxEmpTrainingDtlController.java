package org.reflection.controller;

import org.reflection.dto._SearchDTO;
import org.reflection.exception.ZxEmpTrainingDtlNotFoundException;
import org.reflection.model.sample.ZxEmpTrainingDtl;
import org.reflection.model.sample.ZxLookup;
import org.reflection.service.ZxEmpService;
import org.reflection.service.ZxEmpTrainingDtlService;
import org.reflection.service.ZxLookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigInteger;

@Controller
@RequestMapping(value = "/zxEmpTrainingDtl")
@SessionAttributes({"zxLookupTrainings"})
public class ZxEmpTrainingDtlController extends _BaseController {

    protected static final String MODEL = "zxEmpTrainingDtl";
    protected static final String MODELS = MODEL + "s";
    protected static final String INDEX = MODEL + "/index";
    protected static final String SHOW = MODEL + "/show";
    protected static final String FORM = MODEL + "/form";

    @Autowired
    private ZxEmpTrainingDtlService zxEmpTrainingDtlService;

    @Autowired
    private ZxEmpService zxEmpService;
    @Autowired
    private ZxLookupService zxLookupService;


    @ModelAttribute("zxLookupTrainings")
    public Iterable<ZxLookup> zxLookupTrainings() {
        return zxLookupService.findAll();
    }


    private void commonPost(ZxEmpTrainingDtl currObject, MultipartHttpServletRequest request) {
        try {
            currObject.setZxEmp(zxEmpService.findByCode(currObject.getZxEmp().getCode()));
        } catch (Exception e) {
            currObject.setZxEmp(null);
        }
        try {
            currObject.setZxEmpWhoCheckedBy(zxEmpService.findByCode(currObject.getZxEmpWhoCheckedBy().getCode()));
        } catch (Exception e) {
            currObject.setZxEmpWhoCheckedBy(null);
        }

        String certificate = multipartFileHandler(request, "certificate");
        if (certificate != null && !certificate.isEmpty()) {
            currObject.setCertificate(certificate);
        }
        String pic = multipartImageFileHandler(request, "pic");
        if (pic != null && !pic.isEmpty()) {
            currObject.setPic(pic);
        }

    }

    @GetMapping("/create")
    public String create(ModelMap model) {
        model.addAttribute(MODEL, new ZxEmpTrainingDtl());
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/create"));
        model.addAttribute("_action", "create");
        return FORM;
    }

    @PostMapping("/create")
    public String save(
            @ModelAttribute(MODEL) @Valid ZxEmpTrainingDtl currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes, MultipartHttpServletRequest request) {

        commonPost(currObject, request);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());
        //currObject.setCode(genCode("ZxLookup", "LK-", 4));

        if (!bindingResult.hasErrors()) {
            try {
                ZxEmpTrainingDtl zxEmpTrainingDtl = zxEmpTrainingDtlService.create(currObject);
                zxEmpTrainingDtl.setId(faker(zxEmpTrainingDtl.getId()));
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_CREATED, zxEmpTrainingDtl.getId());
                return "redirect:/" + SHOW + "/" + zxEmpTrainingDtl.getId();
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        }
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/create"));
        model.addAttribute("_action", "create");
        return FORM;
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes redirectAttributes) {

        try {
            ZxEmpTrainingDtl zxEmpTrainingDtl = zxEmpTrainingDtlService.findById(faker(id));
            zxEmpTrainingDtl.setId(faker(zxEmpTrainingDtl.getId()));
            model.addAttribute(MODEL, zxEmpTrainingDtl);
            model.addAttribute("_action", "edit");
            return FORM;
        } catch (ZxEmpTrainingDtlNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:index";
        }
    }

    @PostMapping("/edit/{id}")
    public String update(
            @PathVariable("id") BigInteger id,
            @ModelAttribute(MODEL) @Valid ZxEmpTrainingDtl currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes, MultipartHttpServletRequest request) {

        currObject.setId(faker(id));

        commonPost(currObject, request);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());

        if (!bindingResult.hasErrors()) {
            try {
                ZxEmpTrainingDtl zxEmpTrainingDtl = zxEmpTrainingDtlService.update(currObject);
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_EDITED, zxEmpTrainingDtl.getId());
                return "redirect:/" + SHOW + "/" + id;
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        }
        model.addAttribute("_action", "edit");

        return FORM;
    }

    @GetMapping("/copy/{id}")
    public String copy(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes redirectAttributes) {

        try {
            ZxEmpTrainingDtl zxEmpTrainingDtl = zxEmpTrainingDtlService.findById(faker(id));
            zxEmpTrainingDtl.setId(faker(zxEmpTrainingDtl.getId()));
            model.addAttribute(MODEL, zxEmpTrainingDtl);
            model.addAttribute("_action", "copy");
            return FORM;
        } catch (ZxEmpTrainingDtlNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:index";
        }
    }

    @PostMapping("/copy/{id}")
    public String copied(
            @PathVariable("id") BigInteger id,
            @ModelAttribute(MODEL) @Valid ZxEmpTrainingDtl currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes, MultipartHttpServletRequest request) {

        currObject.setId(faker(id));

        commonPost(currObject, request);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());

        if (!bindingResult.hasErrors()) {
            try {
                ZxEmpTrainingDtl zxEmpTrainingDtl = zxEmpTrainingDtlService.copy(currObject);
                zxEmpTrainingDtl.setId(faker(zxEmpTrainingDtl.getId()));
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_COPIED, zxEmpTrainingDtl.getId());
                return "redirect:/" + SHOW + "/" + zxEmpTrainingDtl.getId();
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        }
        model.addAttribute("_action", "copy");
        return FORM;

    }

    @PostMapping(value = {"/", "/index", ""})
    public String search(@ModelAttribute(SEARCH_CRITERIA) _SearchDTO searchCriteria, ModelMap model) {
        /*
        String searchTerm = searchCriteria.getSearchTerm();
        List<ZxEmpTrainingDtl> zxEmpTrainingDtls;
   
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            zxEmpTrainingDtls = zxEmpTrainingDtlService.search(searchCriteria);
        } else {
            zxEmpTrainingDtls = zxEmpTrainingDtlService.findAll(searchCriteria);
        }
        model.addAttribute(MODELS, zxEmpTrainingDtls);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
        
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<ZxEmpTrainingDtl> zxEmpTrainingDtls = zxEmpTrainingDtlService.findAll();
        for (ZxEmpTrainingDtl obj : zxEmpTrainingDtls) {
            obj.setId(faker(obj.getId()));
        }
        model.addAttribute(MODELS, zxEmpTrainingDtls);
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/index"));
        return INDEX;
    }

    @GetMapping(value = {"/", "/index", ""})
    public String index(ModelMap model) {
        /*_SearchDTO searchCriteria = new _SearchDTO();
        searchCriteria.setPage(1);
        searchCriteria.setPageSize(5);
        
        List<ZxEmpTrainingDtl> zxEmpTrainingDtls = zxEmpTrainingDtlService.findAll(searchCriteria);

        model.addAttribute(MODELS, zxEmpTrainingDtls);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
    
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<ZxEmpTrainingDtl> zxEmpTrainingDtls = zxEmpTrainingDtlService.findAll();
        for (ZxEmpTrainingDtl obj : zxEmpTrainingDtls) {
            obj.setId(faker(obj.getId()));
        }
        model.addAttribute(MODELS, zxEmpTrainingDtls);
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/index"));
        return INDEX;
    }


    @GetMapping(value = "/show/{id}")
    public String show(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes redirectAttributes) {

        try {
            ZxEmpTrainingDtl zxEmpTrainingDtl = zxEmpTrainingDtlService.findById(faker(id));
            zxEmpTrainingDtl.setId(faker(zxEmpTrainingDtl.getId()));
            model.addAttribute(MODEL, zxEmpTrainingDtl);
            return SHOW;
        } catch (ZxEmpTrainingDtlNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:/" + INDEX;
        }
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") BigInteger id, RedirectAttributes redirectAttributes) {

        try {
            ZxEmpTrainingDtl deleted = zxEmpTrainingDtlService.delete(faker(id));
            addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getId());
            return "redirect:/" + INDEX;
        } catch (ZxEmpTrainingDtlNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND, e);
            return "redirect:/" + INDEX;
        } catch (Exception e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, "", e);
            return "redirect:/" + INDEX;
        }
    }
}
