package br.com.hzzp.desafiotransacao.transacao.externo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url="${transacao.host}", name="transacao")
public interface TransacaoClient {

	//metodo para iniciar transacao, enviar o numero do cartao + email no body da requisicao
	@PostMapping("/api/cartoes")
	public TransacaoResponseFeign inicioTransacao(@RequestBody TransacaoRequestFeign request);
	
	//metodo para parar as transacoes, enviar o numero do cartao na url
	@DeleteMapping("/api/cartoes/{id}")
	public TransacaoResponseFeign fimTransacao(@PathVariable("id") String nroCartao);
	
	
	class TransacaoRequestFeign {
		private String id;
		private String email;
		
		public TransacaoRequestFeign(String id, String email) {
			this.id = id;
			this.email = email;
		}

		public String getId() {return id;}
		public String getEmail() {return email;}
	}
	
	class TransacaoResponseFeign {
		private String id;
		private String email;
		
		public TransacaoResponseFeign(String id, String email) {
			this.id = id;
			this.email = email;
		}

		public String getId() {return id;}
		public String getEmail() {return email;}
	}
}
