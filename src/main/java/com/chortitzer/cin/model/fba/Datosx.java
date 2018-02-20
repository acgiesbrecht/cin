package com.chortitzer.cin.model.fba;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Industria
 */
@Entity
@Table(name = "datosx")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Datosx.findAll", query = "SELECT d FROM Datosx d"),
        @NamedQuery(name = "Datosx.findByNroID", query = "SELECT d FROM Datosx d WHERE d.nroID = :nroID"),
        @NamedQuery(name = "Datosx.findByIdf", query = "SELECT d FROM Datosx d WHERE d.idf = :idf"),
        @NamedQuery(name = "Datosx.findByIdp", query = "SELECT d FROM Datosx d WHERE d.idp = :idp"),
        @NamedQuery(name = "Datosx.findBySet", query = "SELECT d FROM Datosx d WHERE d.set = :set"),
        @NamedQuery(name = "Datosx.findByPlata", query = "SELECT d FROM Datosx d WHERE d.plata = :plata"),
        @NamedQuery(name = "Datosx.findByItem", query = "SELECT d FROM Datosx d WHERE d.item = :item"),
        @NamedQuery(name = "Datosx.findByEmax", query = "SELECT d FROM Datosx d WHERE d.emax = :emax"),
        @NamedQuery(name = "Datosx.findByCv", query = "SELECT d FROM Datosx d WHERE d.cv = :cv"),
        @NamedQuery(name = "Datosx.findByVg", query = "SELECT d FROM Datosx d WHERE d.vg = :vg"),
        @NamedQuery(name = "Datosx.findByVf", query = "SELECT d FROM Datosx d WHERE d.vf = :vf")})
public class Datosx implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NroID")
    private Integer nroID;
    @Column(name = "IDF")
    private Integer idf;
    @Column(name = "IDP")
    private Integer idp;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Set")
    private Double set;
    @Column(name = "Plata")
    private Short plata;
    @Column(name = "Item")
    private Short item;
    @Column(name = "Emax")
    private Double emax;
    @Column(name = "CV")
    private Short cv;
    @Column(name = "VG")
    private Short vg;
    @Column(name = "VF")
    private Short vf;

    public Datosx() {
    }

    public Datosx(Integer nroID) {
        this.nroID = nroID;
    }

    public Integer getNroID() {
        return nroID;
    }

    public void setNroID(Integer nroID) {
        this.nroID = nroID;
    }

    public Integer getIdf() {
        return idf;
    }

    public void setIdf(Integer idf) {
        this.idf = idf;
    }

    public Integer getIdp() {
        return idp;
    }

    public void setIdp(Integer idp) {
        this.idp = idp;
    }

    public Double getSet() {
        return set;
    }

    public void setSet(Double set) {
        this.set = set;
    }

    public Short getPlata() {
        return plata;
    }

    public void setPlata(Short plata) {
        this.plata = plata;
    }

    public Short getItem() {
        return item;
    }

    public void setItem(Short item) {
        this.item = item;
    }

    public Double getEmax() {
        return emax;
    }

    public void setEmax(Double emax) {
        this.emax = emax;
    }

    public Short getCv() {
        return cv;
    }

    public void setCv(Short cv) {
        this.cv = cv;
    }

    public Short getVg() {
        return vg;
    }

    public void setVg(Short vg) {
        this.vg = vg;
    }

    public Short getVf() {
        return vf;
    }

    public void setVf(Short vf) {
        this.vf = vf;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nroID != null ? nroID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Datosx)) {
            return false;
        }
        Datosx other = (Datosx) object;
        if ((this.nroID == null && other.nroID != null) || (this.nroID != null && !this.nroID.equals(other.nroID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.chortitzer.zebra.balanceados.Datosx[ nroID=" + nroID + " ]";
    }

}
