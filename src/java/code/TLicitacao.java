/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author João
 */
@Entity
@Table(name = "t_licitacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TLicitacao.findAll", query = "SELECT t FROM TLicitacao t")
    , @NamedQuery(name = "TLicitacao.findById", query = "SELECT t FROM TLicitacao t WHERE t.id = :id")
    , @NamedQuery(name = "TLicitacao.findByItemId", query = "SELECT t FROM TLicitacao t WHERE t.itemid = :itemid")
    , @NamedQuery(name = "TLicitacao.findByValor", query = "SELECT t FROM TLicitacao t WHERE t.valor = :valor")
    , @NamedQuery(name = "TLicitacao.findByData", query = "SELECT t FROM TLicitacao t WHERE t.data = :data")})
public class TLicitacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private float valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @JoinColumn(name = "itemid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TItem itemid;
    @JoinColumn(name = "utilizadorid", referencedColumnName = "username")
    @ManyToOne(optional = false)
    private TUtilizador utilizadorid;

    public TLicitacao() {
    }

    public TLicitacao(Integer id) {
        this.id = id;
    }

    public TLicitacao(Integer id, float valor, Date data) {
        this.id = id;
        this.valor = valor;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public TItem getItemid() {
        return itemid;
    }

    public void setItemid(TItem itemid) {
        this.itemid = itemid;
    }

    public TUtilizador getUtilizadorid() {
        return utilizadorid;
    }

    public void setUtilizadorid(TUtilizador utilizadorid) {
        this.utilizadorid = utilizadorid;
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
        if (!(object instanceof TLicitacao)) {
            return false;
        }
        TLicitacao other = (TLicitacao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "code.TLicitacao[ id=" + id + " ]";
    }
    
}
