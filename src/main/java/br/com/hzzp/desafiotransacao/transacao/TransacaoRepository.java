package br.com.hzzp.desafiotransacao.transacao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TransacaoRepository extends PagingAndSortingRepository<Transacao,Long>{

	boolean existsByCartaoId(Long cartaoId);
	Page<Transacao> findByCartaoId(Long cartaoId, Pageable pageable);
}
