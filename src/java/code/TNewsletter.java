/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

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
    , @NamedQuery(name = "TNewsletter.findByTipo", query = "SELECT t FROM TNewsletter t WHERE t.tipo = :tipo")
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
    @Size(min = 1, max = 20)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "data")
    private String data;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "mensagem")
    private String mensagem;

    public TNewsletter() {
    }

    public TNewsletter(Integer id) {
        this.id = id;
    }

    public TNewsletter(String tipo, String data, String mensagem) {        
        this.tipo = tipo;
        this.data = data;
        this.mensagem = mensagem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
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
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "code.TNewsletter[ id=" + id + " ]";
    }
    
}
