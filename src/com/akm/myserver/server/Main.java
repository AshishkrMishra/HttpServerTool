package com.akm.myserver.server;

import javax.swing.JTextArea;

import com.akm.myserver.gui.ServerGUIMaker;
import com.akm.myserver.httpserver.SimpleHttpServer;
import com.akm.myserver.httpsserver.SimpleHttpsServer;

public class Main {
	public static int port = 9000;
	private static SimpleHttpsServer simpleHttpsServer=new SimpleHttpsServer();
	private static SimpleHttpServer simpleHttpServer=new SimpleHttpServer();
	public static String currentPath=System.getProperty("user.dir");
	public static StringBuilder log=new StringBuilder();
	private static JTextArea logArea;
	public static boolean isHttps=false;
	public static ServerGUIMaker serverGUIMaker;
	
	public static ServerGUIMaker getServerGUIMaker()
	{
		return serverGUIMaker;
	}
	public static void setServerGUIMaker(ServerGUIMaker serverGUIMaker)
	{
		Main.serverGUIMaker=serverGUIMaker;
	}
	
	
	public static void serverStarter(JTextArea logArea)
	{
//		// start http server
		Main.setLogArea(logArea);
		if(isHttps)
		{
			simpleHttpsServer.Start(port);
			
		}else
		{
			simpleHttpServer.Start(port);
		}
		Main.writeLog("Server Started at IP:PORT="+"127.0.0.1:"+port);
	}
	
	public static void serviceStopper(JTextArea logArea)
	{
		Main.setLogArea(logArea);
		
		if(isHttps)
		{
			simpleHttpsServer.Stop();
			
		}else
		{
			simpleHttpServer.Stop();
		}
		Main.writeLog("Server Stopped");
		
	}
	public static void writeLog(String str)
	{
		append(str);
		logArea.setText(log.toString());
	}
	public static void append(String str)
	{
		log.append("\n");
		log.append(str);
		
	}
	public static  void setLogArea(JTextArea logArea) {
			Main.logArea = logArea;
	}

	public  static JTextArea getLogArea() {
		return logArea;
	}
}
