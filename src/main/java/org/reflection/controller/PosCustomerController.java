package org.reflection.controller;

import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosCustomerNotFoundException;
import org.reflection.model.pos.PosCustomer;
import org.reflection.service.PosCustomerService;
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
@RequestMapping(value = "/posCustomer")

public class PosCustomerController extends _BaseController {

    protected static final String MODEL = "posCustomer";
    protected static final String MODELS = MODEL + "s";
    protected static final String INDEX = MODEL + "/index";
    protected static final String SHOW = MODEL + "/show";
    protected static final String FORM = MODEL + "/form";

    @Autowired
    private PosCustomerService posCustomerService;


    @GetMapping("/getCodableDTO")
    public
    @ResponseBody
    String getCodableDTO(@RequestParam(value = "mobile") String mobile) {
        try {
            PosCustomer codable = posCustomerService.findByMobile(mobile);
            return codable.getFullName();
        } catch (Exception e) {
            System.out.println("mac err codable PosCustomer Not Found: " + e);
            return "Not Found";
        }
    }


    private void commonPost(PosCustomer currObject, MultipartHttpServletRequest request) {

        String pic = multipartImageFileHandler(request, "pic");
        if (pic != null && !pic.isEmpty()) {
            currObject.setPic(pic);
        }

    }

    @GetMapping("/create")
    public String create(ModelMap model) {
        model.addAttribute(MODEL, new PosCustomer());
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/create"));
        model.addAttribute("_action", "create");
        return FORM;
    }

    @PostMapping("/create")
    public String save(
            @ModelAttribute(MODEL) @Valid PosCustomer currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes, MultipartHttpServletRequest request) {

        commonPost(currObject, request);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());
        //currObject.setCode(genCode("ZxLookup", "LK-", 4));

        if (!bindingResult.hasErrors()) {
            try {
                PosCustomer posCustomer = posCustomerService.create(currObject);
                posCustomer.setId(faker(posCustomer.getId()));
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_CREATED, posCustomer.getCode());
                return "redirect:/" + SHOW + "/" + posCustomer.getId();
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
            PosCustomer posCustomer = posCustomerService.findById(faker(id));
            posCustomer.setId(faker(posCustomer.getId()));
            model.addAttribute(MODEL, posCustomer);
            model.addAttribute("_action", "edit");
            return FORM;
        } catch (PosCustomerNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:index";
        }
    }

    @PostMapping("/edit/{id}")
    public String update(
            @PathVariable("id") BigInteger id,
            @ModelAttribute(MODEL) @Valid PosCustomer currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes, MultipartHttpServletRequest request) {

        currObject.setId(faker(id));

        commonPost(currObject, request);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());

        if (!bindingResult.hasErrors()) {
            try {
                PosCustomer posCustomer = posCustomerService.update(currObject);
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_EDITED, posCustomer.getCode());
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
            PosCustomer posCustomer = posCustomerService.findById(faker(id));
            posCustomer.setId(faker(posCustomer.getId()));
            model.addAttribute(MODEL, posCustomer);
            model.addAttribute("_action", "copy");
            return FORM;
        } catch (PosCustomerNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:index";
        }
    }

    @PostMapping("/copy/{id}")
    public String copied(
            @PathVariable("id") BigInteger id,
            @ModelAttribute(MODEL) @Valid PosCustomer currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes, MultipartHttpServletRequest request) {

        currObject.setId(faker(id));

        commonPost(currObject, request);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());

        if (!bindingResult.hasErrors()) {
            try {
                PosCustomer posCustomer = posCustomerService.copy(currObject);
                posCustomer.setId(faker(posCustomer.getId()));
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_COPIED, posCustomer.getCode());
                return "redirect:/" + SHOW + "/" + posCustomer.getId();
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
        List<PosCustomer> posCustomers;
   
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            posCustomers = posCustomerService.search(searchCriteria);
        } else {
            posCustomers = posCustomerService.findAll(searchCriteria);
        }
        model.addAttribute(MODELS, posCustomers);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
        
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<PosCustomer> posCustomers = posCustomerService.findAll();
        for (PosCustomer obj : posCustomers) {
            obj.setId(faker(obj.getId()));
        }
        model.addAttribute(MODELS, posCustomers);
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/index"));
        return INDEX;
    }

    @GetMapping(value = {"/", "/index", ""})
    public String index(ModelMap model) {
        /*_SearchDTO searchCriteria = new _SearchDTO();
        searchCriteria.setPage(1);
        searchCriteria.setPageSize(5);
        
        List<PosCustomer> posCustomers = posCustomerService.findAll(searchCriteria);

        model.addAttribute(MODELS, posCustomers);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
    
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<PosCustomer> posCustomers = posCustomerService.findAll();
        for (PosCustomer obj : posCustomers) {
            obj.setId(faker(obj.getId()));
        }
        model.addAttribute(MODELS, posCustomers);
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/index"));
        return INDEX;
    }


    @GetMapping(value = "/show/{id}")
    public String show(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes redirectAttributes) {

        try {
            PosCustomer posCustomer = posCustomerService.findById(faker(id));
            posCustomer.setId(faker(posCustomer.getId()));
            model.addAttribute(MODEL, posCustomer);
            return SHOW;
        } catch (PosCustomerNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:/" + INDEX;
        }
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") BigInteger id, RedirectAttributes redirectAttributes) {

        try {
            PosCustomer deleted = posCustomerService.delete(faker(id));
            addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getCode());
            return "redirect:/" + INDEX;
        } catch (PosCustomerNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND, e);
            return "redirect:/" + INDEX;
        } catch (Exception e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, "", e);
            return "redirect:/" + INDEX;
        }
    }
}
