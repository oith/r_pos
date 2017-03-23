package org.reflection.service.etc;

import org.reflection.model.auth.AuthUser;
import org.reflection.model.pos.PosCustomer;
import org.reflection.model.pos.PosProduct;
import org.reflection.model.pos.PosSalesDetail;
import org.reflection.model.pos.PosSalesMaster;
import org.reflection.service.auth.AuthUserService;
import org.reflection.service.auth.SpringSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.*;

@Service("accVoucherService")
//@Transactional(readOnly = true)
public class AccVoucherServiceImpl implements AccVoucherService {

    private final static Map<String, Map<String, Map<PosProduct, Double>>> MAPS = new LinkedHashMap();
    @Autowired
    private EntityManagerFactory entityManagerFactory;
    @Autowired
    private AuthUserService authUserService;
    @Autowired
    private SpringSecurityService springSecurityService;

    public Map<String, String> saveAndPrint(String sidx,
                                            String uuid,
                                            String mobile,
                                            String customerName,
                                            Double paidAmount
    ) {

        //User authUserExt = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String sid = springSecurityService.currAuthUser().getUsername();

        Map<String, Map<PosProduct, Double>> MAP_UUID_MAP_PROD_QTY = MAPS.get(sid);
        Map<PosProduct, Double> currSalesMaps = MAP_UUID_MAP_PROD_QTY.get(uuid);

        if (currSalesMaps == null || currSalesMaps.isEmpty()) {
            Map<String, String> ret = new LinkedHashMap<>();

            ret.put("status", "error");
            ret.put("message", "No Product choose to sale.");

            return ret;
        }

        if (paidAmount == null || paidAmount <= 0) {
            Map<String, String> ret = new LinkedHashMap<>();

            ret.put("status", "error");
            ret.put("message", "Paid Amount not given.");

            return ret;
        }

        PosSalesMaster slsMaster = new PosSalesMaster();

        Set<PosSalesDetail> slsDetails = new LinkedHashSet();
        Double amount = 0d;
        for (PosProduct posProduct : currSalesMaps.keySet()) {
            slsDetails.add(new PosSalesDetail(slsMaster,
                    posProduct,
                    currSalesMaps.get(posProduct),
                    posProduct.getUnitPriceSalesStd()));
            amount += currSalesMaps.get(posProduct) * posProduct.getUnitPriceSalesStd();
        }

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        AuthUser authUser;
        try {
            authUser = authUserService.findByUsername("mac");
        } catch (Exception e) {
            authUser = null;
        }

        TypedQuery<PosCustomer> ghgh = entityManager.createQuery("select m from " + PosCustomer.class.getSimpleName() + " m where m.mobile=:mobile", PosCustomer.class);
        ghgh.setParameter("mobile", mobile);
        PosCustomer posCustomer = null;
        try {
            posCustomer = ghgh.getSingleResult();
        } catch (Exception e) {
        }

        if (posCustomer == null) {
            if (customerName != null && !customerName.trim().isEmpty()) {
                posCustomer = new PosCustomer(mobile, customerName);
                entityManager.persist(posCustomer);
            } else {
                ghgh = entityManager.createQuery("select m from " + PosCustomer.class.getSimpleName() + " m where m.mobile=:mobile", PosCustomer.class);
                ghgh.setParameter("mobile", "00000000000");
                posCustomer = ghgh.getSingleResult();
            }
        }

        Random rn = new Random();
        int answer = rn.nextInt(100000) + 1;

        slsMaster.setCode("S" + answer);
        slsMaster.setTransDate(new Date());
        slsMaster.setAmount(amount);
        slsMaster.setAuthUserTransBy(authUser);
        slsMaster.setOriginDate(new Date());
        slsMaster.setPosCustomer(posCustomer);
        slsMaster.setChangeAmount(paidAmount - amount);
        slsMaster.setPosSalesDetails(slsDetails);
        slsMaster.setPaidAmount(paidAmount);

        entityManager.persist(slsMaster);

        for (PosSalesDetail slsDetail : slsDetails) {
            entityManager.persist(slsDetail);
        }

        entityManager.getTransaction().commit();

        MAP_UUID_MAP_PROD_QTY.remove(uuid);

        Map<String, String> ret = new LinkedHashMap<>();

        ret.put("status", "ok");

        return ret;
    }

    public Map<String, String> clear(
            String sidx,
            String uuid
    ) {

        //User authUserExt = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String sid = springSecurityService.currAuthUser().getUsername();

        Map<String, Map<PosProduct, Double>> MAP_UUID_MAP_PROD_QTY = MAPS.get(sid);

        System.out.println("maps:::237:" + MAP_UUID_MAP_PROD_QTY);
        if (MAP_UUID_MAP_PROD_QTY.containsKey(uuid)) {
            MAP_UUID_MAP_PROD_QTY.remove(uuid);
        }
        System.out.println("maps:::217:" + MAP_UUID_MAP_PROD_QTY);
        Map<String, String> ret = new LinkedHashMap<>();

        ret.put("status", "ok");

        System.out.println("pos datam:" + ret);

        return ret;
    }

    public String getCustomer(String mobile) {

        EntityManager em = entityManagerFactory.createEntityManager();

        Query qu = em.createQuery("SELECT m FROM " + PosCustomer.class.getSimpleName() + " m WHERE m.mobile=:mobile", PosCustomer.class).setParameter("mobile", mobile);
        PosCustomer pp = null;
        try {
            pp = (PosCustomer) qu.getSingleResult();
            em.close();
        } catch (Exception e) {
        }

        if (pp != null) {
            return pp.getFullName();
        }
        return null;
    }

    @Override
    public Map<String, String> barcodeAction(
            String sidx,
            String uuid,
            String barcode,
            Boolean isAdd,
            Double quantity
    ) {

        System.out.println("finding getProcess: code: >" + barcode + "<");

        EntityManager em = entityManagerFactory.createEntityManager();

        Query qu = em.createQuery("select m from " + PosProduct.class.getSimpleName() + " m where m.barcode=:barcode", PosProduct.class).setParameter("barcode", barcode);
        PosProduct pp = null;
        try {
            pp = (PosProduct) qu.getSingleResult();
            em.close();
        } catch (Exception e) {
        }

        if (pp == null) {
            Map<String, String> ret = new LinkedHashMap<>();
            ret.put("status", "error");
            ret.put("message", "Product not found.");
            return ret;
        }

        //User authUserExt = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String sid = springSecurityService.currAuthUser().getUsername();

        Map<String, Map<PosProduct, Double>> MAP_UUID_MAP_PROD_QTY = MAPS.get(sid);
        if (!MAP_UUID_MAP_PROD_QTY.containsKey(uuid)) {
            MAP_UUID_MAP_PROD_QTY.put(uuid, new HashMap<>());
        }

        Map<PosProduct, Double> currSalesMaps = MAP_UUID_MAP_PROD_QTY.get(uuid);

        if (!currSalesMaps.containsKey(pp)) {
            currSalesMaps.put(pp, quantity);
        } else if (isAdd) {
            currSalesMaps.put(pp, currSalesMaps.get(pp) + quantity);
        } else if (currSalesMaps.get(pp) > quantity) {
            currSalesMaps.put(pp, currSalesMaps.get(pp) - quantity);
        } else {
            currSalesMaps.remove(pp);
        }

        return barcodeActionSts(currSalesMaps);
    }

    public Map<String, String> barcodeActionSts(
            String sidx,
            String uuid
    ) {

        //User authUserExt = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String sid = springSecurityService.currAuthUser().getUsername();

        Map<String, Map<PosProduct, Double>> MAP_UUID_MAP_PROD_QTY = MAPS.get(sid);
        Map<PosProduct, Double> currSalesMaps = MAP_UUID_MAP_PROD_QTY.get(uuid);

        return barcodeActionSts(currSalesMaps);
    }

    private Map<String, String> barcodeActionSts(Map<PosProduct, Double> currSalesMaps) {
        String salesLines = "";
        Double salesTotal = 0d;
        int cnt = 1;
        for (PosProduct posProduct : currSalesMaps.keySet()) {
            salesLines
                    += "<tr>"
                    + "<td>" + cnt + "</td>"
                    + "<td>" + posProduct.getCode() + "</td>"
                    + "<td>" + posProduct.getFullName() + "</td>"
                    + "<td class='right'>" + posProduct.getUnitPriceSalesStd() + "</td>"
                    + "<td class='right'>" + currSalesMaps.get(posProduct) + "</td>"
                    + "<td class='right'>" + currSalesMaps.get(posProduct) * posProduct.getUnitPriceSalesStd() + "</td>"
                    + "</tr>";
            salesTotal += currSalesMaps.get(posProduct) * posProduct.getUnitPriceSalesStd();
            cnt++;
        }

        Map<String, String> ret = new LinkedHashMap<>();
        ret.put("status", "ok");
        ret.put("message", "");
        ret.put("salesLines", salesLines);
        ret.put("salesTotal", salesTotal.toString());

        System.out.println("pos datam:" + ret);

        return ret;
    }

    public Map<String, Map<PosProduct, Double>> index(String sidx) {

        //User authUserExt = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String sid = springSecurityService.currAuthUser().getUsername();

        if (!MAPS.containsKey(sid)) {
            MAPS.put(sid, new LinkedHashMap());
        }

        Map<String, Map<PosProduct, Double>> jjj = MAPS.get(sid);

        if (jjj.size() < 3) {
            String uuid = UUID.randomUUID().toString();
            jjj.put(uuid, new LinkedHashMap<>());
        }

        return jjj;
    }

}
