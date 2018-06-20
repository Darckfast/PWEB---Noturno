package br.fatec.projetofinal.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import br.fatec.projetofinal.model.Evento;
import br.fatec.projetofinal.util.HibernateUtil;

public class EventoDAOHibernate implements EventoDAO {

	// obrigatório para classe hibernate
	// por meio da Session as operacoes do Hibernate chegam ao Banco de Dados

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void salvar(Evento evento) {
		try {
			this.session.getSessionFactory().openSession();
			this.session.beginTransaction();
			this.session.save(evento);
			this.session.getTransaction().commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível inserir o contato. Erro: " + e.getMessage());
		}
	}

	public void atualizar(Evento evento) {
		try {
			this.session.getSessionFactory().openSession();
			session.beginTransaction();
			this.session.update(evento);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível alterar o contato. Erro: " + e.getMessage());
		}
	}

	public void excluir(Evento evento) {
		try {
			this.session.getSessionFactory().openSession();
			session.beginTransaction();
			this.session.delete(evento);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível excluir o contato. Erro: " + e.getMessage());
		}
	}

	// como se fosse o select, busca pela chave, se nao existir retorna null
		// se usar load ao inves de get será gerada uma excecao

		public Evento carregar(Integer codigo) {
			this.session.getSessionFactory().openSession();
			session.beginTransaction();
			return (Evento) this.session.get(Evento.class, codigo);
		}


		public List<Evento> listar() {
      //  this.session.getSessionFactory().openSession();
        Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "from Evento";
		@SuppressWarnings("unchecked")
		List<Evento> lista = session.createQuery(hql).list();
		if (lista != null) {
			return lista;
		}
		else {
			System.out.println("nao passou nada");
			return null;}
		}
		
	// usando hibernate query sql (parecida com sql)
	// busca outros campos

	public Evento buscarPorNome(String nome) {
		this.session.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "select u from Evento u where u.nome=:nome";
		Query consulta = this.session.createQuery(hql);
		consulta.setString("nome", nome); // parametro
		return (Evento) consulta.uniqueResult(); // como sabe-se que login é
													// chave naturam, entao
													// chama-se uniqueresult
													// senao usava
													// consulta.list()
	}

	public Evento carregarnome(String string) {
		this.session.getSessionFactory().openSession();
		session.beginTransaction();
		return (Evento) this.session.get(Evento.class, string);
	}

}
