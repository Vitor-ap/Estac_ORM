package persistence;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import model.Estacionamento;

public class EstacionamentoPersistence implements IEstacionamentoPersistence<Estacionamento>{

	private SessionFactory sf;
	
	public EstacionamentoPersistence(SessionFactory sf) {
		this.sf = sf;
	}
		
	
	@Override
	public void insert(Estacionamento estacionamento) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(estacionamento);
		transaction.commit();
	}

	@Override
	public void delete(Estacionamento estacionamento) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.remove(estacionamento);
		transaction.commit();
	}
	

	
	@Override
	public void update(Estacionamento estacionamento) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(estacionamento);
		transaction.commit();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Estacionamento> selectAll(Estacionamento estac) {
		
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT os, placa, modelo, cor, data, hr_entrada, min_entrada ");
			sql.append("FROM estacionamento");
			sql.append(" WHERE placa LIKE '" + estac.getPlaca() + "%'");
			sql.append(" AND modelo LIKE '" + estac.getModelo() + "%'");
			if (estac.getData() == null) {
				sql.append("");
			} else {
			sql.append(" AND data LIKE '" + estac.getData() + "%'");
			}
			sql.append(" AND os LIKE '" + estac.getOs() + "%'");
			EntityManager entityManager = sf.createEntityManager();
			Query query = entityManager.createNativeQuery(sql.toString());
			List<Object[]> estacionamentoResultSet = query.getResultList();
			List<Estacionamento> estacionamento = new ArrayList<Estacionamento>();
			for (Object[] o : estacionamentoResultSet) {
				Estacionamento es = new Estacionamento();
				es.setOs(o[0].toString());
				es.setPlaca(o[1].toString());
				es.setModelo(o[2].toString());
				es.setCor(o[3].toString());
				es.setData(LocalDate.parse(o[4].toString()));
				es.setHrEntrada(o[5].toString());
				es.setMinEntrada(o[6].toString());

				estacionamento.add(es);
		}
			return estacionamento;
	}

	@Override
	public Estacionamento selectOne(Estacionamento estacionamento) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		estacionamento = entityManager.find(Estacionamento.class, estacionamento.getOs());
		return estacionamento;
	}
}

	
	

