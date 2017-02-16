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
@Table(name = "t_utilizador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TUtilizador.findAll", query = "SELECT t FROM TUtilizador t")
    , @NamedQuery(name = "TUtilizador.findByNome", query = "SELECT t FROM TUtilizador t WHERE t.nome = :nome")
    , @NamedQuery(name = "TUtilizador.findByMorada", query = "SELECT t FROM TUtilizador t WHERE t.morada = :morada")
    , @NamedQuery(name = "TUtilizador.findByUsername", query = "SELECT t FROM TUtilizador t WHERE t.username = :username")
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
        this.activo = false;
        this.conectado = false;
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
        return !((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username)));
    }

    @Override
    public String toString() {
        return "code.TUtilizador[ username=" + username + " ]";
    }
    
}
