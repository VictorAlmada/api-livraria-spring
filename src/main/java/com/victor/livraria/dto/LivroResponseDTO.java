package com.victor.livraria.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LivroResponseDTO {
	
	private Long id;
	private String titulo;
	private String isbn;
	private LocalDate dataPublicacao;
	private String nomeAutor;

}
