package server.commands;

import java.io.Serializable;
import java.math.BigDecimal;
import java.rmi.RemoteException;

import calculation.Calculation;
import client.callback.Callback;

public class CalculationCommand implements Command, Serializable {

	private static final long serialVersionUID = 3202369269194172790L;
	private Calculation calc;
	private Callback<BigDecimal> cb;

	public CalculationCommand(Calculation calc, Callback<BigDecimal> cb) {
		this.calc = calc;
		this.cb = cb;
	}

	@Override
	public void execute() {
		System.out.println("CalculationCommand called!");
		calc.calculate();
		try {
			cb.receive(calc.getResult());
		} catch (RemoteException e) {
			System.err.println("RemoteException during Callback occurred: " + e.getMessage());
		}
	}
}