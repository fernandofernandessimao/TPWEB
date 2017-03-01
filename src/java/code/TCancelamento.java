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
@Table(name = "t_cancelamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TCancelamento.findAll", query = "SELECT t FROM TCancelamento t"),
    @NamedQuery(name = "TCancelamento.findById", query = "SELECT t FROM TCancelamento t WHERE t.id = :id"),
    @NamedQuery(name = "TCancelamento.findByRazao", query = "SELECT t FROM TCancelamento t WHERE t.razao = :razao"),
    @NamedQuery(name = "TCancelamento.findByDataCanc", query = "SELECT t FROM TCancelamento t WHERE t.dataCanc = :dataCanc")})
public class TCancelamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "razao")
    private String razao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_canc")
    @Temporal(TemporalType.DATE)
    private Date dataCanc;
    @JoinColumn(name = "itemid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TItem itemid;

    public TCancelamento() {
    }

    public TCancelamento(Integer id) {
        this.id = id;
    }

    public TCancelamento(Integer id, String razao, Date dataCanc) {
        this.id = id;
        this.razao = razao;
        this.dataCanc = dataCanc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    public Date getDataCanc() {
        return dataCanc;
    }

    public void setDataCanc(Date dataCanc) {
        this.dataCanc = dataCanc;
    }

    public TItem getItemid() {
        return itemid;
    }

    public void setItemid(TItem itemid) {
        this.itemid = itemid;
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
        if (!(object instanceof TCancelamento)) {
            return false;
        }
        TCancelamento other = (TCancelamento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "local.TCancelamento[ id=" + id + " ]";
    }
    
}
