package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.model.sample.ZxChoose;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ZxChooseRepository extends JpaRepository<ZxChoose, BigInteger> {

}
