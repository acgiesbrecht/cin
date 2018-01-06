package com.chortitzer.cin.bas.precioscontratos.model.fba;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "tbl_etiquetadora_contenido")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "TblEtiquetadoraContenido.findAll", query = "SELECT t FROM TblEtiquetadoraContenido t"),
        @NamedQuery(name = "TblEtiquetadoraContenido.findByIdFormula", query = "SELECT t FROM TblEtiquetadoraContenido t WHERE t.idFormula = :idFormula"),
        @NamedQuery(name = "TblEtiquetadoraContenido.findByModoDeUso", query = "SELECT t FROM TblEtiquetadoraContenido t WHERE t.modoDeUso = :modoDeUso"),
        @NamedQuery(name = "TblEtiquetadoraContenido.findByRegistroMag", query = "SELECT t FROM TblEtiquetadoraContenido t WHERE t.registroMag = :registroMag"),
        @NamedQuery(name = "TblEtiquetadoraContenido.findByHumedad", query = "SELECT t FROM TblEtiquetadoraContenido t WHERE t.humedad = :humedad"),
        @NamedQuery(name = "TblEtiquetadoraContenido.findByProteina", query = "SELECT t FROM TblEtiquetadoraContenido t WHERE t.proteina = :proteina"),
        @NamedQuery(name = "TblEtiquetadoraContenido.findByEnergiaDigestible", query = "SELECT t FROM TblEtiquetadoraContenido t WHERE t.energiaDigestible = :energiaDigestible"),
        @NamedQuery(name = "TblEtiquetadoraContenido.findByGrasa", query = "SELECT t FROM TblEtiquetadoraContenido t WHERE t.grasa = :grasa"),
        @NamedQuery(name = "TblEtiquetadoraContenido.findByFda", query = "SELECT t FROM TblEtiquetadoraContenido t WHERE t.fda = :fda"),
        @NamedQuery(name = "TblEtiquetadoraContenido.findByMineralesTotales", query = "SELECT t FROM TblEtiquetadoraContenido t WHERE t.mineralesTotales = :mineralesTotales"),
        @NamedQuery(name = "TblEtiquetadoraContenido.findByCalcio", query = "SELECT t FROM TblEtiquetadoraContenido t WHERE t.calcio = :calcio"),
        @NamedQuery(name = "TblEtiquetadoraContenido.findByFosforo", query = "SELECT t FROM TblEtiquetadoraContenido t WHERE t.fosforo = :fosforo"),
        @NamedQuery(name = "TblEtiquetadoraContenido.findByVitA", query = "SELECT t FROM TblEtiquetadoraContenido t WHERE t.vitA = :vitA"),
        @NamedQuery(name = "TblEtiquetadoraContenido.findByVitD", query = "SELECT t FROM TblEtiquetadoraContenido t WHERE t.vitD = :vitD"),
        @NamedQuery(name = "TblEtiquetadoraContenido.findByVitE", query = "SELECT t FROM TblEtiquetadoraContenido t WHERE t.vitE = :vitE"),
        @NamedQuery(name = "TblEtiquetadoraContenido.findByAnimalDestino", query = "SELECT t FROM TblEtiquetadoraContenido t WHERE t.animalDestino = :animalDestino"),
        @NamedQuery(name = "TblEtiquetadoraContenido.findByCategoria", query = "SELECT t FROM TblEtiquetadoraContenido t WHERE t.categoria = :categoria"),
        @NamedQuery(name = "TblEtiquetadoraContenido.findByNnp", query = "SELECT t FROM TblEtiquetadoraContenido t WHERE t.nnp = :nnp"),
        @NamedQuery(name = "TblEtiquetadoraContenido.findByS", query = "SELECT t FROM TblEtiquetadoraContenido t WHERE t.s = :s"),
        @NamedQuery(name = "TblEtiquetadoraContenido.findByMg", query = "SELECT t FROM TblEtiquetadoraContenido t WHERE t.mg = :mg"),
        @NamedQuery(name = "TblEtiquetadoraContenido.findByNa", query = "SELECT t FROM TblEtiquetadoraContenido t WHERE t.na = :na"),
        @NamedQuery(name = "TblEtiquetadoraContenido.findByCo", query = "SELECT t FROM TblEtiquetadoraContenido t WHERE t.co = :co"),
        @NamedQuery(name = "TblEtiquetadoraContenido.findByCu", query = "SELECT t FROM TblEtiquetadoraContenido t WHERE t.cu = :cu"),
        @NamedQuery(name = "TblEtiquetadoraContenido.findBySe", query = "SELECT t FROM TblEtiquetadoraContenido t WHERE t.se = :se"),
        @NamedQuery(name = "TblEtiquetadoraContenido.findByMn", query = "SELECT t FROM TblEtiquetadoraContenido t WHERE t.mn = :mn"),
        @NamedQuery(name = "TblEtiquetadoraContenido.findByZn", query = "SELECT t FROM TblEtiquetadoraContenido t WHERE t.zn = :zn"),
        @NamedQuery(name = "TblEtiquetadoraContenido.findByY", query = "SELECT t FROM TblEtiquetadoraContenido t WHERE t.y = :y"),
        @NamedQuery(name = "TblEtiquetadoraContenido.findByFe", query = "SELECT t FROM TblEtiquetadoraContenido t WHERE t.fe = :fe"),
        @NamedQuery(name = "TblEtiquetadoraContenido.findByNombreCompleto", query = "SELECT t FROM TblEtiquetadoraContenido t WHERE t.nombreCompleto = :nombreCompleto")})
public class TblEtiquetadoraContenido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_formula")
    private Integer idFormula;
    @Basic(optional = false)
    @Column(name = "modo_de_uso")
    private String modoDeUso;
    @Basic(optional = false)
    @Column(name = "registro_mag")
    private String registroMag;
    @Basic(optional = false)
    @Column(name = "humedad")
    private int humedad;
    @Basic(optional = false)
    @Column(name = "proteina")
    private int proteina;
    @Basic(optional = false)
    @Column(name = "energia_digestible")
    private int energiaDigestible;
    @Basic(optional = false)
    @Column(name = "grasa")
    private int grasa;
    @Basic(optional = false)
    @Column(name = "fda")
    private int fda;
    @Basic(optional = false)
    @Column(name = "minerales_totales")
    private int mineralesTotales;
    @Basic(optional = false)
    @Column(name = "calcio")
    private int calcio;
    @Basic(optional = false)
    @Column(name = "fosforo")
    private int fosforo;
    @Basic(optional = false)
    @Column(name = "vit_a")
    private int vitA;
    @Basic(optional = false)
    @Column(name = "vit_d")
    private int vitD;
    @Basic(optional = false)
    @Column(name = "vit_e")
    private int vitE;
    @Basic(optional = false)
    @Column(name = "animal_destino")
    private String animalDestino;
    @Basic(optional = false)
    @Column(name = "categoria")
    private String categoria;
    @Column(name = "NNP")
    private Integer nnp;
    @Column(name = "S")
    private Integer s;
    @Column(name = "Mg")
    private Integer mg;
    @Column(name = "Na")
    private Integer na;
    @Column(name = "Co")
    private Integer co;
    @Column(name = "Cu")
    private Integer cu;
    @Column(name = "Se")
    private Integer se;
    @Column(name = "Mn")
    private Integer mn;
    @Column(name = "Zn")
    private Integer zn;
    @Column(name = "Y")
    private Integer y;
    @Column(name = "Fe")
    private Integer fe;
    @Column(name = "Cr")
    private Integer Cr;
    @Basic(optional = false)
    @Column(name = "nombre_completo")
    private String nombreCompleto;

    public TblEtiquetadoraContenido() {
    }

    public TblEtiquetadoraContenido(Integer idFormula) {
        this.idFormula = idFormula;
    }

    public TblEtiquetadoraContenido(Integer idFormula, String modoDeUso, String registroMag, int humedad, int proteina, int energiaDigestible, int grasa, int fda, int mineralesTotales, int calcio, int fosforo, int vitA, int vitD, int vitE, String animalDestino, String categoria, String nombreCompleto) {
        this.idFormula = idFormula;
        this.modoDeUso = modoDeUso;
        this.registroMag = registroMag;
        this.humedad = humedad;
        this.proteina = proteina;
        this.energiaDigestible = energiaDigestible;
        this.grasa = grasa;
        this.fda = fda;
        this.mineralesTotales = mineralesTotales;
        this.calcio = calcio;
        this.fosforo = fosforo;
        this.vitA = vitA;
        this.vitD = vitD;
        this.vitE = vitE;
        this.animalDestino = animalDestino;
        this.categoria = categoria;
        this.nombreCompleto = nombreCompleto;
    }

    public Integer getIdFormula() {
        return idFormula;
    }

    public void setIdFormula(Integer idFormula) {
        this.idFormula = idFormula;
    }

    public String getModoDeUso() {
        return modoDeUso;
    }

    public void setModoDeUso(String modoDeUso) {
        this.modoDeUso = modoDeUso;
    }

    public String getRegistroMag() {
        return registroMag;
    }

    public void setRegistroMag(String registroMag) {
        this.registroMag = registroMag;
    }

    public int getHumedad() {
        return humedad;
    }

    public void setHumedad(int humedad) {
        this.humedad = humedad;
    }

    public int getProteina() {
        return proteina;
    }

    public void setProteina(int proteina) {
        this.proteina = proteina;
    }

    public int getEnergiaDigestible() {
        return energiaDigestible;
    }

    public void setEnergiaDigestible(int energiaDigestible) {
        this.energiaDigestible = energiaDigestible;
    }

    public int getGrasa() {
        return grasa;
    }

    public void setGrasa(int grasa) {
        this.grasa = grasa;
    }

    public int getFda() {
        return fda;
    }

    public void setFda(int fda) {
        this.fda = fda;
    }

    public int getMineralesTotales() {
        return mineralesTotales;
    }

    public void setMineralesTotales(int mineralesTotales) {
        this.mineralesTotales = mineralesTotales;
    }

    public int getCalcio() {
        return calcio;
    }

    public void setCalcio(int calcio) {
        this.calcio = calcio;
    }

    public int getFosforo() {
        return fosforo;
    }

    public void setFosforo(int fosforo) {
        this.fosforo = fosforo;
    }

    public int getVitA() {
        return vitA;
    }

    public void setVitA(int vitA) {
        this.vitA = vitA;
    }

    public int getVitD() {
        return vitD;
    }

    public void setVitD(int vitD) {
        this.vitD = vitD;
    }

    public int getVitE() {
        return vitE;
    }

    public void setVitE(int vitE) {
        this.vitE = vitE;
    }

    public String getAnimalDestino() {
        return animalDestino;
    }

    public void setAnimalDestino(String animalDestino) {
        this.animalDestino = animalDestino;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getNnp() {
        return nnp;
    }

    public void setNnp(Integer nnp) {
        this.nnp = nnp;
    }

    public Integer getS() {
        return s;
    }

    public void setS(Integer s) {
        this.s = s;
    }

    public Integer getMg() {
        return mg;
    }

    public void setMg(Integer mg) {
        this.mg = mg;
    }

    public Integer getNa() {
        return na;
    }

    public void setNa(Integer na) {
        this.na = na;
    }

    public Integer getCo() {
        return co;
    }

    public void setCo(Integer co) {
        this.co = co;
    }

    public Integer getCu() {
        return cu;
    }

    public void setCu(Integer cu) {
        this.cu = cu;
    }

    public Integer getSe() {
        return se;
    }

    public void setSe(Integer se) {
        this.se = se;
    }

    public Integer getMn() {
        return mn;
    }

    public void setMn(Integer mn) {
        this.mn = mn;
    }

    public Integer getZn() {
        return zn;
    }

    public void setZn(Integer zn) {
        this.zn = zn;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getFe() {
        return fe;
    }

    public void setFe(Integer fe) {
        this.fe = fe;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFormula != null ? idFormula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblEtiquetadoraContenido)) {
            return false;
        }
        TblEtiquetadoraContenido other = (TblEtiquetadoraContenido) object;
        if ((this.idFormula == null && other.idFormula != null) || (this.idFormula != null && !this.idFormula.equals(other.idFormula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.chortitzer.industria.zebra.domain.bal.TblEtiquetadoraContenido[ idFormula=" + idFormula + " ]";
    }

    /**
     * @return the Cr
     */
    public Integer getCr() {
        return Cr;
    }

    /**
     * @param Cr the Cr to set
     */
    public void setCr(Integer Cr) {
        this.Cr = Cr;
    }

}
