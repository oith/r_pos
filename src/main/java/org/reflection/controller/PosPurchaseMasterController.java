package org.reflection.controller;

import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosPurchaseMasterNotFoundException;
import org.reflection.model.pos.PosPurchaseMaster;
import org.reflection.service.PosPurchaseMasterService;
import org.reflection.service.PosSupplierService;
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
@RequestMapping(value = "/posPurchaseMaster")

public class PosPurchaseMasterController extends _BaseController {

    protected static final String MODEL = "posPurchaseMaster";
    protected static final String MODELS = MODEL + "s";
    protected static final String INDEX = MODEL + "/index";
    protected static final String SHOW = MODEL + "/show";
    protected static final String FORM = MODEL + "/form";

    @Autowired
    private PosPurchaseMasterService posPurchaseMasterService;

    @Autowired
    private AuthUserService authUserService;
    @Autowired
    private PosSupplierService posSupplierService;


    @GetMapping("/getCodableDTO")
    public
    @ResponseBody
    String getCodableDTO(@RequestParam(value = "code") String code) {
        try {
            PosPurchaseMaster codable = posPurchaseMasterService.findByCode(code);
            return codable.getRemarks();
        } catch (Exception e) {
            System.out.println("mac err codable PosPurchaseMaster Not Found: " + e);
            return "Not Found";
        }
    }


    private void commonPost(PosPurchaseMaster currObject) {
        try {
            currObject.setAuthUserTransBy(authUserService.findByUsername(currObject.getAuthUserTransBy().getUsername()));
        } catch (Exception e) {
            currObject.setAuthUserTransBy(null);
        }
        try {
            currObject.setPosSupplier(posSupplierService.findByMobile(currObject.getPosSupplier().getMobile()));
        } catch (Exception e) {
            currObject.setPosSupplier(null);
        }


    }

    @GetMapping("/create")
    public String create(ModelMap model) {
        model.addAttribute(MODEL, new PosPurchaseMaster());
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/create"));
        model.addAttribute("_action", "create");
        return FORM;
    }

    @PostMapping("/create")
    public String save(
            @ModelAttribute(MODEL) @Valid PosPurchaseMaster currObject,
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
                PosPurchaseMaster posPurchaseMaster = posPurchaseMasterService.create(currObject);
                posPurchaseMaster.setId(faker(posPurchaseMaster.getId()));
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_CREATED, posPurchaseMaster.getCode());
                return "redirect:/" + SHOW + "/" + posPurchaseMaster.getId();
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
            PosPurchaseMaster posPurchaseMaster = posPurchaseMasterService.findById(faker(id));
            posPurchaseMaster.setId(faker(posPurchaseMaster.getId()));
            model.addAttribute(MODEL, posPurchaseMaster);
            model.addAttribute("_action", "edit");
            return FORM;
        } catch (PosPurchaseMasterNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:index";
        }
    }

    @PostMapping("/edit/{id}")
    public String update(
            @PathVariable("id") BigInteger id,
            @ModelAttribute(MODEL) @Valid PosPurchaseMaster currObject,
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
                PosPurchaseMaster posPurchaseMaster = posPurchaseMasterService.update(currObject);
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_EDITED, posPurchaseMaster.getCode());
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
            PosPurchaseMaster posPurchaseMaster = posPurchaseMasterService.findById(faker(id));
            posPurchaseMaster.setId(faker(posPurchaseMaster.getId()));
            model.addAttribute(MODEL, posPurchaseMaster);
            model.addAttribute("_action", "copy");
            return FORM;
        } catch (PosPurchaseMasterNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:index";
        }
    }

    @PostMapping("/copy/{id}")
    public String copied(
            @PathVariable("id") BigInteger id,
            @ModelAttribute(MODEL) @Valid PosPurchaseMaster currObject,
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
                PosPurchaseMaster posPurchaseMaster = posPurchaseMasterService.copy(currObject);
                posPurchaseMaster.setId(faker(posPurchaseMaster.getId()));
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_COPIED, posPurchaseMaster.getCode());
                return "redirect:/" + SHOW + "/" + posPurchaseMaster.getId();
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
        List<PosPurchaseMaster> posPurchaseMasters;
   
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            posPurchaseMasters = posPurchaseMasterService.search(searchCriteria);
        } else {
            posPurchaseMasters = posPurchaseMasterService.findAll(searchCriteria);
        }
        model.addAttribute(MODELS, posPurchaseMasters);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
        
        List<Integer> pages = new ArrayList();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<PosPurchaseMaster> posPurchaseMasters = posPurchaseMasterService.findAll();
        for (PosPurchaseMaster obj : posPurchaseMasters) {
            obj.setId(faker(obj.getId()));
        }
        model.addAttribute(MODELS, posPurchaseMasters);
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/index"));
        return INDEX;
    }

    @GetMapping(value = {"/", "/index", ""})
    public String index(ModelMap model) {
        /*_SearchDTO searchCriteria = new _SearchDTO();
        searchCriteria.setPage(1);
        searchCriteria.setPageSize(5);
        
        List<PosPurchaseMaster> posPurchaseMasters = posPurchaseMasterService.findAll(searchCriteria);

        model.addAttribute(MODELS, posPurchaseMasters);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
    
        List<Integer> pages = new ArrayList();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<PosPurchaseMaster> posPurchaseMasters = posPurchaseMasterService.findAll();
        for (PosPurchaseMaster obj : posPurchaseMasters) {
            obj.setId(faker(obj.getId()));
        }
        model.addAttribute(MODELS, posPurchaseMasters);
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/index"));
        return INDEX;
    }


    @GetMapping(value = "/show/{id}")
    public String show(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes redirectAttributes) {

        try {
            PosPurchaseMaster posPurchaseMaster = posPurchaseMasterService.findById(faker(id));
            posPurchaseMaster.setId(faker(posPurchaseMaster.getId()));
            model.addAttribute(MODEL, posPurchaseMaster);
            return SHOW;
        } catch (PosPurchaseMasterNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:/" + INDEX;
        }
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") BigInteger id, RedirectAttributes redirectAttributes) {

        try {
            PosPurchaseMaster deleted = posPurchaseMasterService.delete(faker(id));
            addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getCode());
            return "redirect:/" + INDEX;
        } catch (PosPurchaseMasterNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND, e);
            return "redirect:/" + INDEX;
        } catch (Exception e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, "", e);
            return "redirect:/" + INDEX;
        }
    }
}
