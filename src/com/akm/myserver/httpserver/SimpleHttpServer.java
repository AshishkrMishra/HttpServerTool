package com.akm.myserver.httpserver;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.akm.myserver.handler.Handlers;
import com.akm.myserver.server.Main;
import com.sun.net.httpserver.HttpServer;

public class SimpleHttpServer {
	private int port=5555;
	private HttpServer server;

	public void Start(int port) {
		try {
			this.port = port;
			server = HttpServer.create(new InetSocketAddress(port), 0);
			System.out.println("server started at " + port);
			server.createContext("/", new Handlers.RootHandler());
			server.createContext("/echoHeader", new Handlers.EchoHeaderHandler());
			server.createContext("/echoGet", new Handlers.EchoGetHandler());
			server.createContext("/echoPost", new Handlers.EchoPostHandler());
			server.setExecutor(null);
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("URL That Can be Accessible Are:=\n")
			.append("/\n")
			.append("/echoHeader\n")
			.append("/echoGet\n")
			.append("/echoPost\n");
			server.start();
			stringBuilder.append("Server Started");
			Main.writeLog(stringBuilder.toString());
		} catch (IOException e) {
			//e.printStackTrace();
			Main.getLogArea().setText(e.getMessage());
		}
	}

	public void Stop() {
		server.stop(0);
		//System.out.println("server stopped");
		Main.writeLog("server stopped");
		
	}
}
