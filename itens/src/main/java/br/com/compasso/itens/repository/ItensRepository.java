package br.com.compasso.itens.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.itens.model.Item;

public interface ItensRepository extends JpaRepository<Item, Long> {

}
