package br.com.compasso.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.users.model.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Long> {

	Cargo findByDescricaoCargo(String descricaoCargo);

}
