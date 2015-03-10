package br.com.furb.pmattiollo.tcc.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.engine.spi.SessionFactoryImplementor;

import br.gov.frameworkdemoiselle.util.Beans;

public final class ConnectionPool {

	private ConnectionPool() {
		throw new UnsupportedOperationException();
	}
	
	@SuppressWarnings("deprecation")
	public static Connection getConnection() throws SQLException {
		Session session = (Session) Beans.getReference(EntityManager.class).getDelegate();
		SessionFactoryImplementor sfi = (SessionFactoryImplementor) session.getSessionFactory();
		return sfi.getConnectionProvider().getConnection();
	}

}