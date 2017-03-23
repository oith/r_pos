package org.reflection.model.com;

import org.reflection.model.com.enums.LocationType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ADM_LOCATION")
public class AdmLocation extends AbstractLookable {

    @Column(name = "LOCATION_TYPE", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private LocationType emLocationType;

    @OneToMany(mappedBy = "parentAdmLocation")
    private Set<AdmLocation> admLocations;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private AdmLocation parentAdmLocation;

    public LocationType getEmLocationType() {
        return emLocationType;
    }

    public void setEmLocationType(LocationType emLocationType) {
        this.emLocationType = emLocationType;
    }

    public Set<AdmLocation> getAdmLocations() {
        return admLocations;
    }

    public void setAdmLocations(Set<AdmLocation> admLocations) {
        this.admLocations = admLocations;
    }

    public AdmLocation getParentAdmLocation() {
        return parentAdmLocation;
    }

    public void setParentAdmLocation(AdmLocation parentAdmLocation) {
        this.parentAdmLocation = parentAdmLocation;
    }


}
