package client.callback;

import java.rmi.RemoteException;

/**
 * Implementation of the generic Callback Interface in order to print out the
 * received data.
 * 
 * @author Daniel May
 * @version 20160317.1
 *
 */
public class PrintCallback<T> implements Callback<T> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see client.callback.Callback#receive(java.lang.Object)
	 */
	@Override
	public void receive(T arg0) throws RemoteException {
		System.out.println(arg0);
	}
}