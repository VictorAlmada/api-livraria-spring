package com.victor.livraria.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AutorRequestDTO {
	
	@NotBlank(message = "O nome é obrigatório.")
	@Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
	private String nome;
	
	@NotBlank
	@Email(message = "E-mail inválido.")
	private String email;
	
	@Past(message = "A data de nascimento deve estar no passado.")
	private LocalDate dataNascimento;

	
}
