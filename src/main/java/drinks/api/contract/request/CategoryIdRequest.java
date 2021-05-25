package drinks.api.contract.request;

import javax.validation.constraints.NotNull;

public class CategoryIdRequest {

	@NotNull
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
