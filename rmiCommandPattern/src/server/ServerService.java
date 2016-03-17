package server;

import java.rmi.RemoteException;

import remoteService.DoSomethingService;
import server.commands.Command;

/**
 * ServerService provides execution for a specified command.
 * 
 * @author Michael Borko
 * @version 20160317.1
 *
 */
public class ServerService implements DoSomethingService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * remoteService.DoSomethingService#doSomething(server.commands.Command)
	 */
	@Override
	public void doSomething(Command c) throws RemoteException {
		c.execute();
	}
}