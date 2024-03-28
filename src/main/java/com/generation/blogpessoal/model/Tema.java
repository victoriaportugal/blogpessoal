package com.generation.blogpessoal.model;

	import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
	import jakarta.validation.constraints.NotNull;

	@Entity
	@Table(name = "tb_temas")
	public class Tema {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		@NotNull(message = "O Atributo Descrição é obrigatório")
		private String descricao;
		
		@OneToMany(fetch = FetchType.LAZY, mappedBy = "tema", cascade = CascadeType.REMOVE)
		/*fetch traz informaçoes do Banco de dados que podem ser lazy ou eager (é a forma de carregamento
		 * de dados.
		*Quando tem muita tabela, busca somente oque esta pedindo: Lazy
	    *Quando as informaçoes que preciso, tem que estar pré definidas: Eager
	    *O cascade é a forma como as coisas são excluída
	    *mappedBy: responsavel por criar a chave estrangeria na Postagem
	    *cascade: forma de atualizar as tabelas
	    *cascatetype.remove: indica 
		*/
		@JsonIgnoreProperties("tema")
		private List<Postagem> postagem;

		public Long getId() {
			return this.id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getDescricao() {
			return this.descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		public List<Postagem> getPostagem() {
			return postagem;
		}

		public void setPostagem(List<Postagem> postagem) {
			this.postagem = postagem;
		}
		

	}
	
