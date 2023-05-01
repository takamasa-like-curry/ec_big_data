package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Brand;

@Mapper
public interface BrandsMapper {

	String pickUpNameById(int id);

	List<Brand> findByName(String name);
}