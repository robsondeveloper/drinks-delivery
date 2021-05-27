package drinks.api.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import drinks.api.contract.request.OrderRequest;
import drinks.api.contract.response.OrderResponse;
import drinks.service.OrderService;

@RestController
@RequestMapping("/v1/orders")
public class OrderResource {

	private final OrderService service;

	public OrderResource(OrderService service) {
		this.service = service;
	}

	@GetMapping
	public List<OrderResponse> findAll() {
		return service.findAll();
	}

	@GetMapping(path = "/{id}")
	public OrderResponse findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrderResponse create(@RequestBody @Valid OrderRequest request) {
		return service.create(request);
	}

}
