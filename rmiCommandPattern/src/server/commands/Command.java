package server.commands;

import java.io.Serializable;

/**
 * Command interface with execute method.
 * 
 * @author Michael Borko
 * @version 20160317.1
 *
 */
public interface Command extends Serializable {
	/**
	 * executes something
	 */
	public void execute();
}