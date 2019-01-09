/*
 * Copyright 2018 - Cooperativa CHortitzer Ltda.
 */
package com.chortitzer.cin.model.bascula;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "tbl_bas_pesadas")
@Entity
public class TblBasPesadas {
    private static final long serialVersionUID = 5708887282529799073L;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fechahora_entrada", nullable = false)
    protected Date fechahoraEntrada;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fechahora_salida", nullable = false)
    protected Date fechahoraSalida;
    @Column(name = "chapa", nullable = false, length = 7)
    protected String chapa;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_propietario")
    protected TblContribuyentes idPropietario;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_transportista")
    protected TblContribuyentes idTransportista;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_producto")
    protected Tblproductos idProducto;
    @Column(name = "peso_bruto", nullable = false)
    protected Integer pesoBruto;
    @Column(name = "peso_tara", nullable = false)
    protected Integer pesoTara;
    @Column(name = "descarga_autorizada", nullable = false)
    protected Boolean descargaAutorizada = false;
    @Column(name = "enviado_opm", nullable = false)
    protected Boolean enviadoOpm = false;
    @Column(name = "precio_por_kg", precision = 3, scale = 0)
    protected BigDecimal precioPorKg;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_moneda")
    protected TblBasMonedas idMoneda;
    @Column(name = "id_lote")
    protected Integer idLote;
    @Column(name = "anulado", nullable = false)
    protected Boolean anulado = false;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechahoraEntrada() {
        return fechahoraEntrada;
    }

    public void setFechahoraEntrada(Date fechahoraEntrada) {
        this.fechahoraEntrada = fechahoraEntrada;
    }

    public Date getFechahoraSalida() {
        return fechahoraSalida;
    }

    public void setFechahoraSalida(Date fechahoraSalida) {
        this.fechahoraSalida = fechahoraSalida;
    }

    public String getChapa() {
        return chapa;
    }

    public void setChapa(String chapa) {
        this.chapa = chapa;
    }

    public TblContribuyentes getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(TblContribuyentes idPropietario) {
        this.idPropietario = idPropietario;
    }

    public TblContribuyentes getIdTransportista() {
        return idTransportista;
    }

    public void setIdTransportista(TblContribuyentes idTransportista) {
        this.idTransportista = idTransportista;
    }

    public Tblproductos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Tblproductos idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getPesoBruto() {
        return pesoBruto;
    }

    public void setPesoBruto(Integer pesoBruto) {
        this.pesoBruto = pesoBruto;
    }

    public Integer getPesoTara() {
        return pesoTara;
    }

    public void setPesoTara(Integer pesoTara) {
        this.pesoTara = pesoTara;
    }

    public Boolean getDescargaAutorizada() {
        return descargaAutorizada;
    }

    public void setDescargaAutorizada(Boolean descargaAutorizada) {
        this.descargaAutorizada = descargaAutorizada;
    }

    public Boolean getEnviadoOpm() {
        return enviadoOpm;
    }

    public void setEnviadoOpm(Boolean enviadoOpm) {
        this.enviadoOpm = enviadoOpm;
    }

    public BigDecimal getPrecioPorKg() {
        return precioPorKg;
    }

    public void setPrecioPorKg(BigDecimal precioPorKg) {
        this.precioPorKg = precioPorKg;
    }

    public TblBasMonedas getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(TblBasMonedas idMoneda) {
        this.idMoneda = idMoneda;
    }

    public Integer getIdLote() {
        return idLote;
    }

    public void setIdLote(Integer idLote) {
        this.idLote = idLote;
    }

    public Boolean getAnulado() {
        return anulado;
    }

    public void setAnulado(Boolean anulado) {
        this.anulado = anulado;
    }


}
