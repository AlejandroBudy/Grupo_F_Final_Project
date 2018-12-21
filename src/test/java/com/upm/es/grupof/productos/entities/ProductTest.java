package com.upm.es.grupof.productos.entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProductTest {

	private Product product;


	@Before
	public void setUp(){
		this.product = new Product(Category.COMIDA,"rice");
	}

	@Test
	public void shouldNotBeNull(){
		assertNotNull(this.product);
	}

	@Test
	public void testGetterAndSetter(){
		assertEquals(this.product.getName(),"rice");
		assertEquals(this.product.getCategory(),Category.COMIDA);
		this.product.setCategory(Category.ROPA);
		this.product.setName("jumper");
		assertEquals(this.product.getName(),"jumper");
		assertEquals(this.product.getCategory(),Category.ROPA);
	}
}
