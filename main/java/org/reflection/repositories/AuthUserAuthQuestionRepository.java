package org.reflection.repositories;

import org.reflection.model.auth.AuthUserAuthQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface AuthUserAuthQuestionRepository extends JpaRepository<AuthUserAuthQuestion, BigInteger> {

}
