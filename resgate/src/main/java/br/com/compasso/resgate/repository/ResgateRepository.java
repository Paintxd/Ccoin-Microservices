package br.com.compasso.resgate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.resgate.model.Resgate;

public interface ResgateRepository extends JpaRepository<Resgate, Long> {

}
