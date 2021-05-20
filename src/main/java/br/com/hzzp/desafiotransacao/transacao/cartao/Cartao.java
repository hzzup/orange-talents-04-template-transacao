package br.com.hzzp.desafiotransacao.transacao.cartao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Cartao {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String cartaoNro;
	private String email;
	
	@Deprecated
	public Cartao() {}
	
	public Cartao(String cartaoNro, String email) {
		this.cartaoNro = cartaoNro;
		this.email = email;
	}
}
