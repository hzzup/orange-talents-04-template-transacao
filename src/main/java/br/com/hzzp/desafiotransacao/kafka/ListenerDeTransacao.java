package br.com.hzzp.desafiotransacao.kafka;

import java.util.Optional;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.hzzp.desafiotransacao.transacao.Transacao;
import br.com.hzzp.desafiotransacao.transacao.TransacaoRepository;
import br.com.hzzp.desafiotransacao.transacao.TransacaoResponse;
import br.com.hzzp.desafiotransacao.transacao.cartao.Cartao;
import br.com.hzzp.desafiotransacao.transacao.cartao.CartaoRepository;

@Component
public class ListenerDeTransacao {

	TransacaoRepository transacaoRep;
	CartaoRepository cartaoRep;
	
	public ListenerDeTransacao(TransacaoRepository transacaoRep, CartaoRepository cartaoRep) {
		this.transacaoRep = transacaoRep;
		this.cartaoRep = cartaoRep;
	}
	
    @KafkaListener(topics = "${spring.kafka.topic.transactions}") @Transactional
    public void ouvir(TransacaoResponse eventoTransacao) {
    	Optional<Cartao> cartaoExistente = cartaoRep.findByCartaoNro(eventoTransacao.getCartao().getId());
    	Transacao novaTransacao = eventoTransacao.toModel(cartaoExistente);
    	transacaoRep.save(novaTransacao);
        //System.out.println(eventoDeTransacao);
        //System.out.println("Id = " + eventoDeTransacao.getId() + " Valor = " + eventoDeTransacao.getValor());
    }
}