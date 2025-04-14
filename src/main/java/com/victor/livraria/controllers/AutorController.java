package com.victor.livraria.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

import com.victor.livraria.dto.AutorRequestDTO;
import com.victor.livraria.dto.AutorResponseDTO;
import com.victor.livraria.dto.PaginaRespostaDTO;
import com.victor.livraria.services.AutorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/autores")
public class AutorController {
	
	@Autowired
	private AutorService autorService;
	
	@GetMapping
	public List<AutorResponseDTO> listarTodos() {
		return autorService.listarTodos();
	}
	
	
	// BUSCAR POR ID
	@GetMapping("/{id}")
	public AutorResponseDTO buscarPorId(@PathVariable Long id) {
		return autorService.buscarPorId(id);
	}
	
	// SALVAR
	@PostMapping
	public ResponseEntity<AutorResponseDTO> salvar(@RequestBody @Valid AutorRequestDTO dto) {
		AutorResponseDTO response = autorService.salvar(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	// ATUALIZAR
	@PutMapping("/{id}")
	public ResponseEntity<AutorResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AutorRequestDTO dto) {
		AutorResponseDTO response = autorService.atualizar(id, dto);
		return ResponseEntity.ok(response);
	}
	
	// DELETAR
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id) {
		autorService.deletar(id);
	}
	
	// FILTRAR
	@GetMapping("/filtrar")
	public ResponseEntity<PaginaRespostaDTO<AutorResponseDTO>> filtrar(
			@RequestParam(required = false) String nome,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "nome") String sort,
			@RequestParam(defaultValue = "asc") String direction
			) {
		
		Pageable pageable = PageRequest.of(page, size, direction.equalsIgnoreCase("asc") ? Sort.by(sort).ascending() : Sort.by(sort).descending());
		
		PaginaRespostaDTO<AutorResponseDTO> resultado = autorService.filtrarComPaginacao(nome, pageable);
		
		return ResponseEntity.ok(resultado);
	}
	
	
}
