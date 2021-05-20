package br.com.hzzp.desafiotransacao.transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import br.com.hzzp.desafiotransacao.transacao.cartao.Cartao;
import br.com.hzzp.desafiotransacao.transacao.cartao.CartaoResponse;
import br.com.hzzp.desafiotransacao.transacao.estabelecimento.Estabelecimento;
import br.com.hzzp.desafiotransacao.transacao.estabelecimento.EstabelecimentoResponse;

public class TransacaoResponse {

    private String id;
    private BigDecimal valor;
    private EstabelecimentoResponse estabelecimento;
    private CartaoResponse cartao;
    private LocalDateTime efetivadaEm;
    
	public TransacaoResponse(String id, BigDecimal valor, EstabelecimentoResponse estabelecimento,
			CartaoResponse cartao, LocalDateTime efetivadaEm) {
		this.id = id;
		this.valor = valor;
		this.estabelecimento = estabelecimento;
		this.cartao = cartao;
		this.efetivadaEm = efetivadaEm;
	}
	
	//necessario para o jackson deserializar
	@Deprecated
	public TransacaoResponse() {}

	public String getId() {return id;}
	public BigDecimal getValor() {return valor;}
	public EstabelecimentoResponse getEstabelecimento() {return estabelecimento;}
	public CartaoResponse getCartao() {return cartao;}
	public LocalDateTime getEfetivadaEm() {return efetivadaEm;}

	public Transacao toModel(Optional<Cartao> cartaoExistente) {
    	Estabelecimento novoEstabelecimento = this.estabelecimento.toModel();
    	Cartao novoCartao = null;
    	if (cartaoExistente.isEmpty()) {
    		novoCartao = this.cartao.toModel();
    	} else {
    		novoCartao = cartaoExistente.get();
    	}
		return new Transacao(this.id,this.valor,novoEstabelecimento,novoCartao,this.efetivadaEm);
	}
    
}