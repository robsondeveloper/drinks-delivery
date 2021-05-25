package drinks.service;

import java.text.Normalizer;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import drinks.api.contract.request.CategoryRequest;
import drinks.api.contract.response.CategoryResponse;
import drinks.domain.model.Category;
import drinks.domain.repository.CategoryRepository;
import drinks.exception.ResourceNotFoundException;

@Service
public class CategoryService {

	private final CategoryRepository repository;
	private final ModelMapper modelMapper;

	public CategoryService(CategoryRepository repository, ModelMapper modelMapper) {
		this.repository = repository;
		this.modelMapper = modelMapper;
	}

	public List<CategoryResponse> findAll() {
		return repository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
	}

	public CategoryResponse findById(Long id) {
		var category = findBy(id);
		return toResponse(category);
	}

	@Transactional
	public CategoryResponse create(CategoryRequest request) {
		if (repository.existsByName(request.getName())) {
			throw new IllegalArgumentException("Nome já existente!");
		}
		var category = modelMapper.map(request, Category.class);
		category.setCode(generateCode(category.getName()));
		return toResponse(repository.save(category));
	}

	@Transactional
	public CategoryResponse update(Long id, CategoryRequest request) {
		var category = findBy(id);
		if (repository.existsByNameAndIdNot(request.getName(), id)) {
			throw new IllegalArgumentException("Nome já existente!");
		}
		modelMapper.map(request, category);
		category.setCode(generateCode(category.getName()));
		return toResponse(repository.save(category));
	}

	@Transactional
	public void delete(Long id) {
		var category = findBy(id);
		repository.delete(category);
	}

	private Category findBy(Long id) {
		return repository.findById(id).orElseThrow(ResourceNotFoundException::new);
	}

	private CategoryResponse toResponse(Category category) {
		return modelMapper.map(category, CategoryResponse.class);
	}

	private String generateCode(String name) {
		var unaccent = Normalizer.normalize(name, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
		var words = unaccent.toLowerCase().split(" ");
		if (words.length == 1) {
			return words[0];
		} else {
			return String.join("-", words);
		}
	}

}
