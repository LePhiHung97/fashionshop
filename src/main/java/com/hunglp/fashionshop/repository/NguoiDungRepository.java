package com.hunglp.fashionshop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hunglp.fashionshop.entity.NguoiDung;

@Repository
public interface NguoiDungRepository extends CrudRepository<NguoiDung, Integer>{
	public NguoiDung findByUsername(String username);
}
