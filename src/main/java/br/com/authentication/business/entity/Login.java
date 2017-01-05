package br.com.authentication.business.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="LOGIN")
@NamedQueries({
	@NamedQuery(name = "Login.findById", query = "SELECT l from Login l WHERE l.id = :id") 
})
public class Login implements Serializable  {

	private static final long serialVersionUID = 3488489358946758613L;
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="LOGIN_ID")
	private BigDecimal id;
	
	@Column(name="LOGIN_NOME", nullable=false)
	private String nome;
	
	@Column(name="LOGIN_EMAIL", nullable=false, unique=true)	
	private String email;
	
	@Column(name="LOGIN_SENHA", nullable=false)
	private String senha;
	
	@Column(name="LOGIN_STATUS", nullable=false)
	private String status = Status.ATIVO.getStatusCode();
	
	@Column(name="LOGIN_CREATED", nullable=false, unique=true)
	@Type(type="date")
	private Date dataCriacao;
	
	@Column(name="LOGIN_DT_ULTIMO_LOGIN", nullable=true)
	@Type(type="date")
	private Date dataUltimoLogin;
	
	@Column(name="LOGIN_DT_ALTERACAO_SENHA", nullable=true)
	@Type(type="date")
	private Date dataAlteracaoSenha;
	
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
   
    @PrePersist
    public void prePersist() {
        this.dataCriacao = new Date();
        this.dataUltimoLogin = new Date();
    }
    
    @PreUpdate
    public void preUpdate() {
        this.dataUltimoLogin = new Date();
    }

	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public Date getDataUltimoLogin() {
		return dataUltimoLogin;
	}
	public void setDataUltimoLogin(Date dataUltimoLogin) {
		this.dataUltimoLogin = dataUltimoLogin;
	}
	public Date getDataAlteracaoSenha() {
		return dataAlteracaoSenha;
	}
	public void setDataAlteracaoSenha(Date dataAlteracaoSenha) {
		this.dataAlteracaoSenha = dataAlteracaoSenha;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}	
}
