package com.danielazevedo.livrariarelacionamentos.repository;

import com.danielazevedo.livrariarelacionamentos.model.Autor;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface AutorRepository extends JpaRepository<Autor, Long> {



}
