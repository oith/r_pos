package org.reflection.controller;

import org.reflection.dto._GenDTO;
import org.reflection.model.auth.AuthUser;
import org.reflection.service.util.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/profile")
public class _AdmProfileController extends _BaseController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/changePassword")
    public String changePassword(ModelMap model) {

        _GenDTO genDTO = new _GenDTO();
        model.addAttribute("_GenDTO", genDTO);

        return "_admProfile/changePassword";
    }

    @PostMapping("/changePassword")
    public String changePasswordSubmit(ModelMap model,
                                       @ModelAttribute("_GenDTO") _GenDTO genDTO,
                                       BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes) {

        try {
            profileService.changePassword(genDTO.getData1(), genDTO.getData2(), genDTO.getData3());
            addFeedbackMessage(redirectAttributes, FLASH_SUCCESS, "default.message.passwordChangedSuccess");
            return "_admProfile/changePassword";
        } catch (Exception e) {
            System.out.println("yyyyyyyyyyy" + e);
            ObjectError yyyy = new ObjectError("data1", "Info: " + e.getMessage() + "");

            bindingResult.addError(yyyy);
            return "_admProfile/changePassword";
        }
    }

    @GetMapping("/show")
    public String profile(ModelMap model) {
        AuthUser authUser = profileService.currentAuthUser();
        model.addAttribute("authUser", authUser);
        return "_admProfile/show";
    }
}
