package br.com.compasso.resgate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.resgate.model.ResgateItem;

public interface ResgateItemRepository extends JpaRepository<ResgateItem, Long> {

}
