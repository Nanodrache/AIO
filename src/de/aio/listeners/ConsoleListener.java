package de.aio.listeners;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleListener
{
	public ConsoleListener()
	{
		new Thread(() ->
		{
			String line;
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			try
			{
				while ((line = br.readLine()) != null)
				{
					String[] args = line.split(" ");
					String cmd = args[0];
					
					if (cmd.equalsIgnoreCase(""))
					{
						
					}
					else
					{
						System.out.println("Befehl nicht bekannt.");
					}
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}).start();
	}
}
