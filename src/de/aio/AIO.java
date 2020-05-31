package de.aio;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.security.auth.login.LoginException;

import de.aio.listeners.ConsoleListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;

public class AIO
{
	private Properties options;
	public static AIO INSTANCE;
	public JDA jda;
	
	public static void main(String[] args)
	{
		try
		{
			new AIO();
		}
		catch (LoginException e)
		{
			e.printStackTrace();
		}
	}
	
	public AIO() throws LoginException
	{
		INSTANCE = this;
		options = new Properties();
		
		try
		{
			File optionsFile = new File("options.cfg");
			
			if (optionsFile.exists())
			{
				options.load(new FileReader(optionsFile));
			}
			else
			{
				optionsFile.createNewFile();
				options.setProperty(Options.Token.name(), "");
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		JDABuilder builder = JDABuilder.createDefault(options.getProperty(Options.Token.name()));
		
		jda = builder.build();
		
		builder.setStatus(OnlineStatus.ONLINE);
		
		new ConsoleListener();
	}
}
