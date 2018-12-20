package com.upm.es.grupof.productos.database;

import com.upm.es.grupof.productos.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataBaseLoader {

	@Autowired
	private ProductsRepository repository;

	@PostConstruct
	private void initDataBase(){

	}
}
