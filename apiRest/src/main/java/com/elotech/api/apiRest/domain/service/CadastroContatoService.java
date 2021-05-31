package com.elotech.api.apiRest.domain.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elotech.api.apiRest.domain.exception.NegocioException;
import com.elotech.api.apiRest.domain.model.Contato;
import com.elotech.api.apiRest.domain.model.Pessoa;
import com.elotech.api.apiRest.domain.repository.ContatoRepository;
import com.elotech.api.apiRest.domain.repository.PessoaRepository;

@Service
public class CadastroContatoService {
	
	@Autowired
	private ContatoRepository contatoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Contato salvar(Contato contato) {
		
		
		Pessoa pessoa = pessoaRepository.findById(contato.getPessoa().getId())
				.orElseThrow(() -> new NegocioException("Pessoa não encontrado"));
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		 
		Pattern pattern = Pattern.compile(regex);
		
		Matcher matcher = pattern.matcher(contato.getEmail());
		if (!matcher.matches()) {
			new NegocioException("O e-mail informado não é valido.");
		}
		contato.setPessoa(pessoa);
		
		return contatoRepository.save(contato);
		
	}
	
	public void excluir(Long contatoId) {
		
		contatoRepository.deleteById(contatoId);

	}

}
