package com.victor.livraria.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LivroRequestDTO {
	
	@NotBlank(message = "O titulo é obrigatório.")
	@Size(min = 2, max = 200, message = "O titulo deve ter entre 2 e 200 caracteres")
	private String titulo;
	
	@NotBlank(message = "O ISBN é obrigatório.")
	private String isbn;
	
	@NotNull(message = "A data de publicação é obrigatória.")
	@PastOrPresent(message = "A data de publicação deve estar no passado ou presente.")
	private LocalDate dataPublicacao;
	
	@NotNull(message = "O ID do autor é obrigatório.")
	private Long autorId;
	
	
	
}
