package org.reflection.controller;

import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosAdjustmentMasterNotFoundException;
import org.reflection.model.pos.PosAdjustmentMaster;
import org.reflection.service.PosAdjustmentMasterService;
import org.reflection.service.auth.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigInteger;

@Controller
@RequestMapping(value = "/posAdjustmentMaster")

public class PosAdjustmentMasterController extends _BaseController {

    protected static final String MODEL = "posAdjustmentMaster";
    protected static final String MODELS = MODEL + "s";
    protected static final String INDEX = MODEL + "/index";
    protected static final String SHOW = MODEL + "/show";
    protected static final String FORM = MODEL + "/form";

    @Autowired
    private PosAdjustmentMasterService posAdjustmentMasterService;

    @Autowired
    private AuthUserService authUserService;


    @GetMapping("/getCodableDTO")
    public
    @ResponseBody
    String getCodableDTO(@RequestParam(value = "code") String code) {
        try {
            PosAdjustmentMaster codable = posAdjustmentMasterService.findByCode(code);
            return codable.getRemarks();
        } catch (Exception e) {
            System.out.println("mac err codable PosAdjustmentMaster Not Found: " + e);
            return "Not Found";
        }
    }


    private void commonPost(PosAdjustmentMaster currObject) {
        try {
            currObject.setAuthUserTransBy(authUserService.findByUsername(currObject.getAuthUserTransBy().getUsername()));
        } catch (Exception e) {
            currObject.setAuthUserTransBy(null);
        }


    }

    @GetMapping("/create")
    public String create(ModelMap model) {
        model.addAttribute(MODEL, new PosAdjustmentMaster());
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/create"));
        model.addAttribute("_action", "create");
        return FORM;
    }

    @PostMapping("/create")
    public String save(
            @ModelAttribute(MODEL) @Valid PosAdjustmentMaster currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        commonPost(currObject);
        currObject.getEmbdAuditable().setEntryBy(currAuthUser());
        currObject.getEmbdAuditable().setEntryDate(new java.util.Date());

        //currObject.getEmbdAudit().setEditBy(currAuthUser());
        //currObject.setCode(genCode("ZxLookup", "LK-", 4));

        if (!bindingResult.hasErrors()) {
            try {
                PosAdjustmentMaster posAdjustmentMaster = posAdjustmentMasterService.create(currObject);
                posAdjustmentMaster.setId(faker(posAdjustmentMaster.getId()));
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_CREATED, posAdjustmentMaster.getCode());
                return "redirect:/" + SHOW + "/" + posAdjustmentMaster.getId();
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
            PosAdjustmentMaster posAdjustmentMaster = posAdjustmentMasterService.findById(faker(id));
            posAdjustmentMaster.setId(faker(posAdjustmentMaster.getId()));
            model.addAttribute(MODEL, posAdjustmentMaster);
            model.addAttribute("_action", "edit");
            return FORM;
        } catch (PosAdjustmentMasterNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:index";
        }
    }

    @PostMapping("/edit/{id}")
    public String update(
            @PathVariable("id") BigInteger id,
            @ModelAttribute(MODEL) @Valid PosAdjustmentMaster currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        currObject.setId(faker(id));

        commonPost(currObject);
        currObject.getEmbdAuditable().setEditBy(currAuthUser());
        currObject.getEmbdAuditable().setEditDate(new java.util.Date());

        //currObject.getEmbdAudit().setEditBy(currAuthUser());

        if (!bindingResult.hasErrors()) {
            try {
                PosAdjustmentMaster posAdjustmentMaster = posAdjustmentMasterService.update(currObject);
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_EDITED, posAdjustmentMaster.getCode());
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
            PosAdjustmentMaster posAdjustmentMaster = posAdjustmentMasterService.findById(faker(id));
            posAdjustmentMaster.setId(faker(posAdjustmentMaster.getId()));
            model.addAttribute(MODEL, posAdjustmentMaster);
            model.addAttribute("_action", "copy");
            return FORM;
        } catch (PosAdjustmentMasterNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:index";
        }
    }

    @PostMapping("/copy/{id}")
    public String copied(
            @PathVariable("id") BigInteger id,
            @ModelAttribute(MODEL) @Valid PosAdjustmentMaster currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        currObject.setId(faker(id));

        commonPost(currObject);
        currObject.getEmbdAuditable().setCopyBy(currAuthUser());
        currObject.getEmbdAuditable().setCopyDate(new java.util.Date());

        //currObject.getEmbdAudit().setEditBy(currAuthUser());

        if (!bindingResult.hasErrors()) {
            try {
                PosAdjustmentMaster posAdjustmentMaster = posAdjustmentMasterService.copy(currObject);
                posAdjustmentMaster.setId(faker(posAdjustmentMaster.getId()));
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_COPIED, posAdjustmentMaster.getCode());
                return "redirect:/" + SHOW + "/" + posAdjustmentMaster.getId();
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
        List<PosAdjustmentMaster> posAdjustmentMasters;
   
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            posAdjustmentMasters = posAdjustmentMasterService.search(searchCriteria);
        } else {
            posAdjustmentMasters = posAdjustmentMasterService.findAll(searchCriteria);
        }
        model.addAttribute(MODELS, posAdjustmentMasters);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
        
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<PosAdjustmentMaster> posAdjustmentMasters = posAdjustmentMasterService.findAll();
        for (PosAdjustmentMaster obj : posAdjustmentMasters) {
            obj.setId(faker(obj.getId()));
        }
        model.addAttribute(MODELS, posAdjustmentMasters);
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/index"));
        return INDEX;
    }

    @GetMapping(value = {"/", "/index", ""})
    public String index(ModelMap model) {
        /*_SearchDTO searchCriteria = new _SearchDTO();
        searchCriteria.setPage(1);
        searchCriteria.setPageSize(5);
        
        List<PosAdjustmentMaster> posAdjustmentMasters = posAdjustmentMasterService.findAll(searchCriteria);

        model.addAttribute(MODELS, posAdjustmentMasters);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
    
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<PosAdjustmentMaster> posAdjustmentMasters = posAdjustmentMasterService.findAll();
        for (PosAdjustmentMaster obj : posAdjustmentMasters) {
            obj.setId(faker(obj.getId()));
        }
        model.addAttribute(MODELS, posAdjustmentMasters);
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/index"));
        return INDEX;
    }


    @GetMapping(value = "/show/{id}")
    public String show(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes redirectAttributes) {

        try {
            PosAdjustmentMaster posAdjustmentMaster = posAdjustmentMasterService.findById(faker(id));
            posAdjustmentMaster.setId(faker(posAdjustmentMaster.getId()));
            model.addAttribute(MODEL, posAdjustmentMaster);
            return SHOW;
        } catch (PosAdjustmentMasterNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:/" + INDEX;
        }
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") BigInteger id, RedirectAttributes redirectAttributes) {

        try {
            PosAdjustmentMaster deleted = posAdjustmentMasterService.delete(faker(id));
            addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getCode());
            return "redirect:/" + INDEX;
        } catch (PosAdjustmentMasterNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND, e);
            return "redirect:/" + INDEX;
        } catch (Exception e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, "", e);
            return "redirect:/" + INDEX;
        }
    }
}
