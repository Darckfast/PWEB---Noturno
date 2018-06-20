package br.fatec.projetofinal.controller;

import java.util.List;

import br.fatec.projetofinal.dao.DAOFactoryEvento;
import br.fatec.projetofinal.dao.EventoDAO;
import br.fatec.projetofinal.model.Evento;

// unica camada que podera ser comunicar com a camada de dados
// entretanto nao existe qqq referencia ao hibernate mostrando o alto 
// nivel de dedesacoplamento entre camada de acesso a dados e regra de negocio
// por enquanto so evento so evento tera  regra de negocio
public class EventoRN {
	// padrão formal criar essa propriedade e a instanciacao usando DAOFactory
	private EventoDAO eventoDAO;

	public EventoRN() {
		this.eventoDAO = DAOFactoryEvento.criaEventoDAO();
	}

	// carrega uma instancia
	public Evento carregar(Integer codigo) {
		return this.eventoDAO.carregar(codigo);
	}

	  // faz repasse metodo na classe DAO
	public Evento buscarPorLogin(String nome) {
		return this.eventoDAO.buscarPorNome(nome);
	}

	// se nao existe salva, caso contrario atualiza
	public void salvar(Evento evento) {
		Integer codigo = evento.getCodigo();
		if (codigo == null || codigo == 0) {
			this.eventoDAO.salvar(evento);
		} else {
			this.eventoDAO.atualizar(evento);
		}
	}

	public void excluir(Evento evento) {
		this.eventoDAO.excluir(evento);
	}	

	@SuppressWarnings("unchecked")
	public List<Evento> listar() {
		return this.eventoDAO.listar();
	}
}
