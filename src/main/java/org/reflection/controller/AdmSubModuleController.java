package org.reflection.controller;

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AdmSubModuleNotFoundException;
import org.reflection.model.com.AdmModule;
import org.reflection.model.com.AdmSubModule;
import org.reflection.service.AdmModuleService;
import org.reflection.service.AdmSubModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigInteger;

@Controller
@RequestMapping(value = "/admSubModule")
@SessionAttributes({"admModules"})
public class AdmSubModuleController extends _BaseController {

    protected static final String MODEL = "admSubModule";
    protected static final String MODELS = MODEL + "s";
    protected static final String INDEX = MODEL + "/index";
    protected static final String SHOW = MODEL + "/show";
    protected static final String FORM = MODEL + "/form";

    @Autowired
    private AdmSubModuleService admSubModuleService;

    @Autowired
    private AdmModuleService admModuleService;


    @ModelAttribute("admModules")
    public Iterable<AdmModule> admModules() {
        return admModuleService.findAll();
    }


    private void commonPost(AdmSubModule currObject) {


    }

    @GetMapping("/create")
    public String create(ModelMap model) {
        model.addAttribute(MODEL, new AdmSubModule());
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/create"));
        model.addAttribute("_action", "create");
        return FORM;
    }

    @PostMapping("/create")
    public String save(
            @ModelAttribute(MODEL) @Valid AdmSubModule currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        commonPost(currObject);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());
        //currObject.setCode(genCode("ZxLookup", "LK-", 4));

        if (!bindingResult.hasErrors()) {
            try {
                AdmSubModule admSubModule = admSubModuleService.create(currObject);
                admSubModule.setId(faker(admSubModule.getId()));
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_CREATED, admSubModule.getCode());
                return "redirect:/" + SHOW + "/" + admSubModule.getId();
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
            AdmSubModule admSubModule = admSubModuleService.findById(faker(id));
            admSubModule.setId(faker(admSubModule.getId()));
            model.addAttribute(MODEL, admSubModule);
            model.addAttribute("_action", "edit");
            return FORM;
        } catch (AdmSubModuleNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:index";
        }
    }

    @PostMapping("/edit/{id}")
    public String update(
            @PathVariable("id") BigInteger id,
            @ModelAttribute(MODEL) @Valid AdmSubModule currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        currObject.setId(faker(id));

        commonPost(currObject);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());

        if (!bindingResult.hasErrors()) {
            try {
                AdmSubModule admSubModule = admSubModuleService.update(currObject);
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_EDITED, admSubModule.getCode());
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
            AdmSubModule admSubModule = admSubModuleService.findById(faker(id));
            admSubModule.setId(faker(admSubModule.getId()));
            model.addAttribute(MODEL, admSubModule);
            model.addAttribute("_action", "copy");
            return FORM;
        } catch (AdmSubModuleNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:index";
        }
    }

    @PostMapping("/copy/{id}")
    public String copied(
            @PathVariable("id") BigInteger id,
            @ModelAttribute(MODEL) @Valid AdmSubModule currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        currObject.setId(faker(id));

        commonPost(currObject);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());

        if (!bindingResult.hasErrors()) {
            try {
                AdmSubModule admSubModule = admSubModuleService.copy(currObject);
                admSubModule.setId(faker(admSubModule.getId()));
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_COPIED, admSubModule.getCode());
                return "redirect:/" + SHOW + "/" + admSubModule.getId();
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
        List<AdmSubModule> admSubModules;
   
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            admSubModules = admSubModuleService.search(searchCriteria);
        } else {
            admSubModules = admSubModuleService.findAll(searchCriteria);
        }
        model.addAttribute(MODELS, admSubModules);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
        
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<AdmSubModule> admSubModules = admSubModuleService.findAll();
        for (AdmSubModule obj : admSubModules) {
            obj.setId(faker(obj.getId()));
        }
        model.addAttribute(MODELS, admSubModules);
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/index"));
        return INDEX;
    }

    @GetMapping(value = {"/", "/index", ""})
    public String index(ModelMap model) {
        /*_SearchDTO searchCriteria = new _SearchDTO();
        searchCriteria.setPage(1);
        searchCriteria.setPageSize(5);
        
        List<AdmSubModule> admSubModules = admSubModuleService.findAll(searchCriteria);

        model.addAttribute(MODELS, admSubModules);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
    
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<AdmSubModule> admSubModules = admSubModuleService.findAll();
        for (AdmSubModule obj : admSubModules) {
            obj.setId(faker(obj.getId()));
        }
        model.addAttribute(MODELS, admSubModules);
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/index"));
        return INDEX;
    }


    @GetMapping(value = "/show/{id}")
    public String show(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes redirectAttributes) {

        try {
            AdmSubModule admSubModule = admSubModuleService.findById(faker(id));
            admSubModule.setId(faker(admSubModule.getId()));
            model.addAttribute(MODEL, admSubModule);
            return SHOW;
        } catch (AdmSubModuleNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:/" + INDEX;
        }
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") BigInteger id, RedirectAttributes redirectAttributes) {

        try {
            AdmSubModule deleted = admSubModuleService.delete(faker(id));
            addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getCode());
            return "redirect:/" + INDEX;
        } catch (AdmSubModuleNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND, e);
            return "redirect:/" + INDEX;
        } catch (Exception e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, "", e);
            return "redirect:/" + INDEX;
        }
    }
}
