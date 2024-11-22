package br.com.mts.msmfoodpedido.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import br.com.mts.msmfoodpedido.dto.PagamentoDto;

@Component
public class PagamentoListener {

	//classe consumidora de mensagem
	
	@RabbitListener(queues = "pagamentos.detalhes-pedido")
	public void recebeMensagem(PagamentoDto pagamento) {
		//text bloc surgiu a partir da versao 15 do java, usado para formatar uma mensagem
		String mensagem = """
				Dados do pagamento: %s
				Número do pedido: %s
				Valor R$: %s
				Status: %s 
				""".formatted(
					pagamento.getId(), 
					pagamento.getPedidoId(), 
					pagamento.getValor(), 
					pagamento.getStatus());
		
		System.out.println("Recebi a mensagem" + mensagem);
	}
}
