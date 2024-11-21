package br.com.mts.msmfoodpedido.amqp;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PagamentoListener {

	//classe consumidora de mensagem
	
	@RabbitListener(queues = "pagamento.concluido")
	public void recebeMensagem(Message mensagem) {
		System.out.println("Recebi a mensagem" + mensagem.toString());
	}
}
