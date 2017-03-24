package org.reflection.controller;

import org.reflection.dto._SearchDTO;
import org.reflection.exception.ZxAnyObjectNotFoundException;
import org.reflection.model.sample.ZxAnyObject;
import org.reflection.service.ZxAnyObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigInteger;

@Controller
@RequestMapping(value = "/zxAnyObject")

public class ZxAnyObjectController extends _BaseController {

    protected static final String MODEL = "zxAnyObject";
    protected static final String MODELS = MODEL + "s";
    protected static final String INDEX = MODEL + "/index";
    protected static final String SHOW = MODEL + "/show";
    protected static final String FORM = MODEL + "/form";

    @Autowired
    private ZxAnyObjectService zxAnyObjectService;


    private void commonPost(ZxAnyObject currObject) {


    }

    @GetMapping("/create")
    public String create(ModelMap model) {
        model.addAttribute(MODEL, new ZxAnyObject());
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/create"));
        model.addAttribute("_action", "create");
        return FORM;
    }

    @PostMapping("/create")
    public String save(
            @ModelAttribute(MODEL) @Valid ZxAnyObject currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        commonPost(currObject);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());
        //currObject.setCode(genCode("ZxLookup", "LK-", 4));

        if (!bindingResult.hasErrors()) {
            try {
                ZxAnyObject zxAnyObject = zxAnyObjectService.create(currObject);
                zxAnyObject.setId(faker(zxAnyObject.getId()));
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_CREATED, zxAnyObject.getCode());
                return "redirect:/" + SHOW + "/" + zxAnyObject.getId();
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
            ZxAnyObject zxAnyObject = zxAnyObjectService.findById(faker(id));
            zxAnyObject.setId(faker(zxAnyObject.getId()));
            model.addAttribute(MODEL, zxAnyObject);
            model.addAttribute("_action", "edit");
            return FORM;
        } catch (ZxAnyObjectNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:index";
        }
    }

    @PostMapping("/edit/{id}")
    public String update(
            @PathVariable("id") BigInteger id,
            @ModelAttribute(MODEL) @Valid ZxAnyObject currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        currObject.setId(faker(id));

        commonPost(currObject);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());

        if (!bindingResult.hasErrors()) {
            try {
                ZxAnyObject zxAnyObject = zxAnyObjectService.update(currObject);
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_EDITED, zxAnyObject.getCode());
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
            ZxAnyObject zxAnyObject = zxAnyObjectService.findById(faker(id));
            zxAnyObject.setId(faker(zxAnyObject.getId()));
            model.addAttribute(MODEL, zxAnyObject);
            model.addAttribute("_action", "copy");
            return FORM;
        } catch (ZxAnyObjectNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:index";
        }
    }

    @PostMapping("/copy/{id}")
    public String copied(
            @PathVariable("id") BigInteger id,
            @ModelAttribute(MODEL) @Valid ZxAnyObject currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        currObject.setId(faker(id));

        commonPost(currObject);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());

        if (!bindingResult.hasErrors()) {
            try {
                ZxAnyObject zxAnyObject = zxAnyObjectService.copy(currObject);
                zxAnyObject.setId(faker(zxAnyObject.getId()));
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_COPIED, zxAnyObject.getCode());
                return "redirect:/" + SHOW + "/" + zxAnyObject.getId();
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
        List<ZxAnyObject> zxAnyObjects;
   
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            zxAnyObjects = zxAnyObjectService.search(searchCriteria);
        } else {
            zxAnyObjects = zxAnyObjectService.findAll(searchCriteria);
        }
        model.addAttribute(MODELS, zxAnyObjects);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
        
        List<Integer> pages = new ArrayList();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<ZxAnyObject> zxAnyObjects = zxAnyObjectService.findAll();
        for (ZxAnyObject obj : zxAnyObjects) {
            obj.setId(faker(obj.getId()));
        }
        model.addAttribute(MODELS, zxAnyObjects);
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/index"));
        return INDEX;
    }

    @GetMapping(value = {"/", "/index", ""})
    public String index(ModelMap model) {
        /*_SearchDTO searchCriteria = new _SearchDTO();
        searchCriteria.setPage(1);
        searchCriteria.setPageSize(5);
        
        List<ZxAnyObject> zxAnyObjects = zxAnyObjectService.findAll(searchCriteria);

        model.addAttribute(MODELS, zxAnyObjects);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
    
        List<Integer> pages = new ArrayList();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<ZxAnyObject> zxAnyObjects = zxAnyObjectService.findAll();
        for (ZxAnyObject obj : zxAnyObjects) {
            obj.setId(faker(obj.getId()));
        }
        model.addAttribute(MODELS, zxAnyObjects);
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/index"));
        return INDEX;
    }


    @GetMapping(value = "/show/{id}")
    public String show(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes redirectAttributes) {

        try {
            ZxAnyObject zxAnyObject = zxAnyObjectService.findById(faker(id));
            zxAnyObject.setId(faker(zxAnyObject.getId()));
            model.addAttribute(MODEL, zxAnyObject);
            return SHOW;
        } catch (ZxAnyObjectNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:/" + INDEX;
        }
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") BigInteger id, RedirectAttributes redirectAttributes) {

        try {
            ZxAnyObject deleted = zxAnyObjectService.delete(faker(id));
            addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getCode());
            return "redirect:/" + INDEX;
        } catch (ZxAnyObjectNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND, e);
            return "redirect:/" + INDEX;
        } catch (Exception e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, "", e);
            return "redirect:/" + INDEX;
        }
    }
}
