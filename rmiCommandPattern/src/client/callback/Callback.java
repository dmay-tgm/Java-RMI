package client.callback;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author danie
 *
 */
public interface Callback<T> extends Remote {
	public void receive(T arg0) throws RemoteException;
}
