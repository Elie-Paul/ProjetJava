package cours.java.stic3.CinemaServer;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import cours.java.stic3.service.ActorImpl;
import cours.java.stic3.service.IActor;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws RemoteException, AlreadyBoundException
    {
    	System.setSecurityManager(new SecurityManager());
		Registry registry = LocateRegistry.createRegistry(1004);
		
		IActor actor = new ActorImpl();
		registry.bind("ActorDistant", actor);
		System.out.println("Serveur lance sur le port 1004");
    	
        
    }
}
