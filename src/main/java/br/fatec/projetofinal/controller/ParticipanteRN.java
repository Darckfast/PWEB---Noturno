package br.fatec.projetofinal.controller;

import java.util.List;

import br.fatec.projetofinal.dao.DAOFactoryParticipante;
import br.fatec.projetofinal.dao.ParticipanteDAO;
import br.fatec.projetofinal.model.Participante;

// unica camada que podera ser comunicar com a camada de dados
// entretanto nao existe qqq referencia ao hibernate mostrando o alto 
// nivel de dedesacoplamento entre camada de acesso a dados e regra de negocio
// por enquanto so participante so participante tera  regra de negocio
public class ParticipanteRN {
	// padrão formal criar essa propriedade e a instanciacao usando DAOFactory
	private ParticipanteDAO participanteDAO;

	public ParticipanteRN() {
		this.participanteDAO = DAOFactoryParticipante.criaParticipanteDAO();
	}

	// carrega uma instancia
	public Participante carregar(Integer codigo, String opcao) {
		return this.participanteDAO.carregar(codigo);
	}

	  // faz repasse metodo na classe DAO
	public Participante buscarPorCodigoCPF(Integer codigo, String CPF) {
		return this.participanteDAO.buscarPorEventoCPF(codigo,CPF);
	}

	// se nao existe salva, caso contrario atualiza
	public void salvar(Participante participante) {
		Integer id = participante.getId();
		if (id == null || id == 0) {
			this.participanteDAO.salvar(participante);
		} else {
			this.participanteDAO.atualizar(participante);
		}
	}

	public void excluir(Participante participante) {
		this.participanteDAO.excluir(participante);
	}	

	@SuppressWarnings("unchecked")
	public List<Participante> listar() {
		return this.participanteDAO.listar();
	}
}
