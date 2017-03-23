package org.reflection.model.auth;

import org.reflection.model.com.Abstract;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@Entity
@Table(name = "AUTH_QUESTION")
@XmlRootElement
public class AuthQuestion extends Abstract {

    @Column(name = "QUESTION", length = 100, nullable = false)
    private String question;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id.authQuestion")
    private Set<AuthUserAuthQuestion> authUserAuthQuestions;

    public AuthQuestion() {
    }

    public AuthQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Set<AuthUserAuthQuestion> getAuthUserAuthQuestions() {
        return authUserAuthQuestions;
    }

    public void setAuthUserAuthQuestions(Set<AuthUserAuthQuestion> authUserAuthQuestions) {
        this.authUserAuthQuestions = authUserAuthQuestions;
    }

    @Override
    public String toString() {
        return question;
    }

}
