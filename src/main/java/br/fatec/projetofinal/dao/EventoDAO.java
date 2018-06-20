package br.fatec.projetofinal.dao;

import java.util.List;

import br.fatec.projetofinal.model.Evento;

public interface EventoDAO {
	
	public void salvar(Evento evento);
	public void atualizar(Evento evento);	
	public void excluir(Evento evento);
	public Evento carregarnome(String string);
	public Evento carregar(Integer codigo);
	public Evento buscarPorNome(String nome);
	public List listar();

}
