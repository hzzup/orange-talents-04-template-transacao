package br.com.hzzp.desafiotransacao.transacao.estabelecimento;

public class EstabelecimentoResponse {

	private String nome;
	private String cidade;
	private String endereco;
	
	public EstabelecimentoResponse(String nome, String cidade, String endereco) {
		this.nome = nome;
		this.cidade = cidade;
		this.endereco = endereco;
	}

	//necessario para o jackson deserializar
	@Deprecated
	public EstabelecimentoResponse() {}
	
	public String getNome() {return nome;}
	public String getCidade() {return cidade;}
	public String getEndereco() {return endereco;}

	public Estabelecimento toModel() {
		return new Estabelecimento(this.nome,this.cidade,this.endereco);
	}
}
