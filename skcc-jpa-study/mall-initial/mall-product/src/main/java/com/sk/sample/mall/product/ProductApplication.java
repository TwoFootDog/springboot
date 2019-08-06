package com.sk.sample.mall.product;

import java.util.Iterator;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.config.EnableHypermediaSupport;

import com.sk.sample.mall.product.domain.model.ColorType;
import com.sk.sample.mall.product.domain.model.Money;
import com.sk.sample.mall.product.domain.model.Product;
import com.sk.sample.mall.product.domain.model.ProductDescription;
import com.sk.sample.mall.product.domain.model.QProduct;
import com.sk.sample.mall.product.domain.model.SizeType;
import com.sk.sample.mall.product.domain.repository.ProductRepository;

@SpringBootApplication
@EnableHypermediaSupport(type=EnableHypermediaSupport.HypermediaType.HAL)
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner execSampleCode(ProductRepository productRepository) {	
		return (args) -> {
			insertProduct(productRepository);
			
			executeExample1(productRepository);
			executeExample2(productRepository);
			executeExample3(productRepository);
			executeExample4(productRepository);
			executeExample5(productRepository);
			executeExample6(productRepository);
			executeExample7(productRepository);
		};
	}
	
	public void insertProduct(ProductRepository productRepository) {
		
		Product product1 = new Product("Iron Man", new Money(30000), new ProductDescription(ColorType.RED, SizeType.L));
		productRepository.save(product1);
		
		Product product2 = new Product("Captain America", new Money(20000), new ProductDescription(ColorType.BLUE, SizeType.M));
		productRepository.save(product2);
		
		Product product3 = new Product("Winter Soldier", new Money(17000), new ProductDescription(ColorType.YELLOW, SizeType.M));
		productRepository.save(product3);
		
	}
	
	public void executeExample1(ProductRepository productRepository) {
		// 데이터 출력
		System.out.println("***************************************************************");
		Iterable<Product> productList = productRepository.findAll();
		for(Product product : productList) {
			System.out.println("문제 1) 제품 등록 후 리스트 출력>>>>>>>>>>>>>>>" + product.toString());	
		}
		System.out.println("***************************************************************");
	}
	
	
	public void executeExample2(ProductRepository productRepository) {
		// 데이터 조회 후 값 변경
		List<Product> productList =  productRepository.findByName("Iron Man");
		Iterator itr = productList.iterator();
		for(Product product: productList) {
			product.setPrice(new Money(25000));
			productRepository.save(product);
		}
		
		
		// 데이터 출력
		System.out.println("***************************************************************");
		Iterable<Product> productList2 = productRepository.findAll();
		for(Product product : productList2) {
			System.out.println("문제 2) 금액 변경 후 리스트 출력>>>>>>>>>>>>>>>" + product.toString());	
		}
		System.out.println("***************************************************************");
	
	}
	
	public void executeExample3(ProductRepository productRepository) {
		// 데이터 출력
		System.out.println("***************************************************************");
//		Iterable<Product> productList = productRepository.findByProductDescriptionSizeType(SizeType.M);
		Iterable<Product> productList =  productRepository.findAll(QProduct.product.productDescription.sizeType.eq(SizeType.M));
		
		for(Product product : productList) {
			System.out.println("문제 3) 사이즈 M인 리스트 출력>>>>>>>>>>>>>>>" + product.toString());	
		}
		System.out.println("***************************************************************");
	}
	
	
	public void executeExample4(ProductRepository productRepository) {
		// 데이터 출력
		System.out.println("***************************************************************");
//		Iterable<Product> productList = productRepository.findByProductDescriptionColorType(ColorType.BLUE);
		Iterable<Product> productList =  productRepository.findAll(QProduct.product.productDescription.colorType.eq(ColorType.BLUE));
		
		for(Product product : productList) {
			System.out.println("문제 4) 색상이 BLUE인 리스트 출력>>>>>>>>>>>>>>>" + product.toString());	
		}
		System.out.println("***************************************************************");
	}
	
	public void executeExample5(ProductRepository productRepository) {
		// 데이터 출력
		System.out.println("***************************************************************");
		
//		Iterable<Product> productList = productRepository.findByPriceValueGreaterThanEqual(17000);
		Iterable<Product> productList =  productRepository.findAll(QProduct.product.price.value.gt(17000).or(QProduct.product.price.value.eq(17000)));
		
		for(Product product : productList) {
			System.out.println("문제 5) 금액이 15000 이상인 리스트 출력>>>>>>>>>>>>>>>" + product.toString());	
		}
		System.out.println("***************************************************************");
	}
	
	public void executeExample6(ProductRepository productRepository) {
		// 데이터 출력
		System.out.println("***************************************************************");
		
//		Iterable<Product> productList = productRepository.findByPriceValueLessThenEqual(21000);
//		Prediction p = QProduct.product.price.value.loe(21000);
		Iterable<Product> productList =  productRepository.findAll(QProduct.product.price.value.lt(21000).or(QProduct.product.price.value.eq(21000)));
		
		for(Product product : productList) {
			System.out.println("문제 6) 금액이 21000 이하인 리스트 출력>>>>>>>>>>>>>>>" + product.toString());	
		}
		System.out.println("***************************************************************");
	}
	
	public void executeExample7(ProductRepository productRepository) {
		// 데이터 출력
		System.out.println("***************************************************************");
		
//		Iterable<Product> productList = productRepository.findByPriceValueGreaterThanEqualAndPriceValueLessThen(10000, 20000);
		Iterable<Product> productList =  productRepository.findAll(QProduct.product.price.value.gt(10000).and(QProduct.product.price.value.lt(20000)));
		
		for(Product product : productList) {
			System.out.println("문제 7) 금액이 10000보다 크고 20000미만인 리스트 출력>>>>>>>>>>>>>>>" + product.toString());	
		}
		System.out.println("***************************************************************");
	}

}
