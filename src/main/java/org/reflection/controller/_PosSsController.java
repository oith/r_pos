package org.reflection.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.reflection.model.pos.PosProduct;
import org.reflection.dto._PosProductLineDTO;
import org.reflection.service.etc.PosServiceSs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;

import java.math.BigInteger;
import java.util.*;

@Controller
@RequestMapping(value = "/posSs")
public class _PosSsController extends _BaseController {

    @Autowired
    private PosServiceSs posServiceSs;

    @GetMapping(value = "/posProductInfo", produces = "text/plain;charset=utf-8")
    public
    @ResponseBody
    ResponseEntity<String> posProductInfo(
            @RequestParam(value = "product") BigInteger product
    ) {
        PosProduct ret = posServiceSs.getProductById(product);
        return jesner(ret);
    }

    @GetMapping(value = "/posProductLoad", produces = "text/plain;charset=utf-8")
    public
    @ResponseBody
    ResponseEntity<String> posProductLoad(
            @RequestParam(value = "sid") String sid,
            @RequestParam(value = "uuid") String uuid,
            @RequestParam(value = "product") BigInteger product
    ) {
        _PosProductLineDTO posProductLineDTO = posServiceSs.getProductLoad(sid, uuid, product);
        return jesner(posProductLineDTO);
    }

    @GetMapping(value = "/saveAndPrint", produces = "text/plain;charset=utf-8")
    public
    @ResponseBody
    ResponseEntity<String> saveAndPrint(
            @RequestParam(value = "sid") String sid,
            @RequestParam(value = "uuid") String uuid,
            @RequestParam(value = "customer") BigInteger customer,
            @RequestParam(value = "paidAmount") Double paidAmount
    ) {

        Map<String, String> ret = posServiceSs.saveAndPrint(sid, uuid, customer, paidAmount);
        return jesner(ret);
    }

    @GetMapping(value = "/clear", produces = "text/plain;charset=utf-8")
    public
    @ResponseBody
    ResponseEntity<String> clear(
            @RequestParam(value = "sid") String sid,
            @RequestParam(value = "uuid") String uuid
    ) {

        Map<String, String> ret = posServiceSs.clear(sid, uuid);
        return jesner(ret);
    }

    @GetMapping(value = "/customers", produces = "text/plain;charset=utf-8")
    public
    @ResponseBody
    String customers() {
        boolean isNative = true;
        String customers = posServiceSs.getCustomers(isNative, "");

        return customers;
    }


    @GetMapping("/getCustomer")
    public
    @ResponseBody
    String getCustomer(@RequestParam(value = "mobile") String mobile) {
        return posServiceSs.getCustomer(mobile);
    }

    @GetMapping(value = "/posProductAction", produces = "text/plain;charset=utf-8")
    public
    @ResponseBody
    ResponseEntity<String> barcodeAction(
            @RequestParam(value = "sid") String sid,
            @RequestParam(value = "uuid") String uuid,
            @RequestParam(value = "isOmit") boolean isOmit,
            @RequestParam(value = "product") BigInteger product,
            @RequestParam(value = "unitPrice") Double unitPrice,
            @RequestParam(value = "quantity") Double quantity
    ) {
        boolean isNative = true;
        System.out.println("finding getProcess: barcode: >" + product + " quantity: " + "<");

        Map<String, String> ret = posServiceSs.posProductAction(sid, uuid, isNative, isOmit, product, unitPrice, quantity);
        return jesner(ret);
    }


    ResponseEntity<String> jesner(Object allMap) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return new ResponseEntity<>(objectMapper.writeValueAsString(allMap), headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping({"/", "/index", ""})
    public String indexSs(ModelMap model) {

        boolean isNative = true;

        String sid = RequestContextHolder.currentRequestAttributes().getSessionId();
        model.addAttribute("sid", sid);

        Map<String, Set<_PosProductLineDTO>> jjj = posServiceSs.index(sid);

        List ff = new ArrayList(jjj.keySet());
        model.addAttribute("uuid", jjj.get(ff.get(0)));
        model.addAttribute("uuids", jjj.keySet());

        String ac1s = posServiceSs.getPosAnalysisCodes(isNative, "acOne", "");
        model.addAttribute("acOne", ac1s);
        String ac2s = posServiceSs.getPosAnalysisCodes(isNative, "acTwo", "");
        model.addAttribute("acTwo", ac2s);
        String ac3s = posServiceSs.getPosAnalysisCodes(isNative, "acThree", "");
        model.addAttribute("acThree", ac3s);
        String ac4s = posServiceSs.getPosAnalysisCodes(isNative, "acFour", "");
        model.addAttribute("acFour", ac4s);

        String ac0s = posServiceSs.getProducts(isNative, "");
        model.addAttribute("acZero", ac0s);

        String customers = posServiceSs.getCustomers(isNative, "");
        model.addAttribute("customers", customers);

        return "_pos/indexSs";
    }


    @GetMapping(value = "/getDbText", produces = "text/plain;charset=utf-8")
    public
    @ResponseBody
    ResponseEntity<String> getDbText(
            @RequestParam(value = "colid") String colid,
            @RequestParam(value = "wheres") String wheres
    ) {

        boolean isNative = true;
        System.out.println("finding getProcess: sssssssss: >" + colid + " wheres: " + wheres + "<");

        Map<String, String> ret = new LinkedHashMap();

        String kk0 = posServiceSs.getPosAnalysisCodes(isNative, colid, wheres);
        String kkp = posServiceSs.getProducts(isNative, wheres);

        ret.put(colid, kk0);
        ret.put("products", kkp);

        return jesner(ret);
    }
}
