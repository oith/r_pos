package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.model.pos.PosPurchaseDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface PosPurchaseDetailRepository extends JpaRepository<PosPurchaseDetail, BigInteger> {

}
