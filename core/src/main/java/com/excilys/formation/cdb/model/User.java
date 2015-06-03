package com.excilys.formation.cdb.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Serializable {
	private static final long serialVersionUID = -6072037844916439885L;
	@Id
	private String username;
	private String password;
	private boolean enabled;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "username", cascade = CascadeType.ALL)
	private Collection<Authority> authorities;

	public boolean isEnable() {
		return enabled;
	}

	public void setEnable(boolean enable) {
		enabled = enable;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(Collection<Authority> authorities) {
		this.authorities = authorities;
	}

	public Collection<Authority> getAuthorities() {
		return authorities;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public boolean isAccountNonExpired() {
		return enabled;
	}

	public boolean isAccountNonLocked() {
		return enabled;
	}

	public boolean isCredentialsNonExpired() {
		return enabled;
	}

	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password
				+ ", enable=" + enabled + ", authorities=" + authorities + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result)
				+ ((authorities == null) ? 0 : authorities.hashCode());
		result = (prime * result) + (enabled ? 1231 : 1237);
		result = (prime * result)
				+ ((password == null) ? 0 : password.hashCode());
		result = (prime * result)
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		if (authorities == null) {
			if (other.authorities != null) {
				return false;
			}
		} else if (!authorities.equals(other.authorities)) {
			return false;
		}
		if (enabled != other.enabled) {
			return false;
		}
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (username == null) {
			if (other.username != null) {
				return false;
			}
		} else if (!username.equals(other.username)) {
			return false;
		}
		return true;
	}

}
