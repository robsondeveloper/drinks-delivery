package drinks.domain.model;

public enum OrderStatus {

	CREATED("Criado"),
	CONFIRMATED("Confirmado"),
	DELIVERED("Entregue"),
	CANCELED("Cancelado");

	private String description;

	OrderStatus(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
