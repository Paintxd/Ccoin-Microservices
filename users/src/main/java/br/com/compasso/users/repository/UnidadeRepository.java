package br.com.compasso.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.users.model.Unidade;

public interface UnidadeRepository extends JpaRepository<Unidade, Long> {

	Unidade findByEndereco(String endereco);
	
}