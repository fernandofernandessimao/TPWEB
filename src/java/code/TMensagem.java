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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "t_mensagem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TMensagem.findAll", query = "SELECT t FROM TMensagem t")
    ,
    @NamedQuery(name = "TMensagem.findById", query = "SELECT t FROM TMensagem t WHERE t.id = :id")
    ,
    @NamedQuery(name = "TMensagem.findBySender", query = "SELECT t FROM TMensagem t WHERE t.senderid = :senderid")
    ,
    @NamedQuery(name = "TMensagem.findByReceptor", query = "SELECT t FROM TMensagem t WHERE t.receptorid = :receptorid")
    ,
    @NamedQuery(name = "TMensagem.findByMensagem", query = "SELECT t FROM TMensagem t WHERE t.mensagem = :mensagem")
    ,
    @NamedQuery(name = "TMensagem.findByLida", query = "SELECT t FROM TMensagem t WHERE t.lida = :lida")})
public class TMensagem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "mensagem")
    private String mensagem;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lida")
    private boolean lida;
    @JoinColumn(name = "itemid", referencedColumnName = "id")
    @ManyToOne
    private TItem itemid;
    @JoinColumn(name = "senderid", referencedColumnName = "username")
    @ManyToOne(optional = false)
    private TUtilizador senderid;
    @JoinColumn(name = "receptorid", referencedColumnName = "username")
    @ManyToOne(optional = false)
    private TUtilizador receptorid;

    public TMensagem() {
    }

    public TMensagem(Integer id) {
        this.id = id;
    }

    public TMensagem(TUtilizador sender, TUtilizador receiver, String mensagem) {
        this.senderid = sender;
        this.receptorid = receiver;
        this.mensagem = mensagem;
        this.lida = false;
    }

    public TMensagem(TItem item, TUtilizador sender, TUtilizador receiver, String mensagem) {
        this.itemid = item;
        this.senderid = sender;
        this.receptorid = receiver;
        this.mensagem = mensagem;
        this.lida = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public boolean getLida() {
        return lida;
    }

    public void setLida(boolean lida) {
        this.lida = lida;
    }

    public TItem getItemid() {
        return itemid;
    }

    public void setItemid(TItem itemid) {
        this.itemid = itemid;
    }

    public TUtilizador getSenderid() {
        return senderid;
    }

    public void setSenderid(TUtilizador senderid) {
        this.senderid = senderid;
    }

    public TUtilizador getReceptorid() {
        return receptorid;
    }

    public void setReceptorid(TUtilizador receptorid) {
        this.receptorid = receptorid;
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
        if (!(object instanceof TMensagem)) {
            return false;
        }
        TMensagem other = (TMensagem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "code.TMensagem[ id=" + id + " ]";
    }

}
