package org.reflection.controller;

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AdmMenuPatternNotFoundException;
import org.reflection.model.com.AdmMenuPattern;
import org.reflection.service.AdmMenuCommonService;
import org.reflection.service.AdmMenuPatternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigInteger;

@Controller
@RequestMapping(value = "/admMenuPattern")

public class AdmMenuPatternController extends _BaseController {

    protected static final String MODEL = "admMenuPattern";
    protected static final String MODELS = MODEL + "s";
    protected static final String INDEX = MODEL + "/index";
    protected static final String SHOW = MODEL + "/show";
    protected static final String FORM = MODEL + "/form";

    @Autowired
    private AdmMenuPatternService admMenuPatternService;

    @Autowired
    private AdmMenuCommonService admMenuCommonService;


    private void commonPost(AdmMenuPattern currObject) {
        try {
            currObject.setAdmMenuCommon(admMenuCommonService.findByCode(currObject.getAdmMenuCommon().getCode()));
        } catch (Exception e) {
            currObject.setAdmMenuCommon(null);
        }


    }

    @GetMapping("/create")
    public String create(ModelMap model) {
        model.addAttribute(MODEL, new AdmMenuPattern());
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/create"));
        model.addAttribute("_action", "create");
        return FORM;
    }

    @PostMapping("/create")
    public String save(
            @ModelAttribute(MODEL) @Valid AdmMenuPattern currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        commonPost(currObject);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());
        //currObject.setCode(genCode("ZxLookup", "LK-", 4));

        if (!bindingResult.hasErrors()) {
            try {
                AdmMenuPattern admMenuPattern = admMenuPatternService.create(currObject);
                admMenuPattern.setId(faker(admMenuPattern.getId()));
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_CREATED, admMenuPattern.getId());
                return "redirect:/" + SHOW + "/" + admMenuPattern.getId();
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
            AdmMenuPattern admMenuPattern = admMenuPatternService.findById(faker(id));
            admMenuPattern.setId(faker(admMenuPattern.getId()));
            model.addAttribute(MODEL, admMenuPattern);
            model.addAttribute("_action", "edit");
            return FORM;
        } catch (AdmMenuPatternNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:index";
        }
    }

    @PostMapping("/edit/{id}")
    public String update(
            @PathVariable("id") BigInteger id,
            @ModelAttribute(MODEL) @Valid AdmMenuPattern currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        currObject.setId(faker(id));

        commonPost(currObject);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());

        if (!bindingResult.hasErrors()) {
            try {
                AdmMenuPattern admMenuPattern = admMenuPatternService.update(currObject);
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_EDITED, admMenuPattern.getId());
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
            AdmMenuPattern admMenuPattern = admMenuPatternService.findById(faker(id));
            admMenuPattern.setId(faker(admMenuPattern.getId()));
            model.addAttribute(MODEL, admMenuPattern);
            model.addAttribute("_action", "copy");
            return FORM;
        } catch (AdmMenuPatternNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:index";
        }
    }

    @PostMapping("/copy/{id}")
    public String copied(
            @PathVariable("id") BigInteger id,
            @ModelAttribute(MODEL) @Valid AdmMenuPattern currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        currObject.setId(faker(id));

        commonPost(currObject);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());

        if (!bindingResult.hasErrors()) {
            try {
                AdmMenuPattern admMenuPattern = admMenuPatternService.copy(currObject);
                admMenuPattern.setId(faker(admMenuPattern.getId()));
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_COPIED, admMenuPattern.getId());
                return "redirect:/" + SHOW + "/" + admMenuPattern.getId();
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
        List<AdmMenuPattern> admMenuPatterns;
   
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            admMenuPatterns = admMenuPatternService.search(searchCriteria);
        } else {
            admMenuPatterns = admMenuPatternService.findAll(searchCriteria);
        }
        model.addAttribute(MODELS, admMenuPatterns);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
        
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<AdmMenuPattern> admMenuPatterns = admMenuPatternService.findAll();
        for (AdmMenuPattern obj : admMenuPatterns) {
            obj.setId(faker(obj.getId()));
        }
        model.addAttribute(MODELS, admMenuPatterns);
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/index"));
        return INDEX;
    }

    @GetMapping(value = {"/", "/index", ""})
    public String index(ModelMap model) {
        /*_SearchDTO searchCriteria = new _SearchDTO();
        searchCriteria.setPage(1);
        searchCriteria.setPageSize(5);
        
        List<AdmMenuPattern> admMenuPatterns = admMenuPatternService.findAll(searchCriteria);

        model.addAttribute(MODELS, admMenuPatterns);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
    
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<AdmMenuPattern> admMenuPatterns = admMenuPatternService.findAll();
        for (AdmMenuPattern obj : admMenuPatterns) {
            obj.setId(faker(obj.getId()));
        }
        model.addAttribute(MODELS, admMenuPatterns);
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/index"));
        return INDEX;
    }


    @GetMapping(value = "/show/{id}")
    public String show(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes redirectAttributes) {

        try {
            AdmMenuPattern admMenuPattern = admMenuPatternService.findById(faker(id));
            admMenuPattern.setId(faker(admMenuPattern.getId()));
            model.addAttribute(MODEL, admMenuPattern);
            return SHOW;
        } catch (AdmMenuPatternNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:/" + INDEX;
        }
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") BigInteger id, RedirectAttributes redirectAttributes) {

        try {
            AdmMenuPattern deleted = admMenuPatternService.delete(faker(id));
            addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getId());
            return "redirect:/" + INDEX;
        } catch (AdmMenuPatternNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND, e);
            return "redirect:/" + INDEX;
        } catch (Exception e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, "", e);
            return "redirect:/" + INDEX;
        }
    }
}
