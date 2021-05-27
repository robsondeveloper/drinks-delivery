package drinks.api.contract.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import drinks.domain.model.OrderStatus;

public class OrderResponse {

	private Long id;

	private OrderStatus status;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime createdAt;

	private List<OrderItemResponse> items;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public List<OrderItemResponse> getItems() {
		return items;
	}

	public void setItems(List<OrderItemResponse> items) {
		this.items = items;
	}

	public static class OrderItemResponse {
		private Long id;
		private BigDecimal pricePerUnit;
		private Short quantity;
		private String productName;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public BigDecimal getPricePerUnit() {
			return pricePerUnit;
		}

		public void setPricePerUnit(BigDecimal pricePerUnit) {
			this.pricePerUnit = pricePerUnit;
		}

		public Short getQuantity() {
			return quantity;
		}

		public void setQuantity(Short quantity) {
			this.quantity = quantity;
		}

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}
	}

}
