package com.chortitzer.cin.model.admin;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author adriang
 */
@Entity
@Table(name = "tbl_maquinas_cin")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "TblMaquinasCin.findAll", query = "SELECT t FROM TblMaquinasCin t")
        , @NamedQuery(name = "TblMaquinasCin.findByNombreEquipo", query = "SELECT t FROM TblMaquinasCin t WHERE t.nombreEquipo = :nombreEquipo")
        , @NamedQuery(name = "TblMaquinasCin.findByDescripcion", query = "SELECT t FROM TblMaquinasCin t WHERE t.descripcion = :descripcion")
        , @NamedQuery(name = "TblMaquinasCin.findByRoles", query = "SELECT t FROM TblMaquinasCin t WHERE t.roles = :roles")})
public class TblMaquinasCin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nombre_equipo")
    private String nombreEquipo;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "roles")
    private String roles;

    public TblMaquinasCin() {
    }

    public TblMaquinasCin(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public TblMaquinasCin(String nombreEquipo, String descripcion, String roles) {
        this.nombreEquipo = nombreEquipo;
        this.descripcion = descripcion;
        this.roles = roles;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombreEquipo != null ? nombreEquipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblMaquinasCin)) {
            return false;
        }
        TblMaquinasCin other = (TblMaquinasCin) object;
        if ((this.nombreEquipo == null && other.nombreEquipo != null) || (this.nombreEquipo != null && !this.nombreEquipo.equals(other.nombreEquipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreEquipo;
    }

}
