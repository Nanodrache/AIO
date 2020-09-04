package de.aio.manager;

import java.awt.Color;
import java.util.concurrent.ConcurrentHashMap;

import de.aio.commands.BanCommand;
import de.aio.commands.ChannelCommand;
import de.aio.commands.KickCommand;
import de.aio.commands.LanguageCommand;
import de.aio.commands.MultiTwitchCommand;
import de.aio.commands.NewsletterCommand;
import de.aio.commands.PermissionCommand;
import de.aio.commands.ReactionCommand;
import de.aio.commands.SayCommand;
import de.aio.commands.VerifyCommand;
import de.aio.commands.WelcomeCommand;
import de.aio.commands.types.Commands;
import de.aio.commands.types.TextCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class CommandManager
{
	public ConcurrentHashMap<String, TextCommand> commands;
	
	public CommandManager()
	{
		this.commands = new ConcurrentHashMap<>();

		this.commands.put(Commands.SAY.getCmd(), new SayCommand());
		this.commands.put(Commands.CHANNEL.getCmd(), new ChannelCommand());
		this.commands.put(Commands.LANGUAGE.getCmd(), new LanguageCommand());
		this.commands.put(Commands.KICK.getCmd(), new KickCommand());
		this.commands.put(Commands.BAN.getCmd(), new BanCommand());
		this.commands.put(Commands.PERMISSION.getCmd(), new PermissionCommand());
		this.commands.put(Commands.WELCOME.getCmd(), new WelcomeCommand());
		this.commands.put(Commands.VERIFY.getCmd(), new VerifyCommand());
		this.commands.put(Commands.REACTION.getCmd(), new ReactionCommand());
		this.commands.put(Commands.MULTITWITCH.getCmd(), new MultiTwitchCommand());
		this.commands.put(Commands.NEWSLETTER.getCmd(), new NewsletterCommand());
	}
	
	public boolean perform(String command, Member member, TextChannel channel, Message message)
	{
		TextCommand cmd;
		
		if ((cmd = this.commands.get(command.toLowerCase())) != null)
		{
			cmd.performCommand(member, channel, message);
			return true;
		}
		
		return false;
	}
	
	public void cmdErrorMsg(TextChannel channel, String color, String msg)
	{
		EmbedBuilder eb = new EmbedBuilder();
		eb.setColor(Color.decode(color));
		eb.setDescription(msg);
		channel.sendMessage(eb.build()).queue();
	}
}
