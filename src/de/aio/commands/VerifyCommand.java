package de.aio.commands;

import java.util.concurrent.TimeUnit;

import de.aio.AIO;
import de.aio.PermissionList;
import de.aio.commands.types.TextCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class VerifyCommand implements TextCommand
{
	@Override
	public void performCommand(Member member, TextChannel channel, Message message)
	{
		if (!member.getUser().isBot())
		{
			String[] args = message.getContentDisplay().split(" ");
			
			if (args[1].equalsIgnoreCase("setRole"))
			{
				if (
						member.hasPermission(Permission.ADMINISTRATOR) ||
						AIO.permissionManager.hasUserPermission(member.getGuild().getIdLong(), member.getIdLong(), PermissionList.AIO_VERIFY.name()) ||
						AIO.permissionManager.hasUserPermission(member.getGuild().getIdLong(), member.getIdLong(), PermissionList.AIO_VERIFY_SET_ROLE.name())
					)
				{
					AIO.reactionManager.setVerifyRole(Long.parseLong(args[2]));
					channel.sendMessage(AIO.languageManager.getString("verifySetRole").replace("%roleName%", member.getGuild().getRoleById(Long.parseLong(args[2])) + "")).complete().delete().delay(5, TimeUnit.SECONDS);
				}
				else
				{
					message.delete().complete();
					channel.sendMessage(AIO.languageManager.getString("noPermissionsCommand")).complete().delete().delay(5, TimeUnit.SECONDS);
				}
			}
			else if (args[1].equalsIgnoreCase("setMsg"))
			{
				if (
						member.hasPermission(Permission.ADMINISTRATOR) ||
						AIO.permissionManager.hasUserPermission(member.getGuild().getIdLong(), member.getIdLong(), PermissionList.AIO_VERIFY.name()) ||
						AIO.permissionManager.hasUserPermission(member.getGuild().getIdLong(), member.getIdLong(), PermissionList.AIO_VERIFY_SET_MESSAGE.name())
					)
				{
					AIO.reactionManager.setVerifyMessage(Long.parseLong(args[2]));
					channel.sendMessage(AIO.languageManager.getString("verifySetMessage").replace("%messageId%", Long.parseLong(args[2]) + "")).complete().delete().delay(5, TimeUnit.SECONDS);
				}
				else
				{
					message.delete().complete();
					channel.sendMessage(AIO.languageManager.getString("noPermissionsCommand")).complete().delete().delay(5, TimeUnit.SECONDS);
				}
			}
			else if (args[1].equalsIgnoreCase("setReaction"))
			{
				if (
						member.hasPermission(Permission.ADMINISTRATOR) ||
						AIO.permissionManager.hasUserPermission(member.getGuild().getIdLong(), member.getIdLong(), PermissionList.AIO_VERIFY.name()) ||
						AIO.permissionManager.hasUserPermission(member.getGuild().getIdLong(), member.getIdLong(), PermissionList.AIO_VERIFY_SET_REACTION.name())
					)
				{
					AIO.reactionManager.setVerifyReaction(args[2]);
					channel.sendMessage(AIO.languageManager.getString("verifySetReaction").replace("%reactionName%", args[2])).complete().delete().delay(5, TimeUnit.SECONDS);
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
