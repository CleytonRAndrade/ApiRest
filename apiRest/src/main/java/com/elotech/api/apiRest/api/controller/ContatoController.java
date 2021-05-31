package com.elotech.api.apiRest.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.elotech.api.apiRest.domain.model.Contato;
import com.elotech.api.apiRest.domain.repository.ContatoRepository;
import com.elotech.api.apiRest.domain.service.CadastroContatoService;

@RestController
@RequestMapping("/contatos")
public class ContatoController {
	
	@Autowired
	private ContatoRepository contatoRepository;
	
	@Autowired
	private CadastroContatoService cadastroContato;
	
	@GetMapping
	public List<Contato> listar(){
		return contatoRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Contato adicionar(@Valid @RequestBody Contato contato) {
		return cadastroContato.salvar(contato);
	}
	
	@PutMapping("/{contatoId}")
	public ResponseEntity<Contato> atualizar(@Valid @PathVariable Long contatoId, @RequestBody Contato contato) {
		if (!contatoRepository.existsById(contatoId)) {
			return ResponseEntity.notFound().build();
		}
		contato.setId(contatoId);
		contato = cadastroContato.salvar(contato);
		return ResponseEntity.ok(contato);
	}
	
	@DeleteMapping("/{contatoId}")
	public ResponseEntity<Void> remover(@PathVariable Long contatoId) {
		if (!contatoRepository.existsById(contatoId)) {
			return ResponseEntity.notFound().build();
		}
		cadastroContato.excluir(contatoId);
		return ResponseEntity.noContent().build();
	}
}
