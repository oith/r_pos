package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.model.pos.PosSalesDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface PosSalesDetailRepository extends JpaRepository<PosSalesDetail, BigInteger> {

}
