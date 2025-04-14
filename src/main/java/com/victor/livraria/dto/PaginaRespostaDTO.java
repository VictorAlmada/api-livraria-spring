package com.victor.livraria.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PaginaRespostaDTO<T> {
	
	private List<T> conteudo;
	private int paginaAtual;
	private int totalPaginas;
	private long totalElementos;
	private int tamanhoPagina;
	private boolean temProxima;
	private boolean temAnterior;
	
	public boolean isTemProxima() {
		return temProxima;
	}
	
	public boolean isTemAnterior() {
		return temAnterior;
	}
	
}
