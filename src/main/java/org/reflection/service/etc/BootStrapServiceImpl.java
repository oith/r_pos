package org.reflection.service.etc;

import org.reflection.model.auth.*;
import org.reflection.model.com.*;
import org.reflection.model.com.enums.*;
import org.reflection.model.com.enums.Currency;
import org.reflection.model.com.enums.Locale;
import org.reflection.repositories.AdmMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("bootStrapService")
@Transactional(readOnly = true)
public class BootStrapServiceImpl implements BootStrapService {

    @Autowired
    private EntityManagerFactory entityManagerFactory;
    @Autowired
    private AdmMenuRepository admMenuRepository;

    private static Date getDate(String fdf, String form) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(form);
        try {
            return dateFormat.parse(fdf);
        } catch (Exception e) {
            return null;
        }

    }

    private static String getShowTitle(String fdf) {

        if (fdf == null) {
            return "";
        }

        String uuu = "";
        for (int i = 0; i < fdf.length(); i++) {
            char hhh = fdf.charAt(i);

            if (i == 0) {
                uuu += ("" + hhh).toUpperCase();
                continue;
            }

            if (hhh >= 65 && hhh <= (65 + 26)) {
                uuu += " " + hhh;
            } else {
                uuu += "" + hhh;
            }
        }

        return uuu;
    }

    @Override
    public void dummyMenuData(SortedSet<String> list) {

        long ff = admMenuRepository.count();
        if (ff > 0) {
            return;
        }

        Map<String, AdmSubModule> smenus = new LinkedHashMap<>();
        for (String url : list) {
            String dn = getUrl0Name(url);
            smenus.put(dn, null);
        }

        EntityManager em = entityManagerFactory.createEntityManager();

//        try {
//            em.getTransaction().begin();
//            int koto = em.createQuery("DELETE FROM " + AdmMenuItem.class.getSimpleName()).executeUpdate();
//            em.getTransaction().commit();
//            System.out.println("macsay: ok db menu drop: " + koto);
//        } catch (Exception e) {
//            System.out.println("macsay: err db menu drop: " + e);
//            if (em.getTransaction().isActive()) {
//                em.getTransaction().rollback();
//            }
//        }
        AdmModule admModuleAdm = null;
        try {
            em.getTransaction().begin();

            admModuleAdm = new AdmModule("ADM", "Admin");
            AdmModule admModuleHcm = new AdmModule("HCM", "Human Capital Management");
            AdmModule admModuleAfc = new AdmModule("AFC", "Accounts and Finance Control");
            AdmModule admModulePos = new AdmModule("POS", "Point of Sale");

            em.persist(admModuleAdm);
            em.persist(admModuleHcm);
            em.persist(admModuleAfc);
            em.persist(admModulePos);

            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("macsay: err fastModuleGen: " + e);
            //e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }

        try {
            em.getTransaction().begin();

            System.out.println("admModuleAdm: " + admModuleAdm);

            for (String smenu : smenus.keySet()) {
                if (smenu != null) {
                    AdmSubModule admSubModulex = new AdmSubModule(smenu.toUpperCase(), smenu, admModuleAdm);
                    em.persist(admSubModulex);
                    smenus.put(smenu, admSubModulex);
                }
            }

            int aa = 1;

            for (String url : list) {
                String dnReal = getUrl0Name(url);
                String dn = null;

                try {
                    dn = url.substring(1, url.lastIndexOf("index") - 1);
                } catch (Exception e) {
                    //System.out.println("hhhhhhhhhhhhhhhh" + e + " bb: " + string);
                }

                if (dn == null || dn.isEmpty()) {
                    continue;
                }

                String showTitle = getShowTitle(dn);

                AdmSubModule admSubModule = smenus.get(dnReal);

                String code4Digit = String.format("%3s", aa).replace(' ', '0');// 0001

                AdmMenu admMenu = new AdmMenu("m" + code4Digit, showTitle);
                admMenu.setAdmSubModule(admSubModule);
                admMenu.setIsExternal(Boolean.TRUE);
                admMenu.setIsOpenInNewTab(Boolean.TRUE);
                em.persist(admMenu);

                AdmMenuItem admMenuItem1 = new AdmMenuItem("l" + code4Digit, "List " + showTitle, admMenu,
                        Boolean.FALSE, aa % 3 == 0, "/" + dn + "/index", "fa fa-bell");
                admMenuItem1.setAdmSubModule(admSubModule);
                em.persist(admMenuItem1);

                AdmMenuItem admMenuItem2 = new AdmMenuItem("c" + code4Digit, "Create " + showTitle, admMenu,
                        Boolean.FALSE, aa % 3 == 0, "/" + dn + "/create", "fa fa-list");
                admMenuItem2.setAdmSubModule(admSubModule);
                em.persist(admMenuItem2);

                AdmMenuPattern mm = new AdmMenuPattern(MenuOrientation.MENU_TOP, admMenu);
                em.persist(mm);
                AdmMenuPattern mm1 = new AdmMenuPattern(MenuOrientation.MENU_TOP, admMenuItem1);
                em.persist(mm1);
                AdmMenuPattern mm2 = new AdmMenuPattern(MenuOrientation.MENU_TOP, admMenuItem2);
                em.persist(mm2);

                aa++;
                System.out.println("aaaaaaa" + aa);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            javax.persistence.RollbackException hhhh = (javax.persistence.RollbackException) e;
            String pop = hhhh.getMessage();
            System.out.println("macsay: err fastMenuGen: " + e + " pop: " + pop);
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }
    }

    @Override
    public void mock() {
        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            em.getTransaction().begin();

//            ///////////////client////////////
//            AbstractClient client1 = new AbstractClient("C-001", "Client Indvidule");
//            em.persist(client1);
//            AbstractClient client2 = new AbstractClient("C-002", "Client Company");
//            em.persist(client2);
//            ///////////////supplier////////////
//            AbstractSupplier supplier1 = new AbstractSupplier("C-001", "PySupplier Indvidule");
//            em.persist(supplier1);
//            AbstractSupplier supplier2 = new AbstractSupplier("C-002", "PySupplier Company");
//            em.persist(supplier2);
//            ///////////////employee////////////
//            CrEmployee emp1 = new CrEmployee("C-001", "Emp 1", getDate("02/12/2001", "dd/MM/yyyy"));
//            em.persist(emp1);
//            CrEmployee emp2 = new CrEmployee("C-002", "Emp 2");
//            em.persist(emp2);

            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("macsay: err mock: " + e);
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }
    }

    private String getUrl0Name(String url) {
        String dn = null;

        try {
            dn = url.substring(1, url.lastIndexOf("index") - 1);
        } catch (Exception e) {
            //System.out.println("hhhhhhhhhhhhhhhh" + e + " bb: " + string);
        }

        if (dn == null || dn.isEmpty()) {
            return null;
        }

        String showTitle = getShowTitle(dn);
        String[] showTitles = showTitle.split(" ");
        return showTitles[0];
    }

}
