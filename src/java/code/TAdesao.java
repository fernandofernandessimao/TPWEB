package code;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "t_adesao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TAdesao.findAll", query = "SELECT t FROM TAdesao t"),
    @NamedQuery(name = "TAdesao.findById", query = "SELECT t FROM TAdesao t WHERE t.id = :id"),
    @NamedQuery(name = "TAdesao.findByNome", query = "SELECT t FROM TAdesao t WHERE t.nome = :nome"),
    @NamedQuery(name = "TAdesao.findByMorada", query = "SELECT t FROM TAdesao t WHERE t.morada = :morada"),
    @NamedQuery(name = "TAdesao.findByUsername", query = "SELECT t FROM TAdesao t WHERE t.username = :username"),
    @NamedQuery(name = "TAdesao.findByPassword", query = "SELECT t FROM TAdesao t WHERE t.password = :password"),
    @NamedQuery(name = "TAdesao.findByPendente", query = "SELECT t FROM TAdesao t WHERE t.pendente = :pendente"),
    @NamedQuery(name = "TAdesao.findByAceite", query = "SELECT t FROM TAdesao t WHERE t.aceite = :aceite"),
    @NamedQuery(name = "TAdesao.findByDataProc", query = "SELECT t FROM TAdesao t WHERE t.dataProc = :dataProc"),
    @NamedQuery(name = "TAdesao.findByRazaoRej", query = "SELECT t FROM TAdesao t WHERE t.razaoRej = :razaoRej")})
public class TAdesao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
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
    @Column(name = "pendente")
    private boolean pendente;
    @Column(name = "aceite")
    private Boolean aceite;
    @Column(name = "data_proc")
    @Temporal(TemporalType.DATE)
    private Date dataProc;
    @Size(max = 200)
    @Column(name = "razao_rej")
    private String razaoRej;

    public TAdesao() {
    }

    public TAdesao(Integer id) {
        this.id = id;
    }

    public TAdesao(Integer id, String nome, String morada, String username, String password, boolean pendente) {
        this.id = id;
        this.nome = nome;
        this.morada = morada;
        this.username = username;
        this.password = password;
        this.pendente = pendente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public boolean getPendente() {
        return pendente;
    }

    public void setPendente(boolean pendente) {
        this.pendente = pendente;
    }

    public Boolean getAceite() {
        return aceite;
    }

    public void setAceite(Boolean aceite) {
        this.aceite = aceite;
    }

    public Date getDataProc() {
        return dataProc;
    }

    public void setDataProc(Date dataProc) {
        this.dataProc = dataProc;
    }

    public String getRazaoRej() {
        return razaoRej;
    }

    public void setRazaoRej(String razaoRej) {
        this.razaoRej = razaoRej;
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
        if (!(object instanceof TAdesao)) {
            return false;
        }
        TAdesao other = (TAdesao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "local.TAdesao[ id=" + id + " ]";
    }
    
}
