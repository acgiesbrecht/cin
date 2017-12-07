/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.cin.bas.precioscontratos.model.bascula;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author adriang
 */
@Entity
@Table(name = "tblempresa")
public class Tblempresa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "cuentacorriente")
    private String cuentacorriente;

    @Column(name = "ruc")
    private String ruc;

    @XmlTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpresa")
    private Collection<TblBasContratos> tblBasContratosCollection;

    public Tblempresa() {
    }

    public Tblempresa(Integer id) {
        this.id=id;
    }

    public Tblempresa(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) { this.id= id; }

    public String getNombre() {
        return this.nombre;
    }
    public void setNombre(String nombre) { this.nombre=nombre; }


    public String getDireccion() {
        return this.direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion=direccion;
    }

    public String getTelefono() {
        return this.telefono;
    }
    public void setTelefono(String telefono) { this.telefono=telefono; }

    public String getCuentacorriente() {
        return this.cuentacorriente;
    }
    public void setCuentacorriente(String cuentacorriente) { this.cuentacorriente = cuentacorriente; }

    public String getRuc() {
        return this.ruc;
    }
    public void setRuc(String ruc) { this.ruc=ruc; }

    public Collection<TblBasContratos> getTblBasContratosCollection() {
        return this.tblBasContratosCollection;
    }

    public void setTblBasContratosCollection(Collection<TblBasContratos> tblBasContratosCollection) {
        this.tblBasContratosCollection =tblBasContratosCollection;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblempresa)) {
            return false;
        }
        Tblempresa other = (Tblempresa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nombre;
    }

}
