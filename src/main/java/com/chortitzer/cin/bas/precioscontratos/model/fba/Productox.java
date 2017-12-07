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
 * @author adriang
 */
@Entity
@Table(name = "productox")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Productox.findAll", query = "SELECT p FROM Productox p")
        , @NamedQuery(name = "Productox.findByNroID", query = "SELECT p FROM Productox p WHERE p.nroID = :nroID")
        , @NamedQuery(name = "Productox.findByNombre", query = "SELECT p FROM Productox p WHERE p.nombre = :nombre")
        , @NamedQuery(name = "Productox.findByDos1", query = "SELECT p FROM Productox p WHERE p.dos1 = :dos1")
        , @NamedQuery(name = "Productox.findByDos2", query = "SELECT p FROM Productox p WHERE p.dos2 = :dos2")
        , @NamedQuery(name = "Productox.findByDos3", query = "SELECT p FROM Productox p WHERE p.dos3 = :dos3")
        , @NamedQuery(name = "Productox.findByDos4", query = "SELECT p FROM Productox p WHERE p.dos4 = :dos4")
        , @NamedQuery(name = "Productox.findByDos5", query = "SELECT p FROM Productox p WHERE p.dos5 = :dos5")
        , @NamedQuery(name = "Productox.findByDos6", query = "SELECT p FROM Productox p WHERE p.dos6 = :dos6")
        , @NamedQuery(name = "Productox.findByCvel", query = "SELECT p FROM Productox p WHERE p.cvel = :cvel")
        , @NamedQuery(name = "Productox.findByVgrueso", query = "SELECT p FROM Productox p WHERE p.vgrueso = :vgrueso")
        , @NamedQuery(name = "Productox.findByVfino", query = "SELECT p FROM Productox p WHERE p.vfino = :vfino")
        , @NamedQuery(name = "Productox.findByTdos", query = "SELECT p FROM Productox p WHERE p.tdos = :tdos")
        , @NamedQuery(name = "Productox.findByPlata", query = "SELECT p FROM Productox p WHERE p.plata = :plata")
        , @NamedQuery(name = "Productox.findBySetIR", query = "SELECT p FROM Productox p WHERE p.setIR = :setIR")
        , @NamedQuery(name = "Productox.findBySetFR", query = "SELECT p FROM Productox p WHERE p.setFR = :setFR")
        , @NamedQuery(name = "Productox.findByNp", query = "SELECT p FROM Productox p WHERE p.np = :np")
        , @NamedQuery(name = "Productox.findByVisible", query = "SELECT p FROM Productox p WHERE p.visible = :visible")
        , @NamedQuery(name = "Productox.findByCodigo", query = "SELECT p FROM Productox p WHERE p.codigo = :codigo")})
public class Productox implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NroID")
    private Integer nroID;
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Dos1")
    private Short dos1;
    @Column(name = "Dos2")
    private Short dos2;
    @Column(name = "Dos3")
    private Short dos3;
    @Column(name = "Dos4")
    private Short dos4;
    @Column(name = "Dos5")
    private Short dos5;
    @Column(name = "Dos6")
    private Short dos6;
    @Column(name = "Cvel")
    private Short cvel;
    @Column(name = "Vgrueso")
    private Short vgrueso;
    @Column(name = "Vfino")
    private Short vfino;
    @Column(name = "Tdos")
    private Short tdos;
    @Column(name = "Plata")
    private Short plata;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SetIR")
    private Double setIR;
    @Column(name = "SetFR")
    private Double setFR;
    @Column(name = "Np")
    private String np;
    @Column(name = "Visible")
    private Boolean visible;
    @Column(name = "Codigo")
    private String codigo;

    public Productox() {
    }

    public Productox(Integer nroID) {
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

    public Short getDos1() {
        return dos1;
    }

    public void setDos1(Short dos1) {
        this.dos1 = dos1;
    }

    public Short getDos2() {
        return dos2;
    }

    public void setDos2(Short dos2) {
        this.dos2 = dos2;
    }

    public Short getDos3() {
        return dos3;
    }

    public void setDos3(Short dos3) {
        this.dos3 = dos3;
    }

    public Short getDos4() {
        return dos4;
    }

    public void setDos4(Short dos4) {
        this.dos4 = dos4;
    }

    public Short getDos5() {
        return dos5;
    }

    public void setDos5(Short dos5) {
        this.dos5 = dos5;
    }

    public Short getDos6() {
        return dos6;
    }

    public void setDos6(Short dos6) {
        this.dos6 = dos6;
    }

    public Short getCvel() {
        return cvel;
    }

    public void setCvel(Short cvel) {
        this.cvel = cvel;
    }

    public Short getVgrueso() {
        return vgrueso;
    }

    public void setVgrueso(Short vgrueso) {
        this.vgrueso = vgrueso;
    }

    public Short getVfino() {
        return vfino;
    }

    public void setVfino(Short vfino) {
        this.vfino = vfino;
    }

    public Short getTdos() {
        return tdos;
    }

    public void setTdos(Short tdos) {
        this.tdos = tdos;
    }

    public Short getPlata() {
        return plata;
    }

    public void setPlata(Short plata) {
        this.plata = plata;
    }

    public Double getSetIR() {
        return setIR;
    }

    public void setSetIR(Double setIR) {
        this.setIR = setIR;
    }

    public Double getSetFR() {
        return setFR;
    }

    public void setSetFR(Double setFR) {
        this.setFR = setFR;
    }

    public String getNp() {
        return np;
    }

    public void setNp(String np) {
        this.np = np;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
        if (!(object instanceof Productox)) {
            return false;
        }
        Productox other = (Productox) object;
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
