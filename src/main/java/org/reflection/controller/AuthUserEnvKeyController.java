package org.reflection.controller;

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AuthUserEnvKeyNotFoundException;
import org.reflection.model.auth.AuthUserEnvKey;
import org.reflection.service.AuthUserEnvKeyService;
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
@RequestMapping(value = "/authUserEnvKey")

public class AuthUserEnvKeyController extends _BaseController {

    protected static final String MODEL = "authUserEnvKey";
    protected static final String MODELS = MODEL + "s";
    protected static final String INDEX = MODEL + "/index";
    protected static final String SHOW = MODEL + "/show";
    protected static final String FORM = MODEL + "/form";

    @Autowired
    private AuthUserEnvKeyService authUserEnvKeyService;

    @Autowired
    private AuthUserService authUserService;


    private void commonPost(AuthUserEnvKey currObject) {
        try {
            currObject.setAuthUser(authUserService.findByUsername(currObject.getAuthUser().getUsername()));
        } catch (Exception e) {
            currObject.setAuthUser(null);
        }


    }

    @GetMapping("/create")
    public String create(ModelMap model) {
        model.addAttribute(MODEL, new AuthUserEnvKey());
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/create"));
        model.addAttribute("_action", "create");
        return FORM;
    }

    @PostMapping("/create")
    public String save(
            @ModelAttribute(MODEL) @Valid AuthUserEnvKey currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        commonPost(currObject);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());
        //currObject.setCode(genCode("ZxLookup", "LK-", 4));

        if (!bindingResult.hasErrors()) {
            try {
                AuthUserEnvKey authUserEnvKey = authUserEnvKeyService.create(currObject);
                authUserEnvKey.setId(faker(authUserEnvKey.getId()));
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_CREATED, authUserEnvKey.getId());
                return "redirect:/" + SHOW + "/" + authUserEnvKey.getId();
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
            AuthUserEnvKey authUserEnvKey = authUserEnvKeyService.findById(faker(id));
            authUserEnvKey.setId(faker(authUserEnvKey.getId()));
            model.addAttribute(MODEL, authUserEnvKey);
            model.addAttribute("_action", "edit");
            return FORM;
        } catch (AuthUserEnvKeyNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:index";
        }
    }

    @PostMapping("/edit/{id}")
    public String update(
            @PathVariable("id") BigInteger id,
            @ModelAttribute(MODEL) @Valid AuthUserEnvKey currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        currObject.setId(faker(id));

        commonPost(currObject);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());

        if (!bindingResult.hasErrors()) {
            try {
                AuthUserEnvKey authUserEnvKey = authUserEnvKeyService.update(currObject);
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_EDITED, authUserEnvKey.getId());
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
            AuthUserEnvKey authUserEnvKey = authUserEnvKeyService.findById(faker(id));
            authUserEnvKey.setId(faker(authUserEnvKey.getId()));
            model.addAttribute(MODEL, authUserEnvKey);
            model.addAttribute("_action", "copy");
            return FORM;
        } catch (AuthUserEnvKeyNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:index";
        }
    }

    @PostMapping("/copy/{id}")
    public String copied(
            @PathVariable("id") BigInteger id,
            @ModelAttribute(MODEL) @Valid AuthUserEnvKey currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        currObject.setId(faker(id));

        commonPost(currObject);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());

        if (!bindingResult.hasErrors()) {
            try {
                AuthUserEnvKey authUserEnvKey = authUserEnvKeyService.copy(currObject);
                authUserEnvKey.setId(faker(authUserEnvKey.getId()));
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_COPIED, authUserEnvKey.getId());
                return "redirect:/" + SHOW + "/" + authUserEnvKey.getId();
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
        List<AuthUserEnvKey> authUserEnvKeys;
   
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            authUserEnvKeys = authUserEnvKeyService.search(searchCriteria);
        } else {
            authUserEnvKeys = authUserEnvKeyService.findAll(searchCriteria);
        }
        model.addAttribute(MODELS, authUserEnvKeys);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
        
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<AuthUserEnvKey> authUserEnvKeys = authUserEnvKeyService.findAll();
        for (AuthUserEnvKey obj : authUserEnvKeys) {
            obj.setId(faker(obj.getId()));
        }
        model.addAttribute(MODELS, authUserEnvKeys);
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/index"));
        return INDEX;
    }

    @GetMapping(value = {"/", "/index", ""})
    public String index(ModelMap model) {
        /*_SearchDTO searchCriteria = new _SearchDTO();
        searchCriteria.setPage(1);
        searchCriteria.setPageSize(5);
        
        List<AuthUserEnvKey> authUserEnvKeys = authUserEnvKeyService.findAll(searchCriteria);

        model.addAttribute(MODELS, authUserEnvKeys);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
    
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<AuthUserEnvKey> authUserEnvKeys = authUserEnvKeyService.findAll();
        for (AuthUserEnvKey obj : authUserEnvKeys) {
            obj.setId(faker(obj.getId()));
        }
        model.addAttribute(MODELS, authUserEnvKeys);
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/index"));
        return INDEX;
    }


    @GetMapping(value = "/show/{id}")
    public String show(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes redirectAttributes) {

        try {
            AuthUserEnvKey authUserEnvKey = authUserEnvKeyService.findById(faker(id));
            authUserEnvKey.setId(faker(authUserEnvKey.getId()));
            model.addAttribute(MODEL, authUserEnvKey);
            return SHOW;
        } catch (AuthUserEnvKeyNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:/" + INDEX;
        }
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") BigInteger id, RedirectAttributes redirectAttributes) {

        try {
            AuthUserEnvKey deleted = authUserEnvKeyService.delete(faker(id));
            addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getId());
            return "redirect:/" + INDEX;
        } catch (AuthUserEnvKeyNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND, e);
            return "redirect:/" + INDEX;
        } catch (Exception e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, "", e);
            return "redirect:/" + INDEX;
        }
    }
}
