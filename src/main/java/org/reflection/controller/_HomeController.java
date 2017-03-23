package org.reflection.controller;

import org.reflection.service.etc.BootStrapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class _HomeController {

    private final SortedSet<String> list = new TreeSet();
    @Resource
    private MessageSource messageSource;
    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
    @Autowired
    private BootStrapService bootStrapService;


    @GetMapping("/refresh")
    public String refresh(HttpServletRequest httpServletRequest, Locale locale) {
        HttpSession uuu = httpServletRequest.getSession();

        String omi[] = {"authRoles",
                "selectAny",
                "javax.servlet.jsp.jstl.fmt.request.charset",
                "org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN",
                "SPRING_SECURITY_CONTEXT"
        };
        for (Enumeration<String> gg = uuu.getAttributeNames(); gg.hasMoreElements(); ) {
            String next = gg.nextElement();
            System.out.println("Attribute: " + next + " val: " + uuu.getAttribute(next));

            if (!(next.equals(omi[0])
                    || next.equals(omi[4])
                    || next.equals(omi[1])
                    || next.equals(omi[2])
                    || next.equals(omi[3]))) {
                uuu.removeAttribute(next);
            }
        }
        uuu.setAttribute("selectAny", messageSource.getMessage("default.combo.select.one.label", null, locale));
        //model.addAttribute("selectAny", );

        return "layouts/main";
    }

    @GetMapping(value = {"/", "/index"})
    public String index(ModelMap model) {
        return "index";
    }

    @GetMapping("properties")
    @ResponseBody
    java.util.Properties properties() {
        return System.getProperties();
    }

    @GetMapping("/main")
    public String main(ModelMap model) {
        model.addAttribute("controllerList", list);
        //model.addAttribute("loggedinuser", getPrincipal());
        return "layouts/main";
    }

    @GetMapping("/error")
    public String error(ModelMap model) {
        //model.addAttribute("controllerList", list);
        //model.addAttribute("loggedinuser", getPrincipal());
        return "error";
    }

    @PostConstruct
    public void init() {
        Map<RequestMappingInfo, HandlerMethod> handlerMethods
                = this.requestMappingHandlerMapping.getHandlerMethods();

        System.out.println("mac handlerMethods: " + handlerMethods);

        // index(null, null);
//        for (Entry<RequestMappingInfo, HandlerMethod> item : handlerMethods.entrySet()) {
//            RequestMappingInfo mapping = item.getKey();
//            HandlerMethod method = item.getValue();
//
//            for (String urlPattern : mapping.getPatternsCondition().getPatterns()) {
//                //System.out.println(method.getBeanType().getName() + "#" + method.getMethod().getName()+ " <-- " + urlPattern);
//
//                if (urlPattern.endsWith("index")
//                        || urlPattern.endsWith("excelFileUpload")
//                        || urlPattern.endsWith("reportCenter")
//                        || urlPattern.endsWith("processCenter")
//                        || urlPattern.endsWith("queryCenter")) {
//                    //System.out.println(method.getBeanType().getName() + "#" + method.getMethod().getName() + " <-- " + urlPattern);
//
//                    list.add(urlPattern);
//                }
//                //if (urlPattern.equals("some specific url")) {
//                //add to list of matching METHODS
//                //}
//            }
//        }
        bootStrapService.dummyMenuData(list);
        bootStrapService.mock();
    }
}
