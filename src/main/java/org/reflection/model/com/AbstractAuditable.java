package org.reflection.model.com;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "ABSTRACT_AUDITABLE")
@XmlRootElement
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractAuditable extends Abstract implements IEmbdAuditable {

    @Embedded
    private EmbdAuditable embdAuditable = new EmbdAuditable();

    public AbstractAuditable() {
    }

    public AbstractAuditable(EmbdAuditable embdAuditable) {
        this.embdAuditable = embdAuditable;
    }

    @Override
    public EmbdAuditable getEmbdAuditable() {
        return embdAuditable;
    }

    @Override
    public void setEmbdAuditable(EmbdAuditable embdAuditable) {
        this.embdAuditable = embdAuditable;
    }

}
