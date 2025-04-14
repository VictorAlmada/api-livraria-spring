package com.victor.livraria.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.victor.livraria.dto.LivroRequestDTO;
import com.victor.livraria.dto.LivroResponseDTO;
import com.victor.livraria.dto.PaginaRespostaDTO;
import com.victor.livraria.services.LivroService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/livros")
public class LivroController {
	
	@Autowired
	private LivroService livroService;
	
	// LISTAR TODOS
	@GetMapping
	public List<LivroResponseDTO> listarTodos() {
		return livroService.listarTodos();
	}
	
	// BUSCAR POR ID
	@GetMapping("/{id}")
	public LivroResponseDTO buscarPorId(@PathVariable Long id) {
		return livroService.buscarPorId(id);
	}
	
	// SALVAR
	@PostMapping
	public ResponseEntity<LivroResponseDTO> salvar(@RequestBody @Valid LivroRequestDTO dto) {
		LivroResponseDTO response = livroService.salvar(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);		
	}
	
	// ATUALIZAR
	@PutMapping("/{id}")
	public ResponseEntity<LivroResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid LivroRequestDTO dto) {
		LivroResponseDTO response = livroService.atualizar(id, dto);
		return ResponseEntity.ok(response);
	}
	
	// DELETAR
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id) {
		livroService.deletar(id);
	}
	
	// FILTRAR
	@GetMapping("/filtrar")
	public ResponseEntity<PaginaRespostaDTO<LivroResponseDTO>> filtrar(
			@RequestParam(required = false) String titulo,
			@RequestParam(required = false) String nomeAutor,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim,
			@RequestParam(defaultValue = "0")int page,
			@RequestParam(defaultValue = "5")int size,
			@RequestParam(defaultValue = "titulo")String sort,
			@RequestParam(defaultValue = "asc")String direction
			) {
		
		Pageable pageable = PageRequest.of(page, size,
				direction.equalsIgnoreCase("asc") ? Sort.by(sort).ascending() : Sort.by(sort).descending());
		
		
		PaginaRespostaDTO<LivroResponseDTO> resultado = livroService.filtrarCombinadoComPaginacao(titulo, nomeAutor, dataInicio, dataFim, pageable);
		
		return ResponseEntity.ok(resultado);
	}
	
	
}
