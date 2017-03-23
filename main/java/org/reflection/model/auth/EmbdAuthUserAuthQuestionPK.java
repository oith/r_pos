package org.reflection.model.auth;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class EmbdAuthUserAuthQuestionPK implements Serializable {

    @ManyToOne
    private AuthUser authUser;
    @ManyToOne
    private AuthQuestion authQuestion;

    public AuthUser getAuthUser() {
        return authUser;
    }

    public void setAuthUser(AuthUser authUser) {
        this.authUser = authUser;
    }

    public AuthQuestion getAuthQuestion() {
        return authQuestion;
    }

    public void setAuthQuestion(AuthQuestion authQuestion) {
        this.authQuestion = authQuestion;
    }

    @Override
    public String toString() {
        return authQuestion.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmbdAuthUserAuthQuestionPK)) return false;

        EmbdAuthUserAuthQuestionPK that = (EmbdAuthUserAuthQuestionPK) o;

        if (!getAuthUser().equals(that.getAuthUser())) return false;
        return getAuthQuestion().equals(that.getAuthQuestion());

    }

    @Override
    public int hashCode() {
        int result = getAuthUser().hashCode();
        result = 31 * result + getAuthQuestion().hashCode();
        return result;
    }
}
