package org.reflection.repositories;
/**
 * @author mac
 */

import org.reflection.model.auth.AuthQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface AuthQuestionRepository extends JpaRepository<AuthQuestion, BigInteger> {

}
