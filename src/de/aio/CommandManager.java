package de.aio;

import java.awt.Color;
import java.util.concurrent.ConcurrentHashMap;

import de.aio.commands.SayCommand;
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

		this.commands.put("say", new SayCommand());
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
