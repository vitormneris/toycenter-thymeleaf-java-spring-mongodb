package br.edu.toycenter.api.convert;

import java.util.List;

import org.springframework.stereotype.Component;

import br.edu.toycenter.api.response.OrderItemResponseDTO;
import br.edu.toycenter.api.response.OrderResponseDTO;
import br.edu.toycenter.infrastructure.entities.Order;
import br.edu.toycenter.infrastructure.entities.User;

@Component
public class OrderConvert {
	
//	public Product forProduct(ProductRequestDTO productDTO) {
//		Product product = new Product.Builder()
//				.id(productDTO.id())
//				.name(productDTO.name())
//				.brand(productDTO.brand())
//				.price(productDTO.price())
//				.description(productDTO.description())
//				.details(productDTO.details())
//				.build();
//		
//		for (String categoryId: productDTO.categories()) {
//			product.getCategoriesId().add(categoryId);
//		}
//		
//		return product;
//	}
	
	public OrderResponseDTO forOrderResponseDTO(Order order, User user, List<OrderItemResponseDTO> orderItemDTO) {
		OrderResponseDTO orderDTO = new OrderResponseDTO(				
				order.getId(),
				order.getMoment(),
				user,
				orderItemDTO);
				
		return orderDTO;
	}
}
