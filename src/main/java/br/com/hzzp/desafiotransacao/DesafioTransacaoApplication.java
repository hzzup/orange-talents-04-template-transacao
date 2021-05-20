package br.com.hzzp.desafiotransacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableFeignClients
@EnableTransactionManagement
@EnableJpaRepositories(enableDefaultTransactions = false)
public class DesafioTransacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioTransacaoApplication.class, args);
	}

}