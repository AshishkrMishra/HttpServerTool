package com.akm.myserver.gui.thread;

import com.akm.myserver.gui.ServerGUIMaker;
import com.akm.myserver.server.Main;

public class GuiStarterThread  extends Thread{
	
	public void run()
	{
		try
		{
			Main.setServerGUIMaker(new ServerGUIMaker());
		}catch(Exception e)
		{
			
		}
	}

}
