package br.com.hzzp.desafiotransacao.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.hzzp.desafiotransacao.transacao.Transacao;
import br.com.hzzp.desafiotransacao.transacao.TransacaoRepository;
import br.com.hzzp.desafiotransacao.transacao.TransacaoResponse;

@Component
public class ListenerDeTransacao {

	TransacaoRepository transacaoRep;
	
	public ListenerDeTransacao(TransacaoRepository transacaoRep) {
		this.transacaoRep = transacaoRep;
	}
	
    @KafkaListener(topics = "${spring.kafka.topic.transactions}") @Transactional
    public void ouvir(TransacaoResponse eventoTransacao) {
    	Transacao novaTransacao = eventoTransacao.toModel();
    	transacaoRep.save(novaTransacao);
        //System.out.println(eventoDeTransacao);
        //System.out.println("Id = " + eventoDeTransacao.getId() + " Valor = " + eventoDeTransacao.getValor());
    }
}