package org.reflection.controller;

import org.reflection.dto._SearchDTO;
import org.reflection.exception.ZxEmpNotFoundException;
import org.reflection.model.sample.*;
import org.reflection.service.*;
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
@RequestMapping(value = "/zxEmp")
@SessionAttributes({"zxDepts", "zxDesgs", "zxLookupBloodGroups", "zxChooses"})
public class ZxEmpController extends _BaseController {

    protected static final String MODEL = "zxEmp";
    protected static final String MODELS = MODEL + "s";
    protected static final String INDEX = MODEL + "/index";
    protected static final String SHOW = MODEL + "/show";
    protected static final String FORM = MODEL + "/form";

    @Autowired
    private ZxEmpService zxEmpService;

    @Autowired
    private ZxDeptService zxDeptService;
    @Autowired
    private ZxDesgService zxDesgService;
    @Autowired
    private ZxLookupService zxLookupService;
    @Autowired
    private ZxChooseService zxChooseService;


    @GetMapping("/getCodableDTO")
    public
    @ResponseBody
    String getCodableDTO(@RequestParam(value = "code") String code) {
        try {
            ZxEmp codable = zxEmpService.findByCode(code);
            return codable.getFullName();
        } catch (Exception e) {
            System.out.println("mac err codable ZxEmp Not Found: " + e);
            return "Not Found";
        }
    }

    @ModelAttribute("zxDepts")
    public Iterable<ZxDept> zxDepts() {
        return zxDeptService.findAll();
    }

    @ModelAttribute("zxDesgs")
    public Iterable<ZxDesg> zxDesgs() {
        return zxDesgService.findAll();
    }

    @ModelAttribute("zxLookupBloodGroups")
    public Iterable<ZxLookup> zxLookupBloodGroups() {
        return zxLookupService.findAll();
    }

    @ModelAttribute("zxChooses")
    public Iterable<ZxChoose> zxChooses() {
        return zxChooseService.findAll();
    }


    private void commonPost(ZxEmp currObject, MultipartHttpServletRequest request) {

        String pic = multipartImageFileHandler(request, "pic");
        if (pic != null && !pic.isEmpty()) {
            currObject.setPic(pic);
        }
        String document = multipartFileHandler(request, "document");
        if (document != null && !document.isEmpty()) {
            currObject.setDocument(document);
        }

    }

    @GetMapping("/create")
    public String create(ModelMap model) {
        model.addAttribute(MODEL, new ZxEmp());
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/create"));
        model.addAttribute("_action", "create");
        return FORM;
    }

    @PostMapping("/create")
    public String save(
            @ModelAttribute(MODEL) @Valid ZxEmp currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes, MultipartHttpServletRequest request) {

        commonPost(currObject, request);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());
        //currObject.setCode(genCode("ZxLookup", "LK-", 4));

        if (!bindingResult.hasErrors()) {
            try {
                ZxEmp zxEmp = zxEmpService.create(currObject);
                zxEmp.setId(faker(zxEmp.getId()));
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_CREATED, zxEmp.getCode());
                return "redirect:/" + SHOW + "/" + zxEmp.getId();
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
            ZxEmp zxEmp = zxEmpService.findById(faker(id));
            zxEmp.setId(faker(zxEmp.getId()));
            model.addAttribute(MODEL, zxEmp);
            model.addAttribute("_action", "edit");
            return FORM;
        } catch (ZxEmpNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:index";
        }
    }

    @PostMapping("/edit/{id}")
    public String update(
            @PathVariable("id") BigInteger id,
            @ModelAttribute(MODEL) @Valid ZxEmp currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes, MultipartHttpServletRequest request) {

        currObject.setId(faker(id));

        commonPost(currObject, request);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());

        if (!bindingResult.hasErrors()) {
            try {
                ZxEmp zxEmp = zxEmpService.update(currObject);
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_EDITED, zxEmp.getCode());
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
            ZxEmp zxEmp = zxEmpService.findById(faker(id));
            zxEmp.setId(faker(zxEmp.getId()));
            model.addAttribute(MODEL, zxEmp);
            model.addAttribute("_action", "copy");
            return FORM;
        } catch (ZxEmpNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:index";
        }
    }

    @PostMapping("/copy/{id}")
    public String copied(
            @PathVariable("id") BigInteger id,
            @ModelAttribute(MODEL) @Valid ZxEmp currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes, MultipartHttpServletRequest request) {

        currObject.setId(faker(id));

        commonPost(currObject, request);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());

        if (!bindingResult.hasErrors()) {
            try {
                ZxEmp zxEmp = zxEmpService.copy(currObject);
                zxEmp.setId(faker(zxEmp.getId()));
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_COPIED, zxEmp.getCode());
                return "redirect:/" + SHOW + "/" + zxEmp.getId();
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
        List<ZxEmp> zxEmps;
   
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            zxEmps = zxEmpService.search(searchCriteria);
        } else {
            zxEmps = zxEmpService.findAll(searchCriteria);
        }
        model.addAttribute(MODELS, zxEmps);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
        
        List<Integer> pages = new ArrayList();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<ZxEmp> zxEmps = zxEmpService.findAll();
        for (ZxEmp obj : zxEmps) {
            obj.setId(faker(obj.getId()));
        }
        model.addAttribute(MODELS, zxEmps);
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/index"));
        return INDEX;
    }

    @GetMapping(value = {"/", "/index", ""})
    public String index(ModelMap model) {
        /*_SearchDTO searchCriteria = new _SearchDTO();
        searchCriteria.setPage(1);
        searchCriteria.setPageSize(5);
        
        List<ZxEmp> zxEmps = zxEmpService.findAll(searchCriteria);

        model.addAttribute(MODELS, zxEmps);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
    
        List<Integer> pages = new ArrayList();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<ZxEmp> zxEmps = zxEmpService.findAll();
        for (ZxEmp obj : zxEmps) {
            obj.setId(faker(obj.getId()));
        }
        model.addAttribute(MODELS, zxEmps);
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/index"));
        return INDEX;
    }


    @GetMapping(value = "/show/{id}")
    public String show(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes redirectAttributes) {

        try {
            ZxEmp zxEmp = zxEmpService.findById(faker(id));
            zxEmp.setId(faker(zxEmp.getId()));
            model.addAttribute(MODEL, zxEmp);
            return SHOW;
        } catch (ZxEmpNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:/" + INDEX;
        }
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") BigInteger id, RedirectAttributes redirectAttributes) {

        try {
            ZxEmp deleted = zxEmpService.delete(faker(id));
            addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getCode());
            return "redirect:/" + INDEX;
        } catch (ZxEmpNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND, e);
            return "redirect:/" + INDEX;
        } catch (Exception e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, "", e);
            return "redirect:/" + INDEX;
        }
    }
}
