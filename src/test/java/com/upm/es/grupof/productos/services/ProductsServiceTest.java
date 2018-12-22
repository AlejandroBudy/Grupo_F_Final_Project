package com.upm.es.grupof.productos.services;

import com.upm.es.grupof.productos.database.DataBaseLoader;
import com.upm.es.grupof.productos.entities.Product;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.upm.es.grupof.productos.entities.Category.ROPA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class ProductsServiceTest {

	@Mock
	private DataBaseLoader dataBaseLoader;

	@InjectMocks
	private ProductsService service;
	private Product productReturn;
	private Product nonExistingProduct;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.productReturn = new Product(ROPA,"Jeans");
		this.nonExistingProduct = new Product(ROPA, "IDontExist");
		when(this.dataBaseLoader.getProductByName("Jeans")).thenReturn(productReturn);
		when(this.dataBaseLoader.getProductByName("notExists")).thenThrow(new Exception());
		when(this.dataBaseLoader.deleteProduct(nonExistingProduct)).thenThrow(new Exception());
	}

	@Test
	public void shouldReturnProductWhenExists(){
		Product product = this.service.getProductByName("Jeans");
		assertEquals(product.getName(),this.productReturn.getName());
		assertEquals(product.getCategory(),this.productReturn.getCategory());
	}

	@Test
	public void shouldReturnNullWhenProductNotExits(){
		Product product = this.service.getProductByName("notExists");
		assertNull(product);
	}

	@Test (expected = Exception.class)
	public void shouldThrowExceptionIfProductDoesntExist() throws Exception{
		service.deleteProduct(nonExistingProduct);
	}

	@Test
	public void shouldCreateNonExistingProduct() throws Exception {
		service.createProduct(nonExistingProduct);
	}
}
