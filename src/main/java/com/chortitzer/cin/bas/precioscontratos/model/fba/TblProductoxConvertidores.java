package com.chortitzer.cin.bas.precioscontratos.model.fba;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author adriang
 */
@Entity
@Table(name = "tbl_productox_convertidores")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "TblProductoxConvertidores.findAll", query = "SELECT t FROM TblProductoxConvertidores t")
        , @NamedQuery(name = "TblProductoxConvertidores.findByIdProductox", query = "SELECT t FROM TblProductoxConvertidores t WHERE t.idProductox = :idProductox")
        , @NamedQuery(name = "TblProductoxConvertidores.findByKgPorBolsa", query = "SELECT t FROM TblProductoxConvertidores t WHERE t.kgPorBolsa = :kgPorBolsa")
        , @NamedQuery(name = "TblProductoxConvertidores.findByDescripcionEnEtiqueta", query = "SELECT t FROM TblProductoxConvertidores t WHERE t.descripcionEnEtiqueta = :descripcionEnEtiqueta")})
public class TblProductoxConvertidores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @JoinColumn(name = "id_productox", referencedColumnName = "NroID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Productox idProductox;
    @Column(name = "kg_por_bolsa")
    private Integer kgPorBolsa;
    @Column(name = "descripcion_en_etiqueta")
    private String descripcionEnEtiqueta;


    public TblProductoxConvertidores() {
    }

    public TblProductoxConvertidores(Productox idProductox) {
        this.idProductox = idProductox;
    }

    public Integer getKgPorBolsa() {
        return kgPorBolsa;
    }

    public void setKgPorBolsa(Integer kgPorBolsa) {
        this.kgPorBolsa = kgPorBolsa;
    }

    public String getDescripcionEnEtiqueta() {
        return descripcionEnEtiqueta;
    }

    public void setDescripcionEnEtiqueta(String descripcionEnEtiqueta) {
        this.descripcionEnEtiqueta = descripcionEnEtiqueta;
    }

    public Productox getIdProductox() {
        return idProductox;
    }

    public void setIdProductox(Productox productox) {
        this.idProductox = productox;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProductox != null ? idProductox.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblProductoxConvertidores)) {
            return false;
        }
        TblProductoxConvertidores other = (TblProductoxConvertidores) object;
        if ((this.idProductox == null && other.idProductox != null) || (this.idProductox != null && !this.idProductox.equals(other.idProductox))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descripcionEnEtiqueta;
    }

}
