/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastroserver.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author italo
 */
@Entity
@Table(name = "pessoafisica")
@NamedQueries({
    @NamedQuery(name = "Pessoafisica.findAll", query = "SELECT p FROM Pessoafisica p"),
    @NamedQuery(name = "Pessoafisica.findByIdpessoafisica", query = "SELECT p FROM Pessoafisica p WHERE p.idpessoafisica = :idpessoafisica"),
    @NamedQuery(name = "Pessoafisica.findByCpf", query = "SELECT p FROM Pessoafisica p WHERE p.cpf = :cpf")})
public class Pessoafisica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpessoafisica")
    private Integer idpessoafisica;
    @Basic(optional = false)
    @Column(name = "cpf")
    private String cpf;
    @JoinColumn(name = "pessoa_idpessoa", referencedColumnName = "idpessoa")
    @OneToOne
    private Pessoa pessoaIdpessoa;

    public Pessoafisica() {
    }

    public Pessoafisica(Integer idpessoafisica) {
        this.idpessoafisica = idpessoafisica;
    }

    public Pessoafisica(Integer idpessoafisica, String cpf) {
        this.idpessoafisica = idpessoafisica;
        this.cpf = cpf;
    }

    public Integer getIdpessoafisica() {
        return idpessoafisica;
    }

    public void setIdpessoafisica(Integer idpessoafisica) {
        this.idpessoafisica = idpessoafisica;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Pessoa getPessoaIdpessoa() {
        return pessoaIdpessoa;
    }

    public void setPessoaIdpessoa(Pessoa pessoaIdpessoa) {
        this.pessoaIdpessoa = pessoaIdpessoa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpessoafisica != null ? idpessoafisica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pessoafisica)) {
            return false;
        }
        Pessoafisica other = (Pessoafisica) object;
        if ((this.idpessoafisica == null && other.idpessoafisica != null) || (this.idpessoafisica != null && !this.idpessoafisica.equals(other.idpessoafisica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cadastroserver.model.Pessoafisica[ idpessoafisica=" + idpessoafisica + " ]";
    }
    
}
