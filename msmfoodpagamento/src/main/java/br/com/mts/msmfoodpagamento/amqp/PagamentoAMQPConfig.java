package br.com.mts.msmfoodpagamento.amqp;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PagamentoAMQPConfig {

	@Bean
	public Queue criaFila() {
		//return new Queue("pagamento.concluido", false); ou
		
		return QueueBuilder.nonDurable("pagamento.concluido").build();
	}
	
	@Bean
	public RabbitAdmin criaRabbitAdmin(ConnectionFactory conexao) {
		return new RabbitAdmin(conexao);
	}
	
	@Bean
	public ApplicationListener<ApplicationReadyEvent> inicializaAdmin(RabbitAdmin rabbitAdmin){
		return event -> rabbitAdmin.initialize();
	}
}
