package org.reflection.service.etc;

import org.reflection.model.pos.PosProduct;

import java.util.Map;

public interface AccVoucherService {

    public Map<String, String> saveAndPrint(String sid,
                                            String uuid,
                                            String mobile,
                                            String customerName,
                                            Double paidAmount
    );

    public Map<String, String> clear(
            String sid,
            String uuid
    );

    public String getCustomer(String mobile);

    public Map<String, String> barcodeAction(
            String sid,
            String uuid,
            String barcode,
            Boolean isAdd,
            Double quantity
    );

    public Map<String, String> barcodeActionSts(
            String sid,
            String uuid
    );

    public Map<String, Map<PosProduct, Double>> index(String sid);
}
