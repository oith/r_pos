package org.reflection.controller;

import org.reflection.model.auth.AuthUser;
import org.reflection.service.auth.AuthUserExt;
import org.reflection.service.auth.AuthUserService;
import org.reflection.service.auth.SpringSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class _AuthController {

    @Autowired
    private AuthenticationTrustResolver authenticationTrustResolver;
    @Autowired
    private AuthUserService authUserService;
    @Autowired
    private SpringSecurityService springSecurityService;


    @GetMapping("/login")
    public String loginPage() {
        if (isCurrentAuthenticationAnonymous()) {
            return "layouts/login";
        } else {
            return "redirect:/index";
        }
    }

    @GetMapping(value = "/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new AuthUser());
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String registration(@ModelAttribute("userForm") AuthUser userForm, BindingResult bindingResult, Model model) {


        if (bindingResult.hasErrors()) {
            return "registration";
        }

        authUserService.create(userForm);
        springSecurityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @GetMapping(value = "/welcome")
    public String welcome(Model model) {
        return "welcome";
    }

    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/login?logout";
    }

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof AuthUserExt) {
            userName = ((AuthUserExt) principal).getFullName();
        }
        return userName;
    }

    @GetMapping("/accessDenied")
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "layouts/accessDenied";
    }
}
