package remoteService;

import java.rmi.Remote;
import java.rmi.RemoteException;

import server.commands.Command;

/**
 * Interface for providing an "execute" method that execute a command.
 * 
 * @author Michael Borko
 * @version 20160317.1
 *
 */
public interface DoSomethingService extends Remote {

	/**
	 * Execute the Command's execute method.
	 * 
	 * @param c
	 *            the provided Command
	 * @throws RemoteException
	 */
	public void doSomething(Command c) throws RemoteException;
}