package org.reflection.controller;

import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosSalesDetailNotFoundException;
import org.reflection.model.pos.PosSalesDetail;
import org.reflection.service.PosProductService;
import org.reflection.service.PosSalesDetailService;
import org.reflection.service.PosSalesMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigInteger;

@Controller
@RequestMapping(value = "/posSalesDetail")

public class PosSalesDetailController extends _BaseController {

    protected static final String MODEL = "posSalesDetail";
    protected static final String MODELS = MODEL + "s";
    protected static final String INDEX = MODEL + "/index";
    protected static final String SHOW = MODEL + "/show";
    protected static final String FORM = MODEL + "/form";

    @Autowired
    private PosSalesDetailService posSalesDetailService;

    @Autowired
    private PosSalesMasterService posSalesMasterService;
    @Autowired
    private PosProductService posProductService;


    private void commonPost(PosSalesDetail currObject) {
        try {
            currObject.setPosSalesMaster(posSalesMasterService.findByCode(currObject.getPosSalesMaster().getCode()));
        } catch (Exception e) {
            currObject.setPosSalesMaster(null);
        }
        try {
            currObject.setPosProduct(posProductService.findByCode(currObject.getPosProduct().getCode()));
        } catch (Exception e) {
            currObject.setPosProduct(null);
        }


    }

    @GetMapping("/create")
    public String create(ModelMap model) {
        model.addAttribute(MODEL, new PosSalesDetail());
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/create"));
        model.addAttribute("_action", "create");
        return FORM;
    }

    @PostMapping("/create")
    public String save(
            @ModelAttribute(MODEL) @Valid PosSalesDetail currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        commonPost(currObject);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());
        //currObject.setCode(genCode("ZxLookup", "LK-", 4));

        if (!bindingResult.hasErrors()) {
            try {
                PosSalesDetail posSalesDetail = posSalesDetailService.create(currObject);
                posSalesDetail.setId(faker(posSalesDetail.getId()));
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_CREATED, posSalesDetail.getId());
                return "redirect:/" + SHOW + "/" + posSalesDetail.getId();
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
            PosSalesDetail posSalesDetail = posSalesDetailService.findById(faker(id));
            posSalesDetail.setId(faker(posSalesDetail.getId()));
            model.addAttribute(MODEL, posSalesDetail);
            model.addAttribute("_action", "edit");
            return FORM;
        } catch (PosSalesDetailNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:index";
        }
    }

    @PostMapping("/edit/{id}")
    public String update(
            @PathVariable("id") BigInteger id,
            @ModelAttribute(MODEL) @Valid PosSalesDetail currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        currObject.setId(faker(id));

        commonPost(currObject);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());

        if (!bindingResult.hasErrors()) {
            try {
                PosSalesDetail posSalesDetail = posSalesDetailService.update(currObject);
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_EDITED, posSalesDetail.getId());
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
            PosSalesDetail posSalesDetail = posSalesDetailService.findById(faker(id));
            posSalesDetail.setId(faker(posSalesDetail.getId()));
            model.addAttribute(MODEL, posSalesDetail);
            model.addAttribute("_action", "copy");
            return FORM;
        } catch (PosSalesDetailNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:index";
        }
    }

    @PostMapping("/copy/{id}")
    public String copied(
            @PathVariable("id") BigInteger id,
            @ModelAttribute(MODEL) @Valid PosSalesDetail currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        currObject.setId(faker(id));

        commonPost(currObject);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());

        if (!bindingResult.hasErrors()) {
            try {
                PosSalesDetail posSalesDetail = posSalesDetailService.copy(currObject);
                posSalesDetail.setId(faker(posSalesDetail.getId()));
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_COPIED, posSalesDetail.getId());
                return "redirect:/" + SHOW + "/" + posSalesDetail.getId();
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
        List<PosSalesDetail> posSalesDetails;
   
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            posSalesDetails = posSalesDetailService.search(searchCriteria);
        } else {
            posSalesDetails = posSalesDetailService.findAll(searchCriteria);
        }
        model.addAttribute(MODELS, posSalesDetails);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
        
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<PosSalesDetail> posSalesDetails = posSalesDetailService.findAll();
        for (PosSalesDetail obj : posSalesDetails) {
            obj.setId(faker(obj.getId()));
        }
        model.addAttribute(MODELS, posSalesDetails);
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/index"));
        return INDEX;
    }

    @GetMapping(value = {"/", "/index", ""})
    public String index(ModelMap model) {
        /*_SearchDTO searchCriteria = new _SearchDTO();
        searchCriteria.setPage(1);
        searchCriteria.setPageSize(5);
        
        List<PosSalesDetail> posSalesDetails = posSalesDetailService.findAll(searchCriteria);

        model.addAttribute(MODELS, posSalesDetails);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
    
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<PosSalesDetail> posSalesDetails = posSalesDetailService.findAll();
        for (PosSalesDetail obj : posSalesDetails) {
            obj.setId(faker(obj.getId()));
        }
        model.addAttribute(MODELS, posSalesDetails);
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/index"));
        return INDEX;
    }


    @GetMapping(value = "/show/{id}")
    public String show(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes redirectAttributes) {

        try {
            PosSalesDetail posSalesDetail = posSalesDetailService.findById(faker(id));
            posSalesDetail.setId(faker(posSalesDetail.getId()));
            model.addAttribute(MODEL, posSalesDetail);
            return SHOW;
        } catch (PosSalesDetailNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:/" + INDEX;
        }
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") BigInteger id, RedirectAttributes redirectAttributes) {

        try {
            PosSalesDetail deleted = posSalesDetailService.delete(faker(id));
            addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getId());
            return "redirect:/" + INDEX;
        } catch (PosSalesDetailNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND, e);
            return "redirect:/" + INDEX;
        } catch (Exception e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, "", e);
            return "redirect:/" + INDEX;
        }
    }
}
