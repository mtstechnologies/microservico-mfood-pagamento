package br.com.mts.msmfoodpagamento.amqp;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PagamentoAMQPConfig {

	public Queue criaFila() {
		//return new Queue("pagamento.concluido", false); ou
		
		return QueueBuilder.nonDurable("pagamento.concluido").build();
	}
}
