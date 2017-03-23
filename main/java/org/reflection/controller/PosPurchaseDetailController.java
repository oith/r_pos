package org.reflection.controller;

import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosPurchaseDetailNotFoundException;
import org.reflection.model.pos.PosPurchaseDetail;
import org.reflection.service.PosProductService;
import org.reflection.service.PosPurchaseDetailService;
import org.reflection.service.PosPurchaseMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigInteger;

@Controller
@RequestMapping(value = "/posPurchaseDetail")

public class PosPurchaseDetailController extends _BaseController {

    protected static final String MODEL = "posPurchaseDetail";
    protected static final String MODELS = MODEL + "s";
    protected static final String INDEX = MODEL + "/index";
    protected static final String SHOW = MODEL + "/show";
    protected static final String FORM = MODEL + "/form";

    @Autowired
    private PosPurchaseDetailService posPurchaseDetailService;

    @Autowired
    private PosPurchaseMasterService posPurchaseMasterService;
    @Autowired
    private PosProductService posProductService;


    private void commonPost(PosPurchaseDetail currObject) {
        try {
            currObject.setPosPurchaseMaster(posPurchaseMasterService.findByCode(currObject.getPosPurchaseMaster().getCode()));
        } catch (Exception e) {
            currObject.setPosPurchaseMaster(null);
        }
        try {
            currObject.setPosProduct(posProductService.findByCode(currObject.getPosProduct().getCode()));
        } catch (Exception e) {
            currObject.setPosProduct(null);
        }


    }

    @GetMapping("/create")
    public String create(ModelMap model) {
        model.addAttribute(MODEL, new PosPurchaseDetail());
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/create"));
        model.addAttribute("_action", "create");
        return FORM;
    }

    @PostMapping("/create")
    public String save(
            @ModelAttribute(MODEL) @Valid PosPurchaseDetail currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        commonPost(currObject);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());
        //currObject.setCode(genCode("ZxLookup", "LK-", 4));

        if (!bindingResult.hasErrors()) {
            try {
                PosPurchaseDetail posPurchaseDetail = posPurchaseDetailService.create(currObject);
                posPurchaseDetail.setId(faker(posPurchaseDetail.getId()));
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_CREATED, posPurchaseDetail.getId());
                return "redirect:/" + SHOW + "/" + posPurchaseDetail.getId();
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
            PosPurchaseDetail posPurchaseDetail = posPurchaseDetailService.findById(faker(id));
            posPurchaseDetail.setId(faker(posPurchaseDetail.getId()));
            model.addAttribute(MODEL, posPurchaseDetail);
            model.addAttribute("_action", "edit");
            return FORM;
        } catch (PosPurchaseDetailNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:index";
        }
    }

    @PostMapping("/edit/{id}")
    public String update(
            @PathVariable("id") BigInteger id,
            @ModelAttribute(MODEL) @Valid PosPurchaseDetail currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        currObject.setId(faker(id));

        commonPost(currObject);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());

        if (!bindingResult.hasErrors()) {
            try {
                PosPurchaseDetail posPurchaseDetail = posPurchaseDetailService.update(currObject);
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_EDITED, posPurchaseDetail.getId());
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
            PosPurchaseDetail posPurchaseDetail = posPurchaseDetailService.findById(faker(id));
            posPurchaseDetail.setId(faker(posPurchaseDetail.getId()));
            model.addAttribute(MODEL, posPurchaseDetail);
            model.addAttribute("_action", "copy");
            return FORM;
        } catch (PosPurchaseDetailNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:index";
        }
    }

    @PostMapping("/copy/{id}")
    public String copied(
            @PathVariable("id") BigInteger id,
            @ModelAttribute(MODEL) @Valid PosPurchaseDetail currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        currObject.setId(faker(id));

        commonPost(currObject);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());

        if (!bindingResult.hasErrors()) {
            try {
                PosPurchaseDetail posPurchaseDetail = posPurchaseDetailService.copy(currObject);
                posPurchaseDetail.setId(faker(posPurchaseDetail.getId()));
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_COPIED, posPurchaseDetail.getId());
                return "redirect:/" + SHOW + "/" + posPurchaseDetail.getId();
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
        List<PosPurchaseDetail> posPurchaseDetails;
   
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            posPurchaseDetails = posPurchaseDetailService.search(searchCriteria);
        } else {
            posPurchaseDetails = posPurchaseDetailService.findAll(searchCriteria);
        }
        model.addAttribute(MODELS, posPurchaseDetails);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
        
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<PosPurchaseDetail> posPurchaseDetails = posPurchaseDetailService.findAll();
        for (PosPurchaseDetail obj : posPurchaseDetails) {
            obj.setId(faker(obj.getId()));
        }
        model.addAttribute(MODELS, posPurchaseDetails);
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/index"));
        return INDEX;
    }

    @GetMapping(value = {"/", "/index", ""})
    public String index(ModelMap model) {
        /*_SearchDTO searchCriteria = new _SearchDTO();
        searchCriteria.setPage(1);
        searchCriteria.setPageSize(5);
        
        List<PosPurchaseDetail> posPurchaseDetails = posPurchaseDetailService.findAll(searchCriteria);

        model.addAttribute(MODELS, posPurchaseDetails);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
    
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<PosPurchaseDetail> posPurchaseDetails = posPurchaseDetailService.findAll();
        for (PosPurchaseDetail obj : posPurchaseDetails) {
            obj.setId(faker(obj.getId()));
        }
        model.addAttribute(MODELS, posPurchaseDetails);
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/index"));
        return INDEX;
    }


    @GetMapping(value = "/show/{id}")
    public String show(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes redirectAttributes) {

        try {
            PosPurchaseDetail posPurchaseDetail = posPurchaseDetailService.findById(faker(id));
            posPurchaseDetail.setId(faker(posPurchaseDetail.getId()));
            model.addAttribute(MODEL, posPurchaseDetail);
            return SHOW;
        } catch (PosPurchaseDetailNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:/" + INDEX;
        }
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") BigInteger id, RedirectAttributes redirectAttributes) {

        try {
            PosPurchaseDetail deleted = posPurchaseDetailService.delete(faker(id));
            addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getId());
            return "redirect:/" + INDEX;
        } catch (PosPurchaseDetailNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND, e);
            return "redirect:/" + INDEX;
        } catch (Exception e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, "", e);
            return "redirect:/" + INDEX;
        }
    }
}
