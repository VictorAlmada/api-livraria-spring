package com.victor.livraria.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class AutorResponseDTO {
	
	Long id;
	String nome;
	String email;
	LocalDate dataNascimento;
	
}
