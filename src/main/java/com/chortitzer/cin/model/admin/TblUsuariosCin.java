package com.chortitzer.cin.model.admin;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author adriang
 */
@Entity
@Table(name = "tbl_usuarios_cin")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "TblUsuariosCin.findAll", query = "SELECT t FROM TblUsuariosCin t")
        , @NamedQuery(name = "TblUsuariosCin.findByNombreUsuario", query = "SELECT t FROM TblUsuariosCin t WHERE t.nombreUsuario = :nombreUsuario")
        , @NamedQuery(name = "TblUsuariosCin.findByDescripcion", query = "SELECT t FROM TblUsuariosCin t WHERE t.descripcion = :descripcion")
        , @NamedQuery(name = "TblUsuariosCin.findByRoles", query = "SELECT t FROM TblUsuariosCin t WHERE t.roles = :roles")})
public class TblUsuariosCin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "roles")
    private String roles;

    public TblUsuariosCin() {
    }

    public TblUsuariosCin(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public TblUsuariosCin(String nombreUsuario, String descripcion, String roles) {
        this.nombreUsuario = nombreUsuario;
        this.descripcion = descripcion;
        this.roles = roles;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
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
        hash += (nombreUsuario != null ? nombreUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblUsuariosCin)) {
            return false;
        }
        TblUsuariosCin other = (TblUsuariosCin) object;
        return (this.nombreUsuario != null || other.nombreUsuario == null) && (this.nombreUsuario == null || this.nombreUsuario.equals(other.nombreUsuario));
    }

    @Override
    public String toString() {
        return nombreUsuario;
    }

}
