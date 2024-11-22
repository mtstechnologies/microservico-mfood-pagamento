package br.com.mts.msmfoodpedido.amqp;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
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
		
		@Bean
		public Queue filaDetalhesPedido() {
			return QueueBuilder
					.nonDurable("pagamentos.detalhes-pedido")
					.build();
		}
		
		@Bean
		public FanoutExchange fanoutExchange() {
			return ExchangeBuilder
					.fanoutExchange("pagamentos.ex")
					.build();
		}
		//ligando a minha fila(filaDetalhesPedido)  ao exchange(FanoutExchange)
		@Bean
		public Binding binPagamentoPedido(FanoutExchange fanoutExchange) {
			return BindingBuilder.bind(filaDetalhesPedido())
					.to(fanoutExchange);
		}
		
		@Bean
		public RabbitAdmin criaRabbitAdmin(ConnectionFactory conexao) {
			return new RabbitAdmin(conexao);
		}
		
		//criando a fila no rabbit e detectar que houve conexao
		@Bean
		public ApplicationListener<ApplicationReadyEvent> inicializaAdmin(RabbitAdmin rabbitAdmin){
			return event -> rabbitAdmin.initialize();
		}
}
