package com.hunglp.fashionshop.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "nguoidung")
public class NguoiDung {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "manguoidung")
	private int manguoidung;
	private String username;
	private String password;
	private int trangthai;
	private String vaitro = "";
	private String quyen = "";

	public NguoiDung() {

	}

	public NguoiDung(String username, String password, String vaitro, String quyen) {
		this.username = username;
		this.password = password;
		this.quyen = quyen;
		this.vaitro = vaitro;
		this.trangthai = 1;
	}

	public int getManguoidung() {
		return manguoidung;
	}

	public void setManguoidung(int manguoidung) {
		this.manguoidung = manguoidung;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
	}

	public String getVaitro() {
		return vaitro;
	}

	public void setVaitro(String vaitro) {
		this.vaitro = vaitro;
	}

	public String getQuyen() {
		return quyen;
	}

	public void setQuyen(String quyen) {
		this.quyen = quyen;
	}

	public List<String> layDsVaiTro() {
		if (this.vaitro.length() > 0)
			return Arrays.asList(this.vaitro.split(","));
		return new ArrayList<>();
	}

	public List<String> layDSQuyen() {
		if (this.quyen.length() > 0)
			return Arrays.asList(this.quyen.split(","));
		return new ArrayList<>();
	}
}
