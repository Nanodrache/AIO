package de.aio.commands;

import java.util.concurrent.TimeUnit;

import de.aio.AIO;
import de.aio.PermissionList;
import de.aio.commands.types.TextCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class KickCommand implements TextCommand
{
	@Override
	public void performCommand(Member member, TextChannel channel, Message message)
	{
		if (
				member.hasPermission(Permission.ADMINISTRATOR) ||
				member.hasPermission(Permission.KICK_MEMBERS) ||
				AIO.permissionManager.hasUserPermission(member.getGuild().getIdLong(), member.getIdLong(), PermissionList.AIO_KICK.name())
			)
		{
			String id = message.getContentDisplay().split(" ")[1];
			
			channel.getGuild().kick(id);
			channel.sendMessage(AIO.languageManager.getString("kickFromServer")).queue();
		}
		else
		{
			message.delete().complete();
			channel.sendMessage(AIO.languageManager.getString("noPermissionsCommand")).complete().delete().delay(5, TimeUnit.SECONDS);
		}
	}
}
