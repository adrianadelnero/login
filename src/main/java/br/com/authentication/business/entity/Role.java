package br.com.authentication.business.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="ROLE")
public class Role {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ROLE_ID")
    private long id;

	@Column(name="ROLE_NOME")
    private String role;

    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    @Column(name="ROLE_LOGIN_ID")
    private Set<Login> logins;
    
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<Login> getLogins() {
		return logins;
	}

	public void setLogins(Set<Login> logins) {
		this.logins = logins;
	} 
}
