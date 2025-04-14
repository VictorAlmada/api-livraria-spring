package com.victor.livraria.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.victor.livraria.entities.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long>{
	
	Page<Autor> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
	
	List<Autor> findByEmailContainingIgnoreCase(String email);
	
	List<Autor> findByDataNascimentoBetween(LocalDate dataInicio, LocalDate dataFim);
	
}
