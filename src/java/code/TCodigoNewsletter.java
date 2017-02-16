/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name = "t_codigo_newsletter")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TCodigoNewsletter.findAll", query = "SELECT t FROM TCodigoNewsletter t")
    , @NamedQuery(name = "TCodigoNewsletter.findById", query = "SELECT t FROM TCodigoNewsletter t WHERE t.id = :id")
    , @NamedQuery(name = "TCodigoNewsletter.findByDescricao", query = "SELECT t FROM TCodigoNewsletter t WHERE t.descricao = :descricao")})
public class TCodigoNewsletter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigo")
    private List<TNewsletter> tNewsletterList;

    public TCodigoNewsletter() {
    }

    public TCodigoNewsletter(Integer id) {
        this.id = id;
    }

    public TCodigoNewsletter(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public List<TNewsletter> getTNewsletterList() {
        return tNewsletterList;
    }

    public void setTNewsletterList(List<TNewsletter> tNewsletterList) {
        this.tNewsletterList = tNewsletterList;
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
        if (!(object instanceof TCodigoNewsletter)) {
            return false;
        }
        TCodigoNewsletter other = (TCodigoNewsletter) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "code.TCodigoNewsletter[ id=" + id + " ]";
    }
    
}
