package org.reflection.controller;

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AdmParamNotFoundException;
import org.reflection.model.com.AdmParam;
import org.reflection.service.AdmParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigInteger;

@Controller
@RequestMapping(value = "/admParam")

public class AdmParamController extends _BaseController {

    protected static final String MODEL = "admParam";
    protected static final String MODELS = MODEL + "s";
    protected static final String INDEX = MODEL + "/index";
    protected static final String SHOW = MODEL + "/show";
    protected static final String FORM = MODEL + "/form";

    @Autowired
    private AdmParamService admParamService;


    private void commonPost(AdmParam currObject) {


    }

    @GetMapping("/create")
    public String create(ModelMap model) {
        model.addAttribute(MODEL, new AdmParam());
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/create"));
        model.addAttribute("_action", "create");
        return FORM;
    }

    @PostMapping("/create")
    public String save(
            @ModelAttribute(MODEL) @Valid AdmParam currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        commonPost(currObject);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());
        //currObject.setCode(genCode("ZxLookup", "LK-", 4));

        if (!bindingResult.hasErrors()) {
            try {
                AdmParam admParam = admParamService.create(currObject);
                admParam.setId(faker(admParam.getId()));
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_CREATED, admParam.getCode());
                return "redirect:/" + SHOW + "/" + admParam.getId();
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
            AdmParam admParam = admParamService.findById(faker(id));
            admParam.setId(faker(admParam.getId()));
            model.addAttribute(MODEL, admParam);
            model.addAttribute("_action", "edit");
            return FORM;
        } catch (AdmParamNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:index";
        }
    }

    @PostMapping("/edit/{id}")
    public String update(
            @PathVariable("id") BigInteger id,
            @ModelAttribute(MODEL) @Valid AdmParam currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        currObject.setId(faker(id));

        commonPost(currObject);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());

        if (!bindingResult.hasErrors()) {
            try {
                AdmParam admParam = admParamService.update(currObject);
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_EDITED, admParam.getCode());
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
            AdmParam admParam = admParamService.findById(faker(id));
            admParam.setId(faker(admParam.getId()));
            model.addAttribute(MODEL, admParam);
            model.addAttribute("_action", "copy");
            return FORM;
        } catch (AdmParamNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:index";
        }
    }

    @PostMapping("/copy/{id}")
    public String copied(
            @PathVariable("id") BigInteger id,
            @ModelAttribute(MODEL) @Valid AdmParam currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        currObject.setId(faker(id));

        commonPost(currObject);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());

        if (!bindingResult.hasErrors()) {
            try {
                AdmParam admParam = admParamService.copy(currObject);
                admParam.setId(faker(admParam.getId()));
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_COPIED, admParam.getCode());
                return "redirect:/" + SHOW + "/" + admParam.getId();
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
        List<AdmParam> admParams;
   
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            admParams = admParamService.search(searchCriteria);
        } else {
            admParams = admParamService.findAll(searchCriteria);
        }
        model.addAttribute(MODELS, admParams);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
        
        List<Integer> pages = new ArrayList();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<AdmParam> admParams = admParamService.findAll();
        for (AdmParam obj : admParams) {
            obj.setId(faker(obj.getId()));
        }
        model.addAttribute(MODELS, admParams);
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/index"));
        return INDEX;
    }

    @GetMapping(value = {"/", "/index", ""})
    public String index(ModelMap model) {
        /*_SearchDTO searchCriteria = new _SearchDTO();
        searchCriteria.setPage(1);
        searchCriteria.setPageSize(5);
        
        List<AdmParam> admParams = admParamService.findAll(searchCriteria);

        model.addAttribute(MODELS, admParams);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
    
        List<Integer> pages = new ArrayList();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<AdmParam> admParams = admParamService.findAll();
        for (AdmParam obj : admParams) {
            obj.setId(faker(obj.getId()));
        }
        model.addAttribute(MODELS, admParams);
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/index"));
        return INDEX;
    }


    @GetMapping(value = "/show/{id}")
    public String show(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes redirectAttributes) {

        try {
            AdmParam admParam = admParamService.findById(faker(id));
            admParam.setId(faker(admParam.getId()));
            model.addAttribute(MODEL, admParam);
            return SHOW;
        } catch (AdmParamNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:/" + INDEX;
        }
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") BigInteger id, RedirectAttributes redirectAttributes) {

        try {
            AdmParam deleted = admParamService.delete(faker(id));
            addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getCode());
            return "redirect:/" + INDEX;
        } catch (AdmParamNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND, e);
            return "redirect:/" + INDEX;
        } catch (Exception e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, "", e);
            return "redirect:/" + INDEX;
        }
    }
}
