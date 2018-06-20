package br.fatec.projetofinal.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.fatec.projetofinal.dao.ParticipanteDAO;
import br.fatec.projetofinal.dao.ParticipanteDAOHibernate;
import br.fatec.projetofinal.model.Participante;

@ManagedBean(name = "participanteBean")
@RequestScoped

public class ParticipanteBean {

	private Participante participante = new Participante(); // propriedade do tipo Participante

	private DataModel<Participante> listaParticipantes;

	@SuppressWarnings("unchecked")
	public DataModel<Participante> getListaParticipantes() {
		if (listaParticipantes == null) {
			ParticipanteDAO dao = new ParticipanteDAOHibernate();
			listaParticipantes = new ListDataModel<Participante>(dao.listar());
		}
		return listaParticipantes;
	}

	public void setListaParticipantes(DataModel<Participante> listaParticipantes) {
		this.listaParticipantes = listaParticipantes;
	}

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

		// porta de entrada do formulário
	// preparar cadastro novo participante
	public String novo() {
	this.participante = new Participante();
	/**	this.participante.setInserir(true);
		this.participante.setAlterar(true);
		this.participante.setExcluir(true);
		this.participante.setVisualizar(true);**/
		
		
		return "publico/participante"; // tenta exibir participante.xhtml
	}

	public String listagem() {
		this.participante = new Participante();
		return "publico/listagemparticipante"; // tenta exibir participantealt.xhtml
	}

	public String salvar() {
		// se deu certo
		ParticipanteRN participanteRN = new ParticipanteRN();
		participanteRN.salvar(this.participante);
		return "participantesucesso"; // tenta abrir pagina /public/participantesucesso
									// nao colocou o publico mas
		// o contexto de salvar está em /publico
	}

	public Participante buscaPorLogin() {
		ParticipanteDAO dao = new ParticipanteDAOHibernate();
		Participante Permi = dao.carregar(participante.getId());
		return  Permi;
	//	return "publico/participantealt"; // tenta exibir participantealt.xhtml
	
	}


}
