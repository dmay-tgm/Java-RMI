package client.callback;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * @author danie
 *
 */
public class PrintCallback<T> implements Callback<T> {

	@Override
	public void receive(T arg0) throws RemoteException {
		System.out.println(arg0);
	}

}
