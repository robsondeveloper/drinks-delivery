package drinks.api.contract.request;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class OrderRequest {

	@Valid
	@Size(min = 1)
	@NotNull
	private List<OrderItemRequest> items;

	public List<OrderItemRequest> getItems() {
		return items;
	}

	public void setItems(List<OrderItemRequest> items) {
		this.items = items;
	}

	public static class OrderItemRequest {
		@NotNull
		private Long productId;

		@NotNull
		@Positive
		private Short quantity;

		public Long getProductId() {
			return productId;
		}

		public void setProductId(Long productId) {
			this.productId = productId;
		}

		public Short getQuantity() {
			return quantity;
		}

		public void setQuantity(Short quantity) {
			this.quantity = quantity;
		}
	}

}
