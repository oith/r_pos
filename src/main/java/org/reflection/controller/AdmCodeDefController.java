package org.reflection.controller;

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AdmCodeDefNotFoundException;
import org.reflection.model.com.AdmCodeDef;
import org.reflection.service.AdmCodeDefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigInteger;

@Controller
@RequestMapping(value = "/admCodeDef")

public class AdmCodeDefController extends _BaseController {

    protected static final String MODEL = "admCodeDef";
    protected static final String MODELS = MODEL + "s";
    protected static final String INDEX = MODEL + "/index";
    protected static final String SHOW = MODEL + "/show";
    protected static final String FORM = MODEL + "/form";

    @Autowired
    private AdmCodeDefService admCodeDefService;


    @GetMapping("/getCodableDTO")
    public
    @ResponseBody
    String getCodableDTO(@RequestParam(value = "pojoClass") String pojoClass) {
        try {
            AdmCodeDef codable = admCodeDefService.findByPojoClass(pojoClass);
            return codable.getFullName();
        } catch (Exception e) {
            System.out.println("mac err codable AdmCodeDef Not Found: " + e);
            return "Not Found";
        }
    }


    private void commonPost(AdmCodeDef currObject) {


    }

    @GetMapping("/create")
    public String create(ModelMap model) {
        model.addAttribute(MODEL, new AdmCodeDef());
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/create"));
        model.addAttribute("_action", "create");
        return FORM;
    }

    @PostMapping("/create")
    public String save(
            @ModelAttribute(MODEL) @Valid AdmCodeDef currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        commonPost(currObject);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());
        //currObject.setCode(genCode("ZxLookup", "LK-", 4));

        if (!bindingResult.hasErrors()) {
            try {
                AdmCodeDef admCodeDef = admCodeDefService.create(currObject);
                admCodeDef.setId(faker(admCodeDef.getId()));
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_CREATED, admCodeDef.getId());
                return "redirect:/" + SHOW + "/" + admCodeDef.getId();
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
            AdmCodeDef admCodeDef = admCodeDefService.findById(faker(id));
            admCodeDef.setId(faker(admCodeDef.getId()));
            model.addAttribute(MODEL, admCodeDef);
            model.addAttribute("_action", "edit");
            return FORM;
        } catch (AdmCodeDefNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:index";
        }
    }

    @PostMapping("/edit/{id}")
    public String update(
            @PathVariable("id") BigInteger id,
            @ModelAttribute(MODEL) @Valid AdmCodeDef currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        currObject.setId(faker(id));

        commonPost(currObject);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());

        if (!bindingResult.hasErrors()) {
            try {
                AdmCodeDef admCodeDef = admCodeDefService.update(currObject);
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_EDITED, admCodeDef.getId());
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
            AdmCodeDef admCodeDef = admCodeDefService.findById(faker(id));
            admCodeDef.setId(faker(admCodeDef.getId()));
            model.addAttribute(MODEL, admCodeDef);
            model.addAttribute("_action", "copy");
            return FORM;
        } catch (AdmCodeDefNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:index";
        }
    }

    @PostMapping("/copy/{id}")
    public String copied(
            @PathVariable("id") BigInteger id,
            @ModelAttribute(MODEL) @Valid AdmCodeDef currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        currObject.setId(faker(id));

        commonPost(currObject);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());

        if (!bindingResult.hasErrors()) {
            try {
                AdmCodeDef admCodeDef = admCodeDefService.copy(currObject);
                admCodeDef.setId(faker(admCodeDef.getId()));
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_COPIED, admCodeDef.getId());
                return "redirect:/" + SHOW + "/" + admCodeDef.getId();
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
        List<AdmCodeDef> admCodeDefs;
   
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            admCodeDefs = admCodeDefService.search(searchCriteria);
        } else {
            admCodeDefs = admCodeDefService.findAll(searchCriteria);
        }
        model.addAttribute(MODELS, admCodeDefs);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
        
        List<Integer> pages = new ArrayList();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<AdmCodeDef> admCodeDefs = admCodeDefService.findAll();
        for (AdmCodeDef obj : admCodeDefs) {
            obj.setId(faker(obj.getId()));
        }
        model.addAttribute(MODELS, admCodeDefs);
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/index"));
        return INDEX;
    }

    @GetMapping(value = {"/", "/index", ""})
    public String index(ModelMap model) {
        /*_SearchDTO searchCriteria = new _SearchDTO();
        searchCriteria.setPage(1);
        searchCriteria.setPageSize(5);
        
        List<AdmCodeDef> admCodeDefs = admCodeDefService.findAll(searchCriteria);

        model.addAttribute(MODELS, admCodeDefs);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
    
        List<Integer> pages = new ArrayList();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<AdmCodeDef> admCodeDefs = admCodeDefService.findAll();
        for (AdmCodeDef obj : admCodeDefs) {
            obj.setId(faker(obj.getId()));
        }
        model.addAttribute(MODELS, admCodeDefs);
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/index"));
        return INDEX;
    }


    @GetMapping(value = "/show/{id}")
    public String show(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes redirectAttributes) {

        try {
            AdmCodeDef admCodeDef = admCodeDefService.findById(faker(id));
            admCodeDef.setId(faker(admCodeDef.getId()));
            model.addAttribute(MODEL, admCodeDef);
            return SHOW;
        } catch (AdmCodeDefNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:/" + INDEX;
        }
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") BigInteger id, RedirectAttributes redirectAttributes) {

        try {
            AdmCodeDef deleted = admCodeDefService.delete(faker(id));
            addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getId());
            return "redirect:/" + INDEX;
        } catch (AdmCodeDefNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND, e);
            return "redirect:/" + INDEX;
        } catch (Exception e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, "", e);
            return "redirect:/" + INDEX;
        }
    }
}
