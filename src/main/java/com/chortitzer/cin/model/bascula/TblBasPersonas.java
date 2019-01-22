/*
 * Copyright 2018 - Cooperativa CHortitzer Ltda.
 */
package com.chortitzer.cin.model.bascula;

import javax.persistence.*;

@Table(name = "tbl_bas_personas")
@Entity(name = "cinweb$TblBasPersonas")
public class TblBasPersonas {
    private static final long serialVersionUID = -1293274811950398030L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "razon_social", nullable = false)
    protected String razonSocial;

    @Column(name = "rucci", nullable = false, length = 10)
    protected String rucci;

    @Column(name = "ctacte", nullable = false)
    protected Integer ctacte;

    @Column(name = "id_tblempresas")
    protected Integer idTblempresas;

    @Column(name = "observacion")
    protected String observacion;

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRucci(String rucci) {
        this.rucci = rucci;
    }

    public String getRucci() {
        return rucci;
    }

    public void setCtacte(Integer ctacte) {
        this.ctacte = ctacte;
    }

    public Integer getCtacte() {
        return ctacte;
    }

    public void setIdTblempresas(Integer idTblempresas) {
        this.idTblempresas = idTblempresas;
    }

    public Integer getIdTblempresas() {
        return idTblempresas;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getObservacion() {
        return observacion;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getRazonSocial() + " - " + getRucci() + " - " + getObservacion();
    }
}
