package drinks.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import drinks.api.contract.request.OrderRequest;
import drinks.api.contract.response.OrderResponse;
import drinks.domain.model.Order;
import drinks.domain.model.OrderStatus;
import drinks.domain.repository.OrderRepository;
import drinks.domain.repository.ProductRepository;
import drinks.exception.ResourceNotFoundException;

@Service
public class OrderService {

	private final OrderRepository repository;
	private final ModelMapper modelMapper;
	private final ProductRepository productRepository;

	public OrderService(OrderRepository repository, ModelMapper modelMapper, ProductRepository productRepository) {
		this.repository = repository;
		this.modelMapper = modelMapper;
		this.productRepository = productRepository;
	}

	public List<OrderResponse> findAll() {
		return repository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
	}

	public OrderResponse findById(Long id) {
		var order = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado!"));
		return toResponse(order);
	}

	@Transactional
	public OrderResponse create(OrderRequest request) {
		var order = modelMapper.map(request, Order.class);
		order.getItems().forEach(item -> {
			var product = productRepository.findById(item.getProduct().getId())
					.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado!"));
			item.setOrder(order);
			item.setProduct(product);
			item.setPricePerUnit(product.getPrice());
		});
		order.calculateTotal();
		order.setStatus(OrderStatus.CREATED);
		order.setCreatedAt(LocalDateTime.now());
		return toResponse(repository.save(order));
	}

	private OrderResponse toResponse(Order order) {
		return modelMapper.map(order, OrderResponse.class);
	}

}
