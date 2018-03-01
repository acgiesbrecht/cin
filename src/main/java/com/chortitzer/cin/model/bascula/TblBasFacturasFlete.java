package com.chortitzer.cin.model.bascula;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.time.LocalDateTime;
import java.util.List;
import java.io.Serializable;

/**
 *
 * @author adriang
 */
@Entity
@Table(name = "tbl_bas_facturas_flete")
@XmlRootElement
public class TblBasFacturasFlete implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nro")
    private String nro;
    @Basic(optional = false)
    @Column(name = "nro_timbrado")
    private String nroTimbrado;
    @Column(name = "fecha")
    private LocalDateTime fecha;
    @Column(name = "razon_social")
    private String razonSocial;
    @Basic(optional = false)
    @Column(name = "ruc")
    private String ruc;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private long cantidad;
    @OneToMany(mappedBy = "idFacturaFlete",
            cascade = CascadeType.ALL)
    private List<TblBasNotasDeRemision> tblBasNotasDeRemisionList;

    public TblBasFacturasFlete() {
    }

    public TblBasFacturasFlete(Integer id) {
        this.id = id;
    }

    public TblBasFacturasFlete(Integer id, String nro, String nroTimbrado, String ruc, long cantidad) {
        this.id = id;
        this.nro = nro;
        this.nroTimbrado = nroTimbrado;
        this.ruc = ruc;
        this.cantidad = cantidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNro() {
        return nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

    public String getNroTimbrado() {
        return nroTimbrado;
    }

    public void setNroTimbrado(String nroTimbrado) {
        this.nroTimbrado = nroTimbrado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    @XmlTransient
    public List<TblBasNotasDeRemision> getTblBasNotasDeRemisionList() {
        return tblBasNotasDeRemisionList;
    }

    public void setTblBasNotasDeRemisionList(List<TblBasNotasDeRemision> tblBasNotasDeRemisionList) {
        this.tblBasNotasDeRemisionList = tblBasNotasDeRemisionList;
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
        if (!(object instanceof TblBasFacturasFlete)) {
            return false;
        }
        TblBasFacturasFlete other = (TblBasFacturasFlete) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.chortitzer.industria.bascula.domain.TblBasFacturasFlete[ id=" + id + " ]";
    }

}
