package br.com.fiap.cp1.repository;

import br.com.fiap.cp1.entity.ProdutosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ProductRepository extends JpaRepository<ProdutosEntity, Long> {

}
