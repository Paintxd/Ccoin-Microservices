package br.com.compasso.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.users.model.NivelAcesso;

public interface NivelAcessoRepository extends JpaRepository<NivelAcesso, Long> {

	NivelAcesso findByDescricaoAcesso(String descricaoAcesso);

}
