package org.reflection.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.reflection.model.pos.PosProduct;
import org.reflection.service.etc.PosServiceBc;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/posBc")
public class _PosBcController extends _BaseController {

    @Autowired
    private PosServiceBc posServiceBc;

    @GetMapping(value = "/saveAndPrint", produces = "text/plain;charset=utf-8")
    public
    @ResponseBody
    ResponseEntity<String> saveAndPrint(
            @RequestParam(value = "sid") String sid,
            @RequestParam(value = "uuid") String uuid,
            @RequestParam(value = "mobile") String mobile,
            @RequestParam(value = "customerName") String customerName,
            @RequestParam(value = "paidAmount") Double paidAmount
    ) {

        Map<String, String> ret = posServiceBc.saveAndPrint(sid, uuid, mobile, customerName, paidAmount);
        return jesner(ret);
    }

    @GetMapping(value = "/clear", produces = "text/plain;charset=utf-8")
    public
    @ResponseBody
    ResponseEntity<String> clear(
            @RequestParam(value = "sid") String sid,
            @RequestParam(value = "uuid") String uuid
    ) {

        Map<String, String> ret = posServiceBc.clear(sid, uuid);
        return jesner(ret);
    }

    @GetMapping("/getCustomer")
    public
    @ResponseBody
    String getCustomer(@RequestParam(value = "mobile") String mobile) {
        return posServiceBc.getCustomer(mobile);
    }

    @GetMapping(value = "/barcodeAction", produces = "text/plain;charset=utf-8")
    public
    @ResponseBody
    ResponseEntity<String> barcodeAction(
            @RequestParam(value = "sid") String sid,
            @RequestParam(value = "uuid") String uuid,
            @RequestParam(value = "barcode") String barcode,
            @RequestParam(value = "isAdd") Boolean isAdd,
            @RequestParam(value = "quantity") Double quantity
    ) {

        System.out.println("finding getProcess: barcode: >" + barcode + " quantity: " + quantity + "<");

        Map<String, String> ret = posServiceBc.barcodeAction(sid, uuid, barcode, isAdd, quantity);
        return jesner(ret);
    }

    @GetMapping(value = "/barcodeActionSts", produces = "text/plain;charset=utf-8")
    public
    @ResponseBody
    ResponseEntity<String> barcodeActionSts(
            @RequestParam(value = "sid") String sid,
            @RequestParam(value = "uuid") String uuid
    ) {
        Map<String, String> ret = posServiceBc.barcodeActionSts(sid, uuid);
        return jesner(ret);
    }

    ResponseEntity<String> jesner(Map allMap) {
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
    public String indexBc(ModelMap model) {
        String sid = RequestContextHolder.currentRequestAttributes().getSessionId();
        model.addAttribute("sid", sid);

        Map<String, Map<PosProduct, Double>> jjj = posServiceBc.index(sid);

        List ff = new ArrayList(jjj.keySet());
        model.addAttribute("uuid", jjj.get(ff.get(0)));
        model.addAttribute("uuids", jjj.keySet());

        return "_pos/indexBc";
    }

}
