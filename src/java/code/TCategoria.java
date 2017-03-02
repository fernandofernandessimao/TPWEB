package code;

import java.io.Serializable;
import java.util.Collection;
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

@Entity
@Table(name = "t_categoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TCategoria.findAll", query = "SELECT t FROM TCategoria t")
    , @NamedQuery(name = "TCategoria.findById", query = "SELECT t FROM TCategoria t WHERE t.id = :id")
    , @NamedQuery(name = "TCategoria.findByNome", query = "SELECT t FROM TCategoria t WHERE t.nome = :nome")})
public class TCategoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nome")
    private String nome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoriaid")
    private Collection<TItem> tItemCollection;

    public TCategoria() {
    }

    public TCategoria(Integer id) {
        this.id = id;
    }

    public TCategoria(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
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

    @XmlTransient
    public Collection<TItem> getTItemCollection() {
        return tItemCollection;
    }

    public void setTItemCollection(Collection<TItem> tItemCollection) {
        this.tItemCollection = tItemCollection;
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
        if (!(object instanceof TCategoria)) {
            return false;
        }
        TCategoria other = (TCategoria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "local.TCategoria[ id=" + id + " ]";
    }
    
}

