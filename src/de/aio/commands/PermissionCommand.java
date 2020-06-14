package de.aio.commands;

import java.util.concurrent.TimeUnit;

import de.aio.AIO;
import de.aio.commands.types.TextCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class PermissionCommand implements TextCommand
{
	@Override
	public void performCommand(Member member, TextChannel channel, Message message)
	{
		String[] args = message.getContentDisplay().split(" ");
		long roleId = Long.parseLong(args[3]);
		
		if (args[2].equalsIgnoreCase("get"))
		{
			if (member.hasPermission(Permission.ADMINISTRATOR))
			{
				channel.sendMessage(AIO.languageManager.getString("getPermissionInfo")
						.replace("%roleName%", AIO.INSTANCE.jda.getRoleById(roleId).getName())
						.replace("%permmisionName%", AIO.permissionManager.getPermissionFromString(args[4]).getName())
						.replace("%permissionValue%", AIO.permissionManager.hasPermission(roleId, args[4]) + "")
					).queue();
			}
			else
			{
				message.delete().complete();
				channel.sendMessage(AIO.languageManager.getString("noPermissionsCommand")).complete().delete().delay(5, TimeUnit.SECONDS);
			}
		}
		else if (args[2].equalsIgnoreCase("give"))
		{
			if (member.hasPermission(Permission.ADMINISTRATOR))
			{
				if (AIO.permissionManager.givePermission(roleId, args[4]))
				{
					channel.sendMessage(AIO.languageManager.getString("updatePermission")
							.replace("%roleName%", AIO.INSTANCE.jda.getRoleById(roleId).getName())
							.replace("%permmisionName%", AIO.permissionManager.getPermissionFromString(args[4]).getName())
							.replace("%permissionValue%", AIO.permissionManager.hasPermission(roleId, args[4]) + "")
						).queue();
				}
				else
				{
					channel.sendMessage(AIO.languageManager.getString("unknownRole")
							.replace("%unknownRole%", args[4])
						).queue();
				}
			}
			else
			{
				message.delete().complete();
				channel.sendMessage(AIO.languageManager.getString("noPermissionsCommand")).complete().delete().delay(5, TimeUnit.SECONDS);
			}
		}
		else if (args[2].equalsIgnoreCase("remove"))
		{
			if (member.hasPermission(Permission.ADMINISTRATOR))
			{
				if (AIO.permissionManager.removePermission(roleId, args[4]))
				{
					channel.sendMessage(AIO.languageManager.getString("updatePermission")
							.replace("%roleName%", AIO.INSTANCE.jda.getRoleById(roleId).getName())
							.replace("%permmisionName%", AIO.permissionManager.getPermissionFromString(args[4]).getName())
							.replace("%permissionValue%", AIO.permissionManager.hasPermission(roleId, args[4]) + "")
						).queue();
				}
				else
				{
					channel.sendMessage(AIO.languageManager.getString("unknownRole")
							.replace("%unknownRole%", args[4])
						).queue();
				}
			}
			else
			{
				message.delete().complete();
				channel.sendMessage(AIO.languageManager.getString("noPermissionsCommand")).complete().delete().delay(5, TimeUnit.SECONDS);
			}
		}
		else if (args[2].equalsIgnoreCase("name"))
		{
			if (member.hasPermission(Permission.ADMINISTRATOR))
			{
				String name = "";
				
				for (int i = 3; i < args.length; i++)
				{
					name += args[i];
				}

				channel.sendMessage(AIO.languageManager.getString("updateRoleName")
						.replace("%roleNameOld%", AIO.INSTANCE.jda.getRoleById(roleId).getName())
						.replace("%roleNameNew%", name)
					).queue();
				AIO.permissionManager.setRoleName(roleId, name);
			}
			else
			{
				message.delete().complete();
				channel.sendMessage(AIO.languageManager.getString("noPermissionsCommand")).complete().delete().delay(5, TimeUnit.SECONDS);
			}
		}
		else if (args[2].equalsIgnoreCase("color"))
		{
			if (member.hasPermission(Permission.ADMINISTRATOR))
			{
				int r = Integer.parseInt(args[4]);
				int g = Integer.parseInt(args[5]);
				int b = Integer.parseInt(args[6]);
				
				AIO.permissionManager.setRoleColor(roleId, r, g, b);
				channel.sendMessage(AIO.languageManager.getString("updateRoleColor")
						.replace("%roleName%", AIO.INSTANCE.jda.getRoleById(roleId).getName())
					).queue();
			}
			else
			{
				message.delete().complete();
				channel.sendMessage(AIO.languageManager.getString("noPermissionsCommand")).complete().delete().delay(5, TimeUnit.SECONDS);
			}
		}
	}
}
