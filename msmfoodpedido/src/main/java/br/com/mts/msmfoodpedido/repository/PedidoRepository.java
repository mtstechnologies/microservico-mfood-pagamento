package br.com.mts.msmfoodpedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.mts.msmfoodpedido.model.Pedido;
import br.com.mts.msmfoodpedido.model.Status;
import jakarta.transaction.Transactional;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

	 @Transactional
	    @Modifying(clearAutomatically = true)
	    @Query("update Pedido p set p.status = :status where p = :pedido")
	    void atualizaStatus(Status status, Pedido pedido);

	    @Query(value = "SELECT p from Pedido p LEFT JOIN FETCH p.itens where p.id = :id")
	    Pedido porIdComItens(Long id);
}
