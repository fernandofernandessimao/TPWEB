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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name = "t_newsletter")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TNewsletter.findAll", query = "SELECT t FROM TNewsletter t")
    , @NamedQuery(name = "TNewsletter.findById", query = "SELECT t FROM TNewsletter t WHERE t.id = :id")
    , @NamedQuery(name = "TNewsletter.findByData", query = "SELECT t FROM TNewsletter t WHERE t.data = :data")
    , @NamedQuery(name = "TNewsletter.findByMensagem", query = "SELECT t FROM TNewsletter t WHERE t.mensagem = :mensagem")})
public class TNewsletter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "mensagem")
    private String mensagem;
    @JoinColumn(name = "codigo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TCodigoNewsletter codigo;

    public TNewsletter() {
    }

    public TNewsletter(Integer id) {
        this.id = id;
    }

    public TNewsletter(Integer id, Date data, String mensagem) {
        this.id = id;
        this.data = data;
        this.mensagem = mensagem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public TCodigoNewsletter getCodigo() {
        return codigo;
    }

    public void setCodigo(TCodigoNewsletter codigo) {
        this.codigo = codigo;
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
        if (!(object instanceof TNewsletter)) {
            return false;
        }
        TNewsletter other = (TNewsletter) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "code.TNewsletter[ id=" + id + " ]";
    }
    
}
