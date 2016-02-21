package br.com.autentication.business.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ROLE")

public class Role implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -76928370226369096L;

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="role_name",nullable=false)
	private String roleName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
