package com.demo.spring_security.config;

import com.demo.spring_security.domain.User;
import com.demo.spring_security.domain.UserAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Parisana
 */
public class CustomUserDetails implements UserDetails {
	private static final long serialVersionUID = 5639683223516504866L;
	private final User user;
	private final List<GrantedAuthority> authorities;

	public CustomUserDetails(User user) {
		this.user = user;
		/**
		 * AuthorityUtils.commaSeparatedStringToAuthorityList(String authorityString)
		 * Creates a array of GrantedAuthority objects from a comma-separated string
		 * representation (e.g. "ROLE_A, ROLE_B, ROLE_C").
		 */
		this.authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(
				user.getAuthorities().stream().map(UserAuthority::getAuthority).collect(Collectors.joining(", ")));
	}

	public Long getId(){
		return this.user.getId();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.user.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.user.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.user.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return this.user.isEnabled();
	}
}
