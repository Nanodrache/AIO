package de.aio.commands;

import java.util.concurrent.TimeUnit;

import de.aio.AIO;
import de.aio.commands.types.TextCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class ReactionCommand implements TextCommand
{
	@Override
	public void performCommand(Member member, TextChannel channel, Message message)
	{
		if (!member.getUser().isBot())
		{
			String[] args = message.getContentDisplay().split(" ");
			
			if (args[1].equalsIgnoreCase("disable"))
			{
				AIO.reactionManager.disableReactionOnMessage(Long.parseLong(args[2]));
			}
			else if (args[1].equalsIgnoreCase("enable"))
			{
				AIO.reactionManager.enableReactionOnMessage(Long.parseLong(args[2]));
			}
			else if (args[1].equalsIgnoreCase("disabledList"))
			{
				channel.sendMessage(AIO.languageManager.getString("reactionDisabledList")).complete().delete().delay(1, TimeUnit.MINUTES);
				
				for (long messageId : AIO.reactionManager.getDisabledReactionOnMessages())
				{
					channel.sendMessage(messageId + "").complete().delete().delay(1, TimeUnit.MINUTES);
				}
			}
		}
	}
}
