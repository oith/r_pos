package org.reflection.service;
/**
 * @author mac
 */

import org.hibernate.Hibernate;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.AuthQuestionNotFoundException;
import org.reflection.model.auth.AuthQuestion;
import org.reflection.model.auth.AuthUserAuthQuestion;
import org.reflection.repositories.AuthQuestionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service("authQuestionService")
@Transactional(readOnly = true)
public class AuthQuestionServiceImpl implements AuthQuestionService {

    @Autowired
    private AuthQuestionRepository authQuestionRepository;


    @Transactional
    @Override
    public AuthQuestion create(AuthQuestion lookup) {
        return authQuestionRepository.save(lookup);
    }

    @Transactional(rollbackFor = AuthQuestionNotFoundException.class)
    @Override
    public AuthQuestion update(AuthQuestion updated) throws AuthQuestionNotFoundException {
        AuthQuestion authQuestion = authQuestionRepository.findOne(updated.getId());
        if (authQuestion == null) {
            throw new AuthQuestionNotFoundException();
        }
        //BeanUtils.copyProperties(updated, authQuestion);


        copyProperties(updated, authQuestion);

        return authQuestionRepository.save(authQuestion);
    }

    @Transactional(rollbackFor = AuthQuestionNotFoundException.class)
    @Override
    public AuthQuestion copy(final AuthQuestion copied) {

        AuthQuestion authQuestionOrginal = authQuestionRepository.findOne(copied.getId());

        AuthQuestion authQuestion = new AuthQuestion();
        //BeanUtils.copyProperties(copied, authQuestion);
        //authQuestion.setId(null);


        copyProperties(copied, authQuestion);

        for (AuthUserAuthQuestion currDet : authQuestionOrginal.getAuthUserAuthQuestions()) {

            AuthUserAuthQuestion det = new AuthUserAuthQuestion();
            BeanUtils.copyProperties(currDet, det);

            det.setAuthQuestion(authQuestion);
            if (authQuestion.getAuthUserAuthQuestions() == null) {
                authQuestion.setAuthUserAuthQuestions(new java.util.LinkedHashSet());
            }
            authQuestion.getAuthUserAuthQuestions().add(det);
        }


        return authQuestionRepository.save(authQuestion);
    }

    private void copyProperties(AuthQuestion from, AuthQuestion to) {
        to.setQuestion(from.getQuestion());
        to.setAuthUserAuthQuestions(from.getAuthUserAuthQuestions());

    }

    @Override
    @Transactional
    public AuthQuestion findById(BigInteger id) throws AuthQuestionNotFoundException {
        AuthQuestion authQuestion = authQuestionRepository.findOne(id);
        if (authQuestion == null) {
            throw new AuthQuestionNotFoundException();
        }
        Hibernate.initialize(authQuestion.getAuthUserAuthQuestions());

        return authQuestion;
    }

    @Override
    @Transactional
    public Iterable<AuthQuestion> findAll() {
        Iterable<AuthQuestion> authQuestions = authQuestionRepository.findAll();

        for (AuthQuestion authQuestion : authQuestions) {
            Hibernate.initialize(authQuestion.getAuthUserAuthQuestions());

        }

        return authQuestions;
    }

    @Transactional
    @Override
    public Iterable<AuthQuestion> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<AuthQuestion> search(_SearchDTO pageable) {
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = AuthQuestionNotFoundException.class)
    public AuthQuestion delete(BigInteger id) throws AuthQuestionNotFoundException {

        AuthQuestion authQuestion = authQuestionRepository.findOne(id);

        if (authQuestion == null) {
            throw new AuthQuestionNotFoundException();
        }
        authQuestionRepository.delete(id);
        return authQuestion;
    }
}