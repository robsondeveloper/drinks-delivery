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

import drinks.api.contract.request.CategoryRequest;
import drinks.api.contract.response.CategoryResponse;
import drinks.service.CategoryService;

@RestController
@RequestMapping("/v1/categories")
public class CategoryResource {

	private final CategoryService service;

	public CategoryResource(CategoryService service) {
		this.service = service;
	}

	@GetMapping
	public List<CategoryResponse> findAll() {
		return service.findAll();
	}

	@GetMapping(path = "/{id}")
	public CategoryResponse findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CategoryResponse create(@RequestBody @Valid CategoryRequest request) {
		return service.create(request);
	}

	@PutMapping(path = "/{id}")
	public CategoryResponse update(@PathVariable Long id, @RequestBody @Valid CategoryRequest request) {
		return service.update(id, request);
	}

	@DeleteMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

}
