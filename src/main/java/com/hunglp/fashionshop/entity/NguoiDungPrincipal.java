package com.hunglp.fashionshop.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class NguoiDungPrincipal implements UserDetails {

	private NguoiDung nguoiDung;

	public NguoiDungPrincipal(NguoiDung nguoidung) {
		this.nguoiDung = nguoidung;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authories = new ArrayList<>();
		
		this.nguoiDung.layDSQuyen().forEach(p -> {
			GrantedAuthority authority = new SimpleGrantedAuthority(p);
			authories.add(authority);
		});
		
		this.nguoiDung.layDSQuyen().forEach(p -> {
			GrantedAuthority authority = new SimpleGrantedAuthority(p);
			authories.add(authority);
		});
		
		return authories;
	}

	@Override
	public String getPassword() {
		return this.nguoiDung.getPassword();
	}

	@Override
	public String getUsername() {
		return this.nguoiDung.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.nguoiDung.getTrangthai() == 1;
	}

}
