package org.reflection.controller;

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AdmProcessDetailNotFoundException;
import org.reflection.model.com.AdmParam;
import org.reflection.model.com.AdmProcessDetail;
import org.reflection.service.AdmParamService;
import org.reflection.service.AdmProcessDetailService;
import org.reflection.service.AdmProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigInteger;

@Controller
@RequestMapping(value = "/admProcessDetail")
@SessionAttributes({"admParams"})
public class AdmProcessDetailController extends _BaseController {

    protected static final String MODEL = "admProcessDetail";
    protected static final String MODELS = MODEL + "s";
    protected static final String INDEX = MODEL + "/index";
    protected static final String SHOW = MODEL + "/show";
    protected static final String FORM = MODEL + "/form";

    @Autowired
    private AdmProcessDetailService admProcessDetailService;

    @Autowired
    private AdmProcessService admProcessService;
    @Autowired
    private AdmParamService admParamService;


    @ModelAttribute("admParams")
    public Iterable<AdmParam> admParams() {
        return admParamService.findAll();
    }


    private void commonPost(AdmProcessDetail currObject) {
        try {
            currObject.setAdmProcess(admProcessService.findByCode(currObject.getAdmProcess().getCode()));
        } catch (Exception e) {
            currObject.setAdmProcess(null);
        }


    }

    @GetMapping("/create")
    public String create(ModelMap model) {
        model.addAttribute(MODEL, new AdmProcessDetail());
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/create"));
        model.addAttribute("_action", "create");
        return FORM;
    }

    @PostMapping("/create")
    public String save(
            @ModelAttribute(MODEL) @Valid AdmProcessDetail currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        commonPost(currObject);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());
        //currObject.setCode(genCode("ZxLookup", "LK-", 4));

        if (!bindingResult.hasErrors()) {
            try {
                AdmProcessDetail admProcessDetail = admProcessDetailService.create(currObject);
                admProcessDetail.setId(faker(admProcessDetail.getId()));
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_CREATED, admProcessDetail.getId());
                return "redirect:/" + SHOW + "/" + admProcessDetail.getId();
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
            AdmProcessDetail admProcessDetail = admProcessDetailService.findById(faker(id));
            admProcessDetail.setId(faker(admProcessDetail.getId()));
            model.addAttribute(MODEL, admProcessDetail);
            model.addAttribute("_action", "edit");
            return FORM;
        } catch (AdmProcessDetailNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:index";
        }
    }

    @PostMapping("/edit/{id}")
    public String update(
            @PathVariable("id") BigInteger id,
            @ModelAttribute(MODEL) @Valid AdmProcessDetail currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        currObject.setId(faker(id));

        commonPost(currObject);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());

        if (!bindingResult.hasErrors()) {
            try {
                AdmProcessDetail admProcessDetail = admProcessDetailService.update(currObject);
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_EDITED, admProcessDetail.getId());
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
            AdmProcessDetail admProcessDetail = admProcessDetailService.findById(faker(id));
            admProcessDetail.setId(faker(admProcessDetail.getId()));
            model.addAttribute(MODEL, admProcessDetail);
            model.addAttribute("_action", "copy");
            return FORM;
        } catch (AdmProcessDetailNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:index";
        }
    }

    @PostMapping("/copy/{id}")
    public String copied(
            @PathVariable("id") BigInteger id,
            @ModelAttribute(MODEL) @Valid AdmProcessDetail currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        currObject.setId(faker(id));

        commonPost(currObject);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());

        if (!bindingResult.hasErrors()) {
            try {
                AdmProcessDetail admProcessDetail = admProcessDetailService.copy(currObject);
                admProcessDetail.setId(faker(admProcessDetail.getId()));
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_COPIED, admProcessDetail.getId());
                return "redirect:/" + SHOW + "/" + admProcessDetail.getId();
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
        List<AdmProcessDetail> admProcessDetails;
   
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            admProcessDetails = admProcessDetailService.search(searchCriteria);
        } else {
            admProcessDetails = admProcessDetailService.findAll(searchCriteria);
        }
        model.addAttribute(MODELS, admProcessDetails);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
        
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<AdmProcessDetail> admProcessDetails = admProcessDetailService.findAll();
        for (AdmProcessDetail obj : admProcessDetails) {
            obj.setId(faker(obj.getId()));
        }
        model.addAttribute(MODELS, admProcessDetails);
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/index"));
        return INDEX;
    }

    @GetMapping(value = {"/", "/index", ""})
    public String index(ModelMap model) {
        /*_SearchDTO searchCriteria = new _SearchDTO();
        searchCriteria.setPage(1);
        searchCriteria.setPageSize(5);
        
        List<AdmProcessDetail> admProcessDetails = admProcessDetailService.findAll(searchCriteria);

        model.addAttribute(MODELS, admProcessDetails);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
    
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<AdmProcessDetail> admProcessDetails = admProcessDetailService.findAll();
        for (AdmProcessDetail obj : admProcessDetails) {
            obj.setId(faker(obj.getId()));
        }
        model.addAttribute(MODELS, admProcessDetails);
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/index"));
        return INDEX;
    }


    @GetMapping(value = "/show/{id}")
    public String show(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes redirectAttributes) {

        try {
            AdmProcessDetail admProcessDetail = admProcessDetailService.findById(faker(id));
            admProcessDetail.setId(faker(admProcessDetail.getId()));
            model.addAttribute(MODEL, admProcessDetail);
            return SHOW;
        } catch (AdmProcessDetailNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:/" + INDEX;
        }
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") BigInteger id, RedirectAttributes redirectAttributes) {

        try {
            AdmProcessDetail deleted = admProcessDetailService.delete(faker(id));
            addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getId());
            return "redirect:/" + INDEX;
        } catch (AdmProcessDetailNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND, e);
            return "redirect:/" + INDEX;
        } catch (Exception e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, "", e);
            return "redirect:/" + INDEX;
        }
    }
}
