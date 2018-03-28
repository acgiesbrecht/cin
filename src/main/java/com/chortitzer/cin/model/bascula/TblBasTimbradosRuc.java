package com.chortitzer.cin.model.bascula;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author adriang
 */
@Entity
@Table(name = "tbl_bas_timbrados_ruc")
@XmlRootElement
public class TblBasTimbradosRuc extends AbstractEntity implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "nro_timbrado")
    private Integer nroTimbrado;
    @Basic(optional = false)
    @Column(name = "ruc")
    private String ruc;

    public TblBasTimbradosRuc() {
    }

    public TblBasTimbradosRuc(Integer nroTimbrado) {
        this.setNroTimbrado(nroTimbrado);
    }

    public TblBasTimbradosRuc(Integer nroTimbrado, String ruc) {
        this.setNroTimbrado(nroTimbrado);
        this.setRuc(ruc);
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getNroTimbrado() != null ? getNroTimbrado().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblBasTimbradosRuc)) {
            return false;
        }
        TblBasTimbradosRuc other = (TblBasTimbradosRuc) object;
        return (this.getNroTimbrado() != null || other.getNroTimbrado() == null) && (this.getNroTimbrado() == null || this.getNroTimbrado().equals(other.getNroTimbrado()));
    }

    @Override
    public String toString() {
        return "com.chortitzer.industria.bascula.domain.TblBasVehiculos[ nroTimbrado=" + getNroTimbrado() + " ]";
    }


    public Integer getNroTimbrado() {
        return nroTimbrado;
    }

    public void setNroTimbrado(Integer nroTimbrado) {
        this.nroTimbrado = nroTimbrado;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }
}
