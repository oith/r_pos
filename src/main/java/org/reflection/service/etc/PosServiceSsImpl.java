package org.reflection.service.etc;

import org.reflection.model.auth.AuthUser;
import org.reflection.model.com.EmbdAuditable;
import org.reflection.model.pos.*;
import org.reflection.service.auth.AuthUserService;
import org.reflection.service.auth.SpringSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.math.BigInteger;
import java.util.*;

@Service("posServiceSs")
//@Transactional(readOnly = true)
public class PosServiceSsImpl implements PosServiceSs {

    private final static Map<String, Map<String, Set<PosProductLine>>> MAPS = new LinkedHashMap();
    @Autowired
    private EntityManagerFactory entityManagerFactory;
    @Autowired
    private AuthUserService authUserService;
    @Autowired
    private SpringSecurityService springSecurityService;

    public PosProduct getProductById(BigInteger id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String ssss = "select m from "
                + PosProduct.class.getSimpleName()
                + " m where m.id=" + id;


        System.out.println("rrrr: " + ssss);
        TypedQuery<PosProduct> ghgh = entityManager.createQuery(ssss, PosProduct.class);

        PosProduct sss = ghgh.getSingleResult();//tSingleResult();

        return sss;

    }

    public String getCustomers(boolean isNative, String wheres) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String sss = //"select x from "+PosProductAc.class.getSimpleName()+" x where x.id in ("
                ""
                        + "select m FROM " + PosCustomer.class.getSimpleName()
                        + " m where 1=1 " + wheres
                        + " ORDER BY m.code";//+(isNative?"fullNameNative":"fullName");
        //   + ")";
        TypedQuery<PosCustomer> ghgh = entityManager.createQuery(sss, PosCustomer.class);
        // ghgh.setParameter("mobile", mobile);

        StringBuilder TMPVAR = new StringBuilder();
        try {
            List<PosCustomer> lll = ghgh.getResultList();//tSingleResult();

//            TMPVAR = "<option value=''>Select One</option>";
            for (PosCustomer gdf : lll) {
                TMPVAR.append("<option value='");
                TMPVAR.append(gdf.getId());
                TMPVAR.append("'>");

                if (isNative)
                    TMPVAR.append(gdf.getFullNameNative());
                else
                    TMPVAR.append(gdf.getFullName());

                TMPVAR.append("-" + gdf.getCode() + ": " + gdf.getMobile() + ": " + gdf.getAddress());

                TMPVAR.append("</option>");
            }

            entityManager.close();
        } catch (Exception e) {
        }
        return TMPVAR.toString();
    }

    @Override
    public String getPosAnalysisCodes(boolean isNative, String bbb, String wheres) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String sss = //"select x from "+PosProductAc.class.getSimpleName()+" x where x.id in ("
                ""
                        + "select distinct f FROM " + PosProduct.class.getSimpleName()
                        + " m INNER JOIN m." + bbb + " f where 1=1 " + wheres;
        // +" ORDER BY m."+(isNative?"fullNameNative":"fullName");
        //   + ")";

        System.out.println("dddd: " + sss);
        TypedQuery<PosProductAc> ghgh = entityManager.createQuery(sss, PosProductAc.class);
        // ghgh.setParameter("mobile", mobile);

        String TMPVAR = "";
        try {
            List<PosProductAc> lll = ghgh.getResultList();//tSingleResult();

            TMPVAR = "<option value=''>Select One</option>";
            for (PosProductAc gdf : lll) {

                if (isNative)
                    TMPVAR += "<option value='" + gdf.getId() + "'>" + gdf.getFullNameNative() + "</option>";
                else
                    TMPVAR += "<option value='" + gdf.getId() + "'>" + gdf.getFullName() + "</option>";
            }

            entityManager.close();
        } catch (Exception e) {
        }
        return TMPVAR;

    }

    public String getProducts(boolean isNative, String wheres) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String ssss = "select m from "
                + PosProduct.class.getSimpleName()
                + " m where 1=1 "
                + wheres
                + " ORDER BY m." + (isNative ? "fullNameNative" : "fullName");


        System.out.println("rrrr: " + ssss);
        TypedQuery<PosProduct> ghgh = entityManager.createQuery(ssss, PosProduct.class);

        String TMPVAR = "";
        try {
            List<PosProduct> sss = ghgh.getResultList();//tSingleResult();
            TMPVAR = "<option value=''>Select One</option>";
            for (PosProduct gdf : sss) {

                if (isNative)
                    TMPVAR += "<option value='" + gdf.getId() + "'>" + gdf.getFullNameNative() + "</option>";
                else
                    TMPVAR += "<option value='" + gdf.getId() + "'>" + gdf.getFullName() + "</option>";
            }

            entityManager.close();
        } catch (Exception e) {
        }
        return TMPVAR;

    }

    public Map<String, String> saveAndPrint(String sidx,
                                            String uuid,
                                            BigInteger customer,
                                            Double paidAmount
    ) {

        //User authUserExt = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String sid = "";
        try {
            sid = springSecurityService.currAuthUser().getUsername();
        } catch (Exception gfg) {
            sid = "mac";
        }

        Map<String, Set<PosProductLine>> MAP_UUID_MAP_PROD_QTY = MAPS.get(sid);
        Set<PosProductLine> currSalesMaps = MAP_UUID_MAP_PROD_QTY.get(uuid);

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
        for (PosProductLine posProductLine : currSalesMaps) {
            slsDetails.add(new PosSalesDetail(slsMaster,
                    posProductLine.getPosProduct(),
                    posProductLine.getQuantity(),
                    posProductLine.getUnitPrice()));
            amount += posProductLine.getQuantity() * posProductLine.getUnitPrice();
        }

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        AuthUser authUser;
        try {
            authUser = authUserService.findByUsername("mac");
        } catch (Exception e) {
            authUser = null;
        }

        TypedQuery<PosCustomer> ghgh = entityManager.createQuery("select m from " + PosCustomer.class.getSimpleName() + " m where m.id=:mobile", PosCustomer.class);
        ghgh.setParameter("mobile", customer);
        PosCustomer posCustomer = null;
        try {
            posCustomer = ghgh.getSingleResult();
        } catch (Exception e) {
        }

        Random rn = new Random();
        int answer = rn.nextInt(100000) + 1;

        slsMaster.setCode("S" + answer);
        slsMaster.setEmbdAuditable(new EmbdAuditable(authUser, new Date()));

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

            PosProduct fggh = slsDetail.getPosProduct();

            PosProduct rp = entityManager.find(PosProduct.class, fggh.getId());

            slsDetail.setPosProduct(rp);
            entityManager.persist(slsDetail);
        }

        entityManager.getTransaction().commit();

        MAP_UUID_MAP_PROD_QTY.remove(uuid);
        Map<String, String> ret = new LinkedHashMap<>();
        ret.put("status", "ok");
        return ret;
    }

    public Map<String, String> saveAndPrint(String sidx,
                                            String uuid,
                                            String mobile,
                                            String customerName,
                                            Double paidAmount
    ) {

        String sid = null;
        try {
            sid = springSecurityService.currAuthUser().getUsername();
        } catch (Exception kj) {
            sid = "mac";
        }

        Map<String, Set<PosProductLine>> MAP_UUID_MAP_PROD_QTY = MAPS.get(sid);
        Set<PosProductLine> currSalesMaps = MAP_UUID_MAP_PROD_QTY.get(uuid);

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
        for (PosProductLine posProduct : currSalesMaps) {
            slsDetails.add(new PosSalesDetail(slsMaster,
                    posProduct.getPosProduct(),
                    posProduct.getQuantity(),
                    posProduct.getUnitPrice()));
            amount += posProduct.getQuantity() * posProduct.getUnitPrice();
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
                posCustomer.setCode(mobile);
                posCustomer.setFullNameNative(customerName);
                posCustomer.setEmPosCustomerGroup(EmPosCustomerGroup.GRADE_B);

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
        slsMaster.setEmbdAuditable(new EmbdAuditable(authUser, new Date()));

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

            PosProduct fggh = slsDetail.getPosProduct();
            PosProduct rp = entityManager.find(PosProduct.class, fggh.getId());
            slsDetail.setPosProduct(rp);
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

        String sid = null;
        try {
            sid = springSecurityService.currAuthUser().getUsername();
        } catch (Exception kj) {
            sid = "mac";
        }

        Map<String, Set<PosProductLine>> MAP_UUID_MAP_PROD_QTY = MAPS.get(sid);

        System.out.println("maps:::237:" + MAP_UUID_MAP_PROD_QTY);
        if (MAP_UUID_MAP_PROD_QTY != null && MAP_UUID_MAP_PROD_QTY.containsKey(uuid)) {
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

    public PosProductLine getProductLoad(String sidx, String uuid, BigInteger product) {

        String sid = null;
        try {
            sid = springSecurityService.currAuthUser().getUsername();
        } catch (Exception kj) {
            sid = "mac";
        }

        Map<String, Set<PosProductLine>> MAP_UUID_MAP_PROD_QTY = MAPS.get(sid);

        Set<PosProductLine> kkkp = MAP_UUID_MAP_PROD_QTY.get(uuid);

        PosProductLine ert = null;
        for (PosProductLine mm : kkkp) {
            if (mm.getPosProduct().getId().equals(product)) {
                ert = mm;
                break;
            }
        }
        System.out.println("maps: load::ert:" + ert);
        return ert;

    }

    @Override
    public Map<String, String> posProductAction(
            String sidx,
            String uuid,
            boolean isNative,
            boolean isOmit,
            BigInteger productId,
            Double unitPrice,
            Double quantity
    ) {

        System.out.println("finding getProcproductde: >" + productId + "<");

        EntityManager em = entityManagerFactory.createEntityManager();

        Query qu = em.createQuery("select m from " + PosProduct.class.getSimpleName() + " m where m.id=:productId", PosProduct.class).setParameter("productId", productId);
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

        String sid = null;
        try {
            sid = springSecurityService.currAuthUser().getUsername();
        } catch (Exception kj) {
            sid = "mac";
        }

        if (!MAPS.containsKey(sid)) {
            MAPS.put(sid, new LinkedHashMap<>());
        }

        Map<String, Set<PosProductLine>> MAP_UUID_MAP_PROD_QTY = MAPS.get(sid);

        if (MAP_UUID_MAP_PROD_QTY == null) {
            MAP_UUID_MAP_PROD_QTY = new LinkedHashMap<>();
        }

        if (!MAP_UUID_MAP_PROD_QTY.containsKey(uuid)) {
            MAP_UUID_MAP_PROD_QTY.put(uuid, new LinkedHashSet<>());
        }

        Set<PosProductLine> currSalesMaps = MAP_UUID_MAP_PROD_QTY.get(uuid);

        PosProductLine pppx = null;
        for (PosProductLine ppp : currSalesMaps) {

            if (ppp.getPosProduct().getId().equals(productId)) {
                pppx = ppp;
                break;
            }
        }

        if (pppx == null) {
            pppx = new PosProductLine();
            pppx.setPosProduct(pp);
            pppx.setUnitPrice(unitPrice);
            pppx.setQuantity(quantity);
            pppx.setUnitPriceMax(pp.getUnitPriceSalesMax());
            pppx.setUnitPriceMin(pp.getUnitPriceSalesMin());
            currSalesMaps.add(pppx);
        } else {
            pppx.setUnitPrice(unitPrice);
            pppx.setQuantity(quantity);

        }

        if (isOmit)
            currSalesMaps.remove(pppx);

        return addProductActionSts(isNative, currSalesMaps);
    }

    private Map<String, String> addProductActionSts(boolean isNative, Set<PosProductLine> currSalesMaps) {
        StringBuilder salesLines = new StringBuilder();
        Double salesTotal = 0d;
        int cnt = 1;

        ArrayList<PosProductLine> yyy = new ArrayList(currSalesMaps);
        Collections.reverse(yyy);

        for (PosProductLine posProductLine : yyy) {
            salesLines.append("<tr>");

            String kkk =

                    "<td><button class='btn btn-info' onclick='posProductLoad(" + posProductLine.getPosProduct().getId() + ")'><i class='fa fa-info-circle'></i>&nbsp;Load</button></td>"
                            + "<td><button class='btn btn-danger' onclick='posProductOmit(" + posProductLine.getPosProduct().getId() + ")'><i class='fa fa-trash-o'></i>&nbsp;Omit</button></td>"
                            + "<td>" + cnt + "</td>"
                            + "<td>" + posProductLine.getPosProduct().getCode() + "</td>"
                            + "<td>" + (isNative ? posProductLine.getPosProduct().getFullNameNative() : posProductLine.getPosProduct().getFullName()) + "</td>"
                            + "<td class='right'>" + posProductLine.getUnitPrice() + "</td>"
                            + "<td class='right'>" + posProductLine.getQuantity() + "</td>"
                            + "<td class='right'>" + posProductLine.getQuantity() * posProductLine.getUnitPrice() + "</td>";

            salesLines.append(kkk);
            salesLines.append("</tr>");
            salesTotal += posProductLine.getQuantity() * posProductLine.getUnitPrice();
            cnt++;
        }

        Map<String, String> ret = new LinkedHashMap<>();
        ret.put("status", "ok");
        ret.put("message", "");
        ret.put("salesLines", salesLines.toString());
        ret.put("salesTotal", salesTotal.toString());

        System.out.println("pos datam:" + ret);

        return ret;
    }

    public Map<String, Set<PosProductLine>> index(String sidx) {

        String sid = null;
        try {
            sid = springSecurityService.currAuthUser().getUsername();
        } catch (Exception sdd) {
            sid = "mac";
        }

        if (!MAPS.containsKey(sid)) {
            MAPS.put(sid, new LinkedHashMap());
        }

        Map<String, Set<PosProductLine>> jjj = MAPS.get(sid);

        if (jjj.size() < 3) {
            String uuid = UUID.randomUUID().toString();
            jjj.put(uuid, new LinkedHashSet<>());
        }
        return jjj;
    }
}

