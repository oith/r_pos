package org.reflection.controller;

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AdmModuleNotFoundException;
import org.reflection.model.com.AdmModule;
import org.reflection.service.AdmModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigInteger;

@Controller
@RequestMapping(value = "/admModule")

public class AdmModuleController extends _BaseController {

    protected static final String MODEL = "admModule";
    protected static final String MODELS = MODEL + "s";
    protected static final String INDEX = MODEL + "/index";
    protected static final String SHOW = MODEL + "/show";
    protected static final String FORM = MODEL + "/form";

    @Autowired
    private AdmModuleService admModuleService;


    private void commonPost(AdmModule currObject) {


    }

    @GetMapping("/create")
    public String create(ModelMap model) {
        model.addAttribute(MODEL, new AdmModule());
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/create"));
        model.addAttribute("_action", "create");
        return FORM;
    }

    @PostMapping("/create")
    public String save(
            @ModelAttribute(MODEL) @Valid AdmModule currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        commonPost(currObject);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());
        //currObject.setCode(genCode("ZxLookup", "LK-", 4));

        if (!bindingResult.hasErrors()) {
            try {
                AdmModule admModule = admModuleService.create(currObject);
                admModule.setId(faker(admModule.getId()));
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_CREATED, admModule.getCode());
                return "redirect:/" + SHOW + "/" + admModule.getId();
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
            AdmModule admModule = admModuleService.findById(faker(id));
            admModule.setId(faker(admModule.getId()));
            model.addAttribute(MODEL, admModule);
            model.addAttribute("_action", "edit");
            return FORM;
        } catch (AdmModuleNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:index";
        }
    }

    @PostMapping("/edit/{id}")
    public String update(
            @PathVariable("id") BigInteger id,
            @ModelAttribute(MODEL) @Valid AdmModule currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        currObject.setId(faker(id));

        commonPost(currObject);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());

        if (!bindingResult.hasErrors()) {
            try {
                AdmModule admModule = admModuleService.update(currObject);
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_EDITED, admModule.getCode());
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
            AdmModule admModule = admModuleService.findById(faker(id));
            admModule.setId(faker(admModule.getId()));
            model.addAttribute(MODEL, admModule);
            model.addAttribute("_action", "copy");
            return FORM;
        } catch (AdmModuleNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:index";
        }
    }

    @PostMapping("/copy/{id}")
    public String copied(
            @PathVariable("id") BigInteger id,
            @ModelAttribute(MODEL) @Valid AdmModule currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        currObject.setId(faker(id));

        commonPost(currObject);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());

        if (!bindingResult.hasErrors()) {
            try {
                AdmModule admModule = admModuleService.copy(currObject);
                admModule.setId(faker(admModule.getId()));
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_COPIED, admModule.getCode());
                return "redirect:/" + SHOW + "/" + admModule.getId();
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
        List<AdmModule> admModules;
   
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            admModules = admModuleService.search(searchCriteria);
        } else {
            admModules = admModuleService.findAll(searchCriteria);
        }
        model.addAttribute(MODELS, admModules);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
        
        List<Integer> pages = new ArrayList();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<AdmModule> admModules = admModuleService.findAll();
        for (AdmModule obj : admModules) {
            obj.setId(faker(obj.getId()));
        }
        model.addAttribute(MODELS, admModules);
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/index"));
        return INDEX;
    }

    @GetMapping(value = {"/", "/index", ""})
    public String index(ModelMap model) {
        /*_SearchDTO searchCriteria = new _SearchDTO();
        searchCriteria.setPage(1);
        searchCriteria.setPageSize(5);
        
        List<AdmModule> admModules = admModuleService.findAll(searchCriteria);

        model.addAttribute(MODELS, admModules);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
    
        List<Integer> pages = new ArrayList();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<AdmModule> admModules = admModuleService.findAll();
        for (AdmModule obj : admModules) {
            obj.setId(faker(obj.getId()));
        }
        model.addAttribute(MODELS, admModules);
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/index"));
        return INDEX;
    }


    @GetMapping(value = "/show/{id}")
    public String show(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes redirectAttributes) {

        try {
            AdmModule admModule = admModuleService.findById(faker(id));
            admModule.setId(faker(admModule.getId()));
            model.addAttribute(MODEL, admModule);
            return SHOW;
        } catch (AdmModuleNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:/" + INDEX;
        }
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") BigInteger id, RedirectAttributes redirectAttributes) {

        try {
            AdmModule deleted = admModuleService.delete(faker(id));
            addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getCode());
            return "redirect:/" + INDEX;
        } catch (AdmModuleNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND, e);
            return "redirect:/" + INDEX;
        } catch (Exception e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, "", e);
            return "redirect:/" + INDEX;
        }
    }
}
