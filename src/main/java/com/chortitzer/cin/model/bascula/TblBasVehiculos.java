package com.chortitzer.cin.model.bascula;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author adriang
 */
@Entity
@Table(name = "tbl_bas_vehiculos")
@XmlRootElement
public class TblBasVehiculos extends AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "chapa")
    private String chapa;
    @Basic(optional = false)
    @Column(name = "ruc")
    private String ruc;

    public TblBasVehiculos() {
    }

    public TblBasVehiculos(String chapa) {
        this.setChapa(chapa);
    }

    public TblBasVehiculos(String chapa, String ruc) {
        this.setChapa(chapa);
        this.setRuc(ruc);
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getChapa() != null ? getChapa().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblBasVehiculos)) {
            return false;
        }
        TblBasVehiculos other = (TblBasVehiculos) object;
        return (this.getChapa() != null || other.getChapa() == null) && (this.getChapa() == null || this.getChapa().equals(other.getChapa()));
    }

    @Override
    public String toString() {
        return "com.chortitzer.industria.bascula.domain.TblBasVehiculos[ chapa=" + getChapa() + " ]";
    }


    public String getChapa() {
        return chapa;
    }

    public void setChapa(String chapa) {
        this.chapa = chapa;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
