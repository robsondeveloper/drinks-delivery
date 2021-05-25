package drinks.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import drinks.api.contract.request.ProductRequest;
import drinks.api.contract.response.ProductResponse;
import drinks.domain.model.Product;
import drinks.domain.repository.CategoryRepository;
import drinks.domain.repository.ProductRepository;
import drinks.exception.FileException;
import drinks.exception.ResourceNotFoundException;
import drinks.service.storage.PhotoStorageService;

@Service
public class ProductService {

	private final ProductRepository repository;
	private final ModelMapper modelMapper;
	private final PhotoStorageService photoStorageService;
	private final CategoryRepository categoryRepository;

	public ProductService(ProductRepository repository, ModelMapper modelMapper,
			PhotoStorageService photoStorageService, CategoryRepository categoryRepository) {
		this.repository = repository;
		this.modelMapper = modelMapper;
		this.photoStorageService = photoStorageService;
		this.categoryRepository = categoryRepository;
	}

	public List<ProductResponse> findAll() {
		return repository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
	}

	public ProductResponse findById(Long id) {
		var product = findBy(id);
		return toResponse(product);
	}

	@Transactional
	public ProductResponse create(ProductRequest request) {
		validateCategory(request.getCategory().getId());
		if (repository.existsByName(request.getName())) {
			throw new IllegalArgumentException("Nome já existente!");
		}
		var product = modelMapper.map(request, Product.class);
		return toResponse(repository.save(product));
	}

	@Transactional
	public ProductResponse update(Long id, ProductRequest request) {
		validateCategory(request.getCategory().getId());
		if (repository.existsByNameAndIdNot(request.getName(), id)) {
			throw new IllegalArgumentException("Nome já existente!");
		}
		var product = findBy(id);
		product.setCategory(null);
		modelMapper.map(request, product);
		return toResponse(repository.save(product));
	}

	@Transactional
	public void delete(Long id) {
		var product = findBy(id);
		repository.delete(product);
	}

	public void uploadPhoto(Long id, MultipartFile file) {
		var product = findBy(id);
		photoStorageService.delete(product.getPhoto());
		try {
			var urlPhoto = photoStorageService.upload(file.getBytes());
			product.setPhoto(urlPhoto);
			repository.save(product);
		} catch (IOException e) {
			throw new FileException();
		}
	}

	private Product findBy(Long id) {
		return repository.findById(id).orElseThrow(ResourceNotFoundException::new);
	}

	private ProductResponse toResponse(Product product) {
		return modelMapper.map(product, ProductResponse.class);
	}

	private void validateCategory(Long id) {
		if (!categoryRepository.existsById(id)) {
			throw new ResourceNotFoundException("Categoria não existente!");
		}
	}

}
