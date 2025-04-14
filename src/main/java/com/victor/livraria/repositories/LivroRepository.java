package com.victor.livraria.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.victor.livraria.entities.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
	
	List<Livro> findByTituloContainingIgnoreCase(String titulo);
	
	List<Livro> findByAutorNomeContainingIgnoreCase(String nomeAutor);
	
	List<Livro> findByDataPublicacaoBetween(LocalDate dataInicio, LocalDate dataFim);
	
	
	
	@Query("""
			SELECT l FROM Livro l
			WHERE (:titulo IS NULL OR LOWER(l.titulo) LIKE LOWER(CONCAT('%', :titulo, '%')))
			AND (:nomeAutor IS NULL OR LOWER(l.autor.nome) LIKE LOWER(CONCAT('%', :nomeAutor, '%')))
			AND (:dataInicio IS NULL OR l.dataPublicacao >= :dataInicio)
			AND (:dataFim IS NULL OR l.dataPublicacao <= :dataFim)
			""")
	Page<Livro> filtrarCombinadoComPagicao(
			@Param("titulo") String titulo,
			@Param("nomeAutor") String nomeAutor,
			@Param("dataInicio") LocalDate dataInicio,
			@Param("dataFim") LocalDate dataFim,
			Pageable pageable
			);
}
