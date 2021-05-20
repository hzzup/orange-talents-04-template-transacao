package br.com.hzzp.desafiotransacao.transacao.cartao;

public class CartaoResponse {

	private String id;
	private String email;
	
	public CartaoResponse(String id, String email) {
		this.id = id;
		this.email = email;
	}
	
	//necessario para o jackson deserializar
	@Deprecated
	public CartaoResponse() {}

	public String getId() {return id;}
	public String getEmail() {return email;}

	public Cartao toModel() {
		return new Cartao(this.id,this.email);
	}
}
