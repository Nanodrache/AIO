package de.aio.commands;

import java.util.concurrent.TimeUnit;

import de.aio.AIO;
import de.aio.PermissionList;
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
		long id = Long.parseLong(args[3]);
		
		if (args[2].equalsIgnoreCase("getrole"))
		{
			if (
					member.hasPermission(Permission.ADMINISTRATOR) ||
					AIO.permissionManager.hasUserPermission(member.getGuild().getIdLong(), member.getIdLong(), PermissionList.AIO_PERMISSION_GET.name()) ||
					AIO.permissionManager.hasUserPermission(member.getGuild().getIdLong(), member.getIdLong(), PermissionList.AIO_PERMISSION_GET_ROLE.name())
				)
			{
				channel.sendMessage(AIO.languageManager.getString("getPermissionInfoRole")
						.replace("%roleName%", AIO.INSTANCE.jda.getRoleById(id).getName())
						.replace("%permmisionName%", AIO.permissionManager.getPermissionFromString(args[4]).getName())
						.replace("%permissionValue%", AIO.permissionManager.hasRolePermission(id, args[4]) + "")
					).queue();
			}
			else
			{
				message.delete().complete();
				channel.sendMessage(AIO.languageManager.getString("noPermissionsCommand")).complete().delete().delay(5, TimeUnit.SECONDS);
			}
		}
		else if (args[2].equalsIgnoreCase("getuser"))
		{
			if (
					member.hasPermission(Permission.ADMINISTRATOR) ||
					AIO.permissionManager.hasUserPermission(member.getGuild().getIdLong(), member.getIdLong(), PermissionList.AIO_PERMISSION_GET.name()) ||
					AIO.permissionManager.hasUserPermission(member.getGuild().getIdLong(), member.getIdLong(), PermissionList.AIO_PERMISSION_GET_USER.name())
				)
			{
				Permission perm = AIO.permissionManager.getPermissionFromString(args[4]);
				String permName;
				
				if (perm != Permission.UNKNOWN) permName = perm.getName();
				else if (AIO.permissionManager.isAioPermission(args[4])) permName = args[4];
				else permName = "Error";
				
				channel.sendMessage(AIO.languageManager.getString("getPermissionInfoUser")
						.replace("%userName%", member.getGuild().getMemberById(id).getEffectiveName())
						.replace("%permmisionName%", permName)
						.replace("%permissionValue%", AIO.permissionManager.hasUserPermission(member.getGuild().getIdLong(), id, args[4]) + "")
					).queue();
			}
			else
			{
				message.delete().complete();
				channel.sendMessage(AIO.languageManager.getString("noPermissionsCommand")).complete().delete().delay(5, TimeUnit.SECONDS);
			}
		}
		else if (args[2].equalsIgnoreCase("giveRole"))
		{
			if (
					member.hasPermission(Permission.ADMINISTRATOR) ||
					AIO.permissionManager.hasUserPermission(member.getGuild().getIdLong(), member.getIdLong(), PermissionList.AIO_PERMISSION_GIVE.name()) ||
					AIO.permissionManager.hasUserPermission(member.getGuild().getIdLong(), member.getIdLong(), PermissionList.AIO_PERMISSION_GIVE_ROLE.name())
				)
			{
				if (AIO.permissionManager.giveRolePermission(id, args[4]))
				{
					channel.sendMessage(AIO.languageManager.getString("updatePermissionRole")
							.replace("%roleName%", AIO.INSTANCE.jda.getRoleById(id).getName())
							.replace("%permmisionName%", AIO.permissionManager.getPermissionFromString(args[4]).getName())
							.replace("%permissionValue%", AIO.permissionManager.hasRolePermission(id, args[4]) + "")
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
		else if (args[2].equalsIgnoreCase("giveUser"))
		{
			if (
					member.hasPermission(Permission.ADMINISTRATOR) ||
					AIO.permissionManager.hasUserPermission(member.getGuild().getIdLong(), member.getIdLong(), PermissionList.AIO_PERMISSION_GIVE.name()) ||
					AIO.permissionManager.hasUserPermission(member.getGuild().getIdLong(), member.getIdLong(), PermissionList.AIO_PERMISSION_GIVE_USER.name())
				)
			{
				if (AIO.permissionManager.giveUserPermission(member.getGuild().getIdLong(), id, args[4]))
				{
					Permission perm = AIO.permissionManager.getPermissionFromString(args[4]);
					String permName;
					
					if (perm != Permission.UNKNOWN) permName = perm.getName();
					else if (AIO.permissionManager.isAioPermission(args[4])) permName = args[4];
					else permName = "Error";
					
					channel.sendMessage(AIO.languageManager.getString("updatePermissionUser")
							.replace("%userName%", member.getGuild().getMemberById(id).getEffectiveName())
							.replace("%permmisionName%", permName)
							.replace("%permissionValue%", AIO.permissionManager.hasUserPermission(member.getGuild().getIdLong(), id, args[4]) + "")
						).queue();
				}
				else
				{
					channel.sendMessage(AIO.languageManager.getString("unknownUser")
							.replace("%unknownUser%", id + "")
						).queue();
				}
			}
			else
			{
				message.delete().complete();
				channel.sendMessage(AIO.languageManager.getString("noPermissionsCommand")).complete().delete().delay(5, TimeUnit.SECONDS);
			}
		}
		else if (args[2].equalsIgnoreCase("removeRole"))
		{
			if (
					member.hasPermission(Permission.ADMINISTRATOR) ||
					AIO.permissionManager.hasUserPermission(member.getGuild().getIdLong(), member.getIdLong(), PermissionList.AIO_PERMISSION_REMOVE.name()) ||
					AIO.permissionManager.hasUserPermission(member.getGuild().getIdLong(), member.getIdLong(), PermissionList.AIO_PERMISSION_REMOVE_ROLE.name())
				)
			{
				if (AIO.permissionManager.removeRolePermission(id, args[4]))
				{
					channel.sendMessage(AIO.languageManager.getString("updatePermissionRole")
							.replace("%roleName%", AIO.INSTANCE.jda.getRoleById(id).getName())
							.replace("%permmisionName%", AIO.permissionManager.getPermissionFromString(args[4]).getName())
							.replace("%permissionValue%", AIO.permissionManager.hasRolePermission(id, args[4]) + "")
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
		else if (args[2].equalsIgnoreCase("removeUser"))
		{
			if (
					member.hasPermission(Permission.ADMINISTRATOR) ||
					AIO.permissionManager.hasUserPermission(member.getGuild().getIdLong(), member.getIdLong(), PermissionList.AIO_PERMISSION_REMOVE.name()) ||
					AIO.permissionManager.hasUserPermission(member.getGuild().getIdLong(), member.getIdLong(), PermissionList.AIO_PERMISSION_REMOVE_USER.name())
				)
			{
				if (AIO.permissionManager.removeUserPermission(member.getGuild().getIdLong(), id, args[4]))
				{
					Permission perm = AIO.permissionManager.getPermissionFromString(args[4]);
					String permName;
					
					if (perm != Permission.UNKNOWN) permName = perm.getName();
					else if (AIO.permissionManager.isAioPermission(args[4])) permName = args[4];
					else permName = "Error";
					
					channel.sendMessage(AIO.languageManager.getString("updatePermissionUser")
							.replace("%roleName%", AIO.INSTANCE.jda.getRoleById(id).getName())
							.replace("%permmisionName%", permName)
							.replace("%permissionValue%", AIO.permissionManager.hasRolePermission(id, args[4]) + "")
						).queue();
				}
				else
				{
					channel.sendMessage(AIO.languageManager.getString("unknownUser")
							.replace("%unknownUser%", id + "")
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
			if (
					member.hasPermission(Permission.ADMINISTRATOR) ||
					AIO.permissionManager.hasUserPermission(member.getGuild().getIdLong(), member.getIdLong(), PermissionList.AIO_PERMISSION_NAME.name())
				)
			{
				String name = "";
				
				for (int i = 3; i < args.length; i++)
				{
					name += args[i];
				}

				channel.sendMessage(AIO.languageManager.getString("updateRoleName")
						.replace("%roleNameOld%", AIO.INSTANCE.jda.getRoleById(id).getName())
						.replace("%roleNameNew%", name)
					).queue();
				AIO.permissionManager.setRoleName(id, name);
			}
			else
			{
				message.delete().complete();
				channel.sendMessage(AIO.languageManager.getString("noPermissionsCommand")).complete().delete().delay(5, TimeUnit.SECONDS);
			}
		}
		else if (args[2].equalsIgnoreCase("color"))
		{
			if (
					member.hasPermission(Permission.ADMINISTRATOR) ||
					AIO.permissionManager.hasUserPermission(member.getGuild().getIdLong(), member.getIdLong(), PermissionList.AIO_PERMISSION_COLOR.name())
				)
			{
				int r = Integer.parseInt(args[4]);
				int g = Integer.parseInt(args[5]);
				int b = Integer.parseInt(args[6]);
				
				AIO.permissionManager.setRoleColor(id, r, g, b);
				channel.sendMessage(AIO.languageManager.getString("updateRoleColor")
						.replace("%roleName%", AIO.INSTANCE.jda.getRoleById(id).getName())
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
