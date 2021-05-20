package br.com.hzzp.desafiotransacao.transacao;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hzzp.desafiotransacao.transacao.externo.TransacaoClient;
import br.com.hzzp.desafiotransacao.transacao.externo.TransacaoClient.TransacaoRequest;
import br.com.hzzp.desafiotransacao.transacao.externo.TransacaoClient.TransacaoResponse;
import feign.FeignException;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

	private TransacaoClient trClient;
	
	TransacaoController(TransacaoClient trClient) {
		this.trClient = trClient;
	}
	
	@PostMapping @Transactional
	public ResponseEntity<TransacaoResponse>enviarTransacao(@RequestBody TransacaoRequest request) {

		TransacaoResponse respostaTransacao = null;
		
		try {
			respostaTransacao = trClient.inicioTransacao(request);
		} catch (FeignException e) {
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok().body(respostaTransacao);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?>pararTransacao(@PathVariable("id") String nroCartao) {
		
		try {
			trClient.fimTransacao(nroCartao);
		} catch (FeignException e) {
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok().build();
	}
	
}
