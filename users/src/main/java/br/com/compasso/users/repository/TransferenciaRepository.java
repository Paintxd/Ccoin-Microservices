package br.com.compasso.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.users.model.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

}
