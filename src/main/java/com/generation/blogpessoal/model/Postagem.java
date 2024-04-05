package com.generation.blogpessoal.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_postagens")
public class Postagem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//A Anotação @GeneratedValue indica que a Chave Primária será gerada pelo Banco de dados. 
	//O parâmetro strategy indica de que forma esta Chave Primária será gerada. 
	//A Estratégia GenerationType.IDENTITY indica que a Chave Primária será gerada pelo Banco
	//de dados através da opção auto-incremento (auto-increment) do SQL, que gera uma sequência 
	//numérica iniciando em 1
	private Long id;
	
	@NotBlank(message = "o atributo Titulo é obrigatorio")
	@Size(min = 5, max = 100, message = "Tamanho minimo: 5, maximo: 100")
	private String titulo;
	
	@NotBlank(message = "o atributo Texto é obrigatorio")
	@Size(min = 10, max = 1000, message = "Tamanho minimo: 10, maximo: 1000")
	private String texto;
	
	@UpdateTimestamp
	private LocalDateTime data;
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}
	
	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}	
	
}
