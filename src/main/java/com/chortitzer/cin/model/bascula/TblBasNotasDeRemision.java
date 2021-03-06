package com.chortitzer.cin.model.bascula;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author adriang
 */
@Entity
@Table(name = "tbl_bas_notas_de_remision")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "TblBasNotasDeRemision.findAll", query = "SELECT t FROM TblBasNotasDeRemision t")
        , @NamedQuery(name = "TblBasNotasDeRemision.findById", query = "SELECT t FROM TblBasNotasDeRemision t WHERE t.id = :id")
        , @NamedQuery(name = "TblBasNotasDeRemision.findByNro", query = "SELECT t FROM TblBasNotasDeRemision t WHERE t.nro = :nro")
        , @NamedQuery(name = "TblBasNotasDeRemision.findByNroTimbrado", query = "SELECT t FROM TblBasNotasDeRemision t WHERE t.nroTimbrado = :nroTimbrado")
        , @NamedQuery(name = "TblBasNotasDeRemision.findByRucEmisor", query = "SELECT t FROM TblBasNotasDeRemision t WHERE t.rucEmisor = :rucEmisor")
        , @NamedQuery(name = "TblBasNotasDeRemision.findByRazonSocialEmisor", query = "SELECT t FROM TblBasNotasDeRemision t WHERE t.razonSocialEmisor = :razonSocialEmisor")
        , @NamedQuery(name = "TblBasNotasDeRemision.findByPesoNeto", query = "SELECT t FROM TblBasNotasDeRemision t WHERE t.pesoNeto = :pesoNeto")
        , @NamedQuery(name = "TblBasNotasDeRemision.findByFechaEmision", query = "SELECT t FROM TblBasNotasDeRemision t WHERE t.fechaEmision = :fechaEmision")
        , @NamedQuery(name = "TblBasNotasDeRemision.findByRucTransportadora", query = "SELECT t FROM TblBasNotasDeRemision t WHERE t.rucTransportadora = :rucTransportadora")
        , @NamedQuery(name = "TblBasNotasDeRemision.findByRazonSocialTransportadora", query = "SELECT t FROM TblBasNotasDeRemision t WHERE t.razonSocialTransportadora = :razonSocialTransportadora")})
public class TblBasNotasDeRemision extends AbstractEntity implements Serializable {


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
    @Basic(optional = false)
    @Column(name = "ruc_emisor")
    private String rucEmisor;
    @Basic(optional = false)
    @Column(name = "razon_social_emisor")
    private String razonSocialEmisor;
    @Basic(optional = false)
    @Column(name = "peso_neto")
    private long pesoNeto;
    @Basic(optional = false)
    @Column(name = "fecha_emision")
    private LocalDateTime fechaEmision;
    @Column(name = "ruc_transportadora")
    private String rucTransportadora;
    @Column(name = "razon_social_transportadora")
    private String razonSocialTransportadora;

    @JoinColumn(name = "id_factura_flete", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TblBasFacturasFlete idFacturaFlete;
    @JoinColumn(name = "id_factura_mercaderia", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TblBasFacturasMercaderia idFacturaMercaderia;


    @XmlTransient
    @OneToOne(mappedBy = "idNotaDeRemision")
    private Tblpesadas tblpesadas;

    public TblBasNotasDeRemision() {
    }

    public TblBasNotasDeRemision(Integer id) {
        this.id = id;
    }

    public TblBasNotasDeRemision(Integer id, String nro, Integer nroTimbrado, String rucEmisor, String razonSocialEmisor, long pesoNeto, LocalDateTime fechaEmision) {
        this.id = id;
        this.nro = nro;
        this.nroTimbrado = nroTimbrado;
        this.rucEmisor = rucEmisor;
        this.razonSocialEmisor = razonSocialEmisor;
        this.pesoNeto = pesoNeto;
        this.fechaEmision = fechaEmision;
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

    public String getRucEmisor() {
        return rucEmisor;
    }

    public void setRucEmisor(String rucEmisor) {
        this.rucEmisor = rucEmisor;
    }

    public String getRazonSocialEmisor() {
        return razonSocialEmisor;
    }

    public void setRazonSocialEmisor(String razonSocialEmisor) {
        this.razonSocialEmisor = razonSocialEmisor;
    }

    public long getPesoNeto() {
        return pesoNeto;
    }

    public void setPesoNeto(long pesoNeto) {
        this.pesoNeto = pesoNeto;
    }

    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDateTime fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getRucTransportadora() {
        return rucTransportadora;
    }

    public void setRucTransportadora(String rucTransportadora) {
        this.rucTransportadora = rucTransportadora;
    }

    public String getRazonSocialTransportadora() {
        return razonSocialTransportadora;
    }

    public void setRazonSocialTransportadora(String razonSocialTransportadora) {
        this.razonSocialTransportadora = razonSocialTransportadora;
    }

    public TblBasFacturasFlete getIdFacturaFlete() {
        return idFacturaFlete;
    }

    public void setIdFacturaFlete(TblBasFacturasFlete idFacturaFlete) {
        this.idFacturaFlete = idFacturaFlete;
    }

    public TblBasFacturasMercaderia getIdFacturaMercaderia() {
        return idFacturaMercaderia;
    }

    public void setIdFacturaMercaderia(TblBasFacturasMercaderia idFacturaMercaderia) { this.idFacturaMercaderia = idFacturaMercaderia; }

    public Tblpesadas getTblpesadas() {
        return tblpesadas;
    }

    public void setTblpesadas(Tblpesadas tblpesadas) {
        this.tblpesadas = tblpesadas;
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
        if (!(object instanceof TblBasNotasDeRemision)) {
            return false;
        }
        TblBasNotasDeRemision other = (TblBasNotasDeRemision) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.chortitzer.industria.bascula.domain.TblBasNotasDeRemision[ id=" + id + " ]";
    }



}
