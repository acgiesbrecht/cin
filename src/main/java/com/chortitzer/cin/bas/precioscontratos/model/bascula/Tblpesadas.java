package com.chortitzer.cin.bas.precioscontratos.model.bascula;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author adriang
 */
@Entity
@Table(name = "tblpesadas")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Tblpesadas.findAll", query = "SELECT t FROM Tblpesadas t")
        , @NamedQuery(name = "Tblpesadas.findById", query = "SELECT t FROM Tblpesadas t WHERE t.id = :id")
        , @NamedQuery(name = "Tblpesadas.findByChapa", query = "SELECT t FROM Tblpesadas t WHERE t.chapa = :chapa")
        , @NamedQuery(name = "Tblpesadas.findByBruto", query = "SELECT t FROM Tblpesadas t WHERE t.bruto = :bruto")
        , @NamedQuery(name = "Tblpesadas.findByTara", query = "SELECT t FROM Tblpesadas t WHERE t.tara = :tara")
        , @NamedQuery(name = "Tblpesadas.findByFechahora", query = "SELECT t FROM Tblpesadas t WHERE t.fechahora = :fechahora")
        , @NamedQuery(name = "Tblpesadas.findByPrecioGsPorKg", query = "SELECT t FROM Tblpesadas t WHERE t.precioGsPorKg = :precioGsPorKg")
        , @NamedQuery(name = "Tblpesadas.findByIdLote", query = "SELECT t FROM Tblpesadas t WHERE t.idLote = :idLote")
        , @NamedQuery(name = "Tblpesadas.findByAutorizado", query = "SELECT t FROM Tblpesadas t WHERE t.autorizado = :autorizado")
        , @NamedQuery(name = "Tblpesadas.findByIdRemision", query = "SELECT t FROM Tblpesadas t WHERE t.idRemision = :idRemision")})
public class Tblpesadas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "chapa")
    private String chapa;
    @Basic(optional = false)
    @Column(name = "bruto")
    private int bruto;
    @Basic(optional = false)
    @Column(name = "tara")
    private int tara;
    @Basic(optional = false)
    @Column(name = "fechahora")
    private LocalDateTime fechahora;
    @Column(name = "precio_gs_por_kg")
    private Integer precioGsPorKg;
    @Column(name = "id_lote")
    private Integer idLote;
    @Column(name = "autorizado")
    private Integer autorizado;
    @Column(name = "id_remision")
    private String idRemision;
    @JoinColumn(name = "empresaid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tblempresa empresaid;
    @JoinColumn(name = "productoid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tblproductos productoid;
    @Transient
    private Integer neto;

    public Tblpesadas() {
    }

    public Tblpesadas(Integer id) {
        this.id = id;
    }

    public Tblpesadas(Integer id, String chapa, int bruto, int tara, LocalDateTime fechahora) {
        this.id = id;
        this.chapa = chapa;
        this.bruto = bruto;
        this.tara = tara;
        this.fechahora = fechahora;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChapa() {
        return chapa;
    }

    public void setChapa(String chapa) {
        this.chapa = chapa;
    }

    public int getBruto() {
        return bruto;
    }

    public void setBruto(int bruto) {
        this.bruto = bruto;
    }

    public int getTara() {
        return tara;
    }

    public void setTara(int tara) {
        this.tara = tara;
    }

    public LocalDateTime getFechahora() {
        return fechahora;
    }

    public void setFechahora(LocalDateTime fechahora) {
        this.fechahora = fechahora;
    }

    public Integer getPrecioGsPorKg() {
        return precioGsPorKg;
    }

    public void setPrecioGsPorKg(Integer precioGsPorKg) {
        this.precioGsPorKg = precioGsPorKg;
    }

    public Integer getIdLote() {
        return idLote;
    }

    public void setIdLote(Integer idLote) {
        this.idLote = idLote;
    }

    public Integer getAutorizado() {
        return autorizado;
    }

    public void setAutorizado(Integer autorizado) {
        this.autorizado = autorizado;
    }

    public String getIdRemision() {
        return idRemision;
    }

    public void setIdRemision(String idRemision) {
        this.idRemision = idRemision;
    }

    public Tblempresa getEmpresaid() {
        return empresaid;
    }

    public void setEmpresaid(Tblempresa empresaid) {
        this.empresaid = empresaid;
    }

    public Tblproductos getProductoid() {
        return productoid;
    }

    public void setProductoid(Tblproductos productoid) {
        this.productoid = productoid;
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
        if (!(object instanceof Tblpesadas)) {
            return false;
        }
        Tblpesadas other = (Tblpesadas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id.toString();
    }

    @Transient
    public int getNeto() {
        return getBruto() - getTara();
    }
    @Transient
    public void setNeto(Integer neto) {
        this.neto = neto;
    }

}
