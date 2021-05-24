package drinks.api.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import drinks.api.contract.request.ProductRequest;
import drinks.api.contract.response.ProductResponse;
import drinks.service.ProductService;

@RestController
@RequestMapping("/v1/products")
public class ProductResource {

	private final ProductService service;

	public ProductResource(ProductService service) {
		this.service = service;
	}

	@GetMapping
	public List<ProductResponse> findAll() {
		return service.findAll();
	}

	@GetMapping(path = "/{id}")
	public ProductResponse findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProductResponse create(@RequestBody @Valid ProductRequest request) {
		return service.create(request);
	}

	@PutMapping(path = "/{id}")
	public ProductResponse update(@PathVariable Long id, @RequestBody @Valid ProductRequest request) {
		return service.update(id, request);
	}

	@DeleteMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

}
