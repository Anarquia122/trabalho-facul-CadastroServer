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
@Table(name = "pessoajuridica")
@NamedQueries({
    @NamedQuery(name = "Pessoajuridica.findAll", query = "SELECT p FROM Pessoajuridica p"),
    @NamedQuery(name = "Pessoajuridica.findByIdpessoajuridica", query = "SELECT p FROM Pessoajuridica p WHERE p.idpessoajuridica = :idpessoajuridica"),
    @NamedQuery(name = "Pessoajuridica.findByCnpj", query = "SELECT p FROM Pessoajuridica p WHERE p.cnpj = :cnpj")})
public class Pessoajuridica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpessoajuridica")
    private Integer idpessoajuridica;
    @Basic(optional = false)
    @Column(name = "cnpj")
    private String cnpj;
    @JoinColumn(name = "pessoa_idpessoa", referencedColumnName = "idpessoa")
    @OneToOne
    private Pessoa pessoaIdpessoa;

    public Pessoajuridica() {
    }

    public Pessoajuridica(Integer idpessoajuridica) {
        this.idpessoajuridica = idpessoajuridica;
    }

    public Pessoajuridica(Integer idpessoajuridica, String cnpj) {
        this.idpessoajuridica = idpessoajuridica;
        this.cnpj = cnpj;
    }

    public Integer getIdpessoajuridica() {
        return idpessoajuridica;
    }

    public void setIdpessoajuridica(Integer idpessoajuridica) {
        this.idpessoajuridica = idpessoajuridica;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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
        hash += (idpessoajuridica != null ? idpessoajuridica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pessoajuridica)) {
            return false;
        }
        Pessoajuridica other = (Pessoajuridica) object;
        if ((this.idpessoajuridica == null && other.idpessoajuridica != null) || (this.idpessoajuridica != null && !this.idpessoajuridica.equals(other.idpessoajuridica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cadastroserver.model.Pessoajuridica[ idpessoajuridica=" + idpessoajuridica + " ]";
    }
    
}
