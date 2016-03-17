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

public class Client {

	public static void main(String[] args) {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			Registry registry = LocateRegistry.getRegistry(1234);

			DoSomethingService uRemoteObject = (DoSomethingService) registry.lookup("Service");
			System.out.println("Service found");

			PrintCallback<BigDecimal> cb = new PrintCallback<BigDecimal>();
			Callback<BigDecimal> cbstub = (Callback<BigDecimal>) UnicastRemoteObject.exportObject(cb, 0);

			Calculation calc = new PICalc(100);
			Command pic = new CalculationCommand(calc, cbstub);
			uRemoteObject.doSomething(pic);

			System.out.println("Press [ENTER] to terminate!");

			while (System.in.read() != '\n')
				;
			UnicastRemoteObject.unexportObject(cb, true);

			System.out.println("Callback unbound, System goes down ...");

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
