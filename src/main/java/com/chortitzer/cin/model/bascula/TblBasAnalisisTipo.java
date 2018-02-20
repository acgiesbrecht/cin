package com.chortitzer.cin.model.bascula;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author adriang
 */
@Entity
@Table(name = "tbl_bas_analisis_tipo")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "TblBasAnalisisTipo.findAll", query = "SELECT t FROM TblBasAnalisisTipo t")
        , @NamedQuery(name = "TblBasAnalisisTipo.findById", query = "SELECT t FROM TblBasAnalisisTipo t WHERE t.id = :id")
        , @NamedQuery(name = "TblBasAnalisisTipo.findByDescripcion", query = "SELECT t FROM TblBasAnalisisTipo t WHERE t.descripcion = :descripcion")
        , @NamedQuery(name = "TblBasAnalisisTipo.findByUnidadDeMedida", query = "SELECT t FROM TblBasAnalisisTipo t WHERE t.unidadDeMedida = :unidadDeMedida")
        , @NamedQuery(name = "TblBasAnalisisTipo.findByCantidadDecimales", query = "SELECT t FROM TblBasAnalisisTipo t WHERE t.cantidadDecimales = :cantidadDecimales")})
public class TblBasAnalisisTipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "unidad_de_medida")
    private String unidadDeMedida;
    @Basic(optional = false)
    @Column(name = "cantidad_decimales")
    private int cantidadDecimales;

    public TblBasAnalisisTipo() {
    }

    public TblBasAnalisisTipo(Integer id) {
        this.id = id;
    }

    public TblBasAnalisisTipo(Integer id, String descripcion, String unidadDeMedida, int cantidadDecimales) {
        this.id = id;
        this.descripcion = descripcion;
        this.unidadDeMedida = unidadDeMedida;
        this.cantidadDecimales = cantidadDecimales;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUnidadDeMedida() {
        return unidadDeMedida;
    }

    public void setUnidadDeMedida(String unidadDeMedida) {
        this.unidadDeMedida = unidadDeMedida;
    }

    public int getCantidadDecimales() {
        return cantidadDecimales;
    }

    public void setCantidadDecimales(int cantidadDecimales) {
        this.cantidadDecimales = cantidadDecimales;
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
        if (!(object instanceof TblBasAnalisisTipo)) {
            return false;
        }
        TblBasAnalisisTipo other = (TblBasAnalisisTipo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.chortitzer.industria.bascula.domain.TblBasAnalisisTipo[ id=" + id + " ]";
    }

}
