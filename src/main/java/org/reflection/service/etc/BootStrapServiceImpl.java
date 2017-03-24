package org.reflection.service.etc;

import org.reflection.model.auth.*;
import org.reflection.model.com.*;
import org.reflection.model.com.enums.*;
import org.reflection.model.com.enums.Currency;
import org.reflection.model.com.enums.Locale;
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
