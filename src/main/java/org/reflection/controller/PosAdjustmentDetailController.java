package org.reflection.controller;

import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosAdjustmentDetailNotFoundException;
import org.reflection.model.pos.PosAdjustmentDetail;
import org.reflection.service.PosAdjustmentDetailService;
import org.reflection.service.PosAdjustmentMasterService;
import org.reflection.service.PosProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigInteger;

@Controller
@RequestMapping(value = "/posAdjustmentDetail")

public class PosAdjustmentDetailController extends _BaseController {

    protected static final String MODEL = "posAdjustmentDetail";
    protected static final String MODELS = MODEL + "s";
    protected static final String INDEX = MODEL + "/index";
    protected static final String SHOW = MODEL + "/show";
    protected static final String FORM = MODEL + "/form";

    @Autowired
    private PosAdjustmentDetailService posAdjustmentDetailService;

    @Autowired
    private PosAdjustmentMasterService posAdjustmentMasterService;
    @Autowired
    private PosProductService posProductService;


    private void commonPost(PosAdjustmentDetail currObject) {
        try {
            currObject.setPosAdjustmentMaster(posAdjustmentMasterService.findByCode(currObject.getPosAdjustmentMaster().getCode()));
        } catch (Exception e) {
            currObject.setPosAdjustmentMaster(null);
        }
        try {
            currObject.setPosProduct(posProductService.findByCode(currObject.getPosProduct().getCode()));
        } catch (Exception e) {
            currObject.setPosProduct(null);
        }


    }

    @GetMapping("/create")
    public String create(ModelMap model) {
        model.addAttribute(MODEL, new PosAdjustmentDetail());
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/create"));
        model.addAttribute("_action", "create");
        return FORM;
    }

    @PostMapping("/create")
    public String save(
            @ModelAttribute(MODEL) @Valid PosAdjustmentDetail currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        commonPost(currObject);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());
        //currObject.setCode(genCode("ZxLookup", "LK-", 4));

        if (!bindingResult.hasErrors()) {
            try {
                PosAdjustmentDetail posAdjustmentDetail = posAdjustmentDetailService.create(currObject);
                posAdjustmentDetail.setId(faker(posAdjustmentDetail.getId()));
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_CREATED, posAdjustmentDetail.getId());
                return "redirect:/" + SHOW + "/" + posAdjustmentDetail.getId();
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
            PosAdjustmentDetail posAdjustmentDetail = posAdjustmentDetailService.findById(faker(id));
            posAdjustmentDetail.setId(faker(posAdjustmentDetail.getId()));
            model.addAttribute(MODEL, posAdjustmentDetail);
            model.addAttribute("_action", "edit");
            return FORM;
        } catch (PosAdjustmentDetailNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:index";
        }
    }

    @PostMapping("/edit/{id}")
    public String update(
            @PathVariable("id") BigInteger id,
            @ModelAttribute(MODEL) @Valid PosAdjustmentDetail currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        currObject.setId(faker(id));

        commonPost(currObject);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());

        if (!bindingResult.hasErrors()) {
            try {
                PosAdjustmentDetail posAdjustmentDetail = posAdjustmentDetailService.update(currObject);
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_EDITED, posAdjustmentDetail.getId());
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
            PosAdjustmentDetail posAdjustmentDetail = posAdjustmentDetailService.findById(faker(id));
            posAdjustmentDetail.setId(faker(posAdjustmentDetail.getId()));
            model.addAttribute(MODEL, posAdjustmentDetail);
            model.addAttribute("_action", "copy");
            return FORM;
        } catch (PosAdjustmentDetailNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:index";
        }
    }

    @PostMapping("/copy/{id}")
    public String copied(
            @PathVariable("id") BigInteger id,
            @ModelAttribute(MODEL) @Valid PosAdjustmentDetail currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        currObject.setId(faker(id));

        commonPost(currObject);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());

        if (!bindingResult.hasErrors()) {
            try {
                PosAdjustmentDetail posAdjustmentDetail = posAdjustmentDetailService.copy(currObject);
                posAdjustmentDetail.setId(faker(posAdjustmentDetail.getId()));
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_COPIED, posAdjustmentDetail.getId());
                return "redirect:/" + SHOW + "/" + posAdjustmentDetail.getId();
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
        List<PosAdjustmentDetail> posAdjustmentDetails;
   
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            posAdjustmentDetails = posAdjustmentDetailService.search(searchCriteria);
        } else {
            posAdjustmentDetails = posAdjustmentDetailService.findAll(searchCriteria);
        }
        model.addAttribute(MODELS, posAdjustmentDetails);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
        
        List<Integer> pages = new ArrayList();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<PosAdjustmentDetail> posAdjustmentDetails = posAdjustmentDetailService.findAll();
        for (PosAdjustmentDetail obj : posAdjustmentDetails) {
            obj.setId(faker(obj.getId()));
        }
        model.addAttribute(MODELS, posAdjustmentDetails);
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/index"));
        return INDEX;
    }

    @GetMapping(value = {"/", "/index", ""})
    public String index(ModelMap model) {
        /*_SearchDTO searchCriteria = new _SearchDTO();
        searchCriteria.setPage(1);
        searchCriteria.setPageSize(5);
        
        List<PosAdjustmentDetail> posAdjustmentDetails = posAdjustmentDetailService.findAll(searchCriteria);

        model.addAttribute(MODELS, posAdjustmentDetails);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
    
        List<Integer> pages = new ArrayList();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<PosAdjustmentDetail> posAdjustmentDetails = posAdjustmentDetailService.findAll();
        for (PosAdjustmentDetail obj : posAdjustmentDetails) {
            obj.setId(faker(obj.getId()));
        }
        model.addAttribute(MODELS, posAdjustmentDetails);
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/index"));
        return INDEX;
    }


    @GetMapping(value = "/show/{id}")
    public String show(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes redirectAttributes) {

        try {
            PosAdjustmentDetail posAdjustmentDetail = posAdjustmentDetailService.findById(faker(id));
            posAdjustmentDetail.setId(faker(posAdjustmentDetail.getId()));
            model.addAttribute(MODEL, posAdjustmentDetail);
            return SHOW;
        } catch (PosAdjustmentDetailNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:/" + INDEX;
        }
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") BigInteger id, RedirectAttributes redirectAttributes) {

        try {
            PosAdjustmentDetail deleted = posAdjustmentDetailService.delete(faker(id));
            addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getId());
            return "redirect:/" + INDEX;
        } catch (PosAdjustmentDetailNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND, e);
            return "redirect:/" + INDEX;
        } catch (Exception e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, "", e);
            return "redirect:/" + INDEX;
        }
    }
}
