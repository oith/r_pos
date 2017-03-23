package org.reflection.controller;

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AdmMenuCommonNotFoundException;
import org.reflection.model.com.AdmMenuCommon;
import org.reflection.model.com.AdmSubModule;
import org.reflection.service.AdmMenuCommonService;
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
@RequestMapping(value = "/admMenuCommon")
@SessionAttributes({"admSubModules"})
public class AdmMenuCommonController extends _BaseController {

    protected static final String MODEL = "admMenuCommon";
    protected static final String MODELS = MODEL + "s";
    protected static final String INDEX = MODEL + "/index";
    protected static final String SHOW = MODEL + "/show";
    protected static final String FORM = MODEL + "/form";

    @Autowired
    private AdmMenuCommonService admMenuCommonService;

    @Autowired
    private AdmSubModuleService admSubModuleService;


    @GetMapping("/getCodableDTO")
    public
    @ResponseBody
    String getCodableDTO(@RequestParam(value = "code") String code) {
        try {
            AdmMenuCommon codable = admMenuCommonService.findByCode(code);
            return codable.getFullName();
        } catch (Exception e) {
            System.out.println("mac err codable AdmMenuCommon Not Found: " + e);
            return "Not Found";
        }
    }

    @ModelAttribute("admSubModules")
    public Iterable<AdmSubModule> admSubModules() {
        return admSubModuleService.findAll();
    }


    private void commonPost(AdmMenuCommon currObject) {


    }

    @GetMapping("/create")
    public String create(ModelMap model) {
        model.addAttribute(MODEL, new AdmMenuCommon());
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/create"));
        model.addAttribute("_action", "create");
        return FORM;
    }

    @PostMapping("/create")
    public String save(
            @ModelAttribute(MODEL) @Valid AdmMenuCommon currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        commonPost(currObject);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());
        //currObject.setCode(genCode("ZxLookup", "LK-", 4));

        if (!bindingResult.hasErrors()) {
            try {
                AdmMenuCommon admMenuCommon = admMenuCommonService.create(currObject);
                admMenuCommon.setId(faker(admMenuCommon.getId()));
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_CREATED, admMenuCommon.getCode());
                return "redirect:/" + SHOW + "/" + admMenuCommon.getId();
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
            AdmMenuCommon admMenuCommon = admMenuCommonService.findById(faker(id));
            admMenuCommon.setId(faker(admMenuCommon.getId()));
            model.addAttribute(MODEL, admMenuCommon);
            model.addAttribute("_action", "edit");
            return FORM;
        } catch (AdmMenuCommonNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:index";
        }
    }

    @PostMapping("/edit/{id}")
    public String update(
            @PathVariable("id") BigInteger id,
            @ModelAttribute(MODEL) @Valid AdmMenuCommon currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        currObject.setId(faker(id));

        commonPost(currObject);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());

        if (!bindingResult.hasErrors()) {
            try {
                AdmMenuCommon admMenuCommon = admMenuCommonService.update(currObject);
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_EDITED, admMenuCommon.getCode());
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
            AdmMenuCommon admMenuCommon = admMenuCommonService.findById(faker(id));
            admMenuCommon.setId(faker(admMenuCommon.getId()));
            model.addAttribute(MODEL, admMenuCommon);
            model.addAttribute("_action", "copy");
            return FORM;
        } catch (AdmMenuCommonNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:index";
        }
    }

    @PostMapping("/copy/{id}")
    public String copied(
            @PathVariable("id") BigInteger id,
            @ModelAttribute(MODEL) @Valid AdmMenuCommon currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        currObject.setId(faker(id));

        commonPost(currObject);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());

        if (!bindingResult.hasErrors()) {
            try {
                AdmMenuCommon admMenuCommon = admMenuCommonService.copy(currObject);
                admMenuCommon.setId(faker(admMenuCommon.getId()));
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_COPIED, admMenuCommon.getCode());
                return "redirect:/" + SHOW + "/" + admMenuCommon.getId();
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
        List<AdmMenuCommon> admMenuCommons;
   
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            admMenuCommons = admMenuCommonService.search(searchCriteria);
        } else {
            admMenuCommons = admMenuCommonService.findAll(searchCriteria);
        }
        model.addAttribute(MODELS, admMenuCommons);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
        
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<AdmMenuCommon> admMenuCommons = admMenuCommonService.findAll();
        for (AdmMenuCommon obj : admMenuCommons) {
            obj.setId(faker(obj.getId()));
        }
        model.addAttribute(MODELS, admMenuCommons);
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/index"));
        return INDEX;
    }

    @GetMapping(value = {"/", "/index", ""})
    public String index(ModelMap model) {
        /*_SearchDTO searchCriteria = new _SearchDTO();
        searchCriteria.setPage(1);
        searchCriteria.setPageSize(5);
        
        List<AdmMenuCommon> admMenuCommons = admMenuCommonService.findAll(searchCriteria);

        model.addAttribute(MODELS, admMenuCommons);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
    
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<AdmMenuCommon> admMenuCommons = admMenuCommonService.findAll();
        for (AdmMenuCommon obj : admMenuCommons) {
            obj.setId(faker(obj.getId()));
        }
        model.addAttribute(MODELS, admMenuCommons);
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/index"));
        return INDEX;
    }


    @GetMapping(value = "/show/{id}")
    public String show(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes redirectAttributes) {

        try {
            AdmMenuCommon admMenuCommon = admMenuCommonService.findById(faker(id));
            admMenuCommon.setId(faker(admMenuCommon.getId()));
            model.addAttribute(MODEL, admMenuCommon);
            return SHOW;
        } catch (AdmMenuCommonNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:/" + INDEX;
        }
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") BigInteger id, RedirectAttributes redirectAttributes) {

        try {
            AdmMenuCommon deleted = admMenuCommonService.delete(faker(id));
            addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getCode());
            return "redirect:/" + INDEX;
        } catch (AdmMenuCommonNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND, e);
            return "redirect:/" + INDEX;
        } catch (Exception e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, "", e);
            return "redirect:/" + INDEX;
        }
    }
}
