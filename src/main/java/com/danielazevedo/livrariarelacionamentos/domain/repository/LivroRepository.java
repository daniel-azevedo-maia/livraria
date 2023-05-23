package com.danielazevedo.livrariarelacionamentos.domain.repository;

import com.danielazevedo.livrariarelacionamentos.domain.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface LivroRepository extends JpaRepository<Livro, Long> {
	
	

}
