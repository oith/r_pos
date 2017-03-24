package org.reflection.controller;

import org.reflection.dto._SearchDTO;
import org.reflection.exception.PosProductNotFoundException;
import org.reflection.model.pos.PosProduct;
import org.reflection.model.pos.PosProductAc;
import org.reflection.service.PosProductAcService;
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
@RequestMapping(value = "/posProduct")
@SessionAttributes({"acOnes", "acTwos", "acThrees", "acFours", "acFives"})
public class PosProductController extends _BaseController {

    protected static final String MODEL = "posProduct";
    protected static final String MODELS = MODEL + "s";
    protected static final String INDEX = MODEL + "/index";
    protected static final String SHOW = MODEL + "/show";
    protected static final String FORM = MODEL + "/form";

    @Autowired
    private PosProductService posProductService;

    @Autowired
    private PosProductAcService posProductAcService;


    @GetMapping("/getCodableDTO")
    public
    @ResponseBody
    String getCodableDTO(@RequestParam(value = "code") String code) {
        try {
            PosProduct codable = posProductService.findByCode(code);
            return codable.getFullName();
        } catch (Exception e) {
            System.out.println("mac err codable PosProduct Not Found: " + e);
            return "Not Found";
        }
    }

    @ModelAttribute("acOnes")
    public Iterable<PosProductAc> acOnes() {
        return posProductAcService.findAll();
    }

    @ModelAttribute("acTwos")
    public Iterable<PosProductAc> acTwos() {
        return posProductAcService.findAll();
    }

    @ModelAttribute("acThrees")
    public Iterable<PosProductAc> acThrees() {
        return posProductAcService.findAll();
    }

    @ModelAttribute("acFours")
    public Iterable<PosProductAc> acFours() {
        return posProductAcService.findAll();
    }

    @ModelAttribute("acFives")
    public Iterable<PosProductAc> acFives() {
        return posProductAcService.findAll();
    }


    private void commonPost(PosProduct currObject) {


    }

    @GetMapping("/create")
    public String create(ModelMap model) {
        model.addAttribute(MODEL, new PosProduct());
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/create"));
        model.addAttribute("_action", "create");
        return FORM;
    }

    @PostMapping("/create")
    public String save(
            @ModelAttribute(MODEL) @Valid PosProduct currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        commonPost(currObject);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());
        //currObject.setCode(genCode("ZxLookup", "LK-", 4));

        if (!bindingResult.hasErrors()) {
            try {
                PosProduct posProduct = posProductService.create(currObject);
                posProduct.setId(faker(posProduct.getId()));
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_CREATED, posProduct.getCode());
                return "redirect:/" + SHOW + "/" + posProduct.getId();
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
            PosProduct posProduct = posProductService.findById(faker(id));
            posProduct.setId(faker(posProduct.getId()));
            model.addAttribute(MODEL, posProduct);
            model.addAttribute("_action", "edit");
            return FORM;
        } catch (PosProductNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:index";
        }
    }

    @PostMapping("/edit/{id}")
    public String update(
            @PathVariable("id") BigInteger id,
            @ModelAttribute(MODEL) @Valid PosProduct currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        currObject.setId(faker(id));

        commonPost(currObject);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());

        if (!bindingResult.hasErrors()) {
            try {
                PosProduct posProduct = posProductService.update(currObject);
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_EDITED, posProduct.getCode());
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
            PosProduct posProduct = posProductService.findById(faker(id));
            posProduct.setId(faker(posProduct.getId()));
            model.addAttribute(MODEL, posProduct);
            model.addAttribute("_action", "copy");
            return FORM;
        } catch (PosProductNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:index";
        }
    }

    @PostMapping("/copy/{id}")
    public String copied(
            @PathVariable("id") BigInteger id,
            @ModelAttribute(MODEL) @Valid PosProduct currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes redirectAttributes) {

        currObject.setId(faker(id));

        commonPost(currObject);

        //currObject.getEmbdAudit().setEditBy(currAuthUser());

        if (!bindingResult.hasErrors()) {
            try {
                PosProduct posProduct = posProductService.copy(currObject);
                posProduct.setId(faker(posProduct.getId()));
                addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_COPIED, posProduct.getCode());
                return "redirect:/" + SHOW + "/" + posProduct.getId();
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
        List<PosProduct> posProducts;
   
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            posProducts = posProductService.search(searchCriteria);
        } else {
            posProducts = posProductService.findAll(searchCriteria);
        }
        model.addAttribute(MODELS, posProducts);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
        
        List<Integer> pages = new ArrayList();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<PosProduct> posProducts = posProductService.findAll();
        for (PosProduct obj : posProducts) {
            obj.setId(faker(obj.getId()));
        }
        model.addAttribute(MODELS, posProducts);
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/index"));
        return INDEX;
    }

    @GetMapping(value = {"/", "/index", ""})
    public String index(ModelMap model) {
        /*_SearchDTO searchCriteria = new _SearchDTO();
        searchCriteria.setPage(1);
        searchCriteria.setPageSize(5);
        
        List<PosProduct> posProducts = posProductService.findAll(searchCriteria);

        model.addAttribute(MODELS, posProducts);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
    
        List<Integer> pages = new ArrayList();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<PosProduct> posProducts = posProductService.findAll();
        for (PosProduct obj : posProducts) {
            obj.setId(faker(obj.getId()));
        }
        model.addAttribute(MODELS, posProducts);
        model.addAttribute("_menuCode", MENU_CODE_MAP.get("/" + MODEL + "/index"));
        return INDEX;
    }


    @GetMapping(value = "/show/{id}")
    public String show(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes redirectAttributes) {

        try {
            PosProduct posProduct = posProductService.findById(faker(id));
            posProduct.setId(faker(posProduct.getId()));
            model.addAttribute(MODEL, posProduct);
            return SHOW;
        } catch (PosProductNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND);
            return "redirect:/" + INDEX;
        }
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") BigInteger id, RedirectAttributes redirectAttributes) {

        try {
            PosProduct deleted = posProductService.delete(faker(id));
            addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getCode());
            return "redirect:/" + INDEX;
        } catch (PosProductNotFoundException e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, ERROR_MESSAGE_KEY_OBJECT_NOT_FOUND, e);
            return "redirect:/" + INDEX;
        } catch (Exception e) {
            addFeedbackMessage(redirectAttributes, FLASH_ERROR, "", e);
            return "redirect:/" + INDEX;
        }
    }
}
