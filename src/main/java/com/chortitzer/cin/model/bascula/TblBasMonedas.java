/*
 * Copyright 2018 - Cooperativa CHortitzer Ltda.
 */
package com.chortitzer.cin.model.bascula;

import javax.persistence.*;

@Entity
@Table(name = "tbl_bas_monedas")
public class TblBasMonedas {
    private static final long serialVersionUID = -3329265986501895146L;
    @Lob
    @Column(name = "descripcion", nullable = false)
    protected String descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


}
