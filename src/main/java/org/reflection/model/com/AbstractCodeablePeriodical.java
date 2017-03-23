package org.reflection.model.com;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "ABSTRACT_CODE_PERIOD")
@XmlRootElement
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractCodeablePeriodical extends AbstractCodeable implements IEmbdPeriodical {

    @Embedded
    private EmbdPeriod embdPeriod;

    public AbstractCodeablePeriodical() {
    }

    public AbstractCodeablePeriodical(String code, EmbdPeriod embdPeriod) {
        super(code);
        this.embdPeriod = embdPeriod;
    }

    @Override
    public EmbdPeriod getEmbdPeriod() {
        return embdPeriod;
    }

    @Override
    public void setEmbdPeriod(EmbdPeriod embdPeriod) {
        this.embdPeriod = embdPeriod;
    }

}
