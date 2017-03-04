/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "t_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TItem.findAll", query = "SELECT t FROM TItem t")
    , @NamedQuery(name = "TItem.findById", query = "SELECT t FROM TItem t WHERE t.id = :id")
    , @NamedQuery(name = "TItem.findByDescricao", query = "SELECT t FROM TItem t WHERE t.descricao = :descricao")
    , @NamedQuery(name = "TItem.findByPrecoInicial", query = "SELECT t FROM TItem t WHERE t.precoInicial = :precoInicial")
    , @NamedQuery(name = "TItem.findByPrecoImediato", query = "SELECT t FROM TItem t WHERE t.precoImediato = :precoImediato")
    , @NamedQuery(name = "TItem.findByPrazo", query = "SELECT t FROM TItem t WHERE t.prazo = :prazo")
    , @NamedQuery(name = "TItem.findByValor", query = "SELECT t FROM TItem t WHERE t.valor = :valor")
    , @NamedQuery(name = "TItem.findByConcluido", query = "SELECT t FROM TItem t WHERE t.concluido = :concluido")
    , @NamedQuery(name = "TItem.findByComprado", query = "SELECT t FROM TItem t WHERE t.comprado = :comprado")
    , @NamedQuery(name = "TItem.findByUltLicData", query = "SELECT t FROM TItem t WHERE t.ultLicData = :ultLicData")})
public class TItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "preco_inicial")
    private float precoInicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "preco_imediato")
    private float precoImediato;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prazo")
    @Temporal(TemporalType.DATE)
    private Date prazo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private float valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "concluido")
    private boolean concluido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "comprado")
    private boolean comprado;
    @Column(name = "ult_lic_data")
    @Temporal(TemporalType.DATE)
    private Date ultLicData;
    @ManyToMany(mappedBy = "tItemSegueCollection")
    private Collection<TUtilizador> tUtilizadorSegueCollection;
    @ManyToMany(mappedBy = "tItemLicitacaoCollection")
    private Collection<TUtilizador> tUtilizadorLicitacaoCollection;
    @JoinColumn(name = "categoriaid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TCategoria categoriaid;
    @JoinColumn(name = "vendedorid", referencedColumnName = "username")
    @ManyToOne(optional = false)
    private TUtilizador vendedorid;
    @JoinColumn(name = "compradorid", referencedColumnName = "username")
    @ManyToOne
    private TUtilizador compradorid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iditem")
    private Collection<TDenunciaItem> tDenunciaItemCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemid")
    private Collection<TCancelamento> tCancelamentoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemid")
    private Collection<TMensagem> tMensagemCollection;

    public TItem() {
    }

    public TItem(Integer id) {
        this.id = id;
    }

    public TItem(Integer id, String descricao, float precoInicial, float precoImediato, Date prazo, float valor, boolean concluido, boolean comprado) {
        this.id = id;
        this.descricao = descricao;
        this.precoInicial = precoInicial;
        this.precoImediato = precoImediato;
        this.prazo = prazo;
        this.valor = valor;
        this.concluido = concluido;
        this.comprado = comprado;
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

    public float getPrecoInicial() {
        return precoInicial;
    }

    public void setPrecoInicial(float precoInicial) {
        this.precoInicial = precoInicial;
    }

    public float getPrecoImediato() {
        return precoImediato;
    }

    public void setPrecoImediato(float precoImediato) {
        this.precoImediato = precoImediato;
    }

    public Date getPrazo() {
        return prazo;
    }

    public void setPrazo(Date prazo) {
        this.prazo = prazo;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public boolean getConcluido() {
        return concluido;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }

    public boolean getComprado() {
        return comprado;
    }

    public void setComprado(boolean comprado) {
        this.comprado = comprado;
    }

    public Date getUltLicData() {
        return ultLicData;
    }

    public void setUltLicData(Date ultLicData) {
        this.ultLicData = ultLicData;
    }

    @XmlTransient
    public Collection<TUtilizador> getTUtilizadorSegueCollection() {
        return tUtilizadorSegueCollection;
    }

    public void setTUtilizadorSegueCollection(Collection<TUtilizador> tUtilizadorCollection) {
        this.tUtilizadorSegueCollection = tUtilizadorCollection;
    }

    @XmlTransient
    public Collection<TUtilizador> getTUtilizadorLicitacaoCollection() {
        return tUtilizadorLicitacaoCollection;
    }

    public void setTUtilizadorLicitacaoCollection(Collection<TUtilizador> tUtilizadorCollection) {
        this.tUtilizadorLicitacaoCollection = tUtilizadorCollection;
    }

    public TCategoria getCategoriaid() {
        return categoriaid;
    }

    public void setCategoriaid(TCategoria categoriaid) {
        this.categoriaid = categoriaid;
    }

    public TUtilizador getVendedorid() {
        return vendedorid;
    }

    public void setVendedorid(TUtilizador vendedorid) {
        this.vendedorid = vendedorid;
    }

    public TUtilizador getCompradorid() {
        return compradorid;
    }

    public void setCompradorid(TUtilizador compradorid) {
        this.compradorid = compradorid;
    }

    @XmlTransient
    public Collection<TDenunciaItem> getTDenunciaItemCollection() {
        return tDenunciaItemCollection;
    }

    public void setTDenunciaItemCollection(Collection<TDenunciaItem> tDenunciaItemCollection) {
        this.tDenunciaItemCollection = tDenunciaItemCollection;
    }

    @XmlTransient
    public Collection<TCancelamento> getTCancelamentoCollection() {
        return tCancelamentoCollection;
    }

    public void setTCancelamentoCollection(Collection<TCancelamento> tCancelamentoCollection) {
        this.tCancelamentoCollection = tCancelamentoCollection;
    }

    public Collection<TUtilizador> gettUtilizadorSegueCollection() {
        return tUtilizadorSegueCollection;
    }

    public void settUtilizadorSegueCollection(Collection<TUtilizador> tUtilizadorSegueCollection) {
        this.tUtilizadorSegueCollection = tUtilizadorSegueCollection;
    }

    public Collection<TUtilizador> gettUtilizadorLicitacaoCollection() {
        return tUtilizadorLicitacaoCollection;
    }

    public void settUtilizadorLicitacaoCollection(Collection<TUtilizador> tUtilizadorLicitacaoCollection) {
        this.tUtilizadorLicitacaoCollection = tUtilizadorLicitacaoCollection;
    }

    public Collection<TDenunciaItem> gettDenunciaItemCollection() {
        return tDenunciaItemCollection;
    }

    public void settDenunciaItemCollection(Collection<TDenunciaItem> tDenunciaItemCollection) {
        this.tDenunciaItemCollection = tDenunciaItemCollection;
    }

    public Collection<TCancelamento> gettCancelamentoCollection() {
        return tCancelamentoCollection;
    }

    public void settCancelamentoCollection(Collection<TCancelamento> tCancelamentoCollection) {
        this.tCancelamentoCollection = tCancelamentoCollection;
    }

    public Collection<TMensagem> gettMensagemCollection() {
        return tMensagemCollection;
    }

    public void settMensagemCollection(Collection<TMensagem> tMensagemCollection) {
        this.tMensagemCollection = tMensagemCollection;
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
        if (!(object instanceof TItem)) {
            return false;
        }
        TItem other = (TItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "local.TItem[ id=" + id + " ]";
    }
    
}

