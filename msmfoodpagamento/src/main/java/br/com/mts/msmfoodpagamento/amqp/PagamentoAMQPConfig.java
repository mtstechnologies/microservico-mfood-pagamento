package br.com.mts.msmfoodpagamento.amqp;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PagamentoAMQPConfig {

	/*
	 * @Bean public Queue criaFila() { //return new Queue("pagamento.concluido",
	 * false); ou
	 * 
	 * return QueueBuilder.nonDurable("pagamento.concluido").build(); }
	 */
	@Bean
	public FanoutExchange fanoutExchange() {
		return new FanoutExchange("pagamentos.ex");
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
	
	
	//https://www.rabbitmq.com/docs/download
	//docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:4.0-management
}
