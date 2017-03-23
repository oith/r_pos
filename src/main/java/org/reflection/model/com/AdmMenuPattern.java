package org.reflection.model.com;

import org.reflection.model.com.enums.MenuOrientation;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "ADM_MENU_PATTERN", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ADM_MENU_COMMON_ID", "MENU_ORIENTATION"})})
@XmlRootElement
public class AdmMenuPattern extends Abstract {

    @JoinColumn(name = "ADM_MENU_COMMON_ID", nullable = false)
    @ManyToOne(optional = false)
    private AdmMenuCommon admMenuCommon;

    @Enumerated(EnumType.STRING)
    @Column(name = "MENU_ORIENTATION", length = 10, nullable = false)
    private MenuOrientation menuOrientation = MenuOrientation.MENU_LEFT;

    @Column(name = "SL_NO")
    private Byte slNo;

    public AdmMenuPattern() {
    }

    public AdmMenuPattern(MenuOrientation menuOrientation, AdmMenuCommon admMenuCommon) {
        this.menuOrientation = menuOrientation;
        this.admMenuCommon = admMenuCommon;
    }

    public MenuOrientation getMenuOrientation() {
        return menuOrientation;
    }

    public void setMenuOrientation(MenuOrientation menuOrientation) {
        this.menuOrientation = menuOrientation;
    }

    public Byte getSlNo() {
        return slNo;
    }

    public void setSlNo(Byte slNo) {
        this.slNo = slNo;
    }

    public AdmMenuCommon getAdmMenuCommon() {
        return admMenuCommon;
    }

    public void setAdmMenuCommon(AdmMenuCommon admMenuCommon) {
        this.admMenuCommon = admMenuCommon;
    }

    @Override
    public String toString() {
        return admMenuCommon + "-" + menuOrientation;
    }

}
