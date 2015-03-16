package br.com.furb.pmattiollo.tcc.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import br.com.furb.pmattiollo.tcc.constant.UserEnum;

@MappedSuperclass
public abstract class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long id;
	
	@Column(name = "name", nullable = false, length = 100)
	private String name;
	
	@Column(name = "login", nullable = false, length = 50, unique = true)
	private String login;
	
	@Column(name = "password", nullable = false, length = 50)
	private String password;
	
	@Column(name = "email", nullable = false, length=100)
	private String email;
	
	@Column(name = "active", nullable = false)
	private Boolean active;
	
	@Column(name="paper", nullable=false, length=50)
	@Enumerated(EnumType.STRING)
    private UserEnum paper; 
	
	public User() {
		super();
	}	
	
	public User(String name, String login, String password, String email, Boolean active, UserEnum paper) {
		this.name = name;
		this.login = login;
		this.password = password;
		this.email = email;
		this.active = active;
		this.paper = paper;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Boolean getActive() {
		return active;
	}
	
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	public UserEnum getPaper() {
		return paper;
	}
	
	public void setPaper(UserEnum paper) {
		this.paper = paper;
	}
	
}
