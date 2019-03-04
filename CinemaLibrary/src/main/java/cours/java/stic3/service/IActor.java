package cours.java.stic3.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IActor extends Remote {
	
       List<Object> ListObject(String model) throws RemoteException;
       
	//Object getObject(Class Myclass, Serializable id) throws RemoteException;
       
       Boolean InsertOrUpdateObject(Object obj) throws RemoteException;
       
}
