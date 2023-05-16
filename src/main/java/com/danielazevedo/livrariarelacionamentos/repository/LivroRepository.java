package com.danielazevedo.livrariarelacionamentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.danielazevedo.livrariarelacionamentos.model.Livro;

@Repository
@Transactional
public interface LivroRepository extends JpaRepository<Livro, Long> {
	
	

}
