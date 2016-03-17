package client;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import calculation.Calculation;
import calculation.PICalc;
import client.callback.Callback;
import client.callback.PrintCallback;
import remoteService.DoSomethingService;
import server.commands.CalculationCommand;
import server.commands.Command;

/**
 * Client that invokes a remote method to calculate pi. This is done using the
 * command pattern. A callback is used to return the value.
 * 
 * @author Daniel May, Michael Borko
 * @version 20160317.1
 *
 */
public class Client {

	/**
	 * main function to start the client application
	 * 
	 * @param args
	 *            number of Pi digits
	 */
	public static void main(String[] args) {
		// get SecurityManager if it doesn't exist
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			// locate the registry
			Registry registry = LocateRegistry.getRegistry(1234);

			// lookup the reference of Service
			DoSomethingService uRemoteObject = (DoSomethingService) registry.lookup("Service");
			System.out.println("Service found");

			// create new local object for the callback
			PrintCallback<BigDecimal> cb = new PrintCallback<BigDecimal>();
			// provide the stub for the callback when returning the result
			Callback<BigDecimal> cbstub = (Callback<BigDecimal>) UnicastRemoteObject.exportObject(cb, 0);

			// generate new calculation
			Calculation calc = new PICalc(Integer.parseInt(args[0]));

			// generate a new command with the calculation object and the
			// callback stub
			Command pic = new CalculationCommand(calc, cbstub);
			// execute the command on the remote object
			uRemoteObject.doSomething(pic);

			System.out.println("Press [ENTER] to terminate!");

			// wait until enter is pressed
			while (System.in.read() != '\n')
				;
			// unexport the callback stub
			UnicastRemoteObject.unexportObject(cb, true);

			System.out.println("Callback unbound, System goes down ...");
		} catch (NumberFormatException nfe) {
			System.err.println("Please use one Integer argument!");
		} catch (RemoteException re) {
			System.err.println("Service not found?" + " Check your RMI-Registry!");
			re.printStackTrace();
			System.exit(1);
		} catch (Exception e) {
			System.err.println("Service exception:");
			e.printStackTrace();
			System.exit(1);
		}
	}
}