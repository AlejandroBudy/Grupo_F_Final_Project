package com.upm.es.grupof.productos.controller;

import com.upm.es.grupof.productos.entities.Category;
import com.upm.es.grupof.productos.entities.Product;
import com.upm.es.grupof.productos.services.ProductsService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;


public class ProductsControllerTest {

	@InjectMocks
	private ProductsController controller;

	@Mock
	private ProductsService service;
    private Product nonExistingProduct;

	@Before
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
        this.nonExistingProduct = new Product(Category.COMIDA, "IDontExist");
		when(this.service.getProductByName("jeans"))
				.thenReturn(new Product(Category.ROPA, "jeans"));
		when(this.service.deleteProduct(nonExistingProduct)).thenThrow(new Exception());
	}

	@Test
	public void shouldReturnProductWhenExits(){
		Product product =
				this.controller.getProduct("jeans");
		assertNotNull(product);
		assertEquals(product.getCategory(), Category.ROPA);
		assertEquals(product.getName(),"jeans");
	}

	@Test (expected = Exception.class)
	public void nonExistingProductReturnNegative() throws Exception{
        this.controller.deleteProduct(nonExistingProduct);
    }

    @Test
	public void shouldFinishOkWhenCreatedOk() throws Exception {
		this.controller.createProduct(new Product(Category.HERRAMIENTAS, "clavo"));
	}
}
