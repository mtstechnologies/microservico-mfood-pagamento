package br.com.mts.msmfoodpedido.amqp;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PedidoAMQPConfig {

	//metodo conversor de msg para json
		@Bean
		public Jackson2JsonMessageConverter messageConverter() {
			return new Jackson2JsonMessageConverter();
		}
		
		//metodo para devolver a mensagem convertida
		@Bean
		public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
								Jackson2JsonMessageConverter messageConverter) {
			RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
			rabbitTemplate.setMessageConverter(messageConverter);
			return rabbitTemplate;
		}
}
