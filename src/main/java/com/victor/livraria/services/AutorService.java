package com.victor.livraria.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.victor.livraria.dto.AutorRequestDTO;
import com.victor.livraria.dto.AutorResponseDTO;
import com.victor.livraria.dto.PaginaRespostaDTO;
import com.victor.livraria.entities.Autor;
import com.victor.livraria.repositories.AutorRepository;

@Service
public class AutorService {
	
	@Autowired
	private AutorRepository autorRepository;
	
	// LISTAR TODOS
	public List<AutorResponseDTO> listarTodos() {
		return autorRepository.findAll().stream().map(a -> new AutorResponseDTO(a.getId(), a.getNome(), a.getEmail(), a.getDataNascimento())).toList();
	}
	
	// BUSCAR POR ID
	public AutorResponseDTO buscarPorId(Long id) {
		Autor autor = autorRepository.findById(id).orElseThrow(() -> new RuntimeException("Autor não encontrado."));
		return new AutorResponseDTO(autor.getId(), autor.getNome(), autor.getEmail(), autor.getDataNascimento());
	}
	
	// SALVAR
	public AutorResponseDTO salvar(AutorRequestDTO dto) {
		Autor autor = new Autor(dto.getNome(), dto.getEmail(), dto.getDataNascimento());
		autor = autorRepository.save(autor);
		return new AutorResponseDTO(autor.getId(), autor.getNome(), autor.getEmail(), autor.getDataNascimento());
	}
	
	// ATUALIZAR
	public AutorResponseDTO atualizar(Long id, AutorRequestDTO dto) {
		Autor autor = autorRepository.findById(id).orElseThrow(() -> new RuntimeException("Autor não encontrado."));
		autor.setNome(dto.getNome());
		autor.setEmail(dto.getEmail());
		autor.setDataNascimento(dto.getDataNascimento());
		
		autorRepository.save(autor);
		
		return new AutorResponseDTO(autor.getId(), autor.getNome(), autor.getEmail(), autor.getDataNascimento());
	}
	
	// DELETAR
	public void deletar(Long id) {
		autorRepository.deleteById(id);
	}
	
	// FILTRAR
	public PaginaRespostaDTO<AutorResponseDTO> filtrarComPaginacao(String nome, Pageable pageable) {
		Page<Autor> paginaAutores = autorRepository.findByNomeContainingIgnoreCase(nome == null ? "" : nome, pageable);
		
		List<AutorResponseDTO> conteudoDTO = paginaAutores
				.stream()
				.map(autor -> new AutorResponseDTO(autor.getId(), autor.getNome(), autor.getEmail(), autor.getDataNascimento())).toList();
		
		return new PaginaRespostaDTO<>(conteudoDTO, paginaAutores.getNumber(),
				paginaAutores.getTotalPages(),
				paginaAutores.getTotalElements(),
				paginaAutores.getSize(),
				paginaAutores.hasNext(),
				paginaAutores.hasPrevious());
		
	}
	
	
	
	
	
	
	
	
}
