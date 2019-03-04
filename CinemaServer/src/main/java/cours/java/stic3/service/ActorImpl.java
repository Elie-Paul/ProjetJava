package cours.java.stic3.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;


public class ActorImpl extends UnicastRemoteObject implements IActor {

	public ActorImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean InsertOrUpdateObject(Object obj) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			Session session = Utilitaire.buildSessionFactory().openSession();
			session.beginTransaction();
			session.saveOrUpdate(obj);
			session.getTransaction().commit();
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Object> ListObject(String model) throws RemoteException {
		// TODO Auto-generated method stub
		Session session = Utilitaire.buildSessionFactory().openSession();
		List<Object> object = session.getNamedQuery(model+".findAll").list();
		return object;
	}

	

	

}
