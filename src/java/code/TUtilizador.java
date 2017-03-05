/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "t_utilizador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TUtilizador.findAll", query = "SELECT t FROM TUtilizador t")
    , @NamedQuery(name = "TUtilizador.findByNome", query = "SELECT t FROM TUtilizador t WHERE t.nome = :nome")
    , @NamedQuery(name = "TUtilizador.findByMorada", query = "SELECT t FROM TUtilizador t WHERE t.morada = :morada")
    , @NamedQuery(name = "TUtilizador.findByUsername", query = "SELECT t FROM TUtilizador t WHERE t.username = :username")    
    , @NamedQuery(name = "TUtilizador.findByUsers", query = "SELECT t FROM TUtilizador t WHERE t.username like :username")    
    , @NamedQuery(name = "TUtilizador.findByPassword", query = "SELECT t FROM TUtilizador t WHERE t.password = :password")
    , @NamedQuery(name = "TUtilizador.findBySaldo", query = "SELECT t FROM TUtilizador t WHERE t.saldo = :saldo")
    , @NamedQuery(name = "TUtilizador.findByActivo", query = "SELECT t FROM TUtilizador t WHERE t.activo = :activo")
    , @NamedQuery(name = "TUtilizador.findByConectado", query = "SELECT t FROM TUtilizador t WHERE t.conectado = :conectado")})
public class TUtilizador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "morada")
    private String morada;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "saldo")
    private float saldo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "conectado")
    private boolean conectado;
    @JoinTable(name = "t_segue", joinColumns = {
        @JoinColumn(name = "utilizadorid", referencedColumnName = "username")}, inverseJoinColumns = {
        @JoinColumn(name = "itemid", referencedColumnName = "id")})
    @ManyToMany
    private Collection<TItem> tItemSegueCollection;
    @JoinTable(name = "t_licitacao", joinColumns = {
        @JoinColumn(name = "utilizadorid", referencedColumnName = "username")}, inverseJoinColumns = {
        @JoinColumn(name = "itemid", referencedColumnName = "id")})
    @ManyToMany
    private Collection<TItem> tItemLicitacaoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendedorid")
    private Collection<TItem> tItemVendedorCollection;
    @OneToMany(mappedBy = "compradorid")
    private Collection<TItem> tItemCompradorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "username")
    private Collection<TDenunciaUtilizador> tDenunciaUtilizadorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utilizadorid")
    private Collection<TSuspensao> tSuspensaoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utilizadorid")
    private Collection<TReactivacao> tReactivacaoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "senderid")
    private Collection<TMensagem> tMensagemSenderCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receptorid")
    private Collection<TMensagem> tMensagemReceptorCollection;

    public TUtilizador() {
    }

    public TUtilizador(String username) {
        this.username = username;
    }

    public TUtilizador(String username, String nome, String morada, String password) {
        this.username = username;
        this.nome = nome;
        this.morada = morada;
        this.password = password;
        this.saldo = 500;
        this.activo = true;
        this.conectado = false;
    }

    public TUtilizador(String username, String nome, String morada, String password, float saldo, boolean activo, boolean conectado) {
        this.username = username;
        this.nome = nome;
        this.morada = morada;
        this.password = password;
        this.saldo = saldo;
        this.activo = activo;
        this.conectado = conectado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean getConectado() {
        return conectado;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }

    @XmlTransient
    public Collection<TItem> getTItemSegueCollection() {
        return tItemSegueCollection;
    }

    public void setTItemSegueCollection(Collection<TItem> tItemCollection) {
        this.tItemSegueCollection = tItemCollection;
    }

    @XmlTransient
    public Collection<TItem> getTItemLicitacaoCollection() {
        return tItemLicitacaoCollection;
    }

    public void setTItemLicitacaoCollection(Collection<TItem> tItemCollection) {
        this.tItemLicitacaoCollection = tItemCollection;
    }

    @XmlTransient
    public Collection<TItem> getTItemVendedorCollection() {
        return tItemVendedorCollection;
    }

    public void setTItemVendedorCollection(Collection<TItem> tItemCollection) {
        this.tItemVendedorCollection = tItemCollection;
    }

    @XmlTransient
    public Collection<TItem> getTItemCompradorCollection() {
        return tItemCompradorCollection;
    }

    public void setTItemCompradorCollection(Collection<TItem> tItemCollection) {
        this.tItemCompradorCollection = tItemCollection;
    }

    @XmlTransient
    public Collection<TDenunciaUtilizador> getTDenunciaUtilizadorCollection() {
        return tDenunciaUtilizadorCollection;
    }

    public void setTDenunciaUtilizadorCollection(Collection<TDenunciaUtilizador> tDenunciaUtilizadorCollection) {
        this.tDenunciaUtilizadorCollection = tDenunciaUtilizadorCollection;
    }

    @XmlTransient
    public Collection<TSuspensao> getTSuspensaoCollection() {
        return tSuspensaoCollection;
    }

    public void setTSuspensaoCollection(Collection<TSuspensao> tSuspensaoCollection) {
        this.tSuspensaoCollection = tSuspensaoCollection;
    }

    @XmlTransient
    public Collection<TReactivacao> getTReactivacaoCollection() {
        return tReactivacaoCollection;
    }

    public void setTReactivacaoCollection(Collection<TReactivacao> tReactivacaoCollection) {
        this.tReactivacaoCollection = tReactivacaoCollection;
    }

    @XmlTransient
    public Collection<TMensagem> getTMensagemSenderCollection() {
        return tMensagemSenderCollection;
    }

    public void setTMensagemSenderCollection(Collection<TMensagem> tMensagemCollection) {
        this.tMensagemSenderCollection = tMensagemCollection;
    }

    @XmlTransient
    public Collection<TMensagem> getTMensagemReceptorCollection() {
        return tMensagemReceptorCollection;
    }

    public void setTMensagemReceptorCollection(Collection<TMensagem> tMensagemCollection) {
        this.tMensagemReceptorCollection = tMensagemCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TUtilizador)) {
            return false;
        }
        TUtilizador other = (TUtilizador) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "local.TUtilizador[ username=" + username + " ]";
    }

}
