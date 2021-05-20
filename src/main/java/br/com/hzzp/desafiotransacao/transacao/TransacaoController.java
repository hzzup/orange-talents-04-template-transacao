package br.com.hzzp.desafiotransacao.transacao;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hzzp.desafiotransacao.transacao.externo.TransacaoClient;
import br.com.hzzp.desafiotransacao.transacao.externo.TransacaoClient.TransacaoRequestFeign;
import br.com.hzzp.desafiotransacao.transacao.externo.TransacaoClient.TransacaoResponseFeign;
import feign.FeignException;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

	private TransacaoClient trClient;
	private TransacaoRepository transacaoRep;
	
	TransacaoController(TransacaoClient trClient, TransacaoRepository transacaoRep) {
		this.trClient = trClient;
		this.transacaoRep = transacaoRep;
	}
	
	@PostMapping @Transactional
	public ResponseEntity<TransacaoResponseFeign>enviarTransacao(@RequestBody TransacaoRequestFeign request) {

		TransacaoResponseFeign respostaTransacao = null;
		
		try {
			respostaTransacao = trClient.inicioTransacao(request);
		} catch (FeignException e) {
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok().body(respostaTransacao);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<TransacaoResponseDezUltimas>> listarUltimasDezTransacoes(@PathVariable("id") Long cartaoId){
		
		if(!transacaoRep.existsByCartaoId(cartaoId)) {
			return ResponseEntity.notFound().build();
		}
		
        List<Transacao> dezPrimeirasTransacoes = ListaDezPrimeirasTransacoes(cartaoId);
        List<TransacaoResponseDezUltimas> responseList = dezPrimeirasTransacoes.stream()
        		.map(transacao->new TransacaoResponseDezUltimas(transacao.getEfetivadaEm(),transacao.getValor())).
        		collect(Collectors.toList());
		
		//transacaoRep.findByCartaoId
        return ResponseEntity.ok().body(responseList);
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
	
	private List<Transacao> ListaDezPrimeirasTransacoes(Long cartaoId) {
		Sort sort = Sort.by(Sort.Direction.DESC,"efetivadaEm");
        Integer page = 0; Integer size = 10; //pagina 0 devolvendo os 10 primeiros
        PageRequest pageRequest = PageRequest.of(page,size,sort);
        return transacaoRep.findByCartaoId(cartaoId,pageRequest).toList();
	}
}
