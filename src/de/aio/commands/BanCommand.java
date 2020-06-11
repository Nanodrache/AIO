package de.aio.commands;

import java.util.concurrent.TimeUnit;

import de.aio.AIO;
import de.aio.commands.types.TextCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class BanCommand implements TextCommand
{
	@Override
	public void performCommand(Member member, TextChannel channel, Message message)
	{
		if (member.hasPermission(Permission.ADMINISTRATOR) || member.hasPermission(Permission.BAN_MEMBERS))
		{
			String id = message.getContentDisplay().split(" ")[1];
			int time = 0;
			if (message.getContentDisplay().split(" ").length > 2) time = Integer.parseInt(message.getContentDisplay().split(" ")[2]);
			
			channel.getGuild().ban(id, time);
			channel.sendMessage(AIO.languageManager.getString("banFromServer")).queue();
		}
		else
		{
			message.delete().complete();
			channel.sendMessage(AIO.languageManager.getString("noPermissionsCommand")).complete().delete().delay(5, TimeUnit.SECONDS);
		}
	}
}
