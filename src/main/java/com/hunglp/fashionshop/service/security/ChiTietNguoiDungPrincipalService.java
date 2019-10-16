package com.hunglp.fashionshop.service.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hunglp.fashionshop.entity.NguoiDung;
import com.hunglp.fashionshop.entity.NguoiDungPrincipal;
import com.hunglp.fashionshop.repository.NguoiDungRepository;

@Service
public class ChiTietNguoiDungPrincipalService implements UserDetailsService {

	private NguoiDungRepository nguoidungRepository;

	public ChiTietNguoiDungPrincipalService(NguoiDungRepository nguoidungRepository) {
		this.nguoidungRepository = nguoidungRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		NguoiDung nguoidung = this.nguoidungRepository.findByUsername(username);
		NguoiDungPrincipal nguoiDungPrincipal = new NguoiDungPrincipal(nguoidung);

		return nguoiDungPrincipal;
	}

}
