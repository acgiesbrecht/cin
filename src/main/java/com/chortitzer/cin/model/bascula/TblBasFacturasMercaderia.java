package com.chortitzer.cin.model.bascula;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author adriang
 */
@Entity
@Table(name = "tbl_bas_facturas_mercaderia")
@XmlRootElement
public class TblBasFacturasMercaderia implements Serializable {

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
    private Integer nroTimbrado;
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
    @Basic(optional = false)
    @Column(name = "nro_oc")
    private Integer nroOc;

    @OneToMany(mappedBy = "idFacturaMercaderia",
            cascade = CascadeType.ALL)
    private List<TblBasNotasDeRemision> tblBasNotasDeRemisionList;

    public TblBasFacturasMercaderia() {
    }

    public TblBasFacturasMercaderia(Integer id) {
        this.id = id;
    }

    public TblBasFacturasMercaderia(Integer id, String nro, Integer nroTimbrado, String ruc, long cantidad) {
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

    public Integer getNroTimbrado() {
        return nroTimbrado;
    }

    public void setNroTimbrado(Integer nroTimbrado) {
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

    public Integer getNroOc() {
        return nroOc;
    }

    public void setNroOc(Integer nroOc) {
        this.nroOc = nroOc;
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
        if (!(object instanceof TblBasFacturasMercaderia)) {
            return false;
        }
        TblBasFacturasMercaderia other = (TblBasFacturasMercaderia) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.chortitzer.industria.bascula.domain.TblBasFacturasMercaderia[ id=" + id + " ]";
    }

}
