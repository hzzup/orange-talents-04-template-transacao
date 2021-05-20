package br.com.hzzp.desafiotransacao.transacao.cartao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface CartaoRepository extends CrudRepository<Cartao,Long>{

	public Optional<Cartao> findByCartaoNro(String id);

}
