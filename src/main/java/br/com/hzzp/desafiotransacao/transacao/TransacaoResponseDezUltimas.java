package br.com.hzzp.desafiotransacao.transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransacaoResponseDezUltimas {

	private LocalDateTime efetivadaEm;
	private BigDecimal valor;
	
	public TransacaoResponseDezUltimas(LocalDateTime efetivadaEm, BigDecimal valor) {
		this.efetivadaEm = efetivadaEm;
		this.valor = valor;
	}
	
	public LocalDateTime getEfetivadaEm() {return efetivadaEm;}
	public BigDecimal getValor() {return valor;} 	
}
