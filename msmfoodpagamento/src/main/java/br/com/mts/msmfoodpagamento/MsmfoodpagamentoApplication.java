package br.com.mts.msmfoodpagamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsmfoodpagamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsmfoodpagamentoApplication.class, args);
	}

}
