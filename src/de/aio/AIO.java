package de.aio;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.security.auth.login.LoginException;

import de.aio.listeners.ConsoleListener;
import de.aio.listeners.ServerJoinListener;
import de.aio.listeners.TextChannelListener;
import de.aio.listeners.VoiceChannelListener;
import de.aio.manager.ChannelManager;
import de.aio.manager.CommandManager;
import de.aio.manager.LanguageManager;
import de.aio.manager.PartyManager;
import de.aio.manager.PermissionManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;

public class AIO
{
	public Properties options;
	public static AIO INSTANCE;
	public static LanguageManager languageManager;
	public static CommandManager commandManager;
	public static ChannelManager channelManager;
	public static PartyManager partyManager;
	public static PermissionManager permissionManager;
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
		languageManager = new LanguageManager();
		commandManager = new CommandManager();
		channelManager = new ChannelManager();
		partyManager = new PartyManager();
		permissionManager = new PermissionManager();

		try
		{
			File optionsFile = new File("/AIO", "options.cfg");
			
			if (optionsFile.exists())
			{
				options.load(new FileReader(optionsFile));
			}
			else
			{
				optionsFile.createNewFile();
				options.setProperty(Options.Token.name(), "");
				options.setProperty(Options.Language.name(), "en");
				options.setProperty(Options.WELCOME_MESSAGE.name(), "false");
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		JDABuilder builder = JDABuilder.createDefault(options.getProperty(Options.Token.name()));

		builder.addEventListeners(new TextChannelListener());
		builder.addEventListeners(new VoiceChannelListener());
		builder.addEventListeners(new ServerJoinListener());
		
		jda = builder.build();
		
		builder.setStatus(OnlineStatus.ONLINE);
		
		new ConsoleListener();
	}
}
