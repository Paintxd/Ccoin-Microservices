package br.com.compasso.resgate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.resgate.model.StatusResgate;

public interface StatusResgateRepository extends JpaRepository<StatusResgate, Long> {

}
