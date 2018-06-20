package br.fatec.projetofinal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import br.fatec.projetofinal.dao.EventoDAO;
import br.fatec.projetofinal.dao.EventoDAOHibernate;
import br.fatec.projetofinal.model.Evento;

@ManagedBean(name = "eventoBean")
@RequestScoped

public class EventoBean {

	private List<SelectItem> eventoItem;
	
	private Evento evento = new Evento();

	private DataModel<Evento> listaEventos;

	@SuppressWarnings("unchecked")
	public DataModel<Evento> getListaEventos() {
		if (listaEventos == null) {
			EventoDAO dao = new EventoDAOHibernate();
			listaEventos = new ListDataModel<Evento>(dao.listar());
		}
		return listaEventos;
	}

	public void setListaEventos(DataModel<Evento> listaEventos) {
		this.listaEventos = listaEventos;
	}


	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}



	// porta de entrada do formul�rio
	// preparar cadastro novo evento
	public String novo() {
		this.evento = new Evento();
		return "publico/evento"; // tenta exibir evento.xhtml
	}

	public String listagem() {
		this.evento = new Evento();
		return "publico/listagemevento"; // tenta exibir eventoalt.xhtml
	}

	private String local;

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String salvar() {

		EventoRN eventoRN = new EventoRN();
		eventoRN.salvar(this.evento);
		return "eventosucesso";
	}

	public Evento buscarPorNome() {
		EventoDAO dao = new EventoDAOHibernate();
		Evento Usu = dao.carregarnome(evento.getNome());
		return  Usu;
	//	return "publico/eventoalt"; // tenta exibir eventoalt.xhtml
	
	}


	public List<SelectItem> getEventos() {
		// Cria objeto de modelo Faces
		eventoItem = new ArrayList<SelectItem>();
		// cria objeto DAO
		EventoRN pais = new EventoRN();
		List<Evento> evento2 = pais.listar();
		// Alimenta Modelo
		for (Evento c : evento2) {
			SelectItem selecao = new SelectItem(c.getCodigo(), c.getNome());
			eventoItem.add(selecao);
		}
		return eventoItem;
	}
}
