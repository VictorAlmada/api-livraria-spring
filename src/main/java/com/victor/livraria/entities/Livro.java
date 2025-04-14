package com.victor.livraria.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "livros")
public class Livro {
	
	// attributes
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String titulo;
	
	private String isbn;
	
	private LocalDate dataPublicacao;
	
	@ManyToOne
	@JoinColumn(name = "autor_id")
	private Autor autor;
	
	// constructors
	public Livro() {
		
	}
	
	public Livro(String titulo, String isbn, LocalDate dataPublicacao, Autor autor) {
		this.titulo = titulo;
		this.isbn = isbn;
		this.dataPublicacao = dataPublicacao;
		this.autor = autor;
	}
	
	// getters and setters
	
	public Long getId() {
		return id;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public LocalDate getDataPublicacao() {
		return dataPublicacao;
	}
	
	public void setDataPublicacao(LocalDate dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}
	
	public Autor getAutor() {
		return autor;
	}
	
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
	// toString
	@Override
	public String toString() {
		return "{id=" + getId() + ", titulo=" + getTitulo() + ", isbn=" + getIsbn() + ", data-publicacao=" + getDataPublicacao() + "}";
	}
	
}
