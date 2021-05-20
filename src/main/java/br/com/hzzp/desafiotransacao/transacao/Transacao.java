package br.com.hzzp.desafiotransacao.transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.annotations.CreationTimestamp;

import br.com.hzzp.desafiotransacao.transacao.cartao.Cartao;
import br.com.hzzp.desafiotransacao.transacao.estabelecimento.Estabelecimento;

@Entity
public class Transacao {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotNull
    private String transacaoNro;
	@Positive
    private BigDecimal valor;
	@ManyToOne(cascade=CascadeType.PERSIST)
    private Estabelecimento estabelecimento;
	@ManyToOne(cascade=CascadeType.PERSIST)
    private Cartao cartao;
	@CreationTimestamp
    private LocalDateTime efetivadaEm;
    
    @Deprecated
    public Transacao() {}
    
	public Transacao(String id, BigDecimal valor, Estabelecimento estabelecimento,
			Cartao cartao, LocalDateTime efetivadaEm) {
		this.transacaoNro = id;
		this.valor = valor;
		this.estabelecimento = estabelecimento;
		this.cartao = cartao;
		this.efetivadaEm = efetivadaEm;
	}
}