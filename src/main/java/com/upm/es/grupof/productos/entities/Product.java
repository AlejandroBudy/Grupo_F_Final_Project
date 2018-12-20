package com.upm.es.grupof.productos.entities;

import javax.persistence.*;

@Entity
public class Product {

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private long id;

	@Enumerated(EnumType.STRING)
	private Category category;
	private String name;


	protected Product(){}

	public Product(Category category, String name){
		this.category = category;
		this.name = name;
	}
}
