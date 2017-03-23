package org.reflection.controller;

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AuthUserNotFoundException;
import org.reflection.model.auth.AuthGroup;
import org.reflection.model.auth.AuthUser;
import org.reflection.service.AuthGroupService;
import org.reflection.service.auth.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigInteger;

@Controller
@RequestMapping(value = "/authUser")
@SessionAttributes({"authGroups"})
public class AuthUserController extends _BaseController {

    protected static final String MODEL = "authUser";
    protected static final String MODELS = MODEL + "s";
    protected static final String INDEX = MODEL + "/index";
    protected static final String SHOW = MODEL + "/show";
    protected static final String FORM = MODEL + "/form";

    @Autowired
    private AuthUserService authUserService;

    @Autowired
    private AuthGroupService authGroupService;


    @GetMapping("/getCodableDTO")
    public
    @ResponseBody
    String getCodableDTO(@RequestParam(value = "username") String username) {
        try {
            AuthUser codable = authUserService.findByUsername(username);
            return codable.getFullName();
        } catch (Exception e) {
            System.out.println("mac err codable AuthUser Not Found: " + e);
            return "Not Found";
        }
    }

    @ModelAttribute("authGroups")
    public Iterable<AuthGroup> authGroups() {
        return authGroupService.findAll();
    }


    private void commonPost(AuthUser currObject, MultipartHttpServletRequest request) {

        String pic = multipartImageFileHandler(request, "pic");
        if (pic != null && !pic.isEmpty()) {
            currObject.setPic(pic);
        }

    }

    @GetMapping("/create")
    public String create(ModelMap model) {
        model.addAttribute(MODEL, new AuthUser());
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/create"));
        model.addAttribute("_action", "create");
        return FORM;
    }

    @PostMapping("/create")
    public String save(
            @ModelAttribute(MODEL) @Valid AuthUser currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes, MultipartHttpServletRequest request) {

        commonPost(currObject, request);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());
        //currObject.setCode(genCode("ZxLookup", "LK-", 4));

        if (!bindingResult.hasErrors()) {
            try {
                AuthUser authUser = authUserService.create(currObject);
                authUser.setId(faker(authUser.getId()));
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_CREATED, authUser.getId());
                return "redirect:/" + SHOW + "/" + authUser.getId();
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
            AuthUser authUser = authUserService.findById(faker(id));
            authUser.setId(faker(authUser.getId()));
            model.addAttribute(MODEL, authUser);
            model.addAttribute("_action", "edit");
            return FORM;
        } catch (AuthUserNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:index";
        }
    }

    @PostMapping("/edit/{id}")
    public String update(
            @PathVariable("id") BigInteger id,
            @ModelAttribute(MODEL) @Valid AuthUser currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes, MultipartHttpServletRequest request) {

        currObject.setId(faker(id));

        commonPost(currObject, request);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());

        if (!bindingResult.hasErrors()) {
            try {
                AuthUser authUser = authUserService.update(currObject);
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_EDITED, authUser.getId());
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
            AuthUser authUser = authUserService.findById(faker(id));
            authUser.setId(faker(authUser.getId()));
            model.addAttribute(MODEL, authUser);
            model.addAttribute("_action", "copy");
            return FORM;
        } catch (AuthUserNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:index";
        }
    }

    @PostMapping("/copy/{id}")
    public String copied(
            @PathVariable("id") BigInteger id,
            @ModelAttribute(MODEL) @Valid AuthUser currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes, MultipartHttpServletRequest request) {

        currObject.setId(faker(id));

        commonPost(currObject, request);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());

        if (!bindingResult.hasErrors()) {
            try {
                AuthUser authUser = authUserService.copy(currObject);
                authUser.setId(faker(authUser.getId()));
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_COPIED, authUser.getId());
                return "redirect:/" + SHOW + "/" + authUser.getId();
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
        List<AuthUser> authUsers;
   
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            authUsers = authUserService.search(searchCriteria);
        } else {
            authUsers = authUserService.findAll(searchCriteria);
        }
        model.addAttribute(MODELS, authUsers);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
        
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<AuthUser> authUsers = authUserService.findAll();
        for (AuthUser obj : authUsers) {
            obj.setId(faker(obj.getId()));
        }
        model.addAttribute(MODELS, authUsers);
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/index"));
        return INDEX;
    }

    @GetMapping(value = {"/", "/index", ""})
    public String index(ModelMap model) {
        /*_SearchDTO searchCriteria = new _SearchDTO();
        searchCriteria.setPage(1);
        searchCriteria.setPageSize(5);
        
        List<AuthUser> authUsers = authUserService.findAll(searchCriteria);

        model.addAttribute(MODELS, authUsers);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
    
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<AuthUser> authUsers = authUserService.findAll();
        for (AuthUser obj : authUsers) {
            obj.setId(faker(obj.getId()));
        }
        model.addAttribute(MODELS, authUsers);
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/index"));
        return INDEX;
    }


    @GetMapping(value = "/show/{id}")
    public String show(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes redirectAttributes) {

        try {
            AuthUser authUser = authUserService.findById(faker(id));
            authUser.setId(faker(authUser.getId()));
            model.addAttribute(MODEL, authUser);
            return SHOW;
        } catch (AuthUserNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:/" + INDEX;
        }
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") BigInteger id, RedirectAttributes redirectAttributes) {

        try {
            AuthUser deleted = authUserService.delete(faker(id));
            addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getId());
            return "redirect:/" + INDEX;
        } catch (AuthUserNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND, e);
            return "redirect:/" + INDEX;
        } catch (Exception e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, "", e);
            return "redirect:/" + INDEX;
        }
    }
}
