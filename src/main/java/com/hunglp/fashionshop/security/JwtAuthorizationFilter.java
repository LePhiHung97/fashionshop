package com.hunglp.fashionshop.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.hunglp.fashionshop.entity.NguoiDung;
import com.hunglp.fashionshop.entity.NguoiDungPrincipal;
import com.hunglp.fashionshop.repository.NguoiDungRepository;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

	private NguoiDungRepository nguoiDungRepository;

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, NguoiDungRepository nguoiDungRepository) {
		super(authenticationManager);
		this.nguoiDungRepository = nguoiDungRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// Read the Authorization header, where the jwt token should be
		String header = request.getHeader(JwtProperties.HEADER_STRING);

		// If the header not contain BEARER or is null, ->> delegate to String impl then
		// exit
		if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}

		// If header is present, try grab user principal form database and perform
		// authorization
		Authentication authentication = getUsernamePasswordAuthentication(request);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Continue filter execution
		chain.doFilter(request, response);
	}

	private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
		String token = request.getHeader(JwtProperties.HEADER_STRING);
		if (token != null) {

			// parse the token and validate
			String username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET.getBytes())).build()
					.verify(token.replace(JwtProperties.TOKEN_PREFIX, "")).getSubject();

			// Search in DB if find user by tokensubject
			// if so, grab user details and create spring auth token using username, pass,
			// authorities/roles
			if (username != null) {
				NguoiDung nguoiDung = nguoiDungRepository.findByUsername(username);
				NguoiDungPrincipal principal = new NguoiDungPrincipal(nguoiDung);
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null,
						principal.getAuthorities());
				return auth;
			}
			return null;
		}
		return null;
	}

}
