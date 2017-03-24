package org.reflection.service.auth;

import org.apache.commons.lang.LocaleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    protected MessageSource messageSource;
    @Autowired
    private EntityManagerFactory entityManagerFactory;
    //@Autowired
    //private LocaleResolver localeResolver;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication) throws IOException,
            ServletException {

        String contextPath = request.getContextPath();

        AuthUserExt authUserExt = (AuthUserExt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //session.setAttribute("uname", authUser.getUsername());  
        //session.setAttribute("authorities", authentication.getAuthorities()); 

        Locale current = LocaleUtils.toLocale(authUserExt.getLocale().name());

        //localeResolver.setLocale(request, response, current);

        EntityManager em = entityManagerFactory.createEntityManager();


        String sss = "";

        //System.out.println("menu>>>"+sss);
//        for (AdmMenu admMenu : admMenuService.findAll()) {
//
//            String ccc = admMenu.getUrlPath();
//            ccc = ccc.substring(1, ccc.lastIndexOf("/"));
//            String rrr = messageSource.getMessage(ccc, null, current);
//
//            if (rrr == null) {
//                rrr = admMenu.getFullName();
//            }
//
//            //sss += "<li><a href='" + contextPath + admMenu.getUrlPath() + "'><i class='" + admMenu.getDisplayIconClass() + "'></i> <span><spring:message code='"+admMenu.getUrlPath()+"' text='"+admMenu.getDisplayName()+"'/></span></a></li>";
//            sss += "<li><a href='" + contextPath + admMenu.getUrlPath() + "'><i class='" + admMenu.getDisplayIconClass() + "'></i> <span>" + rrr + "</span></a></li>";
//        }
        em.close();
        authUserExt.setCompleteMenu(sss);
        /*Set target URL to redirect*/
        //String targetUrl = determineTargetUrl(authentication);

        String url = authUserExt.getAfterLoginUrl();// "/main";

        if (url == null || url.isEmpty())
            url = "/main";

        redirectStrategy.sendRedirect(request, response, url);
    }

    //    protected String determineTargetUrl(Authentication authentication) {
//        Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
//        if (authorities.contains("ROLE_ADMIN")) {
//            return "/admin";
//        } else if (authorities.contains("ROLE_USER")) {
//            return "/user";
//        } else {
//            throw new IllegalStateException();
//        }
//    }
    public RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
}
