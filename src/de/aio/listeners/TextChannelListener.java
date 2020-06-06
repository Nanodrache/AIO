package de.aio.listeners;

import de.aio.AIO;
import de.aio.commands.types.Commands;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class TextChannelListener extends ListenerAdapter
{
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent e)
	{
		if (!e.getAuthor().isBot())
		{
			Message msg = e.getMessage();
			TextChannel channel = e.getChannel();
			String cmd = msg.getContentDisplay().split(" ")[0];
			Member member = e.getMember();
			
			if (cmd.equalsIgnoreCase(Commands.SAY.getCmd()))
			{
				if (!AIO.commandManager.perform(cmd, member, channel, msg))
				{
					channel.sendMessage("`Unbekannter Befehl`").queue();
				}
			}
			else if (cmd.equalsIgnoreCase(Commands.CHANNEL.getCmd()))
			{
				if (!AIO.commandManager.perform(cmd, member, channel, msg))
				{
					channel.sendMessage("`Unbekannter Befehl`").queue();
				}
			}
		}
	}
}
