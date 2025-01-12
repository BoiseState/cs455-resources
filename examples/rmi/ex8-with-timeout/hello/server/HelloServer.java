package hello.server;

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;

public class HelloServer extends UnicastRemoteObject implements Hello {
	private static final long serialVersionUID = 8510538827054962873L;
	private String name;
	private static int registryPort = 1099;

	public HelloServer(String s) throws RemoteException {
		super();
		name = s;
	}


	public String sayHello() throws RemoteException
	{
		try {
			Thread.sleep(10000); // sleep for 10 seconds to simulate a slow server
		} catch (InterruptedException e) {
			System.err.println(e);
		}
		return "Hello World!";
	}


	public static void main(String args[])
	{
		if (args.length > 0) {
			registryPort = Integer.parseInt(args[0]);
		}

		try {
			HelloServer obj = new HelloServer("//HelloServer");

			Registry registry = LocateRegistry.getRegistry(registryPort);
			registry.rebind("HelloServer", obj);
			System.out.println("HelloServer bound in registry");

		} catch (RemoteException e) {
			System.out.println("HelloServer err: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
