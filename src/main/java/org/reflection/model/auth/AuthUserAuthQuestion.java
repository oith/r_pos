package org.reflection.model.auth;

import com.oith.annotation.MacCareless;

import javax.persistence.*;
import java.io.Serializable;

@MacCareless
@Entity
@Table(name = "AUTH_USER_AUTH_QUESTION")
@AssociationOverrides({
        @AssociationOverride(name = "id.authUser", joinColumns = @JoinColumn(name = "AUTH_USER_ID")),
        @AssociationOverride(name = "id.authQuestion", joinColumns = @JoinColumn(name = "AUTH_QUESTION_ID"))})
public class AuthUserAuthQuestion implements Serializable {

    @EmbeddedId
    private EmbdAuthUserAuthQuestionPK id = new EmbdAuthUserAuthQuestionPK();

    @Column(name = "ANSWER", length = 50, nullable = false)
    private String answer;

    public AuthUserAuthQuestion() {
    }

    public AuthUserAuthQuestion(AuthUser authUser, AuthQuestion authQuestion, String answer) {
        setAuthUser(authUser);
        setAuthQuestion(authQuestion);
        this.answer = answer;
    }

    public EmbdAuthUserAuthQuestionPK getId() {
        return id;
    }

    public void setId(EmbdAuthUserAuthQuestionPK id) {
        this.id = id;
    }

    public AuthUser getAuthUser() {
        return getId().getAuthUser();
    }

    public void setAuthUser(AuthUser authUser) {
        getId().setAuthUser(authUser);
    }

    public AuthQuestion getAuthQuestion() {
        return getId().getAuthQuestion();
    }

    public void setAuthQuestion(AuthQuestion authQuestion) {
        getId().setAuthQuestion(authQuestion);
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "AuthUserAuthQuestion{" + "id=" + id + ", answer=" + answer + '}';
    }

}
