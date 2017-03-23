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
    public void dummyUserData() {
        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            em.getTransaction().begin();

            String[] quss = {
                    "What is your favorite pet's name",
                    "First train tour destination",
                    "What is your favorite color",
                    "First plane tour"
            };
            AuthQuestion[] qus = new AuthQuestion[quss.length];
            for (int i = 0; i < quss.length; i++) {
                qus[i] = new AuthQuestion(quss[i] + " ?");
                em.persist(qus[i]);
            }
            AuthRole authRoleAdmin = new AuthRole("ADMIN");
            em.persist(authRoleAdmin);
            AuthRole authRoleUser = new AuthRole("USER");
            em.persist(authRoleUser);

            AuthRequestMap authRequestMap1 = new AuthRequestMap("/resources/**,/,/index,/login,/about,/signup", "*");
            em.persist(authRequestMap1);
            AuthRequestMap authRequestMap2 = new AuthRequestMap("/**", "USER");
            em.persist(authRequestMap2);

            Set<AuthRole> authRoles = new LinkedHashSet<>();
            authRoles.add(authRoleAdmin);
            authRoles.add(authRoleUser);

            AuthGroup authGroup1 = new AuthGroup();
            authGroup1.setAuthority("ADMINS");
            authGroup1.setAuthRoles(authRoles);
            em.persist(authGroup1);

            authRoles = new LinkedHashSet<>();
            authRoles.add(authRoleUser);

            AuthGroup authGroup2 = new AuthGroup("NORMS");
            authGroup2.setAuthRoles(authRoles);
            em.persist(authGroup2);

            Set<AuthGroup> authGroups = new LinkedHashSet<>();
            authGroups.add(authGroup1);

            String pin = "$2a$10$su8tS2DoHzJD46Vi9aWFeexyRDeX0QosPHLFG/Ev7dwj1AAco.9OC";

            AuthUser authUser1 = new AuthUser();
            authUser1.setPassword(pin);
            authUser1.setUsername("mac");
            authUser1.setDob(getDate("07/05/1983", "dd/MM/yyyy"));
            authUser1.setEnabled(true);
            authUser1.setPic("aaaa.jpg");
            authUser1.setCellNo("01912917834");
            authUser1.setAccountNonExpired(true);
            authUser1.setAccountNonLocked(true);
            authUser1.setCredentialsNonExpired(true);
            //authUser1.setAuthRoles(authGroup1);
            authUser1.setAuthGroups(authGroups);
            authUser1.setDisplayName("Manik");
            authUser1.setTitle(Title.MR);
            authUser1.setGender(Gender.MALE);
            authUser1.setFullName("MOHAMMAD BADIUZZAMAN");
            authUser1.setEmail("manikmonir@gmail.com");
            authUser1.setLanguage(Language.bn);
            authUser1.setLocale(Locale.bn_IN);
            authUser1.setCountry(Country.IN);
            authUser1.setCurrency(Currency.BDT);
            authUser1.setMenuOrientation(MenuOrientation.MENU_LEFT);

            em.persist(authUser1);

            authUser1.getAuthUserAuthRoles().add(new AuthUserAuthRole(authUser1, authRoleUser, RoleIncludeExclude.INCLUDE));
            authUser1.getAuthUserAuthRoles().add(new AuthUserAuthRole(authUser1, authRoleAdmin, RoleIncludeExclude.EXCLUDE));
            //authUser1.getAuthUserAuthRoles().add(new AuthUserAuthRole(authUser1, authRoleUser, RoleIncludeExclude.EXCLUDE));

            for (AuthUserAuthRole authUserAuthRole : authUser1.getAuthUserAuthRoles()) {
                em.persist(authUserAuthRole);
            }

            authUser1.getAuthUserEnvKeys().add(new AuthUserEnvKey(authUser1, EnvKey.AFTER_LOGIN_URL, "/queryCenter"));
            authUser1.getAuthUserEnvKeys().add(new AuthUserEnvKey(authUser1, EnvKey.DATE_TIME_FORMAT, "dd/MM/yyyy HH:mm"));
            authUser1.getAuthUserEnvKeys().add(new AuthUserEnvKey(authUser1, EnvKey.DATE_FORMAT, "dd/MM/yyyy"));
            authUser1.getAuthUserEnvKeys().add(new AuthUserEnvKey(authUser1, EnvKey.TIME_FORMAT, "HH:mm"));

            for (AuthUserEnvKey authUserEmFieldEnv : authUser1.getAuthUserEnvKeys()) {
                em.persist(authUserEmFieldEnv);
            }

            AuthUserAuthQuestion authUserAuthQuestion = new AuthUserAuthQuestion(authUser1, qus[2], "java");
            em.persist(authUserAuthQuestion);
            AuthUserAuthQuestion authUserAuthQuestion1 = new AuthUserAuthQuestion(authUser1, qus[3], "Chittagong");
            em.persist(authUserAuthQuestion1);

            authRoles = new LinkedHashSet<>();
            authRoles.add(authRoleUser);

            AuthUser authUser2 = new AuthUser();
            authUser2.setPassword(pin);
            authUser2.setUsername("saif_hmk");
            authUser2.setDob(getDate("23/11/1989", "dd/MM/yyyy"));
            authUser2.setPic("bbbb.jpg");
            authUser2.setCellNo("01912917832");
            authUser2.setEnabled(true);
            authUser2.setAccountNonExpired(true);
            authUser2.setAccountNonLocked(true);
            authUser2.setCredentialsNonExpired(true);
            //authUser1.setAuthRoles(authGroup1);
            authUser2.setAuthGroups(authGroups);
            authUser2.setDisplayName("Saif");
            authUser2.setTitle(Title.MR);
            authUser2.setGender(Gender.MALE);
            authUser2.setFullName("MD. HOSHEN MAHMUD KHAN");
            authUser2.setEmail("saif_hmk@live.com");
            authUser2.setLanguage(Language.bn);
            authUser2.setCountry(Country.BD);
            em.persist(authUser2);

            AuthUser authUser3 = new AuthUser();
            authUser3.setPassword(pin);
            authUser3.setUsername("anis");
            authUser3.setDob(getDate("17/09/1980", "dd/MM/yyyy"));
            authUser3.setPic("cccc.jpg");
            authUser3.setCellNo("01912917855");
            authUser3.setEnabled(true);
            authUser3.setAccountNonExpired(true);
            authUser3.setAccountNonLocked(true);
            authUser3.setCredentialsNonExpired(true);
            //authUser1.setAuthRoles(authGroup1);
            authUser3.setAuthGroups(authGroups);
            authUser3.setDisplayName("Anis");
            authUser3.setTitle(Title.MR);
            authUser3.setGender(Gender.MALE);
            authUser3.setFullName("MOHAMMAD ANISUR RAHMAN KHAN");
            authUser3.setEmail("anis.object@gmail.com");
            authUser3.setLanguage(Language.bn);
            authUser3.setCountry(Country.BD);
            em.persist(authUser3);

            AuthUser authUser4 = new AuthUser();
            authUser4.setPassword(pin);
            authUser4.setUsername("shaka");
            authUser4.setDob(getDate("01/12/1991", "dd/MM/yyyy"));
            authUser4.setPic("dddd.jpg");
            authUser4.setCellNo("01912917839");
            authUser4.setEnabled(true);
            authUser4.setAccountNonExpired(true);
            authUser4.setAccountNonLocked(true);
            authUser4.setCredentialsNonExpired(true);
//authUser1.setAuthRoles(authGroup1);
            authUser4.setAuthGroups(authGroups);
            authUser4.setDisplayName("Shaka");
            authUser4.setTitle(Title.MR);
            authUser4.setGender(Gender.MALE);
            authUser4.setFullName("ASHRAFUL AMIN");
            authUser4.setEmail("aminshaka@gmail.com");
            authUser4.setLanguage(Language.bn);
            authUser4.setCountry(Country.BD);
            authUser4.setCurrency(Currency.BDT);
            em.persist(authUser4);

            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("macsay: err dummyUserData: " + e);
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
