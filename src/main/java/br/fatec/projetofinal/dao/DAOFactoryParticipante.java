package br.fatec.projetofinal.dao;

import br.fatec.projetofinal.util.HibernateUtil;

public class DAOFactoryParticipante {

	
	public static ParticipanteDAO criaParticipanteDAO() {
		ParticipanteDAOHibernate participanteDAO = new ParticipanteDAOHibernate();
			      participanteDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
			return participanteDAO;
		}


}
