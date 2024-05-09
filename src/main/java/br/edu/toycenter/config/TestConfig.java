package br.edu.toycenter.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;

import br.edu.toycenter.infrastructure.entities.Category;
import br.edu.toycenter.infrastructure.entities.Order;
import br.edu.toycenter.infrastructure.entities.OrderItem;
import br.edu.toycenter.infrastructure.entities.Product;
import br.edu.toycenter.infrastructure.entities.User;
import br.edu.toycenter.infrastructure.repositories.CategoryRepository;
import br.edu.toycenter.infrastructure.repositories.OrderRepository;
import br.edu.toycenter.infrastructure.repositories.ProductRepository;
import br.edu.toycenter.infrastructure.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
		
    @Autowired
    private MongoTemplate mongoTemplate;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public void run(String... args) throws Exception {
        mongoTemplate.getDb().drop();
		
		User u1 = new User(null, "951.954.148-88", "Maria Brown", "maria@gmail.com", "11988888888", "65cc4123");
		User u2 = new User(null, "152.361.141-64", "Alex Green", "alex@gmail.com", "11977777777", "123cc456");
		User u3 = new User(null, "142.141.391-51", "James Red", "james@gmail.com", "11966666666", "654cc321");
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3));
		
		Product p1 = new Product(null, "Carrinho de controle remoto", "Estrela", 99.90, "Carrinho de controle remoto de qualidade"
				, "Carrinho a pilha de 40 cm" );
		Product p2 = new Product(null, "Boneca da Barbie", "Funtoy", 120.99, "Boneca da Barbie do seus sonhos"
				, "Boneca de plástico de 16 cm");
		Product p3 = new Product(null, "PlayStation 3", "Sony", 1299.80, "Console de antepenúltima geração maravilhoso"
				, "Console com 256 GB de memória");
		
		productRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Category c1 = new Category(null, "Carrinhos");
		Category c2 = new Category(null, "Bonecas");
		Category c3 = new Category(null, "Consoles");
		
		categoryRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		p1.getCategoriesId().add(c1.getId());
		p2.getCategoriesId().add(c2.getId());
		p3.getCategoriesId().add(c3.getId());
		
		c1.getProductsId().add(p1.getId());
		c2.getProductsId().add(p2.getId());
		c3.getProductsId().add(p3.getId());
		
		productRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		categoryRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		OrderItem oi1 = new OrderItem(1, p1.getPrice(), p1.getId());
		OrderItem oi2 = new OrderItem(2, p2.getPrice(), p2.getId());
		OrderItem oi3 = new OrderItem(3, p3.getPrice(), p3.getId());
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), u1.getId(), Arrays.asList(oi1));
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), u2.getId(), Arrays.asList(oi2));
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), u3.getId(), Arrays.asList(oi3));
	
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		
		u1.getOrdersId().add(o1.getId());
		u2.getOrdersId().add(o2.getId());
		u3.getOrdersId().add(o3.getId());

		userRepository.saveAll(Arrays.asList(u1, u2, u3));
		

		
	}
}