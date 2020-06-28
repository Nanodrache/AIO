package de.aio.commands;

import java.util.concurrent.TimeUnit;

import de.aio.AIO;
import de.aio.PermissionList;
import de.aio.commands.types.TextCommand;
import net.dv8tion.jda.api.Permission;
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
				if (
						member.hasPermission(Permission.ADMINISTRATOR) ||
						AIO.permissionManager.hasUserPermission(member.getGuild().getIdLong(), member.getIdLong(), PermissionList.AIO_REACTION.name()) ||
						AIO.permissionManager.hasUserPermission(member.getGuild().getIdLong(), member.getIdLong(), PermissionList.AIO_REACTION_DISABLE.name())
					)
				{
					AIO.reactionManager.disableReactionOnMessage(Long.parseLong(args[2]));
				}
				else
				{
					message.delete().complete();
					channel.sendMessage(AIO.languageManager.getString("noPermissionsCommand")).complete().delete().delay(5, TimeUnit.SECONDS);
				}
			}
			else if (args[1].equalsIgnoreCase("enable"))
			{
				if (
						member.hasPermission(Permission.ADMINISTRATOR) ||
						AIO.permissionManager.hasUserPermission(member.getGuild().getIdLong(), member.getIdLong(), PermissionList.AIO_REACTION.name()) ||
						AIO.permissionManager.hasUserPermission(member.getGuild().getIdLong(), member.getIdLong(), PermissionList.AIO_REACTION_ENABLE.name())
					)
				{
					AIO.reactionManager.enableReactionOnMessage(Long.parseLong(args[2]));
				}
				else
				{
					message.delete().complete();
					channel.sendMessage(AIO.languageManager.getString("noPermissionsCommand")).complete().delete().delay(5, TimeUnit.SECONDS);
				}
			}
			else if (args[1].equalsIgnoreCase("disabledList"))
			{
				if (
						member.hasPermission(Permission.ADMINISTRATOR) ||
						AIO.permissionManager.hasUserPermission(member.getGuild().getIdLong(), member.getIdLong(), PermissionList.AIO_REACTION.name()) ||
						AIO.permissionManager.hasUserPermission(member.getGuild().getIdLong(), member.getIdLong(), PermissionList.AIO_REACTION_LIST.name())
					)
				{
					channel.sendMessage(AIO.languageManager.getString("reactionDisabledList")).complete().delete().delay(1, TimeUnit.MINUTES);
					
					for (long messageId : AIO.reactionManager.getDisabledReactionOnMessages())
					{
						channel.sendMessage(messageId + "").complete().delete().delay(1, TimeUnit.MINUTES);
					}
				}
				else
				{
					message.delete().complete();
					channel.sendMessage(AIO.languageManager.getString("noPermissionsCommand")).complete().delete().delay(5, TimeUnit.SECONDS);
				}
			}
		}
	}
}
