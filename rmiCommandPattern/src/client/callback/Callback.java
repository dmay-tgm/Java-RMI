package client.callback;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Generic callback interface for receiving values
 * 
 * @author Daniel May
 * @version 20160317.1
 *
 */
public interface Callback<T> extends Remote {
	/**
	 * Receives a value.
	 * 
	 * @param arg0
	 *            the received value
	 * @throws RemoteException
	 */
	public void receive(T arg0) throws RemoteException;
}