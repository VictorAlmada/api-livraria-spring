package com.victor.livraria.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.victor.livraria.dto.LivroRequestDTO;
import com.victor.livraria.dto.LivroResponseDTO;
import com.victor.livraria.dto.PaginaRespostaDTO;
import com.victor.livraria.entities.Livro;
import com.victor.livraria.repositories.AutorRepository;
import com.victor.livraria.repositories.LivroRepository;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository livroRepository;
	@Autowired
	private AutorRepository autorRepository;
	
	// LISTAR TODOS
	public List<LivroResponseDTO> listarTodos() {
		List<Livro> livros = livroRepository.findAll();
		return livros.stream()
				.map(l -> new LivroResponseDTO(l.getId(), l.getTitulo(), l.getIsbn(), l.getDataPublicacao(), l.getAutor().getNome()))
				.toList();
	}
	
	// BUSCAR POR ID
	public LivroResponseDTO buscarPorId(Long id) {
		Livro livro = livroRepository.findById(id).orElseThrow(() -> new RuntimeException("livro não encontrado."));
		return new LivroResponseDTO(livro.getId(), livro.getTitulo(), livro.getIsbn(), livro.getDataPublicacao(), livro.getAutor().getNome());
	}
	
	// SALVAR
	public LivroResponseDTO salvar(LivroRequestDTO dto) {
		var autor = autorRepository.findById(dto.getAutorId()).orElseThrow(() -> new RuntimeException("Autor não encontrado."));
		
		Livro livro = new Livro(dto.getTitulo(), dto.getIsbn(), dto.getDataPublicacao(), autor);
		livro = livroRepository.save(livro);
		
		return new LivroResponseDTO(livro.getId(), livro.getTitulo(), livro.getIsbn(), livro.getDataPublicacao(), autor.getNome());
	}
	
	// ATUALIZAR
	public LivroResponseDTO atualizar(Long id, LivroRequestDTO dto) {
		Livro livro = livroRepository.findById(id).orElseThrow(() -> new RuntimeException("Livro não encontrado."));
		var autor = autorRepository.findById(dto.getAutorId()).orElseThrow(() -> new RuntimeException("Autor não encontrado."));
		
		livro.setTitulo(dto.getTitulo());
		livro.setIsbn(dto.getIsbn());
		livro.setDataPublicacao(dto.getDataPublicacao());
		livro.setAutor(autor);
		
		livro = livroRepository.save(livro);
		
		return new LivroResponseDTO(livro.getId(), livro.getTitulo(), livro.getIsbn(), livro.getDataPublicacao(), autor.getNome());
		
		
	}
	
	// DELETAR
	public void deletar(Long id) {
		livroRepository.deleteById(id);
	}
	
	// FILTRAR COMBINADO COM PAGINAÇÃO
	public PaginaRespostaDTO<LivroResponseDTO> filtrarCombinadoComPaginacao(String titulo, String nomeAutor, LocalDate dataInicio, LocalDate dataFim, Pageable pageable) {
		Page<Livro> paginaLivros = livroRepository.filtrarCombinadoComPagicao(titulo, nomeAutor, dataInicio, dataFim, pageable);
		
		List<LivroResponseDTO> conteudoDTO = paginaLivros.getContent().stream()
				.map(livro -> new LivroResponseDTO(livro.getId(), livro.getTitulo(), livro.getIsbn(), livro.getDataPublicacao(), livro.getAutor().getNome())).toList();
		
		return new PaginaRespostaDTO<>(
				conteudoDTO,
				paginaLivros.getNumber(),
				paginaLivros.getTotalPages(),
				paginaLivros.getTotalElements(),
				paginaLivros.getSize(),
				paginaLivros.hasNext(),
				paginaLivros.hasPrevious()
				);
	}
	
	
}
