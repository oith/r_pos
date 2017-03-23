package org.reflection.controller;

import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosPaymentNotFoundException;
import org.reflection.model.pos.PosPayment;
import org.reflection.service.PosPaymentService;
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
@RequestMapping(value = "/posPayment")

public class PosPaymentController extends _BaseController {

    protected static final String MODEL = "posPayment";
    protected static final String MODELS = MODEL + "s";
    protected static final String INDEX = MODEL + "/index";
    protected static final String SHOW = MODEL + "/show";
    protected static final String FORM = MODEL + "/form";

    @Autowired
    private PosPaymentService posPaymentService;

    @Autowired
    private AuthUserService authUserService;
    @Autowired
    private PosPurchaseMasterService posPurchaseMasterService;
    @Autowired
    private PosSupplierService posSupplierService;


    @GetMapping("/getCodableDTO")
    public
    @ResponseBody
    String getCodableDTO(@RequestParam(value = "code") String code) {
        try {
            PosPayment codable = posPaymentService.findByCode(code);
            return codable.getRemarks();
        } catch (Exception e) {
            System.out.println("mac err codable PosPayment Not Found: " + e);
            return "Not Found";
        }
    }


    private void commonPost(PosPayment currObject) {
        try {
            currObject.setAuthUserTransBy(authUserService.findByUsername(currObject.getAuthUserTransBy().getUsername()));
        } catch (Exception e) {
            currObject.setAuthUserTransBy(null);
        }
        try {
            currObject.setPosPurchaseMaster(posPurchaseMasterService.findByCode(currObject.getPosPurchaseMaster().getCode()));
        } catch (Exception e) {
            currObject.setPosPurchaseMaster(null);
        }
        try {
            currObject.setPosSupplier(posSupplierService.findByMobile(currObject.getPosSupplier().getMobile()));
        } catch (Exception e) {
            currObject.setPosSupplier(null);
        }


    }

    @GetMapping("/create")
    public String create(ModelMap model) {
        model.addAttribute(MODEL, new PosPayment());
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/create"));
        model.addAttribute("_action", "create");
        return FORM;
    }

    @PostMapping("/create")
    public String save(
            @ModelAttribute(MODEL) @Valid PosPayment currObject,
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
                PosPayment posPayment = posPaymentService.create(currObject);
                posPayment.setId(faker(posPayment.getId()));
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_CREATED, posPayment.getCode());
                return "redirect:/" + SHOW + "/" + posPayment.getId();
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
            PosPayment posPayment = posPaymentService.findById(faker(id));
            posPayment.setId(faker(posPayment.getId()));
            model.addAttribute(MODEL, posPayment);
            model.addAttribute("_action", "edit");
            return FORM;
        } catch (PosPaymentNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:index";
        }
    }

    @PostMapping("/edit/{id}")
    public String update(
            @PathVariable("id") BigInteger id,
            @ModelAttribute(MODEL) @Valid PosPayment currObject,
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
                PosPayment posPayment = posPaymentService.update(currObject);
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_EDITED, posPayment.getCode());
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
            PosPayment posPayment = posPaymentService.findById(faker(id));
            posPayment.setId(faker(posPayment.getId()));
            model.addAttribute(MODEL, posPayment);
            model.addAttribute("_action", "copy");
            return FORM;
        } catch (PosPaymentNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:index";
        }
    }

    @PostMapping("/copy/{id}")
    public String copied(
            @PathVariable("id") BigInteger id,
            @ModelAttribute(MODEL) @Valid PosPayment currObject,
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
                PosPayment posPayment = posPaymentService.copy(currObject);
                posPayment.setId(faker(posPayment.getId()));
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_COPIED, posPayment.getCode());
                return "redirect:/" + SHOW + "/" + posPayment.getId();
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
        List<PosPayment> posPayments;
   
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            posPayments = posPaymentService.search(searchCriteria);
        } else {
            posPayments = posPaymentService.findAll(searchCriteria);
        }
        model.addAttribute(MODELS, posPayments);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
        
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<PosPayment> posPayments = posPaymentService.findAll();
        for (PosPayment obj : posPayments) {
            obj.setId(faker(obj.getId()));
        }
        model.addAttribute(MODELS, posPayments);
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/index"));
        return INDEX;
    }

    @GetMapping(value = {"/", "/index", ""})
    public String index(ModelMap model) {
        /*_SearchDTO searchCriteria = new _SearchDTO();
        searchCriteria.setPage(1);
        searchCriteria.setPageSize(5);
        
        List<PosPayment> posPayments = posPaymentService.findAll(searchCriteria);

        model.addAttribute(MODELS, posPayments);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
    
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<PosPayment> posPayments = posPaymentService.findAll();
        for (PosPayment obj : posPayments) {
            obj.setId(faker(obj.getId()));
        }
        model.addAttribute(MODELS, posPayments);
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/index"));
        return INDEX;
    }


    @GetMapping(value = "/show/{id}")
    public String show(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes redirectAttributes) {

        try {
            PosPayment posPayment = posPaymentService.findById(faker(id));
            posPayment.setId(faker(posPayment.getId()));
            model.addAttribute(MODEL, posPayment);
            return SHOW;
        } catch (PosPaymentNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:/" + INDEX;
        }
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") BigInteger id, RedirectAttributes redirectAttributes) {

        try {
            PosPayment deleted = posPaymentService.delete(faker(id));
            addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getCode());
            return "redirect:/" + INDEX;
        } catch (PosPaymentNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND, e);
            return "redirect:/" + INDEX;
        } catch (Exception e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, "", e);
            return "redirect:/" + INDEX;
        }
    }
}
