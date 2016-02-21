package br.com.autentication.business.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="LOGIN")
@NamedQueries({
    @NamedQuery(name = "Login.findById", query = "SELECT l from Login l WHERE l.id = :id") 
})
public class Login  implements Serializable  {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3488489358946758613L;
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String nome;
	@Column(nullable=false, unique=true)
	private String email;
	@Column(nullable=false)
	private String senha;	
	@Column(nullable=false, unique=true)
	private boolean ativo;
	@Column(nullable=false, unique=true)
	private Date dataCriacao;
	@Column(nullable=false, unique=true)
	private Date dataUltimoLogin;
	@Column(nullable=false, unique=true)
	private Date dataAlteracaoSenha;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
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
}
