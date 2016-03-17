package server.commands;

import java.io.Serializable;
import java.math.BigDecimal;
import java.rmi.RemoteException;

import calculation.Calculation;
import client.callback.Callback;

/**
 * Calaculation Command that executes an calculation and calls the callback.
 * 
 * @author Daniel May, Michael Borko
 * @version 20160317.1
 */
public class CalculationCommand implements Command, Serializable {

	/**
	 * serial id
	 */
	private static final long serialVersionUID = 3202369269194172790L;
	/**
	 * calculation to execute
	 */
	private Calculation calc;
	/**
	 * BigDecimal Callback to return the result
	 */
	private Callback<BigDecimal> cb;

	/**
	 * constructor with callback and calculation
	 * 
	 * @param calc
	 *            calculation to execute
	 * @param cb
	 *            BigDecimal Callback
	 */
	public CalculationCommand(Calculation calc, Callback<BigDecimal> cb) {
		this.calc = calc;
		this.cb = cb;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see server.commands.Command#execute()
	 */
	@Override
	public void execute() {
		System.out.println("CalculationCommand called!");
		// calculate pi
		calc.calculate();
		try {
			// return it via callback
			cb.receive(calc.getResult());
		} catch (RemoteException e) {
			System.err.println("RemoteException during Callback occurred: " + e.getMessage());
		}
	}
}