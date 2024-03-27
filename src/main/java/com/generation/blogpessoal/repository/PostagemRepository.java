package com.generation.blogpessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.blogpessoal.model.Postagem;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {
	
	public List <Postagem> findAllByTituloContainingIgnoreCase(@Param("titulo")String titulo);

}

//Criando uma lista pra armazenar todos as postagens;
//Find : SELECT
//by : WHERE
//Titulo : Atributo da classe postagem
//Containing : Like
//IgnoreCase: Ignorando letras maiúsculas ou minúsculas
//@Param("titulo") : Define a variável String titulo como um parâmetro da consulta. Esta anotação é obrigatório em consultas do tipo Like.