package com.hunglp.fashionshop.db;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.hunglp.fashionshop.entity.NguoiDung;
import com.hunglp.fashionshop.repository.NguoiDungRepository;

@Service 
public class DBInit implements CommandLineRunner{

	private NguoiDungRepository nguoiDungRepository;
	
	public DBInit(NguoiDungRepository nguoiDungRepository) {
		this.nguoiDungRepository = nguoiDungRepository;
	}
	
	@Override
	public void run(String... args) throws Exception {
		NguoiDung user = new NguoiDung("hunglp","hunglp","USER","USER_ACTION");
		NguoiDung admin = new NguoiDung("admin","admin","ADMIN","ADMIN_ACTION");
		
		this.nguoiDungRepository.save(user);
		this.nguoiDungRepository.save(admin);
		
		
	}

	

}
