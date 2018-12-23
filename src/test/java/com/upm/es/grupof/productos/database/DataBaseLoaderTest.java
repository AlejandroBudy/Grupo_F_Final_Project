package com.upm.es.grupof.productos.database;

import com.upm.es.grupof.productos.entities.Category;
import com.upm.es.grupof.productos.entities.Product;
import com.upm.es.grupof.productos.repository.ProductsRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class DataBaseLoaderTest {

	@InjectMocks
	private DataBaseLoader dataBaseLoader;
	@Mock
	private ProductsRepository repository;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(this.repository.findByName("Jeans"))
				.thenReturn(this.buildProductList());
		when(this.repository.findByName("ggg"))
				.thenReturn(null);
	}

	@Test
	public void shouldReturnProductWhenExits() throws Exception {
		Product product = this.dataBaseLoader.getProductByName("Jeans");
		assertEquals(product.getCategory(),Category.ROPA);
		assertEquals(product.getName(),"Jeans");
	}

	@Test(expected = Exception.class)
	public void shouldThrowExceptionProductWhenNotExits() throws Exception {
		this.dataBaseLoader.getProductByName("ggg");
	}

	private List<Product> buildProductList(){
		Product product = new Product(Category.ROPA,"Jeans");
		List<Product> productList = new ArrayList<>();
		productList.add(product);
		return productList;
	}

	@Test (expected = Exception.class)
	public void deletingNonExistingProductThrowsException() throws Exception{
		Product product = new Product(Category.ROPA,"ggg");
		dataBaseLoader.deleteProduct(product);
	}

	@Test
	public void shouldCreateNonExistingProduct(){
		dataBaseLoader.createProd(new Product(Category.HERRAMIENTAS, "destornillador"));

  @Test  
	public void productCorrectlyDeletedDoesntThrowException() throws  Exception{
		Product existingProduct = new Product(Category.ROPA, "Jeans");
		dataBaseLoader.deleteProduct(existingProduct);
	}

	@Test (expected = Exception.class)
	public void updatingNonExistingProductsThrowsException() throws Exception{
		Product existingProduct = new Product(Category.ROPA, "ggg");
		dataBaseLoader.updateProduct(existingProduct, Category.ROPA, "nuevo");
	}

	@Test (expected = Exception.class)
	public void updatingExistingProductWithDifferentCategoryThrowsException() throws Exception{
		Product existingProduct = new Product(Category.COMIDA, "Jeans");
		dataBaseLoader.updateProduct(existingProduct, Category.ROPA, "nuevo");
	}

	@Test
	public void updatingExistingProductWorksCorrectly() throws Exception{
		Product existingProduct = new Product(Category.ROPA, "Jeans");
		dataBaseLoader.updateProduct(existingProduct, Category.ROPA, "nuevo");
	}
}
