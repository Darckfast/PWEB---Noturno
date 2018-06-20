package br.fatec.projetofinal.dao;

import br.fatec.projetofinal.util.HibernateUtil;

public class DAOFactoryEvento {

	
	public static EventoDAO criaEventoDAO() {
	EventoDAOHibernate eventoDAO = new EventoDAOHibernate();
		      eventoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return eventoDAO;
	}

}
