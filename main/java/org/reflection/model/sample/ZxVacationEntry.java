package org.reflection.model.sample;

/**
 * Created by mbadiuzzaman on 28/1/2017.
 */

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Embeddable
public class ZxVacationEntry {
    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "DAYS")
    private Integer daysTaken;

    public ZxVacationEntry() {
    }

    public ZxVacationEntry(Date startDate, Integer daysTaken) {
        this();
        this.startDate = startDate;
        this.daysTaken = daysTaken;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getDaysTaken() {
        return daysTaken;
    }

    public void setDaysTaken(Integer daysTaken) {
        this.daysTaken = daysTaken;
    }

    public String toString() {
        return "Vacation from " + getStartDate().getTime() +
                " for " + getDaysTaken() + " days.";
    }
}