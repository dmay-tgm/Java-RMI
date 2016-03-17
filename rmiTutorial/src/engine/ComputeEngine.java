/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package engine;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import compute.Compute;
import compute.Task;

/**
 * The server side class that generates an object that can be called from
 * clients in order to execute tasks.
 * 
 * @author Daniel May
 * @version 20160311.1
 */
public class ComputeEngine implements Compute {

	/**
	 * constructor
	 */
	public ComputeEngine() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see compute.Compute#executeTask(compute.Task)
	 */
	@Override
	public <T> T executeTask(Task<T> t) {
		return t.execute();
	}

	/**
	 * main fucntion to start the server side
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// generate SecurityManager if it doesn't exist
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			// local server object
			Compute engine = new ComputeEngine();
			// export the object, now it can be called from everyone that has a
			// reference to this object
			Compute stub = (Compute) UnicastRemoteObject.exportObject(engine, 0);
			// create registry with standard port 1099
			Registry registry = LocateRegistry.createRegistry(1099);
			// bind the stub to the name Compute
			registry.rebind("Compute", stub);
			// success message
			System.out.println("Service bound! Press Enter to terminate ...");
			// wait until enter is pressed then unexport the object
			while (System.in.read() != '\n')
				;
			UnicastRemoteObject.unexportObject(engine, true);
			System.out.println("Terminated.");
		} catch (Exception e) {
			System.err.println("ComputeEngine exception:");
			e.printStackTrace();
		}
	}
}