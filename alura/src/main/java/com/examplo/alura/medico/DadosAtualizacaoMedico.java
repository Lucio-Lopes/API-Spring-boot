package com.examplo.alura.medico;

import com.examplo.alura.endereco.DadosEndereco;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(
		@NotNull
		Long id, 
		String nome, 
		String telefone, 
		DadosEndereco endereco ) {

}
