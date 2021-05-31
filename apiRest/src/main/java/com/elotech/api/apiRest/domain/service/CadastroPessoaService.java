package com.elotech.api.apiRest.domain.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elotech.api.apiRest.domain.exception.NegocioException;
import com.elotech.api.apiRest.domain.model.Pessoa;
import com.elotech.api.apiRest.domain.repository.PessoaRepository;

@Service
public class CadastroPessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Pessoa salvar(Pessoa pessoa) {
		
		
		Date dataAtual = new Date();
		if (pessoa.getDataNascimento().after(dataAtual)) {
			new NegocioException("A data de nascimento não pode ser futura");
		}
		
		return pessoaRepository.save(pessoa);
		
	}
	
	public void excluir(Long pessoaId) {
		
		pessoaRepository.deleteById(pessoaId);

	}
}
