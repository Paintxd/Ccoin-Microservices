package br.com.compasso.itens.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.itens.model.TipoItem;

public interface TipoItemRepository extends JpaRepository<TipoItem, Long> {

}
