package org.reflection.service.etc;

import org.reflection.model.pos.PosProduct;

import java.math.BigInteger;
import java.util.Map;
import java.util.Set;

public interface PosServiceSs {

    public String getPosAnalysisCodes(boolean isNative, String bbb, String wheres);

    public PosProduct getProductById(BigInteger id);

    public String getProducts(boolean isNative, String wheres);

    public String getCustomers(boolean isNative, String wheres);

    public Map<String, String> saveAndPrint(String sid,
                                            String uuid,
                                            BigInteger customer,
                                            Double paidAmount
    );

    public Map<String, String> clear(
            String sid,
            String uuid
    );

    public String getCustomer(String mobile);

    public Map<String, String> posProductAction(
            String sid,
            String uuid,
            boolean isNative,
            boolean isOmit,
            BigInteger barcode,
            Double unitPrice,
            Double quantity
    );

    public Map<String, Set<PosProductLine>> index(String sid);

    public PosProductLine getProductLoad(String sid, String uuid, BigInteger product);
}
