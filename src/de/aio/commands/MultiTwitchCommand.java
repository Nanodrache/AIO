package de.aio.commands;

import de.aio.AIO;
import de.aio.commands.types.TextCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class MultiTwitchCommand implements TextCommand
{
	@Override
	public void performCommand(Member member, TextChannel channel, Message message)
	{
		if (!member.getUser().isBot())
		{
			String[] args = message.getContentDisplay().split(" ");
			String link = "https://multitwitch.live/";
			
			for (String name : args)
			{
				link += name + "/";
			}
			
			message.delete().complete();
			channel.sendMessage(AIO.languageManager.getString("multiTwitch").replace("%link%", link)).queue();
		}
	}
}
