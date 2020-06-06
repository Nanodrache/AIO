package de.aio.commands;

import java.util.concurrent.TimeUnit;

import de.aio.AIO;
import de.aio.commands.types.TextCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class ChannelCommand implements TextCommand
{
	@Override
	public void performCommand(Member member, TextChannel channel, Message message)
	{
		String[] args = message.getContentDisplay().split(" ");
		
		if (args.length == 1)
		{
			//Help for command
		}
		else if (args.length > 1)
		{
			//Channel create
			if (args[1].equalsIgnoreCase("create"))
			{
				//Check permissions
				if (member.hasPermission(Permission.ADMINISTRATOR))
				{
					String channelCreateName = "";
					
					for (int i = 2; i < args.length; i++)
					{
						channelCreateName += args[i];
					}
					
					//Check channel-type
					if (args[2].equalsIgnoreCase("text"))
					{
						AIO.channelManager.createTextChannel(channelCreateName, channel.getParent());
					}
					else if (args[2].equalsIgnoreCase("voice"))
					{
						AIO.channelManager.createVoiceChannel(channelCreateName, channel.getParent());
					}
					else
					{
						message.delete().complete();
						channel.sendMessage("Unknown channel type").complete().delete().delay(5, TimeUnit.SECONDS);
					}
				}
				else
				{
					message.delete().complete();
					channel.sendMessage("You don't have the permissions to perform this command.").complete().delete().delay(5, TimeUnit.SECONDS);
				}
			}
		}
	}
}
