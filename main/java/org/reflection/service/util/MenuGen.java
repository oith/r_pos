package org.reflection.service.util;

import org.reflection.model.com.AdmMenu;
import org.reflection.model.com.AdmMenuCommon;
import org.reflection.model.com.AdmMenuItem;
import org.reflection.model.com.AdmSubModule;
import org.reflection.model.com.enums.MenuOrientation;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class MenuGen {

    private final EntityManager entityManager;
    private final String contextPath;
    private final MessageSource messageSource;
    private final Locale current;
    private final MenuOrientation menuOrientation;

    public MenuGen(EntityManager entityManager, MessageSource messageSource, Locale current, String contextPath, MenuOrientation menuOrientation) {
        this.entityManager = entityManager;
        this.messageSource = messageSource;
        this.current = current;
        this.contextPath = contextPath;
        this.menuOrientation = menuOrientation;
    }

    public String getMenu(Authentication authentication) {

//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<AdmSubModule> q = cb.createQuery(AdmSubModule.class);
//        Root<AdmSubModule> c = q.from(AdmSubModule.class);
//        q.select(c);
//        q.where(cb.equal(c.get("isActive"), true));
//        q.orderBy(cb.asc(c.get("slNo")), cb.desc(c.get("fullName"))); 
//        TypedQuery<AdmSubModule> typedQuery = entityManager.createQuery(q);
//        List<AdmSubModule> admSubModules = typedQuery.getResultList();
        String qu = "SELECT m FROM "
                + AdmSubModule.class.getSimpleName()
                + " m WHERE 1=1 "
                + "AND m.isActive=true "
                + "ORDER BY m.slNo, m.fullName";

        System.out.println("ppppppkkk:" + qu);

        TypedQuery<AdmSubModule> typedQuery = entityManager.createQuery(qu, AdmSubModule.class);

        List<AdmSubModule> admSubModules = typedQuery.getResultList();

        StringBuilder menuString = new StringBuilder();

        for (AdmSubModule admSubModule : admSubModules) {

            String localeText = getLocaleText(admSubModule.getCode(), admSubModule.getFullName());

            String faIconClass = admSubModule.getDisplayIconClass() != null ? admSubModule.getDisplayIconClass() : "fa fa-circle-o";

            String kkk = "<li class='treeview'>\n"
                    + "<a href='#'>\n"
                    + "    <i class='" + faIconClass + "'></i>\n"
                    + "    <span>\n"
                    + "        " + localeText + "\n"
                    + "    </span>"
                    + "<span class=\"pull-right-container\">\n"
                    + "    <i class=\"fa fa-angle-left pull-right\"></i>\n"
                    + "</span>\n"
                    + "</a>";
            menuString.append(kkk);

            String qxxu = "SELECT t \n"
                    + "FROM  AdmSubModule m inner join m.admMenuCommons x, AdmMenuPattern p, AdmMenu t\n"
                    + "WHERE 1=1 \n"
                    + "and t=x \n"
                    + "and p.admMenuCommon=x \n"
                    + "and p.menuOrientation = '" + menuOrientation + "' \n"
                    + "AND m.isActive=true \n"
                    + "ORDER BY x.slNo, x.fullName";

            qxxu = "select g from AdmMenu g";

            System.out.println("ppppppkkk:" + qxxu);

            TypedQuery<AdmMenu> typedQueryx = entityManager.createQuery(qxxu, AdmMenu.class);

            List<AdmMenu> ffff = typedQueryx.getResultList();

            for (AdmMenuCommon admMenu : admSubModule.getAdmMenuCommons()) {
//            for (AdmMenuCommon admMenuCommon : admSubModule.getAdmMenuCommons()) {

                //if (admMenu.getMenuOrientation() != menuOrientation) {
                //    continue;
                //}
                //entityManager.refresh(admMenuCommon);
                menuString.append("<ul class='treeview-menu'>");
                String faIconClassx = admMenu.getDisplayIconClass() != null ? admMenu.getDisplayIconClass() : "fa fa-circle-o";

                String localeTextx = getLocaleText(admMenu.getCode(), admMenu.getFullName());

                // entityManager.refresh(admMenuCommon.getAdmMenuItems());
                String hhhh = ""
                        + "<li class='treeview'>\n"
                        + "    <a href='#'>\n"
                        + "        <i class='" + faIconClassx + "'></i> \n"
                        + "        <span>\n"
                        + "            " + localeTextx + "\n"
                        + "        </span>"
                        + "    <span class=\"pull-right-container\">\n"
                        + "        <i class=\"fa fa-angle-left pull-right\"></i>\n"
                        + "    </span>\n"
                        + "    </a>\n";
                if (admMenu instanceof AdmMenu) {
                    hhhh += "    <ul class='treeview-menu'>\n"
                            + "        " + lix(((AdmMenu) admMenu).getAdmMenuItems()) + "\n"
                            + "    </ul>\n";
                }

                hhhh += "</li>";

                menuString.append(hhhh);
                menuString.append("</ul>");
            }
            menuString.append("</li>");
        }
        return menuString.toString();
    }

    String lix(Set<AdmMenuItem> parentMenu) {

        //String ddd = "<li><a href='<%=request.getContextPath()%>/admReport/create'><i class='fa fa-search'></i> Create</a></li>";
        StringBuilder ddd = new StringBuilder();
        for (AdmMenuItem admMenuItem : parentMenu) {

            String linkProperties = admMenuItem.getIsOpenInNewTab() ? "target='_blank'" : "target='_self'";
            String menuLink = !admMenuItem.getIsExternal() ? contextPath + admMenuItem.getUrlPath() : admMenuItem.getUrlPath();
            String faIconClass = admMenuItem.getDisplayIconClass() != null ? admMenuItem.getDisplayIconClass() : "fa fa-circle-o";

            String localeText = getLocaleText(admMenuItem.getCode(), admMenuItem.getFullName());

            ddd.append("<li><a title='").append(admMenuItem.getCode()).append("' href='").append(menuLink).append("' ").append(linkProperties).append("><i class='").append(faIconClass).append("'></i> ").append(localeText).append("</a></li>");
        }
        return ddd.toString();
    }

    void getChildMenuxx(AdmMenu admMenu, StringBuilder menuString, Authentication authentication) {

        /*
        Set<AdmMenuItem> menuInstanceList = parentMenu.getAdmMenuItems();// ffffff(parentMenu);

        if (!menuInstanceList.isEmpty()) {
            String ul = (level == 0 ? "<ul class='sidebar-menu'>" : "<ul class='treeview-menu'>");
            menuString.append(ul);
            for (AdmMenuItem menuInstance : menuInstanceList) {
                String linkProperties = menuInstance.getIsOpenInNewTab() ? "target='_blank'" : "target='_self'";
                String menuLink = !menuInstance.getIsExternal() ? contextPath + menuInstance.getUrlPath() : menuInstance.getUrlPath(); // grailsLinkGenerator.link(uri: menuInstance?.urlPath, absolute: false);
                String faIconClass = menuInstance.getDisplayIconClass() != null ? menuInstance.getDisplayIconClass() : "fa fa-circle-o";
                String menuTitle = level == 0 ? "<span>" + menuInstance.getFullName() + "</span>" : menuInstance.getFullName();
                int countChild = menuInstanceList.size();
                if (countChild > 0) {
                    menuTitle = menuTitle + "<i class='fa fa-angle-left pull-right'></i>";
                }
                //DefaultWebInvocationPrivilegeEvaluator ggg;
                //ggg = new DefaultWebInvocationPrivilegeEvaluator(null);
                if (//grailsApplication.config.grails.plugin.springsecurity.active && 
                        //ggg.isAllowed(menuInstance.getUrlPath(), authentication)
                        1 == 1) {
                    menuString.append("<li class='treeview'><a href='" + menuLink + "' " + linkProperties + "><i class='" + faIconClass + "'></i>" + menuTitle + "</a>");
                    //getChildMenu(menuInstance, menuType, level + 1, menuString, authentication);
                    menuString.append("</li>");
                }
            }
            menuString.append("</ul>");
        }

        return menuString.toString();
         */
    }

    private String getLocaleText(String string, String def) {
        String rrr = messageSource.getMessage(string, null, current);

        if (rrr == null || string.equals(rrr)) {
            rrr = def;
        }

        return rrr;
    }

}
