package de.aio.listeners;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import de.aio.AIO;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;

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
					
					if (cmd.equalsIgnoreCase("/exit"))
					{
						JDA jda = AIO.INSTANCE.jda;
						
						jda.getShardManager().setStatus(OnlineStatus.OFFLINE);
						jda.shutdown();
						
						br.close();
					}
					else
					{
						System.out.println(AIO.languageManager.getString("unknownCommand"));
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
