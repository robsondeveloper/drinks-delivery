package drinks.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import drinks.api.contract.request.OrderRequest.OrderItemRequest;
import drinks.domain.model.OrderItem;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();

		modelMapper.createTypeMap(OrderItemRequest.class, OrderItem.class)
				.addMappings(mapper -> mapper.skip(OrderItem::setId));

		return modelMapper;
	}

}