package br.fatec.projetofinal.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.fatec.projetofinal.model.Participante;
import br.fatec.projetofinal.util.HibernateUtil;

public class ParticipanteDAOHibernate implements ParticipanteDAO {

	// obrigatório para classe hibernate
	// por meio da Session as operacoes do Hibernate chegam ao Banco de Dados

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void salvar(Participante participante) {
		try {
			this.session.getSessionFactory().openSession();
			this.session.beginTransaction();
			this.session.save(participante);
			this.session.getTransaction().commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível inserir a participante. Erro: " + e.getMessage());
		}
	}

	public void atualizar(Participante participante) {
		try {
			this.session.getSessionFactory().openSession();
			session.beginTransaction();
			this.session.update(participante);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível alterar a participante. Erro: " + e.getMessage());
		}
	}

	public void excluir(Participante participante) {
		try {
			this.session.getSessionFactory().openSession();
			session.beginTransaction();
			this.session.delete(participante);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível excluir a participante. Erro: " + e.getMessage());
		}
	}

	// como se fosse o select, busca pela chave, se nao existir retorna null
	// se usar load ao inves de get será gerada uma excecao

	public Participante carregar(Integer codigo) {
		    Participante p = new Participante();
			this.session.getSessionFactory().openSession();
			session.beginTransaction();
			Criteria c = session.createCriteria(Participante.class);
			c.add(Restrictions.eq("id", codigo));
	        p = (Participante)c.uniqueResult();
			return p;
		}

	public List<Participante> listar() {
		// this.session.getSessionFactory().openSession();
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "from Participante";
		@SuppressWarnings("unchecked")
		List<Participante> lista = session.createQuery(hql).list();
		if (lista != null) {
			return lista;
		} else {
			System.out.println("nao passou nada");
			return null;
		}
	}

	// usando hibernate query sql (parecida com sql)
	// busca outros campos

	public Participante buscarPorEventoCPF(Integer codigo, String cpf) {
		this.session.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "select u from Participante u where u.codigoevento=:codigo and u.cpf=:cpf";
		Query consulta = this.session.createQuery(hql);
		consulta.setInteger("codigo", codigo); // parametro
		consulta.setString("cpf", cpf); // parametro

		return (Participante) consulta.uniqueResult(); 
	}
	
}
