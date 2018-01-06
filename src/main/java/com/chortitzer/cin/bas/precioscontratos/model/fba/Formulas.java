package com.chortitzer.cin.bas.precioscontratos.model.fba;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Industria
 */
@Entity
@Table(name = "formulas")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Formulas.findAll", query = "SELECT f FROM Formulas f"),
        @NamedQuery(name = "Formulas.findByNroID", query = "SELECT f FROM Formulas f WHERE f.nroID = :nroID"),
        @NamedQuery(name = "Formulas.findByNombre", query = "SELECT f FROM Formulas f WHERE f.nombre = :nombre"),
        @NamedQuery(name = "Formulas.findByCodigo", query = "SELECT f FROM Formulas f WHERE f.codigo = :codigo"),
        @NamedQuery(name = "Formulas.findByDesc1A", query = "SELECT f FROM Formulas f WHERE f.desc1A = :desc1A"),
        @NamedQuery(name = "Formulas.findByDesc1B", query = "SELECT f FROM Formulas f WHERE f.desc1B = :desc1B"),
        @NamedQuery(name = "Formulas.findByDesc1C", query = "SELECT f FROM Formulas f WHERE f.desc1C = :desc1C"),
        @NamedQuery(name = "Formulas.findByDesc1D", query = "SELECT f FROM Formulas f WHERE f.desc1D = :desc1D"),
        @NamedQuery(name = "Formulas.findByT1", query = "SELECT f FROM Formulas f WHERE f.t1 = :t1"),
        @NamedQuery(name = "Formulas.findByT2", query = "SELECT f FROM Formulas f WHERE f.t2 = :t2"),
        @NamedQuery(name = "Formulas.findByT3", query = "SELECT f FROM Formulas f WHERE f.t3 = :t3"),
        @NamedQuery(name = "Formulas.findByT4", query = "SELECT f FROM Formulas f WHERE f.t4 = :t4"),
        @NamedQuery(name = "Formulas.findByT5", query = "SELECT f FROM Formulas f WHERE f.t5 = :t5"),
        @NamedQuery(name = "Formulas.findByT6", query = "SELECT f FROM Formulas f WHERE f.t6 = :t6"),
        @NamedQuery(name = "Formulas.findByT7", query = "SELECT f FROM Formulas f WHERE f.t7 = :t7"),
        @NamedQuery(name = "Formulas.findByT8", query = "SELECT f FROM Formulas f WHERE f.t8 = :t8"),
        @NamedQuery(name = "Formulas.findByT9", query = "SELECT f FROM Formulas f WHERE f.t9 = :t9"),
        @NamedQuery(name = "Formulas.findByT10", query = "SELECT f FROM Formulas f WHERE f.t10 = :t10"),
        @NamedQuery(name = "Formulas.findByT11", query = "SELECT f FROM Formulas f WHERE f.t11 = :t11"),
        @NamedQuery(name = "Formulas.findByT12", query = "SELECT f FROM Formulas f WHERE f.t12 = :t12"),
        @NamedQuery(name = "Formulas.findByT13", query = "SELECT f FROM Formulas f WHERE f.t13 = :t13"),
        @NamedQuery(name = "Formulas.findByT14", query = "SELECT f FROM Formulas f WHERE f.t14 = :t14"),
        @NamedQuery(name = "Formulas.findByT15", query = "SELECT f FROM Formulas f WHERE f.t15 = :t15"),
        @NamedQuery(name = "Formulas.findByT16", query = "SELECT f FROM Formulas f WHERE f.t16 = :t16"),
        @NamedQuery(name = "Formulas.findByT17", query = "SELECT f FROM Formulas f WHERE f.t17 = :t17"),
        @NamedQuery(name = "Formulas.findByT18", query = "SELECT f FROM Formulas f WHERE f.t18 = :t18"),
        @NamedQuery(name = "Formulas.findByT19", query = "SELECT f FROM Formulas f WHERE f.t19 = :t19"),
        @NamedQuery(name = "Formulas.findByT20", query = "SELECT f FROM Formulas f WHERE f.t20 = :t20"),
        @NamedQuery(name = "Formulas.findByT21", query = "SELECT f FROM Formulas f WHERE f.t21 = :t21"),
        @NamedQuery(name = "Formulas.findByT22", query = "SELECT f FROM Formulas f WHERE f.t22 = :t22"),
        @NamedQuery(name = "Formulas.findByT23", query = "SELECT f FROM Formulas f WHERE f.t23 = :t23"),
        @NamedQuery(name = "Formulas.findByT24", query = "SELECT f FROM Formulas f WHERE f.t24 = :t24"),
        @NamedQuery(name = "Formulas.findByExtra1", query = "SELECT f FROM Formulas f WHERE f.extra1 = :extra1"),
        @NamedQuery(name = "Formulas.findByExtra2", query = "SELECT f FROM Formulas f WHERE f.extra2 = :extra2"),
        @NamedQuery(name = "Formulas.findByExtra3", query = "SELECT f FROM Formulas f WHERE f.extra3 = :extra3"),
        @NamedQuery(name = "Formulas.findBySetAmp", query = "SELECT f FROM Formulas f WHERE f.setAmp = :setAmp"),
        @NamedQuery(name = "Formulas.findByVt", query = "SELECT f FROM Formulas f WHERE f.vt = :vt"),
        @NamedQuery(name = "Formulas.findByIDgrupo", query = "SELECT f FROM Formulas f WHERE f.iDgrupo = :iDgrupo")})
public class Formulas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NroID")
    private Integer nroID;
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Codigo")
    private String codigo;
    @Column(name = "Desc1A")
    private Short desc1A;
    @Column(name = "Desc1B")
    private Short desc1B;
    @Column(name = "Desc1C")
    private Short desc1C;
    @Column(name = "Desc1D")
    private Short desc1D;
    @Column(name = "T1")
    private Integer t1;
    @Column(name = "T2")
    private Integer t2;
    @Column(name = "T3")
    private Integer t3;
    @Column(name = "T4")
    private Integer t4;
    @Column(name = "T5")
    private Integer t5;
    @Column(name = "T6")
    private Integer t6;
    @Column(name = "T7")
    private Integer t7;
    @Column(name = "T8")
    private Integer t8;
    @Column(name = "T9")
    private Integer t9;
    @Column(name = "T10")
    private Integer t10;
    @Column(name = "T11")
    private Integer t11;
    @Column(name = "T12")
    private Integer t12;
    @Column(name = "T13")
    private Integer t13;
    @Column(name = "T14")
    private Integer t14;
    @Column(name = "T15")
    private Integer t15;
    @Column(name = "T16")
    private Integer t16;
    @Column(name = "T17")
    private Integer t17;
    @Column(name = "T18")
    private Integer t18;
    @Column(name = "T19")
    private Integer t19;
    @Column(name = "T20")
    private Integer t20;
    @Column(name = "T21")
    private Integer t21;
    @Column(name = "T22")
    private Integer t22;
    @Column(name = "T23")
    private Integer t23;
    @Column(name = "T24")
    private Integer t24;
    @Column(name = "Extra1")
    private Short extra1;
    @Column(name = "Extra2")
    private Short extra2;
    @Column(name = "Extra3")
    private Short extra3;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SetAmp")
    private Double setAmp;
    @Column(name = "VT")
    private Boolean vt;
    @Column(name = "IDgrupo")
    private Integer iDgrupo;

    public Formulas() {
    }

    public Formulas(Integer nroID) {
        this.nroID = nroID;
    }

    public Integer getNroID() {
        return nroID;
    }

    public void setNroID(Integer nroID) {
        this.nroID = nroID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Short getDesc1A() {
        return desc1A;
    }

    public void setDesc1A(Short desc1A) {
        this.desc1A = desc1A;
    }

    public Short getDesc1B() {
        return desc1B;
    }

    public void setDesc1B(Short desc1B) {
        this.desc1B = desc1B;
    }

    public Short getDesc1C() {
        return desc1C;
    }

    public void setDesc1C(Short desc1C) {
        this.desc1C = desc1C;
    }

    public Short getDesc1D() {
        return desc1D;
    }

    public void setDesc1D(Short desc1D) {
        this.desc1D = desc1D;
    }

    public Integer getT1() {
        return t1;
    }

    public void setT1(Integer t1) {
        this.t1 = t1;
    }

    public Integer getT2() {
        return t2;
    }

    public void setT2(Integer t2) {
        this.t2 = t2;
    }

    public Integer getT3() {
        return t3;
    }

    public void setT3(Integer t3) {
        this.t3 = t3;
    }

    public Integer getT4() {
        return t4;
    }

    public void setT4(Integer t4) {
        this.t4 = t4;
    }

    public Integer getT5() {
        return t5;
    }

    public void setT5(Integer t5) {
        this.t5 = t5;
    }

    public Integer getT6() {
        return t6;
    }

    public void setT6(Integer t6) {
        this.t6 = t6;
    }

    public Integer getT7() {
        return t7;
    }

    public void setT7(Integer t7) {
        this.t7 = t7;
    }

    public Integer getT8() {
        return t8;
    }

    public void setT8(Integer t8) {
        this.t8 = t8;
    }

    public Integer getT9() {
        return t9;
    }

    public void setT9(Integer t9) {
        this.t9 = t9;
    }

    public Integer getT10() {
        return t10;
    }

    public void setT10(Integer t10) {
        this.t10 = t10;
    }

    public Integer getT11() {
        return t11;
    }

    public void setT11(Integer t11) {
        this.t11 = t11;
    }

    public Integer getT12() {
        return t12;
    }

    public void setT12(Integer t12) {
        this.t12 = t12;
    }

    public Integer getT13() {
        return t13;
    }

    public void setT13(Integer t13) {
        this.t13 = t13;
    }

    public Integer getT14() {
        return t14;
    }

    public void setT14(Integer t14) {
        this.t14 = t14;
    }

    public Integer getT15() {
        return t15;
    }

    public void setT15(Integer t15) {
        this.t15 = t15;
    }

    public Integer getT16() {
        return t16;
    }

    public void setT16(Integer t16) {
        this.t16 = t16;
    }

    public Integer getT17() {
        return t17;
    }

    public void setT17(Integer t17) {
        this.t17 = t17;
    }

    public Integer getT18() {
        return t18;
    }

    public void setT18(Integer t18) {
        this.t18 = t18;
    }

    public Integer getT19() {
        return t19;
    }

    public void setT19(Integer t19) {
        this.t19 = t19;
    }

    public Integer getT20() {
        return t20;
    }

    public void setT20(Integer t20) {
        this.t20 = t20;
    }

    public Integer getT21() {
        return t21;
    }

    public void setT21(Integer t21) {
        this.t21 = t21;
    }

    public Integer getT22() {
        return t22;
    }

    public void setT22(Integer t22) {
        this.t22 = t22;
    }

    public Integer getT23() {
        return t23;
    }

    public void setT23(Integer t23) {
        this.t23 = t23;
    }

    public Integer getT24() {
        return t24;
    }

    public void setT24(Integer t24) {
        this.t24 = t24;
    }

    public Short getExtra1() {
        return extra1;
    }

    public void setExtra1(Short extra1) {
        this.extra1 = extra1;
    }

    public Short getExtra2() {
        return extra2;
    }

    public void setExtra2(Short extra2) {
        this.extra2 = extra2;
    }

    public Short getExtra3() {
        return extra3;
    }

    public void setExtra3(Short extra3) {
        this.extra3 = extra3;
    }

    public Double getSetAmp() {
        return setAmp;
    }

    public void setSetAmp(Double setAmp) {
        this.setAmp = setAmp;
    }

    public Boolean getVt() {
        return vt;
    }

    public void setVt(Boolean vt) {
        this.vt = vt;
    }

    public Integer getIDgrupo() {
        return iDgrupo;
    }

    public void setIDgrupo(Integer iDgrupo) {
        this.iDgrupo = iDgrupo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nroID != null ? nroID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Formulas)) {
            return false;
        }
        Formulas other = (Formulas) object;
        if ((this.nroID == null && other.nroID != null) || (this.nroID != null && !this.nroID.equals(other.nroID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
