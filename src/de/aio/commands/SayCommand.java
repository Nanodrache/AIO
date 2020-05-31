package de.aio.commands;

import de.aio.AIO;
import de.aio.Options;
import de.aio.commands.types.Commands;
import de.aio.commands.types.TextCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class SayCommand implements TextCommand
{
	@Override
	public void performCommand(Member member, TextChannel channel, Message message)
	{
		String outputMessage = message.getContentDisplay().substring((AIO.INSTANCE.options.getProperty(Options.CommandPrefix.name()).length() + Commands.SAY.getCmd().length()));
		
		channel.sendMessage(outputMessage).queue();
	}
}
