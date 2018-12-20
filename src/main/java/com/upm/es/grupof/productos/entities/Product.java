package com.upm.es.grupof.productos.entities;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Enumerated(EnumType.STRING)
	private Category category;
	private String name;


	protected Product(){}

	public Product(Category category, String name){
		this.category = category;
		this.name = name;
	}

	public String getName(){
		return "";
	}
}
