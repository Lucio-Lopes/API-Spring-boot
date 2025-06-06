package com.examplo.alura.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examplo.alura.medico.DadosAtualizacaoMedico;
import com.examplo.alura.medico.DadosCadastroMedico;
import com.examplo.alura.medico.DadosListagemMedico;
import com.examplo.alura.medico.Medico;
import com.examplo.alura.medico.MedicoRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("medicos")
@Transactional
public class MedicoController {
	
	@Autowired
	private MedicoRepository repository;
	
	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
		
		repository.save(new Medico(dados));
		
	}
	
	@GetMapping
	public Page<DadosListagemMedico> listar(@PageableDefault(size = 10,sort = {"nome"}) Pageable paginacao){
		
		return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
		
	}
	
	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
		var medico = repository.getReferenceById(dados.id());
		
		medico.atualizarInformacoes(dados);
		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public void excluir(@PathVariable Long id) {
		
		var medico = repository.getReferenceById(id);
		medico.excluir();
		
	}
}
