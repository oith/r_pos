package org.reflection.service;
/**
 * @author mac
 */

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AuthQuestionNotFoundException;
import org.reflection.model.auth.AuthQuestion;

import java.math.BigInteger;

public interface AuthQuestionService {


    public AuthQuestion findById(BigInteger id) throws AuthQuestionNotFoundException;

    public AuthQuestion create(AuthQuestion authQuestion);

    public AuthQuestion update(AuthQuestion authQuestion) throws AuthQuestionNotFoundException;

    public AuthQuestion copy(AuthQuestion authQuestion) throws AuthQuestionNotFoundException;

    public AuthQuestion delete(BigInteger id) throws AuthQuestionNotFoundException;

    public Iterable<AuthQuestion> search(_SearchDTO pageable);

    public Iterable<AuthQuestion> findAll(_SearchDTO pageable);

    public Iterable<AuthQuestion> findAll();
}