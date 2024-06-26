package com.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;
import com.generation.blogpessoal.repository.TemaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {
	
	@Autowired 
	private PostagemRepository postagemRepository;
	
	@Autowired
	private TemaRepository temaRepository;
	
	@GetMapping
	public ResponseEntity<List<Postagem>> getAll(){
		return ResponseEntity.ok(postagemRepository.findAll());
	}
	@GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo) {
        return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
        // ATENÇÃO: Por que aqui a gente não fez um map ,else ? Todo método que tem como
        // retorno
        // uma lista,auomaticamente ela será gerada,então caso ele não encontre ainda
        // assim será
        // retornado uma lista vazia
        //Ao encapsular a lista de postagens dentro de ResponseEntity.ok(...),
        // você está indicando explicitamente que a operação
        // foi bem-sucedida (código 200 OK) e está incluindo o corpo da resposta, que é
        // a lista de postagens.
    }
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById(@PathVariable Long id){
		return postagemRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				//.map serve para verificar enquanto nao é nulo
				//.ok rerifica se a resposta é realizada
				//PatchVariable: defini que uma variável String é um parâmetro da consulta
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	@PostMapping
	public ResponseEntity<Postagem> post(@Valid @RequestBody Postagem postagem){
		if(temaRepository.existsById(postagem.getTema().getId()))
			// utilizamos para consultar se um Objeto específico persistido no Banco de dados existe,
			//retornando true se foi encontrado
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(postagemRepository.save(postagem));
		
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Tema não existe!", null);
	}
	@PutMapping 
	public ResponseEntity <Postagem> put(@Valid @RequestBody Postagem postagem){
		if(postagemRepository.existsById(postagem.getId())) {
			//Put: Para fazer uma Requisição de Alteração de recursos
			
			if(temaRepository.existsById(postagem.getTema().getId()))
		return ResponseEntity.status(HttpStatus.OK)
						.body(postagemRepository.save(postagem));
				//.body devolve informacoes que foram pegas com o @RequestBody para o Front, de manei-
				//ra atualizada para confirmaçao
				//.map só trabalha com optional (findById), pega a resposta e faz algo com ela.
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tema não exite!", null);
	}
	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
}
	@ResponseStatus(HttpStatus.NO_CONTENT)
    // Eu estou falando que caso a requisição aconteça corretamente,ele retorna um
    // HTTP No_content(204)
    // ao invés de retornar OK(200)
    @DeleteMapping("/{id}")
    // passando o id da publicação que eu quero apagar
    public void delete(@PathVariable Long id) {
        Optional<Postagem> postagem = postagemRepository.findById(id);
        // Caso não seja encontrado a postagem com o id informado,graças ao Optional ele
        // irá encapsular a resposta do Método findById(id)
        if (postagem.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        postagemRepository.deleteById(id);
        // Aqui ele está removendo,retorna uma NO_CONTENT 204

}
}
