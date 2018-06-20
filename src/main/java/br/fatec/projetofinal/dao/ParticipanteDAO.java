package br.fatec.projetofinal.dao;

import java.util.List;

import br.fatec.projetofinal.model.Participante;

public interface ParticipanteDAO {
	
	public void salvar(Participante participante);
	public void atualizar(Participante participante);	
	public void excluir(Participante participante);
	public Participante carregar(Integer codigo);
	public Participante buscarPorEventoCPF(Integer codigo, String cpf);
	public List listar();

}
