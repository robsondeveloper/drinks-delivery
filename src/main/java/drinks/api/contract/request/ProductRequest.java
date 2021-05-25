package drinks.api.contract.request;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ProductRequest {

	@NotBlank
	private String name;

	@NotNull
	@Positive
	private BigDecimal price;

	@Valid
	@NotNull
	private CategoryIdRequest category;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public CategoryIdRequest getCategory() {
		return category;
	}

	public void setCategory(CategoryIdRequest category) {
		this.category = category;
	}

}
