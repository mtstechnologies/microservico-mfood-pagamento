package br.com.mts.msmfoodpagamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mts.msmfoodpagamento.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long>{

}
