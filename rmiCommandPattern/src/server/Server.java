package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import remoteService.DoSomethingService;

/**
 * Server that provides a distributed object for executing commands.f
 * 
 * @author Daniel May, Michael Borko
 * @version 20160317.1
 *
 */
public class Server {

	/**
	 * main function for starting the server application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// generate SecurityManager if it doesn't exist
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			// create local object of the ServerService
			ServerService uRemoteObject = new ServerService();
			// provide stub for remote access
			DoSomethingService stub = (DoSomethingService) UnicastRemoteObject.exportObject(uRemoteObject, 0);
			// create registry
			Registry registry = LocateRegistry.createRegistry(1234);
			// bind the name Service to the stubs reference
			registry.rebind("Service", stub);
			System.out.println("Service bound! Press Enter to terminate ...");

			// waiting for ENTER keystroke
			while (System.in.read() != '\n')
				;
			// unexport object for a safe exit
			UnicastRemoteObject.unexportObject(uRemoteObject, true);

			System.out.println("Service unbound, System goes down ...");
		} catch (RemoteException re) {
			System.err.println("Service already bound?" + " Check your RMI-Registry settings!");
			re.printStackTrace();
			System.exit(1);
		} catch (Exception e) {
			System.err.println("Service exception:");
			e.printStackTrace();
			System.exit(1);
		}
	}
}