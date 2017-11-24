/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.cin.bas.precioscontratos.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author adriang
 */
@Entity
@Table(name = "tblempresa")
@Access(AccessType.FIELD)
public class Tblempresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Transient
    private StringProperty nombre = new SimpleStringProperty();

    @Transient
    private StringProperty direccion= new SimpleStringProperty();

    @Transient
    private StringProperty telefono= new SimpleStringProperty();

    @Transient
    private StringProperty cuentacorriente= new SimpleStringProperty();

    @Transient
    private StringProperty ruc= new SimpleStringProperty();

    @Transient
    private ObjectProperty<Collection<TblBasContratos>> tblBasContratosCollection = new SimpleObjectProperty<>();

    public Tblempresa() {
    }

    public Tblempresa(Integer id) {
        this.id = id;
    }

    /*public Tblempresa(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "nombre", nullable = false)
    @Access(AccessType.PROPERTY)
    public String getNombre() {
        return this.nombre.get();
    }

    public final StringProperty nombreProperty(){return this.nombre;}

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    @Access(AccessType.PROPERTY)
    @Column(name = "direccion")
    public String getDireccion() {
        return this.direccion.get();
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public final StringProperty direccionProperty(){return this.direccion;}

    @Access(AccessType.PROPERTY)
    @Column(name = "telefono")
    public String getTelefono() {
        return this.telefono.get();
    }

    public void setTelefono(String telefono) {
        this.telefono.set(telefono);
    }

    public final StringProperty telefonoProperty(){return this.telefono;}

    @Access(AccessType.PROPERTY)
    @Column(name = "cuentacorriente")
    public String getCuentacorriente() {
        return this.cuentacorriente.get();
    }

    public void setCuentacorriente(String cuentacorriente) {
        this.cuentacorriente.set(cuentacorriente);
    }

    public final StringProperty cuentacorrienteProperty(){return this.cuentacorriente;}

    @Access(AccessType.PROPERTY)
    @Column(name = "ruc")
    public String getRuc() {
        return this.ruc.get();
    }

    public void setRuc(String ruc) {
        this.ruc.set(ruc);
    }

    public final StringProperty rucProperty(){return this.ruc;}

    @XmlTransient
    @Access(AccessType.PROPERTY)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpresa")
    public Collection<TblBasContratos> getTblBasContratosCollection() {
        return this.tblBasContratosCollection.get();
    }

    public void setTblBasContratosCollection(Collection<TblBasContratos> tblBasContratosCollection) {
        this.tblBasContratosCollection.set(tblBasContratosCollection);
    }

    public final ObjectProperty<Collection<TblBasContratos>> tblBasContratosCollectionProperty(){return this.tblBasContratosCollection;}

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
        return this.nombre.get();
    }

}
